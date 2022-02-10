package net.shengjian.test;

import com.baidu.xuper.api.Account;
import com.baidu.xuper.api.Transaction;
import com.baidu.xuper.api.XuperClient;
import com.baidu.xuper.config.Config;
import net.shengjian.frame.util.DateUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.dto.UserChainplatInfoDTO;
import net.shengjian.makerone.entity.NftWorks;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.service.INftUserChainplatService;
import net.shengjian.makerone.service.INftWorksService;
import net.shengjian.makerone.strategy.context.ChainAccountStrategyContext;
import net.shengjian.makerone.strategy.context.ChainExecStrategyContext;
import net.shengjian.makerone.utils.NFTImageUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @descriptions: 合约测试
 * @author: YSK
 * @date: 2022/1/6 10:52
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractTest {
    @Resource
    private ChainExecStrategyContext chainExecStrategyContext;
    @Resource
    private ChainAccountStrategyContext chainAccountStrategyContext;
    @Resource
    private INftUserChainplatService nftUserChainplatService;
    @Resource
    private INftWorksService nftWorksService;
    @Value("${staticdir}")
    private String staticdir;

    XuperClient client;
    Account accountFromFile;
    @Before
    public void before() throws IOException {
        //Config.setConfigPath("E:\\work\\blockchainplatform\\blockchainplatform-nft\\blockchainplatform-nft-web\\src\\main\\resources\\sdk.yaml");
        Config.setConfigInputStream(new ClassPathResource("sdk.yaml").getInputStream());
        client = new XuperClient("39.156.69.83:37100");
        //accountFromFile = Account.getAccountFromFile("E:\\work\\blockchainplatform\\blockchainplatform-nft\\blockchainplatform-nft-web\\src\\main\\resources\\keys\\xuperchain\\chimengnan_keys", "******");
        accountFromFile = Account.getAccountFromFile("C:\\Users\\Admin\\Downloads\\lll", "******");
    }
    @After
    public void after(){
        client.close();
    }
    @Test
    public void test1() throws IOException {
        Map<String,String> args = new HashMap<>();
        args.put("_id","9999");//TokenID
        args.put("_initialSupply","10");//Token数量
        args.put("_data", SecUtils.encoderByBase64("{\"link\":\"https://makerone.shengjian.net/nft/attachmentFile/2022-01-21T131529.6942dcf7550-7159-4ea4-b4ed-7ac4f44a0f3d.png\",\"hash\":\"9d04983c8cb75e02fb431ff17b249638\"}"));//Token 数据,Base64
        args.put("tokenTime","2592000");//Token 数据,Base64
        final Transaction transaction = client.invokeEVMContract(accountFromFile,"SJ_Test_ERC_11","addNewToken", args,new BigInteger("0"));
        System.out.println(transaction.getContractResponse().getBodyStr());
        System.out.println(transaction.getGasUsed());
    }
    //测试获取上架后的作品合约方法
    @Test
    public void test2() throws Exception {
        Map<String,String> args = new HashMap<>();
        args.put("_id","9999");//TokenID
        final Transaction transaction = client.invokeEVMContract(accountFromFile,"SJ_Test_ERC_11","getTokenBytes", args,new BigInteger("0"));
        System.out.println(transaction.getContractResponse().getBodyStr());
    }
    @Test
    public void test3() throws Exception {
        Map<String,String> args = new HashMap<>();
        args.put("account",Account.xchainAKToEVMAddress("cRsoDDnDX1NjhzJtNKLS3GHudBsgyRouQ"));//
        args.put("id","9999");//TokenID
        final Transaction transaction = client.invokeEVMContract(accountFromFile,"SJ_Test_ERC_11","balanceOf", args,new BigInteger("0"));
        System.out.println(transaction.getContractResponse().getBodyStr());
        System.out.println("transaction.getGasUsed() = " + transaction.getGasUsed());
    }
    @Test
    public void test4() throws Exception {

        Map<String,String> args = new HashMap<>();
        args.put("id","9999");//TokenID
        args.put("from",Account.xchainAKToEVMAddress("ULuqhymLPGidfihUb683i2TH4qtaqZ2Dz"));//
        args.put("to",Account.xchainAKToEVMAddress("cRsoDDnDX1NjhzJtNKLS3GHudBsgyRouQ"));//TODO 用户的address转换的数据
        args.put("amount","1");//交易的数量
        args.put("data","");//附加数据

        final Transaction transaction = client.invokeEVMContract(accountFromFile,"SJ_Test_ERC_11","safeTransferFrom", args,null);
        System.out.println(transaction.getContractResponse().getBodyStr());
        System.out.println("transaction.getGasUsed() = " + transaction.getGasUsed());
    }
    @Test
    public void test5() throws Exception {
        Map<String,String> args = new HashMap<>();
        args.put("from",Account.xchainAKToEVMAddress("ULuqhymLPGidfihUb683i2TH4qtaqZ2Dz"));//
        args.put("id","9999");//TokenID
        args.put("to",Account.xchainAKToEVMAddress("cRsoDDnDX1NjhzJtNKLS3GHudBsgyRouQ"));//TODO 用户的address转换的数据
        final Transaction transaction = client.invokeEVMContract(accountFromFile,"SJ_Test_ERC_11","approveForOne", args,new BigInteger("0"));
        System.out.println(transaction.getContractResponse().getBodyStr());
        System.out.println("transaction.getGasUsed() = " + transaction.getGasUsed());
    }
    @Test
    public void test6() throws Exception {
        Map<String,String> args = new HashMap<>();
        final Transaction transaction = client.queryEVMContract(accountFromFile,"SJ_Test_ERC_11","getApproveOne", args);
        System.out.println(transaction.getContractResponse().getBodyStr());
        System.out.println("transaction.getGasUsed() = " + transaction.getGasUsed());
    }
    @Test
    public void test7() throws Exception {
        Map<String,String> args = new HashMap<>();
        args.put("_id","20220207160536096088160970");//1644206303857
        final Transaction transaction = client.queryEVMContract(accountFromFile,"SJ_Test_ERC_11","getTokenExpireTime", args);
        System.out.println(transaction.getContractResponse().getBodyStr());
        System.out.println("transaction.getGasUsed() = " + transaction.getGasUsed());
    }
    @Test
    public void test8() throws Exception {
        Map<String,String> args = new HashMap<>();
        final long currentTimeMillis = System.currentTimeMillis();//1644206303857 1644211662381
        System.out.println("currentTimeMillis = " + currentTimeMillis);
        args.put("_time", DateUtils.addDay(30,new Date()).getTime()+"");
        //args.put("_time", currentTimeMillis+"");//DateUtils.addDay(60,new Date()).getTime()/1000+"");
        final Transaction transaction = client.invokeEVMContract(accountFromFile,"SJ_Test_ERC_11","getTimeStamp", args,null);
        System.out.println(transaction.getContractResponse().getBodyStr());
        System.out.println("transaction.getGasUsed() = " + transaction.getGasUsed());
    }
    //测试作品上链的合约方法
    @Test
    public void testAddNewToken() throws Exception {
        UserChainplatInfoDTO infoDTO = nftUserChainplatService.findUserChainPlatInfo("u_10001", EChainPlat.百度超级链);
        NftWorks byId = nftWorksService.findById("20220104114705423276625431", NftWorks.class);
        //执行合约的信息
        String dataPath = byId.getDataPath();
        String base64Str = NFTImageUtil.imageToBase64Str(staticdir+dataPath);

        Map<String,Object> args = new HashMap<>();
        args.put("_id",byId.getId());//TokenID
        args.put("_initialSupply",byId.getNum());//Token数量
        args.put("_data",base64Str);//Token 数据,Base64
        Map<String, String> preMap = chainExecStrategyContext.rootQuery(EChainPlat.百度超级链
                , CommonConst.getNftContractName()
                , "addNewToken"
                , args);
        String gasUsed = preMap.get("gasUsed");
        chainAccountStrategyContext.sendUserGas(EChainPlat.百度超级链,staticdir+infoDTO.getAddressPath(),new BigInteger(gasUsed));

        Map<String, String> map = chainExecStrategyContext.exec(EChainPlat.百度超级链
                , staticdir+infoDTO.getPrivatePath()
                , CommonConst.getNftContractName()
                ,"addNewToken"
                ,args);
        System.out.println("map = " + map);
    }
    @Test
    public void testAddNewToken2() throws Exception {
        UserChainplatInfoDTO infoDTO = nftUserChainplatService.findUserChainPlatInfo("u_10001", EChainPlat.百度超级链);
        chainAccountStrategyContext.sendUserGas(EChainPlat.百度超级链,staticdir+infoDTO.getAddressPath(),new BigInteger("2000000"));
        //执行合约的信息
        //String base64Str = NFTImageUtil.imageToBase64Str("C:\\Users\\Admin\\Pictures\\dea091038dcb75c10da3ce3fa74ab6fd.jpg");

        Map<String,Object> args = new HashMap<>();
        args.put("_id","100011");//TokenID
        args.put("_initialSupply","10");//Token数量
        args.put("_data","base64Str");//Token 数据,Base64
        args.put("tokenTime",CommonConst.TRADING_COOLING_PERIOD);//Token 数据,Base64

        Map<String, String> map = chainExecStrategyContext.exec(EChainPlat.百度超级链
                , staticdir+infoDTO.getPrivatePath()
                , CommonConst.getNftContractName()
                ,"addNewToken"
                ,args);
        System.out.println("map = " + map);
    }
    //测试获取上架后的作品合约方法
    @Test
    public void testGetTokenBytes() throws Exception {
        Map<String,Object> args = new HashMap<>();
        args.put("_id","123");//TokenID
        Map<String, String> map = chainExecStrategyContext.rootQuery(EChainPlat.百度超级链
                , CommonConst.getNftContractName()
                ,"getTokenBytes"
                ,args);
        //System.out.println("map = " + map);
        List<Map> bodyStr = JsonUtils.readValueList(map.get("bodyStr"), Map.class);
        final Object o = bodyStr.get(0).get("_response");
        System.out.println(o);
    }

    //测试获取作品数量方法
    @Test
    public void testGetNum() throws Exception {
        Map<String,Object> args = new HashMap<>();
        args.put("account","84A0C9DAD04794DF9009F86CD5F23447843F10D1");//
        args.put("id","100011");//TokenID
        Map<String, String> map = chainExecStrategyContext.rootQuery(EChainPlat.百度超级链
                , CommonConst.getNftContractName()
                ,"balanceOf"
                ,args);
        System.out.println("map = " + map);
    }
    //测试作品交易
    @Test
    public void testTrans() throws Exception {
        Map<String,Object> args = new HashMap<>();
        args.put("from","2BEF68690AE24553824BA37C003C2B9067665F81");//TODO 用户的address转换的数据
        args.put("to","84A0C9DAD04794DF9009F86CD5F23447843F10D1");//TODO 用户的address转换的数据
        args.put("id","100011");//交易的作品
        args.put("amount","1");//交易的数量
        args.put("data","");//附加数据

        UserChainplatInfoDTO infoDTO = nftUserChainplatService.findUserChainPlatInfo("u_10001", EChainPlat.百度超级链);
        Map<String, String> map = chainExecStrategyContext.exec(EChainPlat.百度超级链
                ,staticdir+infoDTO.getPrivatePath()
                ,infoDTO.getPasswd()
                ,CommonConst.getNftContractName()
                ,"safeTransferFrom"
                ,args);
        System.out.println("map = " + map);
    }
    @Test
    public void test() throws Exception {
        //预估gas费使用
        String fromEvmAddress = nftUserChainplatService.findEVMAddress("u_10001", EChainPlat.百度超级链);
        String toEvmAddress = nftUserChainplatService.findEVMAddress("20220110163739394089049276", EChainPlat.百度超级链);
        Map<String,Object> args = new HashMap<>();
        args.put("from",fromEvmAddress);//用户的address转换的数据
        args.put("to",toEvmAddress);//用户的address转换的数据
        args.put("id","20220118173211005007304363");//交易的作品
        args.put("amount","1");//交易的数量
        args.put("data","");//附加数据

        UserChainplatInfoDTO infoDTO = nftUserChainplatService.findUserChainPlatInfo("u_10001", EChainPlat.百度超级链);
        Map<String, String> preMap = chainExecStrategyContext.query(EChainPlat.百度超级链, staticdir + infoDTO.getPrivatePath(), CommonConst.getNftContractName(), "safeTransferFrom", args);
        System.out.println(preMap);
    }

    public static void main(String[] args) throws Exception {
        Instant instant = Instant.ofEpochMilli(1641106300857L);
        LocalDateTime d1 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        Duration between = Duration.between(d1, LocalDateTime.now());
        System.out.println("s = " + between.toDays());
    }
}
