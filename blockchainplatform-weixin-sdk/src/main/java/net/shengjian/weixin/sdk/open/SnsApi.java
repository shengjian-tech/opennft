/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package net.shengjian.weixin.sdk.open;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;
import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;

/**
 * 网页授权获取 access_token API
 */
public class SnsApi {
    private static String snsAccessTokenUrl = WxConsts.mpapiurl + "/sns/oauth2";
    private static String authorizeUrL = WxConsts.mpopenurl + "/connect/oauth2/authorize";
    private static String qrconnectUrl = WxConsts.mpopenurl + "/connect/qrconnect";
    private static String userinfoUrl = WxConsts.mpapiurl + "/sns/userinfo?access_token=";


    /**
     * 生成Authorize链接
     *
     * @param redirect_uri 回跳地址
     * @param snsapiBase   snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo（弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
     * @return url
     */
    public static String getAuthorizeURL(IWxMpConfig wxmpconfig, String redirect_uri, boolean snsapiBase) {
        return getAuthorizeURL(wxmpconfig, redirect_uri, null, snsapiBase);
    }

    /**
     * 生成Authorize链接
     *
     * @param redirectUri 回跳地址
     * @param state       重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @param snsapiBase  snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo（弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
     * @return url
     */
    public static String getAuthorizeURL(IWxMpConfig wxmpconfig, String redirectUri, String state, boolean snsapiBase) {


        String apiurl = authorizeUrL + "?appid=" + wxmpconfig.getAppId() + "&response_type=code&redirect_uri=" + redirectUri;

        // snsapi_base（不弹出授权页面，只能拿到用户openid）
        // snsapi_userinfo（弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
        if (snsapiBase) {
            apiurl = apiurl + "&scope=snsapi_base";
        } else {
            apiurl = apiurl + "&scope=snsapi_userinfo";
        }

        if (StringUtils.isBlank(state)) {
            apiurl = apiurl + "&state=wx#wechat_redirect";
        } else {
            apiurl = apiurl + "&state=" + state.concat("#wechat_redirect");
        }

        return apiurl;
    }


    /**
     * 生成网页二维码授权链接
     *
     * @param redirect_uri 回跳地址
     * @return url
     */
    public static String getQrConnectURL(IWxMpConfig wxmpconfig, String redirect_uri) {
        return getQrConnectURL(wxmpconfig, redirect_uri, null);
    }

    /**
     * 生成网页二维码授权链接
     *
     * @param redirect_uri 回跳地址
     * @param state        重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return url
     */
    public static String getQrConnectURL(IWxMpConfig wxmpconfig, String redirect_uri, String state) {

        try {
            if (StringUtils.isNotBlank(redirect_uri)) {
                redirect_uri = URLEncoder.encode(redirect_uri, "UTF-8");
            }
        } catch (Exception e) {
            return null;
        }


        String apiurl = qrconnectUrl + "?appid" + wxmpconfig.getAppId() + "&response_type=code&redirect_uri=" + redirect_uri + "&scope=snsapi_login";
        if (StringUtils.isBlank(state)) {
            apiurl = apiurl + "&state=wx#wechat_redirect";
        } else {
            apiurl = apiurl + "&state=" + state.concat("#wechat_redirect");
        }
        return apiurl;
    }

    /**
     * 用code换取accessToken
     *
     * @param wxmpconfig
     * @param code
     * @return
     */
    public static ApiResult getAccessToken(IWxMpConfig wxmpconfig, String code) {
        //?appid={appid}&secret={secret}&code={code}&grant_type=authorization_code
        final String accessTokenUrl = snsAccessTokenUrl + "/access_token?appid=" + wxmpconfig.getAppId() + "&secret=" + wxmpconfig.getSecret() + "&code=" + code + "&grant_type=authorization_code";
        String json = HttpClientUtils.sendHttpGet(accessTokenUrl);
        ApiResult apiResult = new ApiResult(json);
        // 认证的accessToken 和API的不一样
        //  wxmpconfig.setAccessToken(apiResult.getAccessToken());
        //  wxmpconfig.setAccessTokenExpiresTime(Long.valueOf(apiResult.getExpiresIn()));
        return apiResult;
    }


    /**
     * 获取用的信息,包括unionid
     *
     * @param wxmpconfig
     * @param code
     * @return
     */
    public static WxUserInfo getWxUserInfo(IWxMpConfig wxmpconfig, String code) {
        ApiResult apiResult = getAccessToken(wxmpconfig, code);

        if (!apiResult.isSucceed()) {
            return null;
        }

        String apiUrl = userinfoUrl + apiResult.getAccessToken() + "&openid=" + apiResult.getOpenId() + "&lang=zh_CN";
        String userInfoJson = HttpClientUtils.sendHttpGet(apiUrl);
        WxUserInfo wxUserInfo = JsonUtils.readValue(userInfoJson, WxUserInfo.class);
        if(StringUtils.isBlank(wxUserInfo.getOpenid())){
            //wxUserInfo = new WxUserInfo();
            wxUserInfo.setOpenid(apiResult.getOpenId());
        }
        return wxUserInfo;
    }

}
