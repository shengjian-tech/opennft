/**
 * Copyright (c) 2011-2017, fuyong (859050943@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

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
 * 黑名单管理api
 * 接口有问题
 * https://developers.weixin.qq.com/doc/offiaccount/User_Management/Manage_blacklist.html
 */
public class BlackUserApi {
    private static String getBlackList = WxConsts.mpapiurl + "/cgi-bin/tags/members/getblacklist?access_token=";
    private static String batchBlackList = WxConsts.mpapiurl + "/cgi-bin/tags/members/batchblacklist?access_token=";
    private static String batchUnblackList = WxConsts.mpapiurl + "/cgi-bin/tags/members/batchunblacklist?access_token=";

    /**
     * 获取公众号的黑名单列表
     *
     * @param wxmpconfig
     * @param beginOpenid 当 begin_openid 为空时，默认从开头拉取。
     * @return ApiResult
     */
    public static ApiResult getBlackList(IWxMpConfig wxmpconfig, String beginOpenid) {
        String url = getBlackList + wxmpconfig.getAccessToken();

        Map<String, String> mapData = new HashMap<>();
        if (StringUtils.isNotBlank(beginOpenid)) {
            mapData.put("begin_openid", beginOpenid);
        }
        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(mapData));
        return new ApiResult(jsonResult);
    }

    /**
     * 获取公众号的黑名单列表
     *
     * @return ApiResult
     */
    public static ApiResult getBlackList(IWxMpConfig wxmpconfig) {
        return getBlackList(wxmpconfig, null);
    }

    /**
     * 批量拉黑用户
     *
     * @param jsonStr json字符串
     * @return ApiResult
     */
    public static ApiResult batchBlackUsers(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(batchBlackList + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 批量拉黑用户
     *
     * @param openIdList 需要拉黑的用户openid列表
     * @return ApiResult
     */
    public static ApiResult batchBlackUsers(IWxMpConfig wxmpconfig, List<String> openIdList) throws IllegalArgumentException {
        if (CollectionUtils.isEmpty(openIdList)) {
            throw new IllegalArgumentException();
        }

        Map<String, List<String>> userListMap = new HashMap<>();
        /*
        List<String> userList = new ArrayList<>();
        if (openIdList != null && openIdList.size() > 0) {
            for (String openId : openIdList) {
                userList.add(openId);
            }
        }
        userListMap.put("openid_list", userList);
        */
        userListMap.put("openid_list", openIdList);
        return batchBlackUsers(wxmpconfig, JsonUtils.writeValueAsString(userListMap));
    }

    /**
     * 批量取消拉黑用户
     *
     * @param jsonStr json字符串
     * @return ApiResult
     */
    public static ApiResult batchUnblackUsers(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(batchUnblackList + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 批量取消拉黑用户
     *
     * @param openIdList 需要取消拉黑的用户openid列表
     * @return ApiResult
     */
    public static ApiResult batchUnblackUsers(IWxMpConfig wxmpconfig, List<String> openIdList) throws IllegalArgumentException {
        if (CollectionUtils.isEmpty(openIdList)) {
            throw new IllegalArgumentException();
        }

        Map<String, List<String>> userListMap = new HashMap<>();
        /*
        List<String> userList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(userList)) {
            for (String openId : openIdList) {
                userList.add(openId);
            }
        }
        userListMap.put("openid_list", userList);
        */

        userListMap.put("openid_list", openIdList);

        return batchUnblackUsers(wxmpconfig, JsonUtils.writeValueAsString(userListMap));
    }
}
