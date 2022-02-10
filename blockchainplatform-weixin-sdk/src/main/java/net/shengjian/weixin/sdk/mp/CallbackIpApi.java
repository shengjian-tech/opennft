/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package net.shengjian.weixin.sdk.mp;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;

/**
 * 获取微信服务器IP地址
 * 如果公众号基于安全等考虑，需要获知微信服务器的IP地址列表，以便进行相关限制，可以通过该接口获得微信服务器IP地址列表。
 * https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_the_WeChat_server_IP_address.
 */
public class CallbackIpApi {
    private static String apiUrl = WxConsts.mpapiurl + "/cgi-bin/getcallbackip?access_token=";

    /**
     * 获取微信服务器IP地址
     *
     * @param wxmpconfig
     * @return {ApiResult}
     */
    public static ApiResult getCallbackIp(IWxMpConfig wxmpconfig) {
        String jsonResult = HttpClientUtils.sendHttpGet(apiUrl + wxmpconfig.getAccessToken());
        return new ApiResult(jsonResult);
    }
}
