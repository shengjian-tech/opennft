package net.shengjian.weixin.sdk.mp;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分组Api
 * <p>
 * 这个API估计微信去掉了,未找到对应的文档
 * <p>
 * 文档地址：http://mp.weixin.qq.com/wiki/5/0d8acdd6d4433c877fbea938a2f133cd.html
 */
@Deprecated
public class GroupsApi {

    private static String createUrl = WxConsts.mpapiurl + "/cgi-bin/groups/create?access_token=";
    private static String getUrl = WxConsts.mpapiurl + "/cgi-bin/groups/getErrorMsgByCode?access_token=";
    private static String getIdUrl = WxConsts.mpapiurl + "/cgi-bin/groups/getid?access_token=";
    private static String updateUrl = WxConsts.mpapiurl + "/cgi-bin/groups/update?access_token=";
    private static String membersUpdateUrl = WxConsts.mpapiurl + "/cgi-bin/groups/members/update?access_token=";
    private static String membersBatchUpdateUrl = WxConsts.mpapiurl + "/cgi-bin/groups/members/batchupdate?access_token=";
    private static String deleteUrl = WxConsts.mpapiurl + "/cgi-bin/groups/delete?access_token=";

    /**
     * 创建分组，一个公众账号，最多支持创建100个分组。
     *
     * @param name 分组名
     * @return ApiResult
     */
    public static ApiResult create(IWxMpConfig wxmpconfig, String name) {
        String url = createUrl + wxmpconfig.getAccessToken();

        Map<String, Map<String, String>> groupData = new HashMap<String, Map<String, String>>();
        Map<String, String> mapData = new HashMap<String, String>();
        mapData.put("name", name);
        groupData.put("group", mapData);

        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(groupData));
        return new ApiResult(jsonResult);
    }

    /**
     * 查询所有分组
     *
     * @return ApiResult
     */
    public static ApiResult get(IWxMpConfig wxmpconfig) {
        String url = getUrl + wxmpconfig.getAccessToken();

        String jsonResult = HttpClientUtils.sendHttpGet(url);
        return new ApiResult(jsonResult);
    }

    /**
     * 通过用户的OpenID查询其所在的GroupID
     *
     * @param openid 普通用户的标识，对当前开发者帐号唯一
     * @return ApiResult
     */
    public static ApiResult getId(IWxMpConfig wxmpconfig, String openid) {
        String url = getIdUrl + wxmpconfig.getAccessToken();

        Map<String, String> mapData = new HashMap<String, String>();
        mapData.put("openid", openid);

        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(mapData));
        return new ApiResult(jsonResult);
    }

    /**
     * 修改分组名
     *
     * @param id   分组id，由微信分配
     * @param name 分组名字（30个字符以内）
     * @return ApiResult
     */
    public static ApiResult update(IWxMpConfig wxmpconfig, int id, String name) {
        String url = updateUrl + wxmpconfig.getAccessToken();

        Map<String, Map<String, Object>> groupData = new HashMap<String, Map<String, Object>>();
        Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("id", id);
        mapData.put("name", name);
        groupData.put("group", mapData);

        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(groupData));
        return new ApiResult(jsonResult);
    }

    /**
     * 移动用户分组
     *
     * @param openid     用户唯一标识符
     * @param to_groupid 分组id
     * @return ApiResult
     */
    public static ApiResult membersUpdate(IWxMpConfig wxmpconfig, String openid, int to_groupid) {
        String url = membersUpdateUrl + wxmpconfig.getAccessToken();

        Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("openid", openid);
        mapData.put("to_groupid", to_groupid);

        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(mapData));
        return new ApiResult(jsonResult);
    }

    /**
     * 批量移动用户分组
     *
     * @param openidList 用户唯一标识符openid的列表（size不能超过50）
     * @param to_groupid 分组id
     * @return ApiResult
     */
    public static ApiResult membersBatchUpdate(IWxMpConfig wxmpconfig, List<String> openidList, int to_groupid) {
        String url = membersBatchUpdateUrl + wxmpconfig.getAccessToken();

        Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("openid_list", openidList);
        mapData.put("to_groupid", to_groupid);

        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(mapData));
        return new ApiResult(jsonResult);
    }

    /**
     * 删除分组
     *
     * @param id 分组的id
     * @return ApiResult
     */
    public static ApiResult delete(IWxMpConfig wxmpconfig, int id) {
        String url = deleteUrl + wxmpconfig.getAccessToken();

        Map<String, Map<String, Object>> groupData = new HashMap<String, Map<String, Object>>();
        Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("id", id);
        groupData.put("group", mapData);

        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(groupData));
        return new ApiResult(jsonResult);
    }

}
