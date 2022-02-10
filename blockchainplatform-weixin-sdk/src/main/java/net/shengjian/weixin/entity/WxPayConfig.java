package net.shengjian.weixin.entity;


import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import net.shengjian.weixin.sdk.common.wxconfig.IWxPayConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "wx_payconfig")
public class WxPayConfig extends BaseEntity implements IWxPayConfig {


    private static final long serialVersionUID = 1L;

    //alias
	/*
	public static final String TABLE_ALIAS = "微信号需要的配置信息";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ORGID = "站点Id";
	public static final String ALIAS_APPID = "开发者Id";
	public static final String ALIAS_SECRET = "应用密钥";
	public static final String ALIAS_MCHID = "微信支付商户号";
	public static final String ALIAS_KEY = "交易过程生成签名的密钥，仅保留在商户系统和微信支付后台，不会在网络中传播";
	public static final String ALIAS_CERTIFICATEFILE = "证书地址";
	public static final String ALIAS_NOTIFYURL = "通知地址";
	public static final String ALIAS_SIGNTYPE = "加密方式,MD5和HMAC-SHA256";
	public static final String ALIAS_ACTIVE = "状态 0不可用,1可用";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
	public static final String ALIAS_BAK4 = "bak4";
	public static final String ALIAS_BAK5 = "bak5";
    */
    //date formats

    //columns START

    // id
    private java.lang.String id;

    // 站点Id
    private java.lang.String orgId;

    // 开发者Id
    private java.lang.String appId;

    // 应用密钥
    private java.lang.String secret;

    // 微信支付商户号
    private java.lang.String mchId;

    // 交易过程生成签名的密钥，仅保留在商户系统和微信支付后台，不会在网络中传播
    private java.lang.String key;
    /**
     * v3秘钥
     */
    private java.lang.String apiV3Key;
    // 证书地址
    private java.lang.String certificateFile;

    // 通知地址
    private java.lang.String notifyUrl;

    // 加密方式,MD5和HMAC-SHA256
    private java.lang.String signType;

    // 状态 0不可用,1可用
    private java.lang.Integer active;

    // bak1
    private java.lang.String bak1;

    // bak2
    private java.lang.String bak2;

    // bak3
    private java.lang.String bak3;

    // bak4
    private java.lang.String bak4;

    // bak5
    private java.lang.String bak5;
    //columns END 数据库字段结束

    private String accessToken;

    //concstructor
    public WxPayConfig() {
    }


    //get and set

    /**
     * id
     */
    @Id
    @WhereSQL(sql = "id=:WxPayconfig_id")
    public java.lang.String getId() {
        return this.id;
    }

