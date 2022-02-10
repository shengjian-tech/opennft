package net.shengjian.weixin.entity;

import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMiniappConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "wx_miniappconfig")
public class WxMiniappConfig extends BaseEntity implements IWxMiniappConfig {

    private static final long serialVersionUID = 1L;

    //alias
	/*
	public static final String TABLE_ALIAS = "小程序配置表";
	public static final String ALIAS_ID = "主键id";
	public static final String ALIAS_ORGID = "站点Id";
	public static final String ALIAS_APPID = "开发者Id";
	public static final String ALIAS_SECRET = "应用密钥";
	public static final String ALIAS_PLANID = "签约模板Id";
	public static final String ALIAS_REQUESTSERIAL = "签约请求序列号";
	public static final String ALIAS_ACTIVE = "状态 0不可用,1可用";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
	public static final String ALIAS_BAK4 = "bak4";
	public static final String ALIAS_BAK5 = "bak5";
    */
    //date formats

    //columns START

    // 主键id
    private java.lang.String id;

    // 站点Id
    private java.lang.String orgId;

    // 开发者Id
    private java.lang.String appId;

    // 应用密钥
    private java.lang.String secret;

    // 签约模板Id
    private java.lang.String planId;

    // 签约请求序列号
    private java.lang.String requestSerial;

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
    public WxMiniappConfig() {
    }


    //get and set

    /**
     * 主键id
     */
    @Id
    @WhereSQL(sql = "id=:WxMiniappconfig_id")
    public java.lang.String getId() {
        return this.id;
    }

    /**
     * 主键id
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
    @WhereSQL(sql = "orgId=:WxMiniappconfig_orgId")
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
    @WhereSQL(sql = "appId=:WxMiniappconfig_appId")
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
    @WhereSQL(sql = "secret=:WxMiniappconfig_secret")
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
     * 签约模板Id
     */
    @WhereSQL(sql = "planId=:WxMiniappconfig_planId")
    public java.lang.String getPlanId() {
        return this.planId;
    }

    /**
     * 签约模板Id
     *
     * @param value
     */
    public void setPlanId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.planId = value;
    }

    /**
     * 签约请求序列号
     */
    @WhereSQL(sql = "requestSerial=:WxMiniappconfig_requestSerial")
    public java.lang.String getRequestSerial() {
        return this.requestSerial;
    }

    /**
     * 签约请求序列号
     *
     * @param value
     */
    public void setRequestSerial(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.requestSerial = value;
    }

    /**
     * 状态 0不可用,1可用
     */
    @WhereSQL(sql = "active=:WxMiniappconfig_active")
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
    @WhereSQL(sql = "bak1=:WxMiniappconfig_bak1")
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
    @WhereSQL(sql = "bak2=:WxMiniappconfig_bak2")
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
    @WhereSQL(sql = "bak3=:WxMiniappconfig_bak3")
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
    @WhereSQL(sql = "bak4=:WxMiniappconfig_bak4")
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
    @WhereSQL(sql = "bak5=:WxMiniappconfig_bak5")
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
                .append("主键id[").append(getId()).append("],")
                .append("站点Id[").append(getOrgId()).append("],")
                .append("开发者Id[").append(getAppId()).append("],")
                .append("应用密钥[").append(getSecret()).append("],")
                .append("签约模板Id[").append(getPlanId()).append("],")
                .append("签约请求序列号[").append(getRequestSerial()).append("],")
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

        if (obj instanceof WxMiniappConfig == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        WxMiniappConfig other = (WxMiniappConfig) obj;
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
