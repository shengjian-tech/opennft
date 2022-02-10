package net.shengjian.makerone.strategy.impl;

import net.shengjian.makerone.constant.StrategyBeanKeyConst;
import net.shengjian.makerone.strategy.ChainPlatClientExecStrategy;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @descriptions: 蚂蚁链实现
 * @author: YSK
 * @date: 2021/12/22 10:46
 * @version: 1.0
 */
@Service("antChain"+ StrategyBeanKeyConst.CHAIN_PLAT_CLIENT_EXEC_STRATEGY)
public class AntChainClientExecStrategyImpl implements ChainPlatClientExecStrategy {
    @Override
    public Map<String, String> execPlatChainMethod(String host, String chainName, String addressPath, String privatePath,String passwd, String publicPath, String contractAccount, String contractName, String methodName, Map<String, Object> args) throws Exception {
        //todo 蚂蚁链的实现
        return null;
    }

    @Override
    public Map<String, String> queryPlatChainMethod(String host, String chainName, String addressPath, String privatePath,String passwd, String publicPath, String contractAccount, String contractName, String methodName, Map<String, Object> args) throws Exception {
        //todo 蚂蚁链的实现
        return null;
    }
}
