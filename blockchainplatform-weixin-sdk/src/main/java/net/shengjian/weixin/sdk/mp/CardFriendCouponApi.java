package net.shengjian.weixin.sdk.mp;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 朋友的券
 * <p>
 * https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Coupons_from_Friends/Friend_coupon_description.html
 */
public class CardFriendCouponApi {
    private static String activateUrl = WxConsts.mpapiurl + "/card/pay/activate?access_token=";
    private static String getPayPriceUrl = WxConsts.mpapiurl + "/card/pay/getpayprice?access_token=";
    private static String getCoinsInfoUrl = WxConsts.mpapiurl + "/card/pay/getcoinsinfo?access_token=";
    private static String confirmUrl = WxConsts.mpapiurl + "/card/pay/confirm?access_token=";
    private static String rechargeUrl = WxConsts.mpapiurl + "/card/pay/recharge?access_token=";
    private static String getOrderUrl = WxConsts.mpapiurl + "/card/pay/getorder?access_token=";
    private static String getOrderListUrl = WxConsts.mpapiurl + "/card/pay/getorderlist?access_token=";

    /**
     * 开通券点账户接口
     *
     * @param wxmpconfig
     * @return {ApiResult}
     */
    public static ApiResult activate(IWxMpConfig wxmpconfig) {
        String jsonResult = HttpClientUtils.sendHttpGet(activateUrl + wxmpconfig.getAccessToken());
        return new ApiResult(jsonResult);
    }

    /**
     * 对优惠券批价
     *
     * @param wxmpconfig
     * @param cardId     是 string(32) 需要来配置库存的card_id
     * @param quantity   是 int 本次需要兑换的库存数目
     * @return {ApiResult}
     */
    public static ApiResult getPayPrice(IWxMpConfig wxmpconfig, String cardId, int quantity) {
        Map<String, Object> data = new HashMap<>();
        data.put("card_id", cardId);
        data.put("quantity", quantity);
        String jsonResult = HttpClientUtils.sendHttpPost(getPayPriceUrl + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 查询券点余额接口
     *
     * @param wxmpconfig
     * @return {ApiResult}
     */
    public static ApiResult getCoinsInfo(IWxMpConfig wxmpconfig) {
        String jsonResult = HttpClientUtils.sendHttpGet(getCoinsInfoUrl + wxmpconfig.getAccessToken());
        return new ApiResult(jsonResult);
    }

    /**
     * 确认兑换库存接口
     *
     * @param wxmpconfig
     * @param cardId     是 string(32) 需要来配置库存的card_id
     * @param quantity   是 int 本次需要兑换的库存数目
     * @param orderId    是 string 仅可以使用批价得到的订单号，保证批价有效性
     * @return {ApiResult}
     */
    public static ApiResult confirm(IWxMpConfig wxmpconfig, String cardId, int quantity, String orderId) {
        Map<String, Object> data = new HashMap<>();
        data.put("card_id", cardId);
        data.put("quantity", quantity);
        data.put("order_id", orderId);
        String jsonResult = HttpClientUtils.sendHttpPost(confirmUrl + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 充值券点接口
     *
     * @param wxmpconfig
     * @param coinCount  是 int 需要充值的券点数目，1点=1元
     * @return {ApiResult}
     */
    public static ApiResult recharge(IWxMpConfig wxmpconfig, int coinCount) {
        Map<String, Object> data = new HashMap<>();
        data.put("coin_count", coinCount);
        String jsonResult = HttpClientUtils.sendHttpPost(rechargeUrl + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 查询订单详情接口
     *
     * @param wxmpconfig
     * @param orderId    是 int 充值券点接口中获得的订单号，作为一次交易的唯一凭证，由于类型不是100%确定改为Stirng
     * @return {ApiResult}
     */
    public static ApiResult getOrder(IWxMpConfig wxmpconfig, String orderId) {
        Map<String, Object> data = new HashMap<>();
        data.put("order_id", orderId);
        String jsonResult = HttpClientUtils.sendHttpPost(getOrderUrl + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 查询券点流水详情接口
     *
     * @param wxmpconfig
     * @param jsonStr    JSON数据
     * @return {ApiResult}
     */
    public static ApiResult getOrderList(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(getOrderListUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }
}
