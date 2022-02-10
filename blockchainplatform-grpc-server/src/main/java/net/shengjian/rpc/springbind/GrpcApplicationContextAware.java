package net.shengjian.rpc.springbind;

import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.rpc.grpcimpl.CommonGrpcService;
import net.shengjian.rpc.grpcimpl.GrpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 获取applicationContext,启动grpcServer
 *
 * @author caomei
 */
@Component
public class GrpcApplicationContextAware implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(GrpcApplicationContextAware.class);
    private ApplicationContext applicationContext;

    /**
     * PRC 服务调用
     */
    @Bean("commonGrpcService")
    public CommonGrpcService commonGrpcService() {
        return new CommonGrpcService(applicationContext);
    }


    /**
     * RPC 服务端,启动rpc服务
     */
    // @Bean
    // @ConditionalOnMissingBean(GrpcServer.class)
    public GrpcServer grpcServer() throws Exception {
        GrpcServer server = new GrpcServer();
        server.addService(commonGrpcService());
        server.start();
        return server;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;

        if (GlobalStatic.isRpcServer) {
            try {
                grpcServer();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

    }

}
