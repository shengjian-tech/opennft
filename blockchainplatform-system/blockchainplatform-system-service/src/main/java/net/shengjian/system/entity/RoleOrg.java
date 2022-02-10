package net.shengjian.system.entity;

import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 角色部门
 *
 * @author springrain<Auto generate>
 * @version 2019-07-26 10:01:22
 */
@Table(name = "t_role_org")
public class RoleOrg extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //alias
	/*
	public static final String TABLE_ALIAS = "角色部门中间表";
	public static final String ALIAS_ID = "编号";
	public static final String ALIAS_ORGID = "部门编号";
	public static final String ALIAS_ROLEID = "角色编号";
	public static final String ALIAS_CHILDREN = "0不包含子部门,1包含.用于表示角色和部门的权限关系";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_CREATEUSERID = "createUserId";
	public static final String ALIAS_UPDATETIME = "updateTime";
	public static final String ALIAS_UPDATEUSERID = "updateUserId";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
	public static final String ALIAS_BAK4 = "bak4";
	public static final String ALIAS_BAK5 = "bak5";
    */
    //date formats
    //public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
    //public static final String FORMAT_UPDATETIME = DateUtils.DATETIME_FORMAT;

    //columns START

    /**
     * 编号
     */
    private String id;
    /**
     * 部门编号
     *
     * @required
     */
    private String orgId;
    /**
     * 角色编号
     */
    private String roleId;
    /**
     * 0不包含子部门,1包含.用于表示角色和部门的权限关系.用于记录roleOrgType的结果,缓存值
     */
    private Integer children;
    /**
     * 创建时间
     */
    private java.util.Date createTime;
    /**
     * 创建者
     */
    private String createUserId;
    /**
     * 更新时间
     */
    private java.util.Date updateTime;
    /**
     * 更新者
     */
    private String updateUserId;
    /**
     * bak1
     */
    private String bak1;
    /**
     * bak2
     */
    private String bak2;
    /**
     * bak3
     */
    private String bak3;
    /**
     * bak4
     */
    private String bak4;
    /**
     * bak5
     */
    private String bak5;
    //columns END 数据库字段结束

    /**
     * 是否选中,未选中就是删除
     *
     * @required
     */
    private Boolean check;

    /**
     * isChildren
     */
    private Integer isChildren;

    //concstructor
    public RoleOrg() {
    }


    //get and set


    @Transient
    public Integer getIsChildren() {
        isChildren = this.children;
        return isChildren;
    }

    public void setIsChildren(Integer isChildren) {
        this.isChildren = isChildren;
        this.children = isChildren;
    }

    /**
     * 编号
     */
    @Id
    @WhereSQL(sql = "id=:RoleOrg_id")
    public String getId() {
        return this.id;
    }

    /**
     * 编号
     *
     * @param value
     */
    public void setId(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.id = value;
    }

    /**
     * 部门编号
     */
    @WhereSQL(sql = "orgId=:RoleOrg_orgId")
    public String getOrgId() {
        return this.orgId;
    }

    /**
     * 部门编号
     *
     * @param value
     */
    public void setOrgId(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.orgId = value;
    }

    /**
     * 角色编号
     */
    @WhereSQL(sql = "roleId=:RoleOrg_roleId")
    public String getRoleId() {
        return this.roleId;
    }

    /**
     * 角色编号
     *
     * @param value
     */
    public void setRoleId(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.roleId = value;
    }

    /**
     * 0不包含子部门,1包含.用于表示角色和部门的权限关系
     */
    @WhereSQL(sql = "children=:RoleOrg_children")
    public Integer getChildren() {
        return this.children;
    }

    /**
     * 0不包含子部门,1包含.用于表示角色和部门的权限关系
     *
     * @param value
     */
    public void setChildren(Integer value) {
        this.children = value;
        this.isChildren = value;
    }
		/*
	public String getcreateTimeString() {
		return DateUtils.convertDate2String(FORMAT_CREATETIME, getcreateTime());
	}
	public void setcreateTimeString(String value) throws ParseException{
		setcreateTime(DateUtils.convertString2Date(FORMAT_CREATETIME,value));
	}*/

    /**
     * createTime
     */
    @WhereSQL(sql = "createTime=:RoleOrg_createTime")
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * createTime
     *
     * @param value
     */
    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }

    /**
     * createUserId
     */
    @WhereSQL(sql = "createUserId=:RoleOrg_createUserId")
    public String getCreateUserId() {
        return this.createUserId;
    }

    /**
     * createUserId
     *
     * @param value
     */
    public void setCreateUserId(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.createUserId = value;
    }
		/*
	public String getupdateTimeString() {
		return DateUtils.convertDate2String(FORMAT_UPDATETIME, getupdateTime());
	}
	public void setupdateTimeString(String value) throws ParseException{
		setupdateTime(DateUtils.convertString2Date(FORMAT_UPDATETIME,value));
	}*/

    /**
     * updateTime
     */
    @WhereSQL(sql = "updateTime=:RoleOrg_updateTime")
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * updateTime
     *
     * @param value
     */
    public void setUpdateTime(java.util.Date value) {
        this.updateTime = value;
    }

    /**
     * updateUserId
     */
    @WhereSQL(sql = "updateUserId=:RoleOrg_updateUserId")
    public String getUpdateUserId() {
        return this.updateUserId;
    }

    /**
     * updateUserId
     *
     * @param value
     */
    public void setUpdateUserId(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.updateUserId = value;
    }

    /**
     * bak1
     */
    @WhereSQL(sql = "bak1=:RoleOrg_bak1")
    public String getBak1() {
        return this.bak1;
    }

    /**
     * bak1
     *
     * @param value
     */
    public void setBak1(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak1 = value;
    }

    /**
     * bak2
     */
    @WhereSQL(sql = "bak2=:RoleOrg_bak2")
    public String getBak2() {
        return this.bak2;
    }

    /**
     * bak2
     *
     * @param value
     */
    public void setBak2(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak2 = value;
    }

    /**
     * bak3
     */
    @WhereSQL(sql = "bak3=:RoleOrg_bak3")
    public String getBak3() {
        return this.bak3;
    }

    /**
     * bak3
     *
     * @param value
     */
    public void setBak3(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak3 = value;
    }

    /**
     * bak4
     */
    @WhereSQL(sql = "bak4=:RoleOrg_bak4")
    public String getBak4() {
        return this.bak4;
    }

    /**
     * bak4
     *
     * @param value
     */
    public void setBak4(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak4 = value;
    }

    /**
     * bak5
     */
    @WhereSQL(sql = "bak5=:RoleOrg_bak5")
    public String getBak5() {
        return this.bak5;
    }

    /**
     * bak5
     *
     * @param value
     */
    public void setBak5(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak5 = value;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("编号[").append(getId()).append("],")
                .append("部门编号[").append(getOrgId()).append("],")
                .append("角色编号[").append(getRoleId()).append("],")
                .append("0不包含子部门,1包含.用于表示角色和部门的权限关系[").append(getChildren()).append("],")
                .append("createTime[").append(getCreateTime()).append("],")
                .append("createUserId[").append(getCreateUserId()).append("],")
                .append("updateTime[").append(getUpdateTime()).append("],")
                .append("updateUserId[").append(getUpdateUserId()).append("],")
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

        if (obj instanceof RoleOrg == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        RoleOrg other = (RoleOrg) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }


    @Transient
    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

}

	
