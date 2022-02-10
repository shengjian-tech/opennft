package net.shengjian.rpc.grpcimpl;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import net.shengjian.frame.util.GlobalStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * gRPC Server管理,启动grpcServer
 */
@Component
public class GrpcServer implements DisposableBean {
    private static final Logger logger = LoggerFactory.getLogger(GrpcServer.class);

    // 声明server
    ServerBuilder<?> serverBuilder = null;

    private Server server;

    public GrpcServer() {
        serverBuilder = ServerBuilder.forPort(GlobalStatic.rpcPort);
    }


    /**
     * 启动服务
     *
     * @throws Exception 异常
     */
    public void start() throws Exception {
        ThreadPoolTaskExecutor bizExecutor = new ThreadPoolTaskExecutor();

        bizExecutor.initialize();
        bizExecutor.setCorePoolSize(1000);
        bizExecutor.setMaxPoolSize(2000);

        // 指定线程池
        serverBuilder.executor(bizExecutor);
        // 添加自己的服务,并启动
        server = serverBuilder.build().start();
        startDaemonAwaitThread();
    }

    public ServerBuilder addService(BindableService bindableService) {
        return serverBuilder.addService(bindableService);
    }


    /**
     * 销毁
     */
    @Override
    public void destroy() {
        Optional.ofNullable(server).ifPresent(Server::shutdown);
    }

    private void startDaemonAwaitThread() {
        Thread awaitThread = new Thread(() -> {

            try {
                this.server.awaitTermination();
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }

        });
        awaitThread.setDaemon(false);
        awaitThread.start();
    }

}