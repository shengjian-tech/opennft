package net.shengjian.test;

import net.shengjian.frame.util.JsonUtils;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.weixin.sdk.common.wxconfig.IWxPayConfig;
import net.shengjian.weixin.sdk.pay.WXPayApi;
import net.shengjian.weixin.service.IWxPayConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 微信支付测试
 * @author: YSK
 * @date: 2022/1/5 13:09
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeChartPayTest {
    @Resource
    private IWxPayConfigService wxPayConfigService;

    @Test
    public void testFindConfig() throws Exception {
        final IWxPayConfig wxPayConfigById = wxPayConfigService.findWxPayConfigById("1");
        System.out.println("wxPayConfigById" + JsonUtils.writeValueAsString(wxPayConfigById));
        Map<String,String> reqData = new HashMap<>();
        reqData.put("body", "测试商品");
        reqData.put("out_trade_no", SecUtils.getTimeNO());
        reqData.put("total_fee", "1");
        reqData.put("spbill_create_ip", "1.192.62.108");
        reqData.put("trade_type", "JSAPI");
        reqData.put("openid", "o84I85qQTBOFUHkN68PV-VnB0Xfs");

        Map<String, String> map = WXPayApi.unifiedOrder(wxPayConfigById, reqData);
        System.out.println("reqData = " + reqData);
        System.out.println("map = " + map);
    }
}
