package net.shengjian.weixin.entity;

import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "wx_mpconfig")
public class WxMpConfig extends BaseEntity implements IWxMpConfig {

    private static final long serialVersionUID = 1L;

    //alias
	/*
	public static final String TABLE_ALIAS = "微信号需要的配置信息";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ORGID = "站点Id";
	public static final String ALIAS_APPID = "开发者Id";
	public static final String ALIAS_SECRET = "应用密钥";
	public static final String ALIAS_TOKEN = "开发者令牌";
	public static final String ALIAS_AESKEY = "消息加解密密钥";
	public static final String ALIAS_WXORIGINALID = "微信原始ID";
	public static final String ALIAS_OAUTH2 = "是否支持微信oauth2.0协议,0是不支持,1是支持";
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

    // 开发者令牌
    private java.lang.String token;

    // 消息加解密密钥
    private java.lang.String aesKey;

    // 微信原始ID
    private java.lang.String wxOriginalId;

    // 是否支持微信oauth2.0协议,0是不支持,1是支持
    private java.lang.Integer oauth2;

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

    // private String jsApiTicket;
    // private Long jsApiTicketExpiresTime = 0L;

    // private String cardApiTicket;
    // private Long cardApiTicketExpiresTime = 0L;


    //concstructor

    public WxMpConfig() {
    }

    public WxMpConfig(
            java.lang.String id
    ) {
        this.id = id;
    }

    @Override
    @Id
    @WhereSQL(sql = "id=:WxMpconfig_id")
    public java.lang.String getId() {
        return this.id;
    }

    //getErrorMsgByCode and set
    @Override
    public void setId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.id = value;
    }


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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
    public String getSecret() {
        return secret;
    }

    @Override
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getAesKey() {
        return aesKey;
    }

    @Override
    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getWxOriginalId() {
        return wxOriginalId;
    }

    public void setWxOriginalId(String wxOriginalId) {
        this.wxOriginalId = wxOriginalId;
    }

    @Override
    public Integer getOauth2() {
        return oauth2;
    }

    @Override
    public void setOauth2(Integer oauth2) {
        this.oauth2 = oauth2;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getBak1() {
        return bak1;
    }

    public void setBak1(String bak1) {
        this.bak1 = bak1;
    }

    public String getBak2() {
        return bak2;
    }

    public void setBak2(String bak2) {
        this.bak2 = bak2;
    }

    public String getBak3() {
        return bak3;
    }

    public void setBak3(String bak3) {
        this.bak3 = bak3;
    }

    public String getBak4() {
        return bak4;
    }

    public void setBak4(String bak4) {
        this.bak4 = bak4;
    }

    public String getBak5() {
        return bak5;
    }

    public void setBak5(String bak5) {
        this.bak5 = bak5;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("id[").append(getId()).append("],")
                .append("站点Id[").append(getOrgId()).append("],")
                .append("开发者Id[").append(getAppId()).append("],")
                .append("应用密钥[").append(getSecret()).append("],")
                .append("开发者令牌[").append(getToken()).append("],")
                .append("消息加解密密钥[").append(getAesKey()).append("],")
                .append("微信原始ID[").append(getWxOriginalId()).append("],")
                .append("是否支持微信oauth2.0协议,0是不支持,1是支持[").append(getOauth2()).append("],")
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
        if (obj instanceof WxMpConfig == false) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        WxMpConfig other = (WxMpConfig) obj;
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
