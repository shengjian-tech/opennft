package net.shengjian.makerone.strategy.context;

import com.baidu.xuper.api.Account;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.constant.StrategyBeanKeyConst;
import net.shengjian.makerone.entity.NftChainPlat;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.service.INftChainPlatService;
import net.shengjian.makerone.strategy.ChainPlatClientExecStrategy;
import net.shengjian.makerone.utils.PathUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @descriptions: 链平台执行合约上下文
 * @author: YSK
 * @date: 2021/12/22 10:49
 * @version: 1.0
 */
@Service
public class ChainExecStrategyContext {

    @Resource
    private INftChainPlatService nftChainPlatService;

    @Autowired
    private Map<String, ChainPlatClientExecStrategy> chainPlatClientExecStrategyMap;

    /**
     * 执行合约
     *
     * @param chainPlat       链平台枚举
     * @param addressPath     ak
     * @param privatePath     私钥
     * @param publicPath      公钥
     * @param contractAccount 合约账号
     * @param contractName    合约名称
     * @param methodName      方法名称
     * @param args            参数
     * @return 返回kv值, 返回空指针则执行失败
     */
    public Map<String, String> exec(EChainPlat chainPlat,
                                    String addressPath,
                                    String privatePath,
                                    String passwd,
                                    String publicPath,
                                    String contractAccount,
                                    String contractName,
                                    String methodName,
                                    Map<String, Object> args) throws Exception {
        NftChainPlat db = nftChainPlatService.findNftChainPlatByEnglishName(chainPlat.getCode());
        String host = db.getNodeHost();
        String chainName = db.getChainName();
        return chainPlatClientExecStrategyMap.get(chainPlat.getCode(StrategyBeanKeyConst.CHAIN_PLAT_CLIENT_EXEC_STRATEGY))
                .execPlatChainMethod(host, chainName, addressPath, privatePath,passwd, publicPath, contractAccount, contractName, methodName, args);
    }
    /**
     * 查询链平台合约方法数据,query不消耗gas,数据不会上链,不产生交易id,不耗费gas
     *
     * @param addressPath     ak
     * @param privatePath     私钥
     * @param publicPath      公钥
     * @param contractAccount 合约账号
     * @param contractName    合约名称
     * @param methodName      方法名称
     * @param args            参数
     * @return 返回kv值, 返回空指针则执行失败
     */
    public Map<String, String> query(EChainPlat chainPlat,
                                     String addressPath,
                                     String privatePath,
                                     String passwd,
                                     String publicPath,
                                     String contractAccount,
                                     String contractName,
                                     String methodName,
                                     Map<String, Object> args) throws Exception {
        NftChainPlat db = nftChainPlatService.findNftChainPlatByEnglishName(chainPlat.getCode());
        String host = db.getNodeHost();
        String chainName = db.getChainName();
        return chainPlatClientExecStrategyMap.get(chainPlat.getCode(StrategyBeanKeyConst.CHAIN_PLAT_CLIENT_EXEC_STRATEGY))
                .queryPlatChainMethod(host, chainName, addressPath, privatePath,passwd, publicPath, contractAccount, contractName, methodName, args);
    }

    /**
     * 执行合约
     *
     * @param chainPlat       链平台枚举
     * @param privatePath     私钥
     * @param contractName    合约名称
     * @param methodName      方法名称
     * @param args            参数
     * @return 返回kv值, 返回空指针则执行失败
     */
    public Map<String, String> exec(EChainPlat chainPlat,
                                    String privatePath,
                                    String contractName,
                                    String methodName,
                                    Map<String, Object> args) throws Exception {
        return exec(chainPlat,privatePath,null, contractName,methodName,args);
    }
    /**
     * 执行合约
     *
     * @param chainPlat       链平台枚举
     * @param privatePath     私钥
     * @param contractName    合约名称
     * @param methodName      方法名称
     * @param args            参数
     * @return 返回kv值, 返回空指针则执行失败
     */
    public Map<String, String> exec(EChainPlat chainPlat,
                                    String privatePath,
                                    String passwd,
                                    String contractName,
                                    String methodName,
                                    Map<String, Object> args) throws Exception {
        if(StringUtils.isBlank(privatePath) || StringUtils.isBlank(passwd)){//系统账户为作者代发,再授权给作者
            return rootExec(chainPlat,contractName,methodName,args);
        }
        return exec(chainPlat,null,privatePath,passwd,null,null, contractName,methodName,args);
    }

