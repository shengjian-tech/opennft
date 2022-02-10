package net.shengjian.makerone.strategy.context;

import net.shengjian.makerone.constant.StrategyBeanKeyConst;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.strategy.ChainPlatAccountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Map;

/**
 * @descriptions: 链平台创建账号上下文
 * @author: YSK
 * @date: 2021/12/22 16:53
 * @version: 1.0
 */
@Service
public class ChainAccountStrategyContext {

    @Autowired
    private Map<String, ChainPlatAccountStrategy> chainPlatCreateAccountStrategyMap;

    /**
     * 创建账户
     * @return 创建成功后的privatePath,publicPath,address,addressPath,mnemonic
     * @deprecated 离线创建的账号无法在百度超级链开放网络使用,用户完善个人信息时绑定账号
     */
    @Deprecated
    public Map<String,String> createChainAccount(EChainPlat eChainPlat) throws Exception {
        return chainPlatCreateAccountStrategyMap.get(eChainPlat.getCode(StrategyBeanKeyConst.CHAIN_PLAT_CREATE_ACCOUNT_STRATEGY))
                .createChainAccount();
    }

    /**
     * 由系统账户向用户发放gas
     * @param eChainPlat 链平台类型枚举
     * @param addressPath 用户address路徑
     * @param gasAmount gas数量
     * @return 是否执行成功
     * @throws Exception 异常
     */
    public Boolean sendUserGas(EChainPlat eChainPlat, String addressPath, BigInteger gasAmount) throws Exception {
        return chainPlatCreateAccountStrategyMap.get(eChainPlat.getCode(StrategyBeanKeyConst.CHAIN_PLAT_CREATE_ACCOUNT_STRATEGY))
                .sendUserGas(addressPath,gasAmount);
    }
}
