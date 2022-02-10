package net.shengjian.makerone.strategy.impl;

import com.baidu.xuper.api.Account;
import com.baidu.xuper.api.Transaction;
import com.baidu.xuper.api.XuperClient;
import com.baidu.xuper.config.Config;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.constant.NFTExceptionConst;
import net.shengjian.makerone.constant.StrategyBeanKeyConst;
import net.shengjian.makerone.exception.NFTException;
import net.shengjian.makerone.strategy.ChainPlatClientExecStrategy;
import net.shengjian.makerone.utils.NFTMapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 百度超级链的实现
 * @author: YSK
 * @date: 2021/12/22 10:20
 * @version: 1.0
 */
@Service("xuperChain"+ StrategyBeanKeyConst.CHAIN_PLAT_CLIENT_EXEC_STRATEGY)
public class XuperChainClientExecStrategyImpl implements ChainPlatClientExecStrategy {
    Logger logger  = LoggerFactory.getLogger(getClass());
    @Value("${staticdir}")
    private String staticdir;

    @PostConstruct
    public void init() throws IOException {
        Config.setConfigInputStream(new ClassPathResource("sdk.yaml").getInputStream());
    }

    @Override
    public Map<String, String> execPlatChainMethod(String host,
                                                   String chainName,
                                                   String addressPath,
                                                   String privatePath,
                                                   String passwd,
                                                   String publicPath,
                                                   String contractAccount,
                                                   String contractName,
                                                   String methodName,
                                                   Map<String, Object> args) throws Exception {
        XuperClient xuperClient = null;
        Transaction transaction;
        try {
            xuperClient = new XuperClient(host);
            xuperClient.setChainName(chainName);
            Account account = getAccount(privatePath,contractAccount,passwd);
            Map<String, String> map = NFTMapUtils.oConvertStr(args);
            transaction = xuperClient.invokeEVMContract(account, contractName, methodName, map,null);
            if (transaction == null) {
                throw NFTExceptionConst.OPERATION_FAIL;
            }
        }catch (NFTException e){
            throw e;
        }catch (Exception e){
            throw NFTException.NFTExceptionERC1155(e);
        }finally {
            if(xuperClient!=null){
                xuperClient.close();
            }
        }
        Map<String, String> returnMap = new HashMap<>();
        returnMap.put("txId", transaction.getTxid());
        returnMap.put("gasUsed", transaction.getGasUsed() + "");
        returnMap.put("bodyStr", transaction.getContractResponse().getBodyStr());
        returnMap.put("browserAddress",CommonConst.BAIDU_BROWSER_ADDRESS);
        returnMap.put("contractName",CommonConst.getNftContractName());
        return returnMap;
    }

    @Override
    public Map<String, String> queryPlatChainMethod(String host, String chainName, String addressPath, String privatePath,String passwd, String publicPath, String contractAccount, String contractName, String methodName, Map<String, Object> args) throws Exception {
        XuperClient xuperClient=null;
        Transaction transaction;
        try{
            xuperClient = new XuperClient(host);
            xuperClient.setChainName(chainName);
            Account account = getAccount(privatePath,contractAccount,passwd);
            Map<String,String> map = NFTMapUtils.oConvertStr(args);
            transaction = xuperClient.queryEVMContract(account, contractName, methodName, map);
        }catch (NFTException e){
            throw e;
        }catch (Exception e){
            throw NFTException.NFTExceptionERC1155(e);
        }finally {
            if(xuperClient!=null){
                xuperClient.close();
            }
        }
        if (transaction == null) {
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        Map<String, String> returnMap = new HashMap<>();
        returnMap.put("gasUsed", transaction.getGasUsed() + "");
        returnMap.put("bodyStr", transaction.getContractResponse().getBodyStr());
        return returnMap;
    }
    //获取account
    public Account getAccount(String privatePath,String contractAccount,String passwd) throws IOException {
        InputStream is;
        if (privatePath.contains(CommonConst.CLASS_PATH)) {
            privatePath = privatePath.replace(CommonConst.CLASS_PATH, "").replace(staticdir,"");
            ClassPathResource classPathResource = new ClassPathResource(privatePath);
            if (!classPathResource.exists()) {
                throw new NFTException("文件不存在！["+privatePath+"]");
            }
            is = classPathResource.getInputStream();
        } else {
            if (!new File(privatePath).exists()) {
                throw new NFTException("文件不存在！["+privatePath+"]");
            }
            is = new FileInputStream(privatePath);
        }
        Account account;
        if(StringUtils.isBlank(passwd)){
            account = Account.create(is);
        }else{
            String dir = staticdir+"temp"+File.separator+SecUtils.getTimeNO()+File.separator;
            new File(dir).mkdirs();
            String tempPrivate =  dir+"private.key";
            try (FileOutputStream fos = new FileOutputStream(tempPrivate)){
                IOUtils.copy(is, fos);
            }
            account = Account.getAccountFromFile(dir,SecUtils.decoderByRSAPublicKey(passwd));
            new File(tempPrivate).deleteOnExit();
        }
        if(StringUtils.isNotBlank(contractAccount)){
            account.setContractAccount(contractAccount);
        }
        return account;
    }
}
