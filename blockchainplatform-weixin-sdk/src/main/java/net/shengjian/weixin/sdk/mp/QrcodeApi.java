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
 * 生成带参数的二维码 API
 * https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
 * https://developers.weixin.qq.com/doc/offiaccount/Account_Management/Generating_a_Parametric_QR_Code.html
 */
public class QrcodeApi {
    private static String apiUrl = WxConsts.mpapiurl + "/cgi-bin/qrcode/create?access_token=";
    private static String showQrcodeUrl = WxConsts.mpweixinurl + "/cgi-bin/showqrcode?ticket=";

    public static ApiResult create(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(apiUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 创建临时二维码
     *
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过604800（即7天）。
     * @param sceneId       场景值ID，临时二维码时为32位非0整型
     * @return ApiResult 二维码信息
     */
    public static ApiResult createTemporary(IWxMpConfig wxmpconfig, int expireSeconds, int sceneId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("expire_seconds", expireSeconds);
        params.put("action_name", "QR_SCENE");

        Map<String, Object> actionInfo = new HashMap<String, Object>();
        Map<String, Object> scene = new HashMap<String, Object>();
        scene.put("scene_id", sceneId);

        actionInfo.put("scene", scene);
        params.put("action_info", actionInfo);
        return create(wxmpconfig, JsonUtils.writeValueAsString(params));
    }

    /**
     * 创建永久二维码
     *
     * @param sceneId 场景值ID，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @return ApiResult 二维码信息
     */
    public static ApiResult createPermanent(IWxMpConfig wxmpconfig, int sceneId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("action_name", "QR_LIMIT_SCENE");

        Map<String, Object> actionInfo = new HashMap<String, Object>();
        Map<String, Object> scene = new HashMap<String, Object>();
        scene.put("scene_id", sceneId);

        actionInfo.put("scene", scene);
        params.put("action_info", actionInfo);
        return create(wxmpconfig, JsonUtils.writeValueAsString(params));
    }

    /**
     * 创建永久二维码
     *
     * @param sceneStr 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段
     * @return ApiResult 二维码信息
     */
    public static ApiResult createPermanent(IWxMpConfig wxmpconfig, String sceneStr) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("action_name", "QR_LIMIT_STR_SCENE");

        Map<String, Object> actionInfo = new HashMap<String, Object>();
        Map<String, Object> scene = new HashMap<String, Object>();
        scene.put("scene_str", sceneStr);

        actionInfo.put("scene", scene);
        params.put("action_info", actionInfo);
        return create(wxmpconfig, JsonUtils.writeValueAsString(params));
    }

    /**
     * 通过ticket换取二维码地址
     *
     * @param ticket 换取二维码参数
     * @return String url
     */
    public static String getShowQrcodeUrl(IWxMpConfig wxmpconfig, String ticket) {
        return showQrcodeUrl + ticket;
    }
}
