package net.shengjian.weixin.sdk.mp;


import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 卡券相关接口
 * https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Distributing_Coupons_Vouchers_and_Cards.html
 */
public class CardApi {


    private static String cardCreateUrl = WxConsts.mpapiurl + "/card/create?access_token=";
    private static String createQrcodeCard = WxConsts.mpapiurl + "/card/qrcode/create?access_token=";
    private static String createLandingPageCard = WxConsts.mpapiurl + "/card/landingpage/create?access_token=";
    private static String gethtmlMpnews = WxConsts.mpapiurl + "/card/mpnews/gethtml?access_token=";
    private static String setTestWhiteList = WxConsts.mpapiurl + "/card/testwhitelist/set?access_token=";
    private static String setPaycell = WxConsts.mpapiurl + "/card/testwhitelist/set?access_token=";
    private static String setSelfconsumecell = WxConsts.mpapiurl + "/card/selfconsumecell/set?access_token=";
    private static String getUserCardList = WxConsts.mpapiurl + "/card/user/getcardlist?access_token=";
    private static String getCard = WxConsts.mpapiurl + "/card/getErrorMsgByCode?access_token=";
    private static String getBatch = WxConsts.mpapiurl + "/card/batchget?access_token=";
    private static String update = WxConsts.mpapiurl + "/card/update?access_token=";
    private static String modifystock = WxConsts.mpapiurl + "/card/modifystock?access_token=";
    private static String delete = WxConsts.mpapiurl + "/card/delete?access_token=";
    private static String unavailable = WxConsts.mpapiurl + "/card/code/unavailable?access_token=";

    /**
     * 创建会员卡接口
     *
     * @param wxmpconfig
     * @param jsonStr    JSON数据
     * @return {ApiResult}
     */

