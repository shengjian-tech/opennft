package net.shengjian.weixin.sdk.mp;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * menu api
 */
public class MenuApi {

    private static String getMenu = WxConsts.mpapiurl + "/cgi-bin/menu/getErrorMsgByCode?access_token=";
    private static String createMenu = WxConsts.mpapiurl + "/cgi-bin/menu/create?access_token=";
    private static String deleteMenuUrl = WxConsts.mpapiurl + "/cgi-bin/menu/delete?access_token=";
    private static String addConditionalUrl = WxConsts.mpapiurl + "/cgi-bin/menu/addconditional?access_token=";
    private static String delConditionalUrl = WxConsts.mpapiurl + "/cgi-bin/menu/delconditional?access_token=";
    private static String tryMatchUrl = WxConsts.mpapiurl + "/cgi-bin/menu/trymatch?access_token=";
    private static String getCurrentSelfMenuInfoUrl = WxConsts.mpapiurl + "/cgi-bin/get_current_selfmenu_info?access_token=";

    /**
     * 查询自定义菜单
     *
     * @return {ApiResult}
     */
    public static ApiResult getMenu(IWxMpConfig wxmpconfig) {
        String jsonResult = HttpClientUtils.sendHttpGet(getMenu + wxmpconfig.getAccessToken());
        return new ApiResult(jsonResult);
    }

    /**
     * 创建自定义菜单
     *
     * @param jsonStr json字符串
     * @return {ApiResult}
     */
    public static ApiResult createMenu(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(createMenu + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 自定义菜单删除接口
     *
     * @return ApiResult
     */
    public static ApiResult deleteMenu(IWxMpConfig wxmpconfig) {
        String jsonResult = HttpClientUtils.sendHttpGet(deleteMenuUrl + wxmpconfig.getAccessToken());
        return new ApiResult(jsonResult);
    }

    /**
     * 创建个性化菜单
     *
     * @param jsonStr json字符串
     * @return {ApiResult}
     */
    public static ApiResult addConditional(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(addConditionalUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 删除个性化菜单
     *
     * @param menuid menuid为菜单id，可以通过自定义菜单查询接口获取。
     * @return ApiResult
     */
    public static ApiResult delConditional(IWxMpConfig wxmpconfig, String menuid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("menuid", menuid);

        String url = delConditionalUrl + wxmpconfig.getAccessToken();

        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(params));
        return new ApiResult(jsonResult);
    }

    /**
     * 测试个性化菜单匹配结果
     *
     * @param userId user_id可以是粉丝的OpenID，也可以是粉丝的微信号。
     * @return ApiResult
     */
    public static ApiResult tryMatch(IWxMpConfig wxmpconfig, String userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", userId);

        String url = tryMatchUrl + wxmpconfig.getAccessToken();

        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(params));
        return new ApiResult(jsonResult);
    }

    /**
     * 获取自定义菜单配置接口
     *
     * @return {ApiResult}
     */
    public static ApiResult getCurrentSelfMenuInfo(IWxMpConfig wxmpconfig) {
        String jsonResult = HttpClientUtils.sendHttpGet(getCurrentSelfMenuInfoUrl + wxmpconfig.getAccessToken());
        return new ApiResult(jsonResult);
    }

}


