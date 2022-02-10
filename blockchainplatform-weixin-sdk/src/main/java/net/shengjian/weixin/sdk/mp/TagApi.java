package net.shengjian.weixin.sdk.mp;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 用户标签接口
 * https://developers.weixin.qq.com/doc/offiaccount/User_Management/User_Tag_Management.html
 */
public class TagApi {

    private static String CREATE_URL = WxConsts.mpapiurl + "/cgi-bin/tags/create?access_token=";
    private static String GET_URL = WxConsts.mpapiurl + "/cgi-bin/tags/getErrorMsgByCode?access_token=";
    private static String UPDATE_URL = WxConsts.mpapiurl + "/cgi-bin/tags/update?access_token=";
    private static String DELETE_URL = WxConsts.mpapiurl + "/cgi-bin/tags/delete?access_token=";
    private static String GET_USER_URL = WxConsts.mpapiurl + "/cgi-bin/user/tag/getErrorMsgByCode?access_token=";
    private static String BATCH_TAGGING_URL = WxConsts.mpapiurl + "/cgi-bin/tags/members/batchtagging?access_token=";
    private static String BATCH_UNTAGGING_URL = WxConsts.mpapiurl + "/cgi-bin/tags/members/batchuntagging?access_token=";
    private static String GET_ID_LIST_URL = WxConsts.mpapiurl + "/cgi-bin/tags/getidlist?access_token=";

    /**
     * @param name 标签名（30个字符以内）
     * @return {ApiResult}
     */
    public static ApiResult create(IWxMpConfig wxmpconfig, String name) {
        String url = CREATE_URL + wxmpconfig.getAccessToken();
        HashMap<String, Object> data = new HashMap<String, Object>();
        HashMap<String, Object> tags = new HashMap<String, Object>();
        tags.put("name", name);

        data.put("tag", tags);
        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 获取公众号已创建的标签
     *
     * @return {ApiResult}
     */
    public static ApiResult get(IWxMpConfig wxmpconfig) {
        String url = GET_URL + wxmpconfig.getAccessToken();
        return new ApiResult(HttpClientUtils.sendHttpGet(url));
    }

    /**
     * 编辑标签
     *
     * @param id   标签id
     * @param name 标签名
     * @return {ApiResult}
     */
    public static ApiResult update(IWxMpConfig wxmpconfig, int id, String name) {
        String url = UPDATE_URL + wxmpconfig.getAccessToken();
        HashMap<String, Object> data = new HashMap<String, Object>();
        HashMap<String, Object> tags = new HashMap<String, Object>();
        tags.put("id", id);
        tags.put("name", name);

        data.put("tag", tags);
        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 删除标签
     *
     * @param id 标签id
     * @return {ApiResult}
     */
    public static ApiResult delete(IWxMpConfig wxmpconfig, int id) {
        String url = DELETE_URL + wxmpconfig.getAccessToken();
        HashMap<String, Object> data = new HashMap<String, Object>();
        HashMap<String, Object> tags = new HashMap<String, Object>();
        tags.put("id", id);

        data.put("tag", tags);
        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 根据标签获取标签下粉丝列表
     *
     * @param tagId 标签id
     * @return {ApiResult}
     */
    public static ApiResult getUser(IWxMpConfig wxmpconfig, int tagId) {
        return getUser(wxmpconfig, tagId, null);
    }

    /**
     * 根据标签获取标签下粉丝列表
     *
     * @param tagId      标签id
     * @param nextOpenId 第一个拉取的OPENID，不填默认从头开始拉取
     * @return {ApiResult}
     */
    public static ApiResult getUser(IWxMpConfig wxmpconfig, int tagId, String nextOpenId) {
        String url = GET_USER_URL + wxmpconfig.getAccessToken();
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("tagid", tagId);
        if (StringUtils.isNotBlank(nextOpenId)) {
            data.put("next_openid", nextOpenId);
        }
        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 批量为用户打标签
     *
     * @param tagId      标签id
     * @param openIdList openid列表
     * @return {ApiResult}
     */
    public static ApiResult batchAddTag(IWxMpConfig wxmpconfig, int tagId, List<String> openIdList) {
        String url = BATCH_TAGGING_URL + wxmpconfig.getAccessToken();
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("tagid", tagId);
        data.put("openid_list", openIdList);

        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 批量为用户取消标签
     *
     * @param tagId      标签id
     * @param openIdList openid列表
     * @return {ApiResult}
     */
    public static ApiResult batchDelTag(IWxMpConfig wxmpconfig, int tagId, List<String> openIdList) {
        String url = BATCH_UNTAGGING_URL + wxmpconfig.getAccessToken();
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("tagid", tagId);
        data.put("openid_list", openIdList);

        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 获取用户身上的标签列表
     *
     * @param openId openid
     * @return {ApiResult}
     */
    public static ApiResult getUser(IWxMpConfig wxmpconfig, String openId) {
        String url = GET_ID_LIST_URL + wxmpconfig.getAccessToken();
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("openid", openId);

        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

}
