package net.shengjian.weixin.sdk.mp;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理 API
 */
public class UserApi {

    private static String getUserInfo = WxConsts.mpapiurl + "/cgi-bin/user/info?access_token=";
    private static String getFollowers = WxConsts.mpapiurl + "/cgi-bin/user/getErrorMsgByCode?access_token=";
    private static String batchGetUserInfo = WxConsts.mpapiurl + "/cgi-bin/user/info/batchget?access_token=";
    private static String updateRemarkUrl = WxConsts.mpapiurl + "/cgi-bin/user/info/updateremark?access_token=";

    /**
     * 获取用户基本信息（包括UnionID机制）
     * <p>
     * https://developers.weixin.qq.com/doc/offiaccount/User_Management/Get_users_basic_information_UnionID.html
     *
     * @param wxmpconfig
     * @param openId     普通用户的标识，对当前公众号唯一
     * @return ApiResult
     */
    public static ApiResult getUserInfo(IWxMpConfig wxmpconfig, String openId) {
        String apiurl = getUserInfo + wxmpconfig.getAccessToken() + "&openid=" + openId + "&lang=zh_CN";
        String jsonResult = HttpClientUtils.sendHttpGet(apiurl);
        return new ApiResult(jsonResult);
    }

    /**
     * 获取用户列表
     *
     * @param wxmpconfig
     * @param nextOpenid 第一个拉取的OPENID，不填默认从头开始拉取
     * @return ApiResult
     */
    public static ApiResult getFollowers(IWxMpConfig wxmpconfig, String nextOpenid) {
        String apiurl = getFollowers + wxmpconfig.getAccessToken();

        if (StringUtils.isNotBlank(nextOpenid)) {
            apiurl = apiurl + "&next_openid=" + nextOpenid;
        }
        String jsonResult = HttpClientUtils.sendHttpGet(apiurl);
        return new ApiResult(jsonResult);
    }

    /**
     * 获取用户列表
     *
     * @param wxmpconfig
     * @return ApiResult
     */
    public static ApiResult getFollows(IWxMpConfig wxmpconfig) {
        return getFollowers(wxmpconfig, null);
    }

    /**
     * 批量获取用户基本信息, by Unas
     *
     * @param wxmpconfig
     * @param jsonStr    json字符串
     * @return ApiResult
     */
    public static ApiResult batchGetUserInfo(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(batchGetUserInfo + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 批量获取用户基本信息
     *
     * @param wxmpconfig
     * @param openIdList openid列表
     * @return ApiResult
     */
    public static ApiResult batchGetUserInfo(IWxMpConfig wxmpconfig, List<String> openIdList) {
        Map<String, List<Map<String, Object>>> userListMap = new HashMap<String, List<Map<String, Object>>>();

        List<Map<String, Object>> userList = new ArrayList<>();
        for (String openId : openIdList) {
            Map<String, Object> mapData = new HashMap<>();
            mapData.put("openid", openId);
            mapData.put("lang", "zh_CN");
            userList.add(mapData);
        }
        userListMap.put("user_list", userList);

        return batchGetUserInfo(wxmpconfig, JsonUtils.writeValueAsString(userListMap));
    }

    /**
     * 设置备注名
     *
     * @param wxmpconfig
     * @param openid     用户标识
     * @param remark     新的备注名，长度必须小于30字符
     * @return {ApiResult}
     */
    public static ApiResult updateRemark(IWxMpConfig wxmpconfig, String openid, String remark) {
        String url = updateRemarkUrl + wxmpconfig.getAccessToken();

        Map<String, String> mapData = new HashMap<>();
        mapData.put("openid", openid);
        mapData.put("remark", remark);
        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(mapData));

        return new ApiResult(jsonResult);
    }
}
