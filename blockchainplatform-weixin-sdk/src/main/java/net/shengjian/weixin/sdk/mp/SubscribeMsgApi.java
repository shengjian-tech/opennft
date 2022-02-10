/**
 * Copyright (c) 2011-2017, Javen Zhou (javendev@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package net.shengjian.weixin.sdk.mp;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 一次性订阅消息 API
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1500374289_66bvB
 */
public class SubscribeMsgApi {
    private static String subscribe = WxConsts.mpapiurl + "/cgi-bin/message/template/subscribe?access_token=";
    private static String authorize_uri = WxConsts.mpweixinurl + "/mp/subscribemsg?action=get_confirm";

    public static String getAuthorizeURL(IWxMpConfig wxmpconfig, String scene, String template_id, String redirectUri, String reserved) throws UnsupportedEncodingException {
        StringBuffer sbf = new StringBuffer();
        sbf.append(authorize_uri).append("&appid=").append(wxmpconfig.getAppId())
                .append("&scene=").append(scene)
                .append("&template_id=").append(template_id)
                .append("&redirect_uri=").append(URLEncoder.encode(redirectUri, "UTF-8").replace("+", "%20"));
        if (StringUtils.isNotBlank(reserved)) {
            sbf.append("&reserved=").append(reserved);
        }
        sbf.append("#wechat_redirect");

        return sbf.toString();
    }

    /**
     * 发送一次性订阅消息
     *
     * @param jsonStr json字符串
     * @return ApiResult 发送json数据示例:
     * {
     * "touser" : "OPENID",
     * "template_id" :
     * "TEMPLATE_ID",
     * "url" : "URL",
     * "scene" : "SCENE",
     * "title" :
     * "TITLE",
     * "data" : {
     * "content" : {
     * "value" : "VALUE",
     * "color" :
     * "COLOR"
     * }
     * }
     * }
     */
    public static ApiResult subscribe(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(subscribe + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    public static ApiResult subscribe(IWxMpConfig wxmpconfig, SubscribeInfo subscribeInfo) {
        return new ApiResult(JsonUtils.writeValueAsString(subscribeInfo));
    }

    public static ApiResult subscribe(IWxMpConfig wxmpconfig, String openId, String templateId, String url, int scene, String title,
                                      String value, String color) {
        SubscribeInfo subscribeInfo = SubscribeInfo.Builder().setTouser(openId).setTemplate_id(templateId).setUrl(url)
                .setScene(String.valueOf(scene)).setTitle(title)
                .setData(Data.Builder().setContent(Content.Builder().setValue(value).setColor(color).build()).build());
        return subscribe(wxmpconfig, JsonUtils.writeValueAsString(subscribeInfo));
    }

}

class SubscribeInfo {
    private String touser;
    private String template_id;
    private String url;
    private String scene;
    private String title;
    private Data data;

    private SubscribeInfo() {

    }

    private SubscribeInfo(String touser, String template_id, String url, String scene, String title, Data data) {
        this.touser = touser;
        this.template_id = template_id;
        this.url = url;
        this.scene = scene;
        this.title = title;
        this.data = data;
    }

    public static SubscribeInfo Builder() {
        return new SubscribeInfo();
    }

    public SubscribeInfo build() {
        if (StringUtils.isBlank(touser)) {
            throw new IllegalStateException("touser is null");
        }
        if (StringUtils.isBlank(template_id)) {
            throw new IllegalStateException("template_id is null");
        }
        if (StringUtils.isBlank(url)) {
            throw new IllegalStateException("url is null");
        }
        if (StringUtils.isBlank(scene)) {
            throw new IllegalStateException("scene is null");
        }
        if (StringUtils.isBlank(title)) {
            throw new IllegalStateException("title is null");
        }
        if (data == null) {
            throw new IllegalStateException("data is null");
        }
        return new SubscribeInfo(touser, template_id, url, scene, title, data);
    }

    public String getTouser() {
        return touser;
    }

    public SubscribeInfo setTouser(String touser) {
        this.touser = touser;
        return this;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public SubscribeInfo setTemplate_id(String template_id) {
        this.template_id = template_id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SubscribeInfo setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getScene() {
        return scene;
    }

    public SubscribeInfo setScene(String scene) {
        this.scene = scene;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SubscribeInfo setTitle(String title) {
        this.title = title;
        return this;
    }

    public Data getData() {
        return data;
    }

    public SubscribeInfo setData(Data data) {
        this.data = data;
        return this;
    }

}

class Data {
    private Content content;

    private Data() {
    }

    private Data(Content content) {
        this.content = content;
    }

    public static Data Builder() {
        return new Data();
    }

    public Data build() {
        if (content == null) {
            throw new IllegalStateException("content is null");
        }
        return new Data(content);
    }

    public Content getContent() {
        return content;
    }

    public Data setContent(Content content) {
        this.content = content;
        return this;
    }

}

class Content {
    private String value;
    private String color;

    private Content() {
    }

    private Content(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public static Content Builder() {
        return new Content();
    }

    public Content build() {
        if (StringUtils.isBlank(value)) {
            throw new IllegalStateException("value is null");
        }
        if (StringUtils.isBlank(color)) {
            throw new IllegalStateException("color is null");
        }
        return new Content(value, color);
    }

    public String getValue() {
        return value;
    }

    public Content setValue(String value) {
        this.value = value;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Content setColor(String color) {
        this.color = color;
        return this;
    }

}
