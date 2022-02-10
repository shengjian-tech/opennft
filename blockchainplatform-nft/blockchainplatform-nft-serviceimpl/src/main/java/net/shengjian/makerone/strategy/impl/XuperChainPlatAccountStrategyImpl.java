package net.shengjian.makerone.strategy.impl;

import com.baidu.xuper.api.Account;
import com.baidu.xuper.api.XuperClient;
import com.baidu.xuper.crypto.ECKeyPair;
import net.shengjian.makerone.constant.NFTExceptionConst;
import net.shengjian.makerone.constant.StrategyBeanKeyConst;
import net.shengjian.makerone.entity.NftChainPlat;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.service.INftChainPlatService;
import net.shengjian.makerone.strategy.ChainPlatAccountStrategy;
import net.shengjian.rpc.sessionuser.SessionUser;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 百度超级链的创建account实现
 * @author: YSK
 * @date: 2021/12/22 15:19
 * @version: 1.0
 */
@Service("xuperChain" + StrategyBeanKeyConst.CHAIN_PLAT_CREATE_ACCOUNT_STRATEGY)
public class XuperChainPlatAccountStrategyImpl implements ChainPlatAccountStrategy {
    Logger logger  = LoggerFactory.getLogger(getClass());
    @Resource
    private INftChainPlatService nftChainPlatService;

    @Autowired
    private XuperChainClientExecStrategyImpl xuperChainClientExecStrategyImpl;

    @Value("${staticdir}")
    private String staticdir;

    @Override
    public Map<String, String> createChainAccount() throws Exception {
        NftChainPlat db = nftChainPlatService.findNftChainPlatByEnglishName(EChainPlat.百度超级链.getCode());
        String host = db.getNodeHost();
        String chainName = db.getChainName();
        XuperClient xuperClient = new XuperClient(host);
        xuperClient.setChainName(chainName);
        //创建账户
        Account account = Account.create(1, 1);
        String address = account.getAddress();
        ECKeyPair keyPair = account.getKeyPair();
        String jsonPublicKey = keyPair.getJSONPublicKey();
        String jsonPrivateKey = keyPair.getJSONPrivateKey();
        //文件路径
        String folder = staticdir + File.separator + "userkeys" + File.separator + SessionUser.getUserId() + File.separator + "xuperchain" + File.separator;
        new File(folder).mkdirs();
        String addressPath = folder + "address";
        String publicPath = folder + "public.key";
        String privatePath = folder + "private.key";
        try (FileOutputStream os = new FileOutputStream(addressPath);
             FileOutputStream os2 = new FileOutputStream(publicPath);
             FileOutputStream os3 = new FileOutputStream(privatePath)) {
            os.write(address.getBytes());
            os2.write(jsonPublicKey.getBytes());
            os3.write(jsonPrivateKey.getBytes());
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw NFTExceptionConst.OPERATION_FAIL;
        }finally {
            if(xuperClient!=null){
                xuperClient.close();
            }
        }
        Map<String, String> returnMap = new HashMap<>();
        returnMap.put("EVMAddress", Account.xchainAKToEVMAddress(address));
        //返回相对路径
        returnMap.put("addressPath", addressPath.replace(staticdir, ""));
        returnMap.put("address", address);
        returnMap.put("publicPath", publicPath.replace(staticdir, ""));
        returnMap.put("privatePath", privatePath.replace(staticdir, ""));
        returnMap.put("mnemonic", account.getMnemonic());
        return returnMap;
    }

    @Override
    public Boolean sendUserGas(String addressPath, BigInteger gasAmount) throws Exception {
        if(true){//百度超级链无法进行gas交易
            return true;
        }
        if(StringUtils.isBlank(addressPath)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        File file = new File(addressPath);
        if (!file.exists()) {
            throw NFTExceptionConst.USER_NOT_CHAIN_PLAT_ACCOUNT;
        }
        byte[] bytes= new byte[(int) file.length()];
        try (FileInputStream is = new FileInputStream(file)){
            IOUtils.read(is, bytes);
        }
        String address = new String(bytes);
        NftChainPlat db = nftChainPlatService.findNftChainPlatByEnglishName(EChainPlat.百度超级链.getCode());
        String host = db.getNodeHost();
        String chainName = db.getChainName();
        XuperClient xuperClient = new XuperClient(host);
        xuperClient.setChainName(chainName);
        Account account = xuperChainClientExecStrategyImpl.getAccount(db.getPrivatePath(), db.getContractAccount(),db.getPasswd());
        xuperClient.transfer(account, address, gasAmount, "0");
        return true;
    }
}
