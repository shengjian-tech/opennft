package net.shengjian.makerone.strategy;


import java.util.Map;

/**
 * @descriptions: 链平台客户端执行接口
 * @author: YSK
 * @date: 2021/12/22 9:57
 * @version: 1.0
 */
public interface ChainPlatClientExecStrategy {

    /**
     * 执行链平台合约方法
     *
     * @param host            链接ip+端口
     * @param chainName       链名
     * @param addressPath     ak
     * @param privatePath     私钥
     * @param passwd          密码
     * @param publicPath      公钥
     * @param contractAccount 合约账号
     * @param contractName    合约名称
     * @param methodName      方法名称
     * @param args            参数
     * @return 返回kv值, 返回空指针则执行失败
     */
    Map<String, String> execPlatChainMethod(String host,
                                            String chainName,
                                            String addressPath,
                                            String privatePath,
                                            String passwd,
                                            String publicPath,
                                            String contractAccount,
                                            String contractName,
                                            String methodName,
                                            Map<String,Object> args) throws Exception;

    /**
     * 查询链平台合约方法数据,query不消耗gas,数据不会上链,不产生交易id,不耗费gas
     *
     * @param host            链接ip+端口
     * @param chainName       链名
     * @param addressPath     ak
     * @param privatePath     私钥
     * @param passwd     私钥
     * @param publicPath      公钥
     * @param contractAccount 合约账号
     * @param contractName    合约名称
     * @param methodName      方法名称
     * @param args            参数
     * @return 返回kv值, 返回空指针则执行失败
     */
    Map<String, String> queryPlatChainMethod(String host,
                                            String chainName,
                                            String addressPath,
                                            String privatePath,
                                            String passwd,
                                            String publicPath,
                                            String contractAccount,
                                            String contractName,
                                            String methodName,
                                            Map<String, Object> args) throws Exception;
}
