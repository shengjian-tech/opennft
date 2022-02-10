package net.shengjian.weixin.entity;

import net.shengjian.frame.entity.BaseEntity;
import net.shengjian.weixin.sdk.common.wxconfig.IWxCpConfig;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "wx_cpconfig")
public class WxCpConfig extends BaseEntity implements IWxCpConfig {
    private static final long serialVersionUID = 1L;

    private volatile String id;

    // 应用密钥
    private java.lang.String secret;
    // 开发者Id
    private java.lang.String appId;

    private String accessToken;


    @Override
    @Id
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }


    @Override
    @Transient
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    @Override
    public String getSecret() {
        return null;
    }

    @Override
    public void setSecret(String secret) {

    }


}
