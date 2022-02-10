package net.shengjian.test;

import net.shengjian.frame.util.DateUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.makerone.entity.NftOrder;
import net.shengjian.makerone.enums.EPayPlat;
import net.shengjian.makerone.enums.EPayState;
import net.shengjian.makerone.enums.ETradeType;
import net.shengjian.makerone.service.INftOrderService;
import net.shengjian.makerone.utils.PayUtil;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.rpc.sessionuser.UserVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @descriptions:
 * @author: YSK
 * @date: 2021/12/24 15:13
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTester {
    @Resource
    private INftOrderService nftOrderService;
    @Before
    public void classBefore(){
        UserVO userVO = new UserVO();
        userVO.setUserId("u_10001");
        SessionUser.sessionUserLocal.set(userVO);
    }
    @Test
    public void testInsertGenerateOrderByWorksId() throws Exception {
        NftOrder order = nftOrderService.saveGenerateOrderByWorksId("20220110144440850629069666","u_10001",1, ETradeType.交易作品);
        System.out.println(JsonUtils.writeValueAsString(order));
    }
    @Test
    public void testPayAfter() throws Exception {
        Boolean aBoolean = nftOrderService.updatePayAfter("20211229155109218873377240", EPayPlat.微信支付, new Date(), EPayState.已支付,"",null);
        System.out.println(aBoolean);
    }
    @Test
    public void testCount() throws Exception {
        Integer userOrderCount = nftOrderService.findUserOrderCount("", "u_10001", null, EPayPlat.微信支付, ETradeType.交易作品, EPayState.已支付);
        System.out.println("userOrderCount = " + userOrderCount);
    }
    @Test
    public void testOrderList() throws Exception {
        List<NftOrder> u_10001 = nftOrderService.findListBy("u_10001", null, EPayState.已支付, ETradeType.交易作品);
        System.out.println("userOrderCount = " + JsonUtils.writeValueAsString(u_10001));
    }
    @Test
    public void testFindListByMinuteBefore() throws Exception {
        List<NftOrder> listByMinuteBefore = nftOrderService.findListByMinuteBefore(EPayState.未支付, 15);
        System.out.println("userOrderCount = " + JsonUtils.writeValueAsString(listByMinuteBefore));
    }
    @Test
    public void testUpdateCancelOrder() throws Exception {
        final Boolean aBoolean = nftOrderService.updateCancelOrder("null");
        System.out.println("aBoolean = " + aBoolean);
    }

    //测试作品上架,下单
    @Test
    public void testWorksIn() throws Exception {
        final NftOrder order = nftOrderService.saveGenerateOrderWorksIn("20220114151629017101402302", DateUtils.addDay(10),new Date(),  new BigDecimal("0.01"));
        System.out.println("JsonUtils.writeValueAsString(order) = " + JsonUtils.writeValueAsString(order));
    }
    @Resource
    PayUtil payUtil;
    //测试作品上架,下单
    @Test
    public void testFindOrder() throws Exception {
        final Map<String, String> stringMap = payUtil.finOrder("20220118141848565146030495");
        System.out.println(JsonUtils.writeValueAsString(stringMap));
    }
}
