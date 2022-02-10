package net.shengjian.weixin.sdk.mp;


import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 核销卡券接口
 * https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Redeeming_a_coupon_voucher_or_card.html
 */
public class CardCodeApi {
    // https://mp.weixin.qq.com/wiki?action=doc&id=mp1451025239&t=0.5997588644623877#1.1
    private static String getCodeUrl = WxConsts.mpapiurl + "/card/code/getErrorMsgByCode?access_token=";
    private static String consumeCode = WxConsts.mpapiurl + "/card/code/consume?access_token=";
    private static String decryptCode = WxConsts.mpapiurl + "/card/code/decrypt?access_token=";
    private static String setDeposit = WxConsts.mpapiurl + "/card/code/deposit?access_token=";
    private static String getDepositCount = WxConsts.mpapiurl + "/card/code/getdepositcount?access_token=";
    private static String checkCode = WxConsts.mpapiurl + "/card/code/checkcode?access_token=";
    private static String update = WxConsts.mpapiurl + "/card/code/update?access_token=";
    private static String mark = WxConsts.mpapiurl + "/card/code/mark?access_token=";

    /**
     * 查询Code接口
     *
     * @param wxmpconfig
     * @param code         单张卡券的唯一标准。
     * @param cardId       卡券ID代表一类卡券。自定义code卡券必填。
     * @param checkConsume 是否校验code核销状态，填入true和false时的code异常状态返回数据不同。
     * @return {ApiResult}
     */
    public static ApiResult get(IWxMpConfig wxmpconfig, String code, String cardId, boolean checkConsume) {
        Map<String, Object> data = new HashMap<>();
        data.put("code", code);
        if (StringUtils.isNotBlank(cardId)) {
            data.put("card_id", cardId);
        }
        data.put("check_consume", checkConsume);
        String jsonResult = HttpClientUtils.sendHttpPost(getCodeUrl + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 核销Code接口
     *
     * @param wxmpconfig
     * @param code       需核销的Code码。
     * @return {ApiResult}
     */
    public static ApiResult consume(IWxMpConfig wxmpconfig, String code) {
        return consume(wxmpconfig, code, null);
    }

    /**
     * 核销Code接口
     *
     * @param wxmpconfig
     * @param code       需核销的Code码。
     * @param cardId     card_id卡券ID。创建卡券时use_custom_code填写true时必填。非自定义Code不必填写。
     * @return {ApiResult}
     */
    public static ApiResult consume(IWxMpConfig wxmpconfig, String code, String cardId) {
        Map<String, Object> data = new HashMap<>();
        data.put("code", code);
        if (StringUtils.isNotBlank(cardId)) {
            data.put("card_id", cardId);
        }
        String jsonResult = HttpClientUtils.sendHttpPost(consumeCode + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 线上核销Code接口
     *
     * @param wxmpconfig
     * @param code       需核销的Code码。
     * @param openid     是 string(20) 当前卡券使用者的openid，通常通过网页授权登录或自定义url跳转参数获得。
     * @return {ApiResult}
     */
    public static ApiResult consumeOnline(IWxMpConfig wxmpconfig, String code, String openid) {
        Map<String, Object> data = new HashMap<>();
        data.put("code", code);
        data.put("openid", openid);
        String jsonResult = HttpClientUtils.sendHttpPost(consumeCode + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * Code解码接口
     *
     * @param wxmpconfig
     * @param encryptCode 经过加密的Code码。
     * @return {ApiResult}
     */
    public static ApiResult decrypt(IWxMpConfig wxmpconfig, String encryptCode) {
        Map<String, Object> data = new HashMap<>();
        data.put("encrypt_code", encryptCode);
        String jsonResult = HttpClientUtils.sendHttpPost(decryptCode + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 导入code接口，文档有歧义 ？？buffer 是 文件的数据流
     *
     * @param wxmpconfig
     * @param cardId     需要进行导入code的卡券ID。
     * @param codeList   需要进行导入code的卡券ID。
     * @return {ApiResult}
     */
    public static ApiResult setDeposit(IWxMpConfig wxmpconfig, String cardId, List<String> codeList) {
        Map<String, Object> data = new HashMap<>();
        data.put("card_id", cardId);
        data.put("code", codeList);
        String jsonResult = HttpClientUtils.sendHttpPost(setDeposit + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 查询导入code数目接口
     *
     * @param wxmpconfig
     * @param cardId     需要进行导入code的卡券ID。
     * @return {ApiResult}
     */
    public static ApiResult getDepositCount(IWxMpConfig wxmpconfig, String cardId) {
        Map<String, Object> data = new HashMap<>();
        data.put("card_id", cardId);
        String jsonResult = HttpClientUtils.sendHttpPost(getDepositCount + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 核查code接口
     *
     * @param wxmpconfig
     * @param cardId     需要进行导入code的卡券ID。
     * @param codeList   已经微信卡券后台的自定义code，上限为100个。
     * @return {ApiResult}
     */
    public static ApiResult checkCode(IWxMpConfig wxmpconfig, String cardId, List<String> codeList) {
        Map<String, Object> data = new HashMap<>();
        data.put("card_id", cardId);
        data.put("code", codeList);
        String jsonResult = HttpClientUtils.sendHttpPost(checkCode + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 更改Code接口
     *
     * @param wxmpconfig
     * @param code       需变更的Code码。
     * @param newCode    变更后的有效Code码。
     * @return {ApiResult}
     */
    public static ApiResult update(IWxMpConfig wxmpconfig, String code, String newCode) {
        return update(null, code, newCode);
    }

    /**
     * 更改Code接口
     *
     * @param wxmpconfig
     * @param cardId     卡券ID。自定义Code码卡券为必填。
     * @param code       需变更的Code码。
     * @param newCode    变更后的有效Code码。
     * @return {ApiResult}
     */
    public static ApiResult update(IWxMpConfig wxmpconfig, String cardId, String code, String newCode) {
        Map<String, Object> data = new HashMap<>();
        data.put("code", code);
        data.put("new_code", newCode);
        if (StringUtils.isNotBlank(cardId)) {
            data.put("card_id", cardId);
        }
        String jsonResult = HttpClientUtils.sendHttpPost(update + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 朋友的券-Mark(占用)Code接口
     *
     * @param wxmpconfig
     * @param code       是 卡券的code码。
     * @param cardId     需要进行导入code的卡券ID。
     * @param openid     是 用券用户的openid。
     * @param isMark     是    是否要mark（占用）这个code，填写true或者false，表示占用或解除占用。
     * @return {ApiResult}
     */
    public static ApiResult markCode(IWxMpConfig wxmpconfig, String code, String cardId, String openid, boolean isMark) {
        Map<String, Object> data = new HashMap<>();
        data.put("code", code);
        data.put("card_id", cardId);
        data.put("openid", openid);
        data.put("is_mark", isMark);
        String jsonResult = HttpClientUtils.sendHttpPost(mark + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

}
