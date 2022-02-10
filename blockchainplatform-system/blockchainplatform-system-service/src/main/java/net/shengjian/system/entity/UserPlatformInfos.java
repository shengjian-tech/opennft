package net.shengjian.system.entity;

import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2017-07-31 16:41:34
 * @copyright {@link jiagou.com}
 */
@Table(name = "t_user_platform_infos")
public class UserPlatformInfos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    // alias
    /*
     * public static final String TABLE_ALIAS = "UserPlatformInfos"; public static
     * final String ALIAS_ID = "id"; public static final String ALIAS_OPENID =
     * "公众号openId,企业号userId,小程序openId,APP推送deviceToken"; public static final String
     * ALIAS_DEVICETYPE = "设备/应用类型：1公众号2小程序3企业号4APP消息推送"; public static final String
     * ALIAS_SITEID = "所属站点ID"; public static final String ALIAS_USERID =
     * "t_user表中ID"; public static final String ALIAS_BAK1 = "bak1"; public static
     * final String ALIAS_BAK2 = "bak2"; public static final String ALIAS_BAK3 =
     * "bak3"; public static final String ALIAS_BAK4 = "bak4";
     */
    // date formats

    // columns START
    /**
     * id
     */
    private java.lang.String id;
    /**
     * 公众号openId,企业号userId,小程序openId,APP推送deviceToken
     */
    private java.lang.String openId;
    /**
     * 设备/应用类型：1公众号2小程序3企业号4APP消息推送
     */
    private java.lang.Integer deviceType;
    /**
     * 所属站点ID
     */
    private java.lang.String siteId;
    /**
     * t_user表中ID
     */
    private java.lang.String userId;
    /**
     * bak1
     */
    private java.lang.String bak1;
    /**
     * bak2
     */
    private java.lang.String bak2;
    /**
     * bak3
     */
    private java.lang.String bak3;
    /**
     * bak4
     */
    private java.lang.String bak4;
    // columns END 数据库字段结束

    // concstructor

    public UserPlatformInfos() {
    }

    public UserPlatformInfos(java.lang.String id) {
        this.id = id;
    }

    // get and set

    /**
     * id
     */
    @Id
    @WhereSQL(sql = "id=:UserPlatformInfos_id")
    public java.lang.String getId() {
        return this.id;
    }

    /**
     * id
     */
    public void setId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.id = value;
    }

    /**
     * 公众号openId,企业号userId,小程序openId,APP推送deviceToken
     */
    @WhereSQL(sql = "openId=:UserPlatformInfos_openId")
    public java.lang.String getOpenId() {
        return this.openId;
    }

    /**
     * 公众号openId,企业号userId,小程序openId,APP推送deviceToken
     */
    public void setOpenId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.openId = value;
    }

    /**
     * 设备/应用类型：1公众号2小程序3企业号4APP消息推送
     */
    @WhereSQL(sql = "deviceType=:UserPlatformInfos_deviceType")
    public java.lang.Integer getDeviceType() {
        return this.deviceType;
    }

    /**
     * 设备/应用类型：1公众号2小程序3企业号4APP消息推送
     */
    public void setDeviceType(java.lang.Integer value) {
        this.deviceType = value;
    }

    /**
     * 所属站点ID
     */
    @WhereSQL(sql = "siteId=:UserPlatformInfos_siteId")
    public java.lang.String getSiteId() {
        return this.siteId;
    }

    /**
     * 所属站点ID
     */
    public void setSiteId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.siteId = value;
    }

    /**
     * t_user表中ID
     */
    @WhereSQL(sql = "userId=:UserPlatformInfos_userId")
    public java.lang.String getUserId() {
        return this.userId;
    }

    /**
     * t_user表中ID
     */
    public void setUserId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.userId = value;
    }

    /**
     * bak1
     */
    @WhereSQL(sql = "bak1=:UserPlatformInfos_bak1")
    public java.lang.String getBak1() {
        return this.bak1;
    }

    /**
     * bak1
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
    @WhereSQL(sql = "bak2=:UserPlatformInfos_bak2")
    public java.lang.String getBak2() {
        return this.bak2;
    }

    /**
     * bak2
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
    @WhereSQL(sql = "bak3=:UserPlatformInfos_bak3")
    public java.lang.String getBak3() {
        return this.bak3;
    }

    /**
     * bak3
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
    @WhereSQL(sql = "bak4=:UserPlatformInfos_bak4")
    public java.lang.String getBak4() {
        return this.bak4;
    }

    /**
     * bak4
     */
    public void setBak4(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak4 = value;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("id[").append(getId()).append("],")
                .append("公众号openId,企业号userId,小程序openId,APP推送deviceToken[").append(getOpenId()).append("],")
                .append("设备/应用类型：1公众号2小程序3企业号4APP消息推送[").append(getDeviceType()).append("],").append("所属站点ID[")
                .append(getSiteId()).append("],").append("t_user表中ID[").append(getUserId()).append("],").append("bak1[")
                .append(getBak1()).append("],").append("bak2[").append(getBak2()).append("],").append("bak3[")
                .append(getBak3()).append("],").append("bak4[").append(getBak4()).append("],").toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserPlatformInfos == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        UserPlatformInfos other = (UserPlatformInfos) obj;
        return new EqualsBuilder().append(getId(), other.getId()).isEquals();
    }
}