    /**
     * 系统账户执行合约
     *
     * @param chainPlat    链平台枚举
     * @param contractName 合约名称
     * @param methodName   方法名称
     * @param args         参数
     * @return 返回kv值, 返回空指针则执行失败
     */
    public Map<String, String> rootExec(EChainPlat chainPlat,
                                        String contractName,
                                        String methodName,
                                        Map<String, Object> args) throws Exception {
        NftChainPlat db = nftChainPlatService.findNftChainPlatByEnglishName(chainPlat.getCode());
        String addressPath = db.getAddressPath();
        String privatePath = db.getPrivatePath();
        String publicPath = db.getPublicPath();
        String contractAccount = db.getContractAccount();
        String passwd = db.getPasswd();
        if (args!=null && args.containsKey("from")) {
            args.put("from",Account.xchainAKToEVMAddress(PathUtil.readPath(addressPath)));
        }
        return exec(chainPlat, addressPath, privatePath,passwd, publicPath, contractAccount, contractName, methodName, args);
    }


    /**
     * 查询链平台合约方法数据,query不消耗gas,数据不会上链,不产生交易id,不耗费gas
     *
     * @param privatePath     私钥
     * @param contractName    合约名称
     * @param methodName      方法名称
     * @param args            参数
     * @return 返回kv值, 返回空指针则执行失败
     */
    public Map<String, String> query(EChainPlat chainPlat,
                                     String privatePath,
                                     String contractName,
                                     String methodName,
                                     Map<String, Object> args) throws Exception {
        return this.query(chainPlat,privatePath,null,contractName,methodName,args);
    }
    /**
     * 查询链平台合约方法数据,query不消耗gas,数据不会上链,不产生交易id,不耗费gas
     *
     * @param privatePath     私钥
     * @param passwd          密码
     * @param contractName    合约名称
     * @param methodName      方法名称
     * @param args            参数
     * @return 返回kv值, 返回空指针则执行失败
     */
    public Map<String, String> query(EChainPlat chainPlat,
                                     String privatePath,
                                     String passwd,
                                     String contractName,
                                     String methodName,
                                     Map<String, Object> args) throws Exception {
        if(StringUtils.isBlank(privatePath) || StringUtils.isBlank(passwd)){
            return rootQuery(chainPlat,contractName,methodName,args);
        }
        return query(chainPlat,null,privatePath,passwd,null,null,contractName,methodName,args);
    }

    /**
     * 查询链平台合约方法数据,query不消耗gas,数据不会上链,不产生交易id,不耗费gas
     *
     * @param contractName 合约名称
     * @param methodName   方法名称
     * @param args         参数
     * @return 返回kv值, 返回空指针则执行失败
     */
    public Map<String, String> rootQuery(EChainPlat chainPlat,
                                         String contractName,
                                         String methodName,
                                         Map<String, Object> args) throws Exception {
        NftChainPlat db = nftChainPlatService.findNftChainPlatByEnglishName(chainPlat.getCode());
        String addressPath = db.getAddressPath();
        String privatePath = db.getPrivatePath();
        String publicPath = db.getPublicPath();
        String contractAccount = db.getContractAccount();
        String passwd = db.getPasswd();
        if (args!=null && args.containsKey("from")) {
            String akAddress;
            if(addressPath.contains(CommonConst.CLASS_PATH)){
                addressPath = addressPath.replace(CommonConst.CLASS_PATH, "");
                akAddress = PathUtil.readPath(new ClassPathResource(addressPath).getInputStream());
            }else {
                akAddress = PathUtil.readPath(addressPath);
            }
            args.put("from",Account.xchainAKToEVMAddress(akAddress));
        }
        return query(chainPlat, addressPath, privatePath,passwd, publicPath, contractAccount, contractName, methodName, args);
    }
}
