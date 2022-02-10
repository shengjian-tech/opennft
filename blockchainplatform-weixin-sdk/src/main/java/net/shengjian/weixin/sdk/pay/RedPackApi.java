package net.shengjian.weixin.sdk.pay;

import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxPayConfig;

import java.util.Map;

/**
 * 微信红包api
 * https://developers.weixin.qq.com/doc/offiaccount/Shake_Nearby/Shake_RedPack/Shake_Red_Packet_Description..html
 */
public class RedPackApi {

    // 文档地址：https://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_5#
    private static String sendRedPackUrl = WxConsts.mppaybaseurl + "/mmpaymkttransfers/sendredpack";
    // 文档地址：https://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_7&index=6
    private static String getRedPackInfo = WxConsts.mppaybaseurl + "/mmpaymkttransfers/gethbinfo ";
    // 裂变红包：https://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=16_5
    private static String sendGroupRedPackUrl = WxConsts.mppaybaseurl + "/mmpaymkttransfers/sendgroupredpack";

    /**
     * 发送红包
     *
     * @param params 请求参数
     * @return {String}
     */
    public static String sendRedPack(IWxPayConfig config, Map<String, String> params) {
        return WXPayApi.payRequest(config, sendRedPackUrl, params, true);
    }

    /**
     * 根据商户订单号查询信息
     *
     * @param params 请求参数
     * @return {String}
     */
    public static String getRedPackInfo(IWxPayConfig config, Map<String, String> params) {

        return WXPayApi.payRequest(config, getRedPackInfo, params, true);

    }

    /**
     * 发送裂变红包
     *
     * @param params 请求参数
     * @return {String}
     */
    public static String sendGroupRedPack(IWxPayConfig config, Map<String, String> params) {
        return WXPayApi.payRequest(config, sendGroupRedPackUrl, params, true);


    }
}
