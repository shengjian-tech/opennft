package net.shengjian.weixin.sdk.mp;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 会员卡接口
 * <p>
 * https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Membership_Cards/Manage_Member_Card.html
 */
public class MemberCardApi {
    private static String activateUrl = WxConsts.mpapiurl + "/card/membercard/activate?access_token=";
    private static String setActivateUserFormUrl = WxConsts.mpapiurl + "/card/membercard/activateuserform/set?access_token=";
    private static String getUserInfoUrl = WxConsts.mpapiurl + "/card/membercard/userinfo/getErrorMsgByCode?access_token=";
    private static String getActivateTempInfoUrl = WxConsts.mpapiurl + "/card/membercard/activatetempinfo/getErrorMsgByCode?access_token=";
    private static String updateUserUrl = WxConsts.mpapiurl + "/card/membercard/updateuser?access_token=";

    /**
     * 接口激活
     *
     * @param jsonStr JSON数据
     * @return {ApiResult}
     */
    public static ApiResult activate(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(activateUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 普通一键激活-设置开卡字段接口
     *
     * @param jsonStr JSON数据
     * @return {ApiResult}
     */
    public static ApiResult setActivateUserForm(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(setActivateUserFormUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 拉取会员信息接口
     *
     * @param cardId 卡券ID代表一类卡券。
     * @param code   卡券code。
     * @return {ApiResult}
     */
    public static ApiResult getUserInfo(IWxMpConfig wxmpconfig, String cardId, String code) {
        Map<String, Object> data = new HashMap<>();
        data.put("card_id", cardId);
        data.put("code", code);
        String jsonResult = HttpClientUtils.sendHttpPost(getUserInfoUrl + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 跳转型一键激活-设置开卡字段接口-获取用户提交资料
     *
     * @param activaTeicket 用户填写信息的参数
     * @return {ApiResult}
     */
    public static ApiResult getActivateTempInfo(IWxMpConfig wxmpconfig, String activaTeicket) {
        Map<String, Object> data = new HashMap<>();
        data.put("activate_ticket", activaTeicket);
        String jsonResult = HttpClientUtils.sendHttpPost(getActivateTempInfoUrl + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 更新会员信息
     *
     * @param jsonStr JSON数据
     * @return {ApiResult}
     */
    public static ApiResult updateUser(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(updateUserUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }
}
