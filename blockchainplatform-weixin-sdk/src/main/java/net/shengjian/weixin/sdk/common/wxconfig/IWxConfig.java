package net.shengjian.weixin.sdk.common.wxconfig;

public interface IWxConfig {
    String getId();// 业务Id

    void setId(String string);

    String getAppId();

    void setAppId(String appId);

    String getAccessToken();

    void setAccessToken(String accessToken);

    // void setAccessTokenExpiresTime(Long accessTokenExpiresTime);

    // void setExpiresIn(Integer expiresIn);

    // boolean isAccessTokenExpired();

    String getSecret();

    void setSecret(String secret);


}
