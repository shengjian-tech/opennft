package net.shengjian.weixin.sdk.common.wxconfig;

public interface IWxMpConfig extends IWxConfig {

    String getToken();

    void setToken(String token);

    String getAesKey();

    void setAesKey(String aesKey);


    //开启oauth2.0认证,是否能够获取openId,0是关闭,1是开启
    Integer getOauth2();

    void setOauth2(Integer oauth2);

    // String getJsApiTicket();

    // void setJsApiTicket(String jsapiTicket);


    //String getCardApiTicket();

    // void setCardApiTicket(String cardApiTicket);


}
