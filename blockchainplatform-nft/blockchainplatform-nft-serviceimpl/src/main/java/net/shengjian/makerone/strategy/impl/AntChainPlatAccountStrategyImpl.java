package net.shengjian.makerone.strategy.impl;

import net.shengjian.makerone.constant.StrategyBeanKeyConst;
import net.shengjian.makerone.strategy.ChainPlatAccountStrategy;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Map;

/**
 * @descriptions: 蚂蚁链创建账户实现
 * @author: YSK
 * @date: 2021/12/22 16:27
 * @version: 1.0
 */
@Service("antChain" + StrategyBeanKeyConst.CHAIN_PLAT_CREATE_ACCOUNT_STRATEGY)
public class AntChainPlatAccountStrategyImpl implements ChainPlatAccountStrategy {
    @Override
    public Map<String, String> createChainAccount() throws Exception {
        //TODO 蚂蚁链创建账户实现
        return null;
    }

    @Override
    public Boolean sendUserGas(String addressPath, BigInteger gasAmount) throws Exception {
        return null;
    }
}