    /**
     * id
     *
     * @param value
     */
    public void setId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.id = value;
    }

    /**
     * 站点Id
     */
    @WhereSQL(sql = "orgId=:WxPayconfig_orgId")
    public java.lang.String getOrgId() {
        return this.orgId;
    }

    /**
     * 站点Id
     *
     * @param value
     */
    public void setOrgId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.orgId = value;
    }

    /**
     * 开发者Id
     */
    @WhereSQL(sql = "appId=:WxPayconfig_appId")
    public java.lang.String getAppId() {
        return this.appId;
    }

    /**
     * 开发者Id
     *
     * @param value
     */
    public void setAppId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.appId = value;
    }

    /**
     * 应用密钥
     */
    @WhereSQL(sql = "secret=:WxPayconfig_secret")
    public java.lang.String getSecret() {
        return this.secret;
    }

    /**
     * 应用密钥
     *
     * @param value
     */
    public void setSecret(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.secret = value;
    }

    /**
     * 微信支付商户号
     */
    @WhereSQL(sql = "mchId=:WxPayconfig_mchId")
    public java.lang.String getMchId() {
        return this.mchId;
    }

    /**
     * 微信支付商户号
     *
     * @param value
     */
    public void setMchId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.mchId = value;
    }

    /**
     * 交易过程生成签名的密钥，仅保留在商户系统和微信支付后台，不会在网络中传播
     */
    @WhereSQL(sql = "key=:WxPayconfig_key")
    public java.lang.String getKey() {
        return this.key;
    }

    /**
     * 交易过程生成签名的密钥，仅保留在商户系统和微信支付后台，不会在网络中传播
     *
     * @param value
     */
    public void setKey(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.key = value;
    }

    @WhereSQL(sql = "apiV3Key=:WxPayconfig_apiV3Key")
    public String getApiV3Key() {
        return apiV3Key;
    }

    public void setApiV3Key(String apiV3Key) {
        this.apiV3Key = apiV3Key;
    }

    /**
     * 证书地址
     */
    @WhereSQL(sql = "certificateFile=:WxPayconfig_certificateFile")
    public java.lang.String getCertificateFile() {
        return this.certificateFile;
    }

    /**
     * 证书地址
     *
     * @param value
     */
    public void setCertificateFile(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.certificateFile = value;
    }

    /**
     * 通知地址
     */
    @WhereSQL(sql = "notifyUrl=:WxPayconfig_notifyUrl")
    public java.lang.String getNotifyUrl() {
        return this.notifyUrl;
    }

    /**
     * 通知地址
     *
     * @param value
     */
    public void setNotifyUrl(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.notifyUrl = value;
    }

    /**
     * 加密方式,MD5和HMAC-SHA256
     */
    @WhereSQL(sql = "signType=:WxPayconfig_signType")
    public java.lang.String getSignType() {
        return this.signType;
    }

    /**
     * 加密方式,MD5和HMAC-SHA256
     *
     * @param value
     */
    public void setSignType(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.signType = value;
    }

    /**
     * 状态 0不可用,1可用
     */
    @WhereSQL(sql = "active=:WxPayconfig_active")
    public java.lang.Integer getActive() {
        return this.active;
    }

    /**
     * 状态 0不可用,1可用
     *
     * @param value
     */
    public void setActive(java.lang.Integer value) {
        this.active = value;
    }

    /**
     * bak1
     */
    @WhereSQL(sql = "bak1=:WxPayconfig_bak1")
    public java.lang.String getBak1() {
        return this.bak1;
    }

    /**
     * bak1
     *
     * @param value
     */
    public void setBak1(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak1 = value;
    }

    /**
     * bak2
     */
    @WhereSQL(sql = "bak2=:WxPayconfig_bak2")
    public java.lang.String getBak2() {
        return this.bak2;
    }

    /**
     * bak2
     *
     * @param value
     */
    public void setBak2(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak2 = value;
    }

    /**
     * bak3
     */
    @WhereSQL(sql = "bak3=:WxPayconfig_bak3")
    public java.lang.String getBak3() {
        return this.bak3;
    }

    /**
     * bak3
     *
     * @param value
     */
    public void setBak3(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak3 = value;
    }

    /**
     * bak4
     */
    @WhereSQL(sql = "bak4=:WxPayconfig_bak4")
    public java.lang.String getBak4() {
        return this.bak4;
    }

    /**
     * bak4
     *
     * @param value
     */
    public void setBak4(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak4 = value;
    }

    /**
     * bak5
     */
    @WhereSQL(sql = "bak5=:WxPayconfig_bak5")
    public java.lang.String getBak5() {
        return this.bak5;
    }

    /**
     * bak5
     *
     * @param value
     */
    public void setBak5(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak5 = value;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("id[").append(getId()).append("],")
                .append("站点Id[").append(getOrgId()).append("],")
                .append("开发者Id[").append(getAppId()).append("],")
                .append("应用密钥[").append(getSecret()).append("],")
                .append("微信支付商户号[").append(getMchId()).append("],")
                .append("交易过程生成签名的密钥，仅保留在商户系统和微信支付后台，不会在网络中传播[").append(getKey()).append("],")
                .append("证书地址[").append(getCertificateFile()).append("],")
                .append("通知地址[").append(getNotifyUrl()).append("],")
                .append("加密方式,MD5和HMAC-SHA256[").append(getSignType()).append("],")
                .append("状态 0不可用,1可用[").append(getActive()).append("],")
                .append("bak1[").append(getBak1()).append("],")
                .append("bak2[").append(getBak2()).append("],")
                .append("bak3[").append(getBak3()).append("],")
                .append("bak4[").append(getBak4()).append("],")
                .append("bak5[").append(getBak5()).append("],")
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (obj instanceof WxPayConfig == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        WxPayConfig other = (WxPayConfig) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
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
}
