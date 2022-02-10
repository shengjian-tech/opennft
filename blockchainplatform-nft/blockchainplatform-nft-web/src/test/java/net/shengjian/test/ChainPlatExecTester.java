package net.shengjian.test;

import net.shengjian.frame.util.JsonUtils;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.dto.NftUserDetails;
import net.shengjian.makerone.dto.UserChainplatInfoDTO;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.service.INftUserAssetsService;
import net.shengjian.makerone.service.INftUserChainplatService;
import net.shengjian.makerone.strategy.context.ChainAccountStrategyContext;
import net.shengjian.makerone.strategy.context.ChainExecStrategyContext;
import net.shengjian.makerone.vo.BindOpenNetVO;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.rpc.sessionuser.UserVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 链平台执行测试
 * @author: YSK
 * @date: 2021/12/22 11:52
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChainPlatExecTester {
    @Resource
    private ChainExecStrategyContext chainExecStrategyContext;
    @Resource
    private INftUserChainplatService nftUserChainplatService;
    @Resource
    private INftUserAssetsService userAssetsService;
    @Value("${staticdir}")
    private String staticdir;
    @Before
    public void classBefore(){
        UserVO userVO = new UserVO();
        userVO.setUserId("u_10001");
        SessionUser.sessionUserLocal.set(userVO);
    }
    @Test
    public void testRootExec() throws Exception {
        Map<String,Object> args = new HashMap<>();
        args.put("key","num");
        Map<String, String> map = chainExecStrategyContext.rootQuery(EChainPlat.百度超级链, "counter", "increase", args);
        System.out.println(map);
    }
    @Test
    public void testExec() throws Exception {
        Map<String,Object> args = new HashMap<>();
        args.put("key","num");
        Map<String, String> map = chainExecStrategyContext.query(EChainPlat.百度超级链,"", staticdir+"\\userkeys\\null\\xuperchain\\private.key",null,"","","counter", "get", args);
        System.out.println(map);
    }

    @Test
    public void test1() throws Exception {
        final Long userAssetsLastDay = userAssetsService.findUserAssetsLastDay("20220110163739394089049276", "9999", EChainPlat.百度超级链);
        System.out.println("userAssetsLastDay = " + userAssetsLastDay);
    }
    @Resource
    private ChainAccountStrategyContext chainAccountStrategyContext;

    @Test
    public void  testFindUserChain() throws Exception {
        UserChainplatInfoDTO infoDTO = nftUserChainplatService.findUserChainPlatInfo("20220110163739394089049276", EChainPlat.百度超级链);
        System.out.println(JsonUtils.writeValueAsString(infoDTO));
    }
    @Test
    public void  testFindUserChain2() throws Exception {
        final BindOpenNetVO bindOpenNetVO = nftUserChainplatService.reviewBind("20220110163739394089049276", EChainPlat.百度超级链);
        System.out.println(JsonUtils.writeValueAsString(bindOpenNetVO));
    }
    @Test
    public void testCreateChainAccount() throws Exception {
        Map<String, String> chainAccount = chainAccountStrategyContext.createChainAccount(EChainPlat.百度超级链);
        System.out.println("chainAccount = " + chainAccount);
    }

    @Test
    public void testFindUserDetails() throws Exception {
        NftUserDetails userDetails = userAssetsService.findUserDetails();
        System.out.println("userDetails = " + JsonUtils.writeValueAsString(userDetails));
    }
    @Test
    public void testSendUserGas() throws Exception {
        UserChainplatInfoDTO ui = nftUserChainplatService.findUserChainPlatInfo("20210521175017791641757545", EChainPlat.百度超级链);
        Boolean aBoolean = chainAccountStrategyContext.sendUserGas(EChainPlat.百度超级链, staticdir+ui.getAddressPath(),new BigInteger("2000000"));
        System.out.println("aBoolean = " + aBoolean);
    }
    @Test
    public void testQuery() throws Exception {
        UserChainplatInfoDTO ui = nftUserChainplatService.findUserChainPlatInfo("20210521180451455507632824", EChainPlat.百度超级链);
        Map<String,Object> args = new HashMap<>();
        args.put("_id","1345");
        args.put("_initialSupply","10");
        args.put("_data","sgfahg");
        final Map<String, String> query = chainExecStrategyContext.query(EChainPlat.百度超级链, staticdir + ui.getPrivatePath(), CommonConst.getNftContractName(), "addNewToken", args);
        System.out.println("query = " + query);
    }

}
