package net.shengjian.test;

import net.shengjian.frame.util.DateUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.makerone.entity.NftCollection;
import net.shengjian.makerone.entity.NftOrder;
import net.shengjian.makerone.entity.NftWorks;
import net.shengjian.makerone.enums.EPayPlat;
import net.shengjian.makerone.enums.EPayState;
import net.shengjian.makerone.enums.ETradeType;
import net.shengjian.makerone.service.INftCollectionService;
import net.shengjian.makerone.service.INftOrderService;
import net.shengjian.makerone.service.INftWorksService;
import net.shengjian.makerone.vo.NftCollectionVO;
import net.shengjian.makerone.vo.NftWorksVO;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.rpc.sessionuser.UserVO;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;
import net.shengjian.weixin.service.IWxMpConfigService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @descriptions:
 * @author: YSK
 * @date: 2021/12/29 16:12
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Tester {
    @Resource
    private INftCollectionService nftCollectionService;

    @Resource
    private INftWorksService nftWorksService;

    @Resource
    private INftOrderService nftOrderService;

    @Resource
    private IWxMpConfigService wxMpConfigService;

    @Before
    public void classBefore(){
        UserVO userVO = new UserVO();
        userVO.setUserId("u_10001");
        SessionUser.sessionUserLocal.set(userVO);
    }
    //1.创建合集
    @Test
    public void testCreateCollection() throws Exception {
        NftCollectionVO db = JsonUtils.readValue(
                "{\"logoPath\":\"/path/1\",\"coverPath\":\"/path/2\",\"bannerPath\":\"/path/3\",\"name\":\"合集名称\",\"link\":\"自定义链接\",\"details\":\"详情\",\"type\":\"1\",\"royalties\":\"2.555\"}"
                ,NftCollectionVO.class);
        NftCollection nftCollection = nftCollectionService.saveCreateCollection(db);
        System.out.println("nftCollection = " + JsonUtils.writeValueAsString(nftCollection));
    }
    //2.创建作品
    @Test
    public void testCreateWorks() throws Exception {
        NftWorksVO db = JsonUtils.readValue(
                "{\n" +
                        "  \"collectionId\": \"20211230112212984643880564\",\n" +
                        "  \"dataPath\": \"/path/4\",\n" +
                        "  \"name\": \"name\",\n" +
                        "  \"type\": \"1\",\n" +
                        "  \"link\": \"链接\",\n" +
                        "  \"details\": \"详情\",\n" +
                        "  \"price\": \"99\",\n" +
                        "  \"num\": \"3\"\n" +
                        "}"
                ,NftWorksVO.class);
        NftWorks nftWorks = nftWorksService.saveCreateNftWorks(db);
        System.out.println("nftWorks = " + JsonUtils.writeValueAsString(nftWorks));
    }
    //3.上架合集
    @Test
    public void testUpdateCollectionOnShelves() throws Exception {
        Boolean ok = nftCollectionService.updateCollectionOnShelves("20211230112212984643880564", new Date(), DateUtils.addDay(100), new Date());
        System.out.println("ok = " + ok);
    }
    //4.根据作品id生成订单
    @Test
    public void testGenerateOrderByWorksId() throws Exception {
        UserVO userVO = new UserVO();
        userVO.setUserId("20210521175017791641757545");//购买者
        SessionUser.sessionUserLocal.set(userVO);
        NftOrder order = nftOrderService.saveGenerateOrderByWorksId("20211230112229145123270217", "u_10001", 1, ETradeType.交易作品);
        System.out.println("order = " + JsonUtils.writeValueAsString(order));
    }
    //5.支付成功后置处理
    @Test
    public void testUpdatePayAfter() throws Exception {
        UserVO userVO = new UserVO();
        userVO.setUserId("20210521175017791641757545");//购买者
        SessionUser.sessionUserLocal.set(userVO);
        Boolean ok = nftOrderService.updatePayAfter("20211230114822072284397560", EPayPlat.微信支付, new Date(), EPayState.已支付,"",null);
        System.out.println("ok = " + ok);
    }

    //
    @Test
    public void testTicket() throws Exception {
        final IWxMpConfig wxMpConfigById = wxMpConfigService.findWxMpConfigById("1");
        final Map<String, String> mpJsApiParam = wxMpConfigService.findMpJsApiParam(wxMpConfigById, "https://makerone.shengjian.net/front_nft_mobile/nft_mobile_collection?collectionId=20220113182009690204993830");
        System.out.println(JsonUtils.writeValueAsString(mpJsApiParam));
    }
}
