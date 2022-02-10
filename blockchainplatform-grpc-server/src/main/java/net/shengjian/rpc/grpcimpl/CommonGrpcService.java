package net.shengjian.rpc.grpcimpl;

import com.google.protobuf.ByteString;
import io.seata.core.context.RootContext;
import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.rpc.grpcauto.CommonRequest;
import net.shengjian.rpc.grpcauto.CommonResponse;
import net.shengjian.rpc.grpcauto.GrpcCommonServiceGrpc;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.rpc.sessionuser.UserVO;
import net.shengjian.rpc.util.FstSerializeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;

/**
 * 集成自动产生的java类,自定义自己的实现.总体思路是
 * 请求的class,方法,和参数做成二进制,通过grpc传递,实际是二次序列化,对性能有损耗,但是方便......
 * <p>
 * 使用seata实现数据库分布式事务
 *
 * @author caomei
 */
public class CommonGrpcService extends GrpcCommonServiceGrpc.GrpcCommonServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(CommonGrpcService.class);
    // 事务匹配的规则
    // String pattern = ".*Service.(save|update|delete)(.*)";
    // 创建 Pattern 对象
    // Pattern r = Pattern.compile(pattern);
    private ApplicationContext applicationContext = null;

    public CommonGrpcService(ApplicationContext applicationContext) {

        this.applicationContext = applicationContext;
    }

    /**
     * <pre>
     * 处理请求
     * </pre>
     */
    @Override
    public void commonHandle(CommonRequest commonRequest,
                             io.grpc.stub.StreamObserver<CommonResponse> responseObserver) {

        // 把请求反序列化成正常对象,GrpcRequest
        GrpcCommonRequest grpcRequest = FstSerializeUtils.deserialize(commonRequest);

        // String beanName = grpcRequest.getBeanName();
        // 需要调用的类
        String className = grpcRequest.getClazz();
        // 获取获取参数
        Object[] args = grpcRequest.getArgs();
        // spring bean name
        String beanName = grpcRequest.getBeanName();
        // 当前登录用户对象信息
        UserVO userVO = grpcRequest.getUserVO();

        // 方法的全路径
        String methodPath = className + "." + grpcRequest.getMethod();

        Object bean = null;

        try {
            if ((beanName != null) && (!"".equals(beanName))) {// 先按照beanName查找
                bean = getBeanByName(beanName);
            }

            if (bean == null) {// 按照类型找到springbean
                bean = getBean(Class.forName(className));
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return;
        }


        // 分布式事务的XID
        String seataXID = grpcRequest.getTxGroupId();
        try {

            if (GlobalStatic.seataEnable && StringUtils.isNotBlank(seataXID)) {
                RootContext.bind(seataXID);
                //分支事务
                GlobalStatic.seataBranchTransaction.set(true);
            }

            // 调用的方法
            Method method = bean.getClass().getMethod(grpcRequest.getMethod(), grpcRequest.getArgTypes());


            // 如果启用seata-spring注解@GlobalTransactional方法,和grpcserver的切面存在冲突,会重复提交,grpc就不再负责seata事务管理了.
            // 代码先注释了,不想引入seata-spring的jar,用到了再解开.

            /**
             * if (GlobalStatic.seataSpringEnable &&
             * method.isAnnotationPresent(GlobalTransactional.class)) { if
             * (isSpringTxMethod) {// 如果有spring本地事务,设置为false,由seata-spring管理事务.
             * isSpringTxMethod = false; } else { throw new
             * Exception("有@GlobalTransactional注解,却没有Spring本地事务,认为异常"); } }
             **/


            if (userVO != null) {
                SessionUser.sessionUserLocal.set(userVO);
            }

            // 执行service的方法
            GrpcCommonResponse grpcResponse = invokeMethod(bean, method, args, userVO);


            // 序列化需要返回的结果
            ByteString bytes = FstSerializeUtils.serialize(grpcResponse);
            // 封装成grpc传递的对象
            CommonResponse commonResponse = CommonResponse.newBuilder().setResponse(bytes).build();
            // grpc下一步处理
            responseObserver.onNext(commonResponse);
            // 完成传输
            responseObserver.onCompleted();


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {

            SessionUser.sessionUserLocal.remove();
            GlobalStatic.seataBranchTransaction.remove();
        }

    }

    /**
     * 获取 Service Bean
     */
    private Object getBean(Class clazz) {

        try {
            Object bean = applicationContext.getBean(clazz);
            return bean;
        } catch (BeansException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    private Object getBeanByName(String beanName) {

        try {
            Object bean = applicationContext.getBean(beanName);
            return bean;
        } catch (BeansException e) {
            return null;
        }
    }

    /**
     * 反射调用方法,也可以使用MethodUtils, 直接反射会造成形参和实参不对应的时候找不到方法,例如形参Object,参数Entity
     *
     * @param bean
     * @param method
     * @param args
     * @return
     * @throws Exception
     */
    private GrpcCommonResponse invokeMethod(Object bean, Method method, Object[] args,
                                            UserVO userVO) {
        // 初始化需要返回的对象
        GrpcCommonResponse grpcResponse = new GrpcCommonResponse();

        try {
            // Method method = bean.getClass().getMethod(methodName, parameterTypes);
            // Method method = MethodUtils.getMatchingMethod(bean.getClass(), methodName,
            // parameterTypes);
            FastClass serviceFastClass = FastClass.create(bean.getClass());
            FastMethod serviceFastMethod = serviceFastClass.getMethod(method);
            Object result = serviceFastMethod.invoke(bean, args);
            grpcResponse.success(result);
        } catch (Exception e) {
            grpcResponse.error(e.getMessage(), e, e.getStackTrace());
        }
        return grpcResponse;

    }


}
