/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package net.shengjian.weixin.sdk.mp;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 将一条长链接转成短链接 API
 * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Account_Management/URL_Shortener.html
 */
public class ShorturlApi {
    private static String apiUrl = WxConsts.mpapiurl + "/cgi-bin/shorturl?access_token=";

    public static ApiResult getShorturl(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(apiUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 长链接转短链接接口
     *
     * @param longUrl 需要转换的长链接，支持http://、https://、weixin://wxpay 格式的url
     * @return ApiResult 短连接信息
     */
    public static ApiResult getShortUrl(IWxMpConfig wxmpconfig, String longUrl) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("action", "long2short");
        params.put("long_url", longUrl);
        return getShorturl(wxmpconfig, JsonUtils.writeValueAsString(params));
    }
}
