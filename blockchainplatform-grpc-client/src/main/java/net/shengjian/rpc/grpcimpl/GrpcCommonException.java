package net.shengjian.rpc.grpcimpl;

/**
 * Grpc的运行异常
 *
 * @author caomei
 */
public class GrpcCommonException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public GrpcCommonException(String message) {
        super(message);
    }

}
