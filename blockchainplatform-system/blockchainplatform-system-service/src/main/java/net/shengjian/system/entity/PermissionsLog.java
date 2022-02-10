package net.shengjian.system.entity;

import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 权限变更日志
 *
 * @author springrain<Auto generate>
 * @version 2017-12-15 12:13:15
 * @copyright {@link jiagou.com}
 * @see PermissionsLog
 */
@Table(name = "t_permissions_log")
public class PermissionsLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    // alias
    /*
     * public static final String TABLE_ALIAS = "权限变更日志"; public static final String
     * ALIAS_ID = "主键"; public static final String ALIAS_SITEID = "站点ID"; public
     * static final String ALIAS_ACTIONTYPE = "操作类型 创建、编辑、删除、启用、禁用"; public static
     * final String ALIAS_OPERATORUSERID = "操作人ID"; public static final String
     * ALIAS_OPERATORUSERNAME = "操作人当时名称"; public static final String
     * ALIAS_OPERATORUSERROLES = "操作人当时所属角色名称，逗号分割"; public static final String
     * ALIAS_OPERATOROBJECTID = "操作对象ID"; public static final String
     * ALIAS_OPERATOROBJECTNAME = "操作对象当时的名称"; public static final String
     * ALIAS_ACTIONCONTENT = "操作内容详情"; public static final String ALIAS_CREATEUSERID
     * = "记录创建人"; public static final String ALIAS_CREATETIME = "记录创建时间"; public
     * static final String ALIAS_BAK1 = "备用字段"; public static final String
     * ALIAS_BAK2 = "备用字段"; public static final String ALIAS_BAK3 = "备用字段"; public
     * static final String ALIAS_BAK4 = "备用字段"; public static final String
     * ALIAS_BAK5 = "备用字段";
     */
    // date formats
    // public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;

    // columns START
    /**
     * 主键
     */
    private java.lang.String id;
    /**
     * 站点ID
     */
    private java.lang.String siteId;
    /**
     * 操作类型 创建、编辑、删除、启用、禁用
     */
    private java.lang.Integer actionType;
    /**
     * 操作人ID
     */
    private java.lang.String operatorUserId;
    /**
     * 操作人当时名称
     */
    private java.lang.String operatorUserName;
    /**
     * 操作人当时所属角色名称，逗号分割
     */
    private java.lang.String operatorUserRoles;

    /**
     * 操作对象类型
     */
    private java.lang.Integer operatorObjectType;

    /**
     * 操作对象ID
     */
    private java.lang.String operatorObjectId;
    /**
     * 操作对象当时的名称
     */
    private java.lang.String operatorObjectName;
    /**
     * 操作内容详情
     */
    private java.lang.String actionContent;
    /**
     * 记录创建人
     */
    private java.lang.String createUserId;
    /**
     * 记录创建时间
     */
    private java.util.Date createTime;
    /**
     * 备用字段
     */
    private java.lang.String bak1;
    /**
     * 备用字段
     */
    private java.lang.String bak2;
    /**
     * 备用字段
     */
    private java.lang.String bak3;
    /**
     * 备用字段
     */
    private java.lang.String bak4;
    /**
     * 备用字段
     */
    private java.lang.String bak5;
    // columns END 数据库字段结束

    // concstructor

    public PermissionsLog() {
    }

    public PermissionsLog(java.lang.String id) {
        this.id = id;
    }

    public PermissionsLog(String siteId, Integer actionType, String operatorUserId, String operatorUserName,
                          String operatorUserRoles, Integer operatorObjectType, String operatorObjectId, String operatorObjectName,
                          String actionContent, String createUserId, Date createTime) {
        super();
        this.siteId = siteId;
        this.actionType = actionType;
        this.operatorUserId = operatorUserId;
        this.operatorUserName = operatorUserName;
        this.operatorUserRoles = operatorUserRoles;
        this.operatorObjectType = operatorObjectType;
        this.operatorObjectId = operatorObjectId;
        this.operatorObjectName = operatorObjectName;
        this.actionContent = actionContent;
        this.createUserId = createUserId;
        this.createTime = createTime;
    }

    public PermissionsLog(String siteId, Integer actionType, Integer operatorObjectType, String operatorObjectId,
                          String operatorObjectName, String actionContent) {
        super();
        this.siteId = siteId;
        this.actionType = actionType;
        this.operatorObjectType = operatorObjectType;
        this.operatorObjectId = operatorObjectId;
        this.operatorObjectName = operatorObjectName;
        this.actionContent = actionContent;
    }

    // get and set

    /**
     * 主键
     */
    @Id
    @WhereSQL(sql = "id=:PermissionsLog_id")
    public java.lang.String getId() {
        return this.id;
    }

    /**
     * 主键
     */
    public void setId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.id = value;
    }

    /**
     * 站点ID
     */
    @WhereSQL(sql = "siteId=:PermissionsLog_siteId")
    public java.lang.String getSiteId() {
        return this.siteId;
    }

    /**
     * 站点ID
     */
    public void setSiteId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.siteId = value;
    }

    /**
     * 操作类型 创建、编辑、删除、启用、禁用
     */
    @WhereSQL(sql = "actionType=:PermissionsLog_actionType")
    public java.lang.Integer getActionType() {
        return this.actionType;
    }

    /**
     * 操作类型 创建、编辑、删除、启用、禁用
     */
    public void setActionType(java.lang.Integer value) {
        this.actionType = value;
    }

    /**
     * 操作人ID
     */
    @WhereSQL(sql = "operatorUserId=:PermissionsLog_operatorUserId")
    public java.lang.String getOperatorUserId() {
        return this.operatorUserId;
    }

    /**
     * 操作人ID
     */
    public void setOperatorUserId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.operatorUserId = value;
    }

    /**
     * 操作人当时名称
     */
    @WhereSQL(sql = "operatorUserName=:PermissionsLog_operatorUserName")
    public java.lang.String getOperatorUserName() {
        return this.operatorUserName;
    }

    /**
     * 操作人当时名称
     */
    public void setOperatorUserName(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.operatorUserName = value;
    }

    /**
     * 操作人当时所属角色名称，逗号分割
     */
    @WhereSQL(sql = "operatorUserRoles=:PermissionsLog_operatorUserRoles")
    public java.lang.String getOperatorUserRoles() {
        return this.operatorUserRoles;
    }

    /**
     * 操作人当时所属角色名称，逗号分割
     */
    public void setOperatorUserRoles(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.operatorUserRoles = value;
    }

    /**
     * 操作对象类型
     *
     * @return
     */
    public java.lang.Integer getOperatorObjectType() {
        return operatorObjectType;
    }

    public void setOperatorObjectType(java.lang.Integer operatorObjectType) {
        this.operatorObjectType = operatorObjectType;
    }

    /**
     * 操作对象类型
     */
    @WhereSQL(sql = "operatorObjectId=:PermissionsLog_operatorObjectId")
    public java.lang.String getOperatorObjectId() {
        return this.operatorObjectId;
    }

    /**
     * 操作对象类型
     */
    public void setOperatorObjectId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.operatorObjectId = value;
    }

    /**
     * 操作对象当时的名称
     */
    @WhereSQL(sql = "operatorObjectName=:PermissionsLog_operatorObjectName")
    public java.lang.String getOperatorObjectName() {
        return this.operatorObjectName;
    }

    /**
     * 操作对象当时的名称
     */
    public void setOperatorObjectName(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.operatorObjectName = value;
    }

    /**
     * 操作内容详情
     */
    @WhereSQL(sql = "actionContent=:PermissionsLog_actionContent")
    public java.lang.String getActionContent() {
        return this.actionContent;
    }

    /**
     * 操作内容详情
     */
    public void setActionContent(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.actionContent = value;
    }

    /**
     * 记录创建人
     */
    @WhereSQL(sql = "createUserId=:PermissionsLog_createUserId")
    public java.lang.String getCreateUserId() {
        return this.createUserId;
    }

    /**
     * 记录创建人
     */
    public void setCreateUserId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.createUserId = value;
    }
    /*
     * public String getcreateTimeString() { return
     * DateUtils.convertDate2String(FORMAT_CREATETIME, getcreateTime()); } public
     * void setcreateTimeString(String value) throws ParseException{
     * setcreateTime(DateUtils.convertString2Date(FORMAT_CREATETIME,value)); }
     */

    /**
     * 记录创建时间
     */
    @WhereSQL(sql = "createTime=:PermissionsLog_createTime")
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 记录创建时间
     */
    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }

    /**
     * 备用字段
     */
    @WhereSQL(sql = "bak1=:PermissionsLog_bak1")
    public java.lang.String getBak1() {
        return this.bak1;
    }

    /**
     * 备用字段
     */
    public void setBak1(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak1 = value;
    }

    /**
     * 备用字段
     */
    @WhereSQL(sql = "bak2=:PermissionsLog_bak2")
    public java.lang.String getBak2() {
        return this.bak2;
    }

    /**
     * 备用字段
     */
    public void setBak2(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak2 = value;
    }

    /**
     * 备用字段
     */
    @WhereSQL(sql = "bak3=:PermissionsLog_bak3")
    public java.lang.String getBak3() {
        return this.bak3;
    }

    /**
     * 备用字段
     */
    public void setBak3(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak3 = value;
    }

    /**
     * 备用字段
     */
    @WhereSQL(sql = "bak4=:PermissionsLog_bak4")
    public java.lang.String getBak4() {
        return this.bak4;
    }

    /**
     * 备用字段
     */
    public void setBak4(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak4 = value;
    }

    /**
     * 备用字段
     */
    @WhereSQL(sql = "bak5=:PermissionsLog_bak5")
    public java.lang.String getBak5() {
        return this.bak5;
    }

    /**
     * 备用字段
     */
    public void setBak5(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak5 = value;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("主键[").append(getId()).append("],").append("站点ID[").append(getSiteId())
                .append("],").append("操作类型 创建、编辑、删除、启用、禁用[").append(getActionType()).append("],").append("操作人ID[")
                .append(getOperatorUserId()).append("],").append("操作人当时名称[").append(getOperatorUserName()).append("],")
                .append("操作人当时所属角色名称，逗号分割[").append(getOperatorUserRoles()).append("],").append("操作对象类型[")
                .append(getOperatorObjectId()).append("],").append("操作对象当时的名称[").append(getOperatorObjectName())
                .append("],").append("操作内容详情[").append(getActionContent()).append("],").append("记录创建人[")
                .append(getCreateUserId()).append("],").append("记录创建时间[").append(getCreateTime()).append("],")
                .append("备用字段[").append(getBak1()).append("],").append("备用字段[").append(getBak2()).append("],")
                .append("备用字段[").append(getBak3()).append("],").append("备用字段[").append(getBak4()).append("],")
                .append("备用字段[").append(getBak5()).append("],").toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PermissionsLog == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        PermissionsLog other = (PermissionsLog) obj;
        return new EqualsBuilder().append(getId(), other.getId()).isEquals();
    }
}