    public static ApiResult create(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(cardCreateUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 创建二维码接口
     *
     * @param wxmpconfig
     * @param jsonStr    JSON数据
     * @return {ApiResult}
     */
    public static ApiResult createQrcode(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(createQrcodeCard + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 创建货架接口
     *
     * @param wxmpconfig
     * @param jsonStr    JSON数据
     * @return {ApiResult}
     */
    public static ApiResult createLandingPage(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(createLandingPageCard + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 图文消息群发卡券
     *
     * @param wxmpconfig
     * @param cardId     必填 否 卡券ID。
     * @return {ApiResult}
     */

    public static ApiResult gethtmlMpnews(IWxMpConfig wxmpconfig, String cardId) {
        Map<String, String> data = new HashMap<>();
        if (StringUtils.isNotBlank(cardId)) {
            data.put("card_id", cardId);
        }
        String jsonResult = HttpClientUtils.sendHttpPost(gethtmlMpnews + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 设置测试白名单
     *
     * @param wxmpconfig
     * @param jsonStr    JSON数据
     * @return {ApiResult}
     */
    public static ApiResult setTestWhiteList(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(setTestWhiteList + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 设置买单接口
     *
     * @param wxmpconfig
     * @param cardId     卡券ID
     * @param isOpen     是否开启买单功能，填true/false
     * @return {ApiResult}
     */
    public static ApiResult setPaycell(IWxMpConfig wxmpconfig, String cardId, boolean isOpen) {

        Map<String, Object> data = new HashMap<>();
        data.put("card_id", cardId);
        data.put("is_open", isOpen);
        String jsonResult = HttpClientUtils.sendHttpPost(setPaycell + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 设置自助核销接口
     *
     * @param wxmpconfig
     * @param cardId     卡券ID
     * @param isOpen     是否开启买单功能，填true/false
     * @return {ApiResult}
     */
    public static ApiResult setSelfconsumecell(IWxMpConfig wxmpconfig, String cardId, boolean isOpen) {
        return setSelfconsumecell(wxmpconfig, cardId, isOpen, false, false);
    }

    /**
     * 设置自助核销接口
     *
     * @param wxmpconfig
     * @param cardId           卡券ID
     * @param isOpen           是否开启买单功能，填true/false
     * @param needVerifyCod    用户核销时是否需要输入验证码，填true/false，默认为false
     * @param needRemarkAmount 用户核销时是否需要备注核销金额，填true/false，默认为false
     * @return {ApiResult}
     */
    public static ApiResult setSelfconsumecell(IWxMpConfig wxmpconfig, String cardId, boolean isOpen, boolean needVerifyCod, boolean needRemarkAmount) {
        Map<String, Object> data = new HashMap<>();
        data.put("card_id", cardId);
        data.put("is_open", isOpen);
        data.put("need_verify_cod", needVerifyCod);
        data.put("need_remark_amount", needRemarkAmount);
        String jsonResult = HttpClientUtils.sendHttpPost(setSelfconsumecell + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 获取用户已领取卡券接口
     *
     * @param wxmpconfig
     * @param openid     是 string(64) 需要查询的用户openid
     * @return {ApiResult}
     */
    public static ApiResult getUserCardList(IWxMpConfig wxmpconfig, String openid) {
        return getUserCardList(wxmpconfig, openid, null);
    }

    /**
     * 获取用户已领取卡券接口
     *
     * @param wxmpconfig
     * @param openid     是 string(64) 需要查询的用户openid
     * @param cardId     否    string(32) 卡券ID。不填写时默认查询当前appid下的卡券。
     * @return {ApiResult}
     */
    public static ApiResult getUserCardList(IWxMpConfig wxmpconfig, String openid, String cardId) {
        Map<String, Object> data = new HashMap<>();
        data.put("openid", openid);
        if (StringUtils.isNotBlank(cardId)) {
            data.put("card_id", cardId);
        }
        String jsonResult = HttpClientUtils.sendHttpPost(getUserCardList + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 查看卡券详情
     *
     * @param wxmpconfig
     * @param cardId     卡券ID
     * @return {ApiResult}
     */
    public static ApiResult get(IWxMpConfig wxmpconfig, String cardId) {
        Map<String, Object> data = new HashMap<>();
        data.put("card_id", cardId);
        String jsonResult = HttpClientUtils.sendHttpPost(getCard + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 批量查询卡券列表
     *
     * @param wxmpconfig
     * @param offset     查询卡列表的起始偏移量，从0开始，即offset: 5是指从从列表里的第六个开始读取。
     * @param count      需要查询的卡片的数量（数量最大50）。
     * @return {ApiResult}
     */
    public static ApiResult getBatch(IWxMpConfig wxmpconfig, int offset, int count) {
        return getBatch(wxmpconfig, offset, count, null);
    }

    /**
     * 批量查询卡券列表
     *
     * @param wxmpconfig
     * @param offset     查询卡列表的起始偏移量，从0开始，即offset: 5是指从从列表里的第六个开始读取。
     * @param count      需要查询的卡片的数量（数量最大50）。
     * @param statusList 支持开发者拉出指定状态的卡券列表“CARD_STATUS_NOT_VERIFY”,待审核；“CARD_STATUS_VERIFY_FAIL”,审核失败；“CARD_STATUS_VERIFY_OK”，通过审核；“CARD_STATUS_DELETE”，卡券被商户删除；“CARD_STATUS_DISPATCH”在公众平台投放过的卡券；
     * @return {ApiResult}
     */
    public static ApiResult getBatch(IWxMpConfig wxmpconfig, int offset, int count, List<String> statusList) {
        Map<String, Object> data = new HashMap<>();
        data.put("offset", offset);
        data.put("count", count);
        if (CollectionUtils.isNotEmpty(statusList)) {
            data.put("status_list", statusList);
        }
        String jsonResult = HttpClientUtils.sendHttpPost(getBatch + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 更改卡券信息接口
     *
     * @param wxmpconfig
     * @param jsonStr    JSON数据
     * @return {ApiResult}
     */
    public static ApiResult update(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(update + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 修改库存接口
     *
     * @param wxmpconfig
     * @param cardId            卡券ID
     * @param increasetockValue 增的库存数量
     * @param reduceStockValue  减的库存数量
     * @return {ApiResult}
     */
    public static ApiResult modifystock(IWxMpConfig wxmpconfig, String cardId, int increasetockValue, int reduceStockValue) {
        Map<String, Object> data = new HashMap<>();
        data.put("card_id", cardId);
        if (increasetockValue >= 0) {
            data.put("increase_stock_value", increasetockValue);
        }
        if (reduceStockValue >= 0) {
            data.put("reduce_stock_value", reduceStockValue);
        }
        String jsonResult = HttpClientUtils.sendHttpPost(modifystock + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 删除卡券接口
     *
     * @param wxmpconfig
     * @param cardId     卡券ID
     * @return {ApiResult}
     */
    public static ApiResult delete(IWxMpConfig wxmpconfig, String cardId) {
        Map<String, Object> data = new HashMap<>();
        data.put("card_id", cardId);
        String jsonResult = HttpClientUtils.sendHttpPost(delete + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 设置卡券失效接口,自定义卡券的请求
     *
     * @param wxmpconfig
     * @param code       设置失效的Code码。
     * @param reason     用户发生退款    失效理由
     * @return {ApiResult}
     */
    public static ApiResult unavailableByCode(IWxMpConfig wxmpconfig, String code, String reason) {
        Map<String, Object> data = new HashMap<>();
        data.put("code", code);
        data.put("reason", reason);
        String jsonResult = HttpClientUtils.sendHttpPost(unavailable + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 设置卡券失效接口,自定义code卡券的请求。
     *
     * @param wxmpconfig
     * @param cardId     卡券ID
     * @param reason     用户发生退款    失效理由
     * @return {ApiResult}
     */
    public static ApiResult unavailableByCard(IWxMpConfig wxmpconfig, String cardId, String reason) {
        Map<String, Object> data = new HashMap<>();
        data.put("card_id", cardId);
        data.put("reason", reason);
        String jsonResult = HttpClientUtils.sendHttpPost(unavailable + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }
}
