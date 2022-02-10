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
 * @version 2019-07-26 13:02:32
 */
@Table(name = "t_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //alias
	/*
	public static final String TABLE_ALIAS = "角色";
	public static final String ALIAS_ID = "角色ID";
	public static final String ALIAS_NAME = "角色名称";
	public static final String ALIAS_CODE = "权限编码";
	public static final String ALIAS_PID = "上级角色ID,暂时不实现";
	public static final String ALIAS_PRIVATEORG = "角色的部门是否私有,0否,1是,默认0.当角色私有时,菜单只使用此角色的部门权限,不再扩散到全局角色权限,用于设置特殊的菜单权限.公共权限时部门主管有所管理部门的数据全权限,无论角色是否分配. 私有部门权限时,严格按照配置的数据执行,部门主管可能没有部门权限.";
	public static final String ALIAS_ROLEORGTYPE = "0自己的数据,1所在部门,2所在部门及子部门数据,3.自定义部门数据.";
	public static final String ALIAS_ORGID = "角色的归属部门,只有归属部门的主管和上级主管才可以管理角色,其他人员只能增加归属到角色的人员.不能选择部门或则其他操作,只能添加人员,不然存在提权风险,例如 员工角色下有1000人, 如果给 角色 设置了部门,那这1000人都起效了.";
	public static final String ALIAS_SHAREROLE = "角色是否共享,0否 1是,默认0,共享的角色可以被下级部门直接使用,但是下级只能添加人员,不能设置其他属性.共享的角色一般只设置roleOrgType,并不设定部门.";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_CREATEUSERID = "createUserId";
	public static final String ALIAS_UPDATETIME = "updateTime";
	public static final String ALIAS_UPDATEUSERID = "updateUserId";
	public static final String ALIAS_SORTNO = "排序,查询时倒叙排列";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_ACTIVE = "是否有效(0否,1是)";
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
     * 角色ID
     *
     * @required
     */
    private String id;
    /**
     * 角色名称
     *
     * @required
     */
    private String name;
    /**
     * 权限编码
     *
     * @required
     */
    private String code;
    /**
     * 上级角色ID,暂时不实现
     */
    private String pid;
    /**
     * 角色的部门是否私有,0否,1是,默认0.当角色私有时,菜单只使用此角色的部门权限,不再扩散到全局角色权限,用于设置特殊的菜单权限.公共权限时部门主管有所管理部门的数据全权限,无论角色是否分配. 私有部门权限时,严格按照配置的数据执行,部门主管可能没有部门权限.
     */
    private Integer privateOrg;
    /**
     * 0自己的数据,1所在部门,2所在部门及子部门数据,3.自定义部门数据,4.全部数据权限
     */
    private Integer roleOrgType;
    /**
     * 角色的归属部门,只有归属部门的主管和上级主管才可以管理角色,其他人员只能增加归属到角色的人员.不能选择部门或则其他操作,只能添加人员,不然存在提权风险,例如 员工角色下有1000人, 如果给 角色 设置了部门,那这1000人都起效了.
     */
    private String orgId;
    /**
     * 角色是否共享,0否 1是,默认0,共享的角色可以被下级部门直接使用,但是下级只能添加人员,不能设置其他属性.共享的角色一般只设置roleOrgType,并不设定部门.
     */
    private Integer shareRole;
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
     * 排序,查询时倒叙排列
     */
    private Integer sortno;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否有效(0否,1是)
     */
    private Integer active;
    /**
     * 角色登录后默认进入的页面
     */
    private String indexPage;
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

    //concstructor
    public Role() {
    }


    //get and set

    /**
     * 角色ID
     */
    @Id
    @WhereSQL(sql = "id=:Role_id")
    public String getId() {
        return this.id;
    }

    /**
     * 角色ID
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
     * 角色名称
     */
    @WhereSQL(sql = "name=:Role_name")
    public String getName() {
        return this.name;
    }

    /**
     * 角色名称
     *
     * @param value
     */
    public void setName(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.name = value;
    }

    /**
     * 权限编码
     */
    @WhereSQL(sql = "code=:Role_code")
    public String getCode() {
        return this.code;
    }

    /**
     * 权限编码
     *
     * @param value
     */
    public void setCode(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.code = value;
    }

    /**
     * 上级角色ID,暂时不实现
     */
    @WhereSQL(sql = "pid=:Role_pid")
    public String getPid() {
        return this.pid;
    }

    /**
     * 上级角色ID,暂时不实现
     *
     * @param value
     */
    public void setPid(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.pid = value;
    }

    /**
     * 角色的部门是否私有,0否,1是,默认0.当角色私有时,菜单只使用此角色的部门权限,不再扩散到全局角色权限,用于设置特殊的菜单权限.公共权限时部门主管有所管理部门的数据全权限,无论角色是否分配. 私有部门权限时,严格按照配置的数据执行,部门主管可能没有部门权限.
     */
    @WhereSQL(sql = "privateOrg=:Role_privateOrg")
    public Integer getPrivateOrg() {
        return this.privateOrg;
    }

    /**
     * 角色的部门是否私有,0否,1是,默认0.当角色私有时,菜单只使用此角色的部门权限,不再扩散到全局角色权限,用于设置特殊的菜单权限.公共权限时部门主管有所管理部门的数据全权限,无论角色是否分配. 私有部门权限时,严格按照配置的数据执行,部门主管可能没有部门权限.
     *
     * @param value
     */
    public void setPrivateOrg(Integer value) {
        this.privateOrg = value;
    }

    /**
     * 0自己的数据,1所在部门,2所在部门及子部门数据,3.自定义部门数据,4.全部数据权限
     */
    @WhereSQL(sql = "roleOrgType=:Role_roleOrgType")
    public Integer getRoleOrgType() {
        return this.roleOrgType;
    }

    /**
     * 0自己的数据,1所在部门,2所在部门及子部门数据,3.自定义部门数据.
     *
     * @param value
     */
    public void setRoleOrgType(Integer value) {
        this.roleOrgType = value;
    }

    /**
     * 角色的归属部门,只有归属部门的主管和上级主管才可以管理角色,其他人员只能增加归属到角色的人员.不能选择部门或则其他操作,只能添加人员,不然存在提权风险,例如 员工角色下有1000人, 如果给 角色 设置了部门,那这1000人都起效了.
     */
    @WhereSQL(sql = "orgId=:Role_orgId")
    public String getOrgId() {
        return this.orgId;
    }

    /**
     * 角色的归属部门,只有归属部门的主管和上级主管才可以管理角色,其他人员只能增加归属到角色的人员.不能选择部门或则其他操作,只能添加人员,不然存在提权风险,例如 员工角色下有1000人, 如果给 角色 设置了部门,那这1000人都起效了.
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
     * 角色是否共享,0否 1是,默认0,共享的角色可以被下级部门直接使用,但是下级只能添加人员,不能设置其他属性.共享的角色一般只设置roleOrgType,并不设定部门.
     */
    @WhereSQL(sql = "shareRole=:Role_shareRole")
    public Integer getShareRole() {
        return this.shareRole;
    }

    /**
     * 角色是否共享,0否 1是,默认0,共享的角色可以被下级部门直接使用,但是下级只能添加人员,不能设置其他属性.共享的角色一般只设置roleOrgType,并不设定部门.
     *
     * @param value
     */
    public void setShareRole(Integer value) {
        this.shareRole = value;
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
    @WhereSQL(sql = "createTime=:Role_createTime")
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
    @WhereSQL(sql = "createUserId=:Role_createUserId")
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
    @WhereSQL(sql = "updateTime=:Role_updateTime")
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
    @WhereSQL(sql = "updateUserId=:Role_updateUserId")
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
     * 排序,查询时倒叙排列
     */
    @WhereSQL(sql = "sortno=:Role_sortno")
    public Integer getSortno() {
        return this.sortno;
    }

    /**
     * 排序,查询时倒叙排列
     *
     * @param value
     */
    public void setSortno(Integer value) {
        this.sortno = value;
    }

    /**
     * 备注
     */
    @WhereSQL(sql = "remark=:Role_remark")
    public String getRemark() {
        return this.remark;
    }

    /**
     * 备注
     *
     * @param value
     */
    public void setRemark(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.remark = value;
    }

    /**
     * 是否有效(0否,1是)
     */
    @WhereSQL(sql = "active=:Role_active")
    public Integer getActive() {
        return this.active;
    }

    /**
     * 是否有效(0否,1是)
     *
     * @param value
     */
    public void setActive(Integer value) {
        this.active = value;
    }

    @WhereSQL(sql = "indexPage=:Role_indexPage")
    public String getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(String indexPage) {
        this.indexPage = indexPage;
    }

    /**
     * bak1
     */
    @WhereSQL(sql = "bak1=:Role_bak1")
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
    @WhereSQL(sql = "bak2=:Role_bak2")
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
    @WhereSQL(sql = "bak3=:Role_bak3")
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
    @WhereSQL(sql = "bak4=:Role_bak4")
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
    @WhereSQL(sql = "bak5=:Role_bak5")
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
                .append("角色ID[").append(getId()).append("],")
                .append("角色名称[").append(getName()).append("],")
                .append("权限编码[").append(getCode()).append("],")
                .append("上级角色ID,暂时不实现[").append(getPid()).append("],")
                .append("角色的部门是否私有,0否,1是,默认0.当角色私有时,菜单只使用此角色的部门权限,不再扩散到全局角色权限,用于设置特殊的菜单权限.公共权限时部门主管有所管理部门的数据全权限,无论角色是否分配. 私有部门权限时,严格按照配置的数据执行,部门主管可能没有部门权限.[").append(getPrivateOrg()).append("],")
                .append("0自己的数据,1所在部门,2所在部门及子部门数据,3.自定义部门数据.[").append(getRoleOrgType()).append("],")
                .append("角色的归属部门,只有归属部门的主管和上级主管才可以管理角色,其他人员只能增加归属到角色的人员.不能选择部门或则其他操作,只能添加人员,不然存在提权风险,例如 员工角色下有1000人, 如果给 角色 设置了部门,那这1000人都起效了.[").append(getOrgId()).append("],")
                .append("角色是否共享,0否 1是,默认0,共享的角色可以被下级部门直接使用,但是下级只能添加人员,不能设置其他属性.共享的角色一般只设置roleOrgType,并不设定部门.[").append(getShareRole()).append("],")
                .append("createTime[").append(getCreateTime()).append("],")
                .append("createUserId[").append(getCreateUserId()).append("],")
                .append("updateTime[").append(getUpdateTime()).append("],")
                .append("updateUserId[").append(getUpdateUserId()).append("],")
                .append("排序,查询时倒叙排列[").append(getSortno()).append("],")
                .append("备注[").append(getRemark()).append("],")
                .append("是否有效(0否,1是)[").append(getActive()).append("],")
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

        if (obj instanceof Role == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Role other = (Role) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

	
