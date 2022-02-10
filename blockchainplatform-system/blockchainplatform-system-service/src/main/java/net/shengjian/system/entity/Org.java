package net.shengjian.system.entity;

import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;


/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2019-07-24 14:20:37
 */
@Table(name = "t_org")
public class Org extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //alias
	/*
	public static final String TABLE_ALIAS = "部门";
	public static final String ALIAS_ID = "编号";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_COMCODE = "代码";
	public static final String ALIAS_PID = "上级部门ID";
	public static final String ALIAS_ORGTYPE = "0-99门店,100-199部门,200-299,分公司,300-399集团公司,900-999总平台";
	public static final String ALIAS_SORTNO = "排序,查询时倒叙排列";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_CREATEUSERID = "createUserId";
	public static final String ALIAS_UPDATETIME = "updateTime";
	public static final String ALIAS_UPDATEUSERID = "updateUserId";
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
     * 子部门
     */
    List<Org> children;
    /**
     * 编号
     */
    private String id;
    /**
     * 名称
     *
     * @required
     */
    private String name;
    /**
     * 代码
     */
    private String comcode;
    /**
     * 上级部门ID
     *
     * @required
     */
    private String pid;
    /**
     * 0-99门店,100-199部门,200-299,分公司,300-399集团公司,900-999总平台
     *
     * @required
     */
    private Integer orgType;
    /**
     * 排序,查询时倒叙排列
     *
     * @required
     */
    private Integer sortno;
    /**
     * 备注
     */
    private String remark;
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
     * 是否有效(0否,1是)
     *
     * @required
     */
    private Integer active;
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
     * 部门类型名字
     */
    private String orgTypeName;

    @Transient
    public String getOrgTypeName() {
        return orgTypeName;
    }

    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

    /**
     * 用户对部门的管理类型(在部门中的地位-0会员,1员工,2主管)
     *
     * @required
     */
    private Integer managerType;

    @Transient
    public Integer getManagerType() {
        return managerType;
    }

    public void setManagerType(Integer managerType) {
        this.managerType = managerType;
    }

    private String[] comcodeList;

    @Transient
    public String[] getComcodeList() {
        if (StringUtils.isBlank(this.comcode)) {
            this.comcode = ",";
        }
        this.comcode = this.comcode.substring(1);
        return this.comcode.split(",");
    }

    public void setComcodeList(String[] comcodeList) {
        this.comcodeList = comcodeList;
    }

    //constructor
    public Org() {
    }


    //get and set

    /**
     * 编号
     */
    @Id
    @WhereSQL(sql = "id=:Org_id")
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
     * 名称
     */
    @WhereSQL(sql = "name like :%Org_name%")
    public String getName() {
        return this.name;
    }

    /**
     * 名称
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
     * 代码
     */
    @WhereSQL(sql = "comcode like :%Org_comcode%")
    public String getComcode() {
        return this.comcode;
    }

    /**
     * 代码
     *
     * @param value
     */
    public void setComcode(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.comcode = value;
    }

    /**
     * 上级部门ID
     */
    @WhereSQL(sql = "pid=:Org_pid")
    public String getPid() {
        return this.pid;
    }

    /**
     * 上级部门ID
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
     * 0-99门店,100-199部门,200-299,分公司,300-399集团公司,900-999总平台
     */
    @WhereSQL(sql = "orgType=:Org_orgType")
    public Integer getOrgType() {
        return this.orgType;
    }

    /**
     * 0-99门店,100-199部门,200-299,分公司,300-399集团公司,900-999总平台
     *
     * @param value
     */
    public void setOrgType(Integer value) {
        this.orgType = value;
    }

    /**
     * 排序,查询时倒叙排列
     */
    @WhereSQL(sql = "sortno=:Org_sortno")
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
    @WhereSQL(sql = "remark=:Org_remark")
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
    @WhereSQL(sql = "createTime=:Org_createTime")
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
    @WhereSQL(sql = "createUserId=:Org_createUserId")
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
    @WhereSQL(sql = "updateTime=:Org_updateTime")
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
    @WhereSQL(sql = "updateUserId=:Org_updateUserId")
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
     * 是否有效(0否,1是)
     */
    @WhereSQL(sql = "active=:Org_active")
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

    /**
     * bak1
     */
    @WhereSQL(sql = "bak1=:Org_bak1")
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
    @WhereSQL(sql = "bak2=:Org_bak2")
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
    @WhereSQL(sql = "bak3=:Org_bak3")
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
    @WhereSQL(sql = "bak4=:Org_bak4")
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
    @WhereSQL(sql = "bak5=:Org_bak5")
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
                .append("名称[").append(getName()).append("],")
                .append("代码[").append(getComcode()).append("],")
                .append("上级部门ID[").append(getPid()).append("],")
                .append("0-99门店,100-199部门,200-299,分公司,300-399集团公司,900-999总平台[").append(getOrgType()).append("],")
                .append("排序,查询时倒叙排列[").append(getSortno()).append("],")
                .append("备注[").append(getRemark()).append("],")
                .append("createTime[").append(getCreateTime()).append("],")
                .append("createUserId[").append(getCreateUserId()).append("],")
                .append("updateTime[").append(getUpdateTime()).append("],")
                .append("updateUserId[").append(getUpdateUserId()).append("],")
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

        if (obj instanceof Org == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Org other = (Org) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }


    @Transient
    public List<Org> getChildren() {
        return children;
    }

    public void setChildren(List<Org> children) {
        this.children = children;
    }


}

	
