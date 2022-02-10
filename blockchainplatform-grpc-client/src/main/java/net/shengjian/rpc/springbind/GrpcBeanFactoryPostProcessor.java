package net.shengjian.rpc.springbind;

import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.rpc.grpcimpl.GrpcCommonRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * 在spring初始化之前,通过beanFactory先注入需要代理的bean,不然springbean初始化会异常.
 * 需要实现EnvironmentAware,setEnvironment
 * 这样才能正常获取到Environment变量,参考:https://blog.csdn.net/xiejx618/article/details/50413412
 *
 * @author caomei
 */
@Component("grpcBeanFactoryPostProcessor")
public class GrpcBeanFactoryPostProcessor implements BeanFactoryPostProcessor, EnvironmentAware {
    private static final Logger logger = LoggerFactory.getLogger(GrpcBeanFactoryPostProcessor.class);


    private Environment environment;


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            // 初始化全局变量
            initGlobalProperty();
            // 初始化代理bean,必须在bean加载前处理好,因为spring初始化找不到实现会报错,提前把接口的实现注册上去就可以了.
            initRpcServiceImpl(beanFactory);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 扫描RpcServiceAnnotation的注解接口,找到默认实现,如果没有,就启动RPC代理
     *
     * @throws Exception
     */
    private void initRpcServiceImpl(ConfigurableListableBeanFactory beanFactory) throws Exception {

        //基础包名
        String basepackagepath = environment.getProperty("springrain.basepackagepath");


        String basepackagepathStr = basepackagepath.replaceAll("\\.", "/");

        String classPath = basepackagepathStr + "/**/service/*.class";


        PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
        Resource[] resources = pmrpr
                .getResources(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + classPath);

        for (Resource resource : resources) {

            URI uri = resource.getURI();
            String rpcServiceClassName = uri.toString();

            rpcServiceClassName = rpcServiceClassName.substring(rpcServiceClassName.lastIndexOf(basepackagepathStr),
                    rpcServiceClassName.lastIndexOf(".class"));
            rpcServiceClassName = rpcServiceClassName.replaceAll("/", ".");

            Class<?> clazz = Class.forName(rpcServiceClassName);

            if ((!clazz.isInterface()) || (!clazz.isAnnotationPresent(RpcServiceAnnotation.class))) {// 只处理有@RpcService注解的接口
                continue;
            }


            // 获取实现类名
            String classSimpleName = clazz.getSimpleName();
            String classImplSimpleName = classSimpleName.substring(1, classSimpleName.length()) + "Impl";

            // 实现类的全路径
            String rpcServiceImplPath = null;


            RpcServiceAnnotation rpcServiceAnnotation = clazz.getAnnotation(RpcServiceAnnotation.class);
            String implpackage = rpcServiceAnnotation.implpackage();

            if (StringUtils.isBlank(implpackage)) {//没有指定包路径
                // 根据包名规则,组装接口默认实现的class路径,如果类存在,就认为是本机加载,找不到就启用RPC
                String rpcServiceImplClassName = rpcServiceClassName.replace(".service.", ".service.impl.");
                rpcServiceImplPath = rpcServiceImplClassName.substring(0, rpcServiceImplClassName.lastIndexOf("."))
                        + "." + classImplSimpleName;
            } else {
                rpcServiceImplPath = basepackagepath + "." + implpackage + "." + classImplSimpleName;
            }


            try {
                Class rpcServiceImplClass = Class.forName(rpcServiceImplPath);
                if (rpcServiceImplClass != null) {
                    continue;
                }

            } catch (Exception e) {
                logger.error("未找到接口" + clazz.getName() + "的实现类" + rpcServiceImplPath + ",开始RPC调用远程实现");
            }

            // 因为有远程调用的service,设置seata为启用状态.
            if (GlobalStatic.seataGlobalEnable) {
                if (!GlobalStatic.seataEnable) {
                    GlobalStatic.seataEnable = true;
                }
            } else {// 如果全局禁用seata,就设置为false
                GlobalStatic.seataEnable = false;
            }


            String rpcHost = rpcServiceAnnotation.rpcHost();
            Integer rpcPort = rpcServiceAnnotation.rpcPort();
            String beanName = rpcServiceAnnotation.beanName();

            if (rpcHost == null || rpcHost.equals("")) {
                rpcHost = GlobalStatic.rpcHost;
            }

            if (rpcPort == null || rpcPort <= 0) {
                rpcPort = GlobalStatic.rpcPort;
            }

            if (beanName == null || beanName.equals("")) {
                beanName = clazz.getName();
            }
            // 开始GRPC请求调用
            GrpcCommonRequest grpcRequest = new GrpcCommonRequest();
            grpcRequest.setClazz(clazz.getName());
            grpcRequest.setBeanName(beanName);
            grpcRequest.setTimeout(rpcServiceAnnotation.timeout());
            grpcRequest.setVersionCode(rpcServiceAnnotation.versionCode());
            grpcRequest.setAutocommit(rpcServiceAnnotation.autocommit());

            // 创建接口实现的GRPC代理类
            // Object invoker = new Object();
            InvocationHandler invocationHandler = new GrpcServiceProxy<>(rpcHost, rpcPort, grpcRequest);
            Object proxy = Proxy.newProxyInstance(RpcServiceAnnotation.class.getClassLoader(), new Class[]{clazz},
                    invocationHandler);
            // 手动注册 springbean
            beanFactory.registerSingleton(beanName, proxy);

        }


    }

    /**
     * 初始化全局变量
     *
     * @throws Exception
     */
    private void initGlobalProperty() throws Exception {
        // jwtSecret
        String jwtSecret = environment.getProperty("springrain.jwt.secret");
        GlobalStatic.jwtSecret = jwtSecret;

        //JWT token的超时时间
        Long jwtTimeout = environment.getProperty("springrain.jwt.timeout", Long.class);
        GlobalStatic.jwtTimeout = jwtTimeout;

        //JWT token key
        String jwtTokenKey = environment.getProperty("springrain.jwt.tokenkey");
        GlobalStatic.jwtTokenKey = jwtTokenKey;

        //rsa私钥
        String rsaPrivateKey = environment.getProperty("springrain.rsa.privatekey", "");
        //rsa公钥
        String rsaPublicKey = environment.getProperty("springrain.rsa.publickey", "");

        //初始化 rsa 证书
        if (StringUtils.isNotBlank(rsaPrivateKey)) {
            GlobalStatic.rsaPrivateKeyPem = rsaPrivateKey;
        }
        if (StringUtils.isNotBlank(rsaPublicKey)) {
            GlobalStatic.rsaPublicKeyPem = rsaPublicKey;
        }

        SecUtils.initRSA();
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
