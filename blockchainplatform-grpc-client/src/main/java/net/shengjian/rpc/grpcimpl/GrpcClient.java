package net.shengjian.rpc.grpcimpl;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.shengjian.rpc.grpcauto.CommonRequest;
import net.shengjian.rpc.grpcauto.CommonResponse;
import net.shengjian.rpc.grpcauto.GrpcCommonServiceGrpc;
import net.shengjian.rpc.grpcauto.GrpcCommonServiceGrpc.GrpcCommonServiceBlockingStub;
import net.shengjian.rpc.util.FstSerializeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Grpc的客户端
 *
 * @author caomei
 */

public class GrpcClient {
    private static final Logger logger = LoggerFactory.getLogger(GrpcClient.class);

    /**
     * 处理 gRPC 请求
     */
    public static GrpcCommonResponse commonHandle(GrpcCommonServiceBlockingStub blockingStub, GrpcCommonRequest grpcRequest) {

        ByteString bytes = FstSerializeUtils.serialize(grpcRequest);

        CommonRequest request = CommonRequest.newBuilder().setRequest(bytes).build();
        CommonResponse response = null;
        try {
            response = blockingStub.commonHandle(request);

            GrpcCommonResponse grpcResponse = FstSerializeUtils.deserialize(response);
            return grpcResponse;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // response = getBlockingStub(rpcHost, rpcPort).handle(request);
            return null;
        }

    }

    /**
     * 获取grpc连接
     *
     * @param rpcHost
     * @param rpcPort
     * @return
     */
    public static GrpcCommonServiceBlockingStub getCommonServiceBlockingStub(String rpcHost, Integer rpcPort) {
        if (rpcHost == null || "".equals(rpcHost) || rpcPort == null || rpcPort <= 0) {
            return null;
        }

        ManagedChannel channel = ManagedChannelBuilder.forAddress(rpcHost, rpcPort).usePlaintext().build();
        GrpcCommonServiceBlockingStub blockingStub = GrpcCommonServiceGrpc.newBlockingStub(channel);

        // blockingStub.withDeadlineAfter(100, TimeUnit.MILLISECONDS);

        return blockingStub;

    }


}
