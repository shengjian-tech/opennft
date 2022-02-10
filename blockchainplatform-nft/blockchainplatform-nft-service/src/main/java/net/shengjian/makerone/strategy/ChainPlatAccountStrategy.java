package net.shengjian.makerone.strategy;

import java.math.BigInteger;
import java.util.Map;

/**
 * @descriptions: 创建链平台账户策略接口
 * @author: YSK
 * @date: 2021/12/22 15:11
 * @version: 1.0
 */
public interface ChainPlatAccountStrategy {
    /**
     * 创建账户
     * @return 创建成功后的privatePath,publicPath,addressPath,mnemonic
     */
    Map<String,String> createChainAccount() throws Exception;


    /**
     * 由系统账户向用户发放gas
     * @param addressPath 用户addressPath路徑
     * @param gasAmount gas数量
     * @return 是否执行成功
     * @throws Exception 异常
     */
    Boolean sendUserGas(String addressPath, BigInteger gasAmount) throws Exception;
}
