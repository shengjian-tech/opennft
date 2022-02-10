package net.shengjian.system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.system.entity.Org;
import net.shengjian.system.entity.Role;
import net.shengjian.system.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @descriptions:
 * @author: YSK
 * @date: 2021/7/23 16:55
 * @version: 1.0
 */
public class UserDTO implements Serializable {
    /**
     * id
     *
     * @required
     */
    private String id;
    /**
     * 姓名
     *
     * @required
     */
    private String userName;
    /**
     * 账号
     *
     * @required
     */
    private String account;
    /**
     * 密码
     *
     * @required
     */
    @JsonIgnore
    private String password;
    /**
     * 性别
     */
    private String sex;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 微信openId
     */
    private String openId;
    /**
     * 微信UnionID
     */
    private String unionID;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 0会员,1员工,2店长收银,9系统管理员
     *
     * @required
     */
    private String userType;
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
     * 昵称
     */
    private String nickName;
    /**
     * 是否有效(0否,1是)
     */
    private Integer active;
    /**
     * 伪删除状态 0删除 1未删除
     */
    private Integer status;
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
    /**
     * 部门id 作为搜索关键字使用
     *
     * @required
     */
    private String deptId;
    /**
     * 用户的所有角色
     */
    private List<Role> roles;
    /**
     * 部门集合
     */
    private List<Org> orgList;

    @Transient
    public List<Org> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<Org> orgList) {
        this.orgList = orgList;
    }

    //columns END 数据库字段结束
    //concstructor
    public UserDTO() {
    }

    @Transient
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String value) {

        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.nickName = value;
    }
    //get and set

    /**
     *
     */
    @Id
    @WhereSQL(sql = "id=:User_id")
    public String getId() {
        return this.id;
    }

    /**
     * @param value
     */
    public void setId(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.id = value;
    }

    /**
     * 姓名
     */
    @WhereSQL(sql = "userName=:User_userName")
    public String getUserName() {
        return this.userName;
    }

    /**
     * 姓名
     *
     * @param value
     */
    public void setUserName(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.userName = value;
    }

    /**
     * 账号
     */
    @WhereSQL(sql = "account=:User_account")
    public String getAccount() {
        return this.account;
    }

    /**
     * 账号
     *
     * @param value
     */
    public void setAccount(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.account = value;
    }

    /**
     * 密码
     */
    @WhereSQL(sql = "password=:User_password")
    public String getPassword() {
        return this.password;
    }

    /**
     * 密码
     *
     * @param value
     */
    public void setPassword(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.password = value;
    }

    /**
     * 性别
     */
    @WhereSQL(sql = "sexMap=:User_sex")
    public String getSex() {
        return this.sex;
    }

    /**
     * 性别
     *
     * @param value
     */
    public void setSex(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.sex = value;
    }

    /**
     * 手机号码
     */
    @WhereSQL(sql = "mobile=:User_mobile")
    public String getMobile() {
        return this.mobile;
    }

    /**
     * 手机号码
     *
     * @param value
     */
    public void setMobile(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.mobile = value;
    }

    /**
     * 邮箱
     */
    @WhereSQL(sql = "email=:User_email")
    public String getEmail() {
        return this.email;
    }

    /**
     * 邮箱
     *
     * @param value
     */
    public void setEmail(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.email = value;
    }

    /**
     * 微信openId
     */
    @WhereSQL(sql = "openId=:User_openId")
    public String getOpenId() {
        return this.openId;
    }

    /**
     * 微信openId
     *
     * @param value
     */
    public void setOpenId(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.openId = value;
    }

    /**
     * 微信UnionID
     */
    @WhereSQL(sql = "unionID=:User_unionID")
    public String getUnionID() {
        return this.unionID;
    }

    /**
     * 微信UnionID
     *
     * @param value
     */
    public void setUnionID(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.unionID = value;
    }

    /**
     * 头像地址
     */
    @WhereSQL(sql = "avatar=:User_avatar")
    public String getAvatar() {
        return this.avatar;
    }

    /**
     * 头像地址
     *
     * @param value
     */
    public void setAvatar(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.avatar = value;
    }

    /**
     * 0会员,1员工,2店长收银,9系统管理员
     */
    @WhereSQL(sql = "userType=:User_userType")
    public String getUserType() {
        return this.userType;
    }

    /**
     * 0会员,1员工,2店长收银,9系统管理员
     *
     * @param value
     */
    public void setUserType(String value) {
        this.userType = value;
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
    @WhereSQL(sql = "createTime=:User_createTime")
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
    @WhereSQL(sql = "createUserId=:User_createUserId")
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
    @WhereSQL(sql = "updateTime=:User_updateTime")
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
    @WhereSQL(sql = "updateUserId=:User_updateUserId")
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
    @WhereSQL(sql = "active=:User_active")
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

    @WhereSQL(sql = "status=:User_status")
    @Transient
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * bak1
     */
    @WhereSQL(sql = "bak1=:User_bak1")
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
    @WhereSQL(sql = "bak2=:User_bak2")
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
    @WhereSQL(sql = "bak3=:User_bak3")
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
    @WhereSQL(sql = "bak4=:User_bak4")
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
    @WhereSQL(sql = "bak5=:User_bak5")
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
                .append(" [").append(getId()).append("],")
                .append("姓名[").append(getUserName()).append("],")
                .append("账号[").append(getAccount()).append("],")
                .append("密码[").append(getPassword()).append("],")
                .append("性别[").append(getSex()).append("],")
                .append("手机号码[").append(getMobile()).append("],")
                .append("邮箱[").append(getEmail()).append("],")
                .append("微信openId[").append(getOpenId()).append("],")
                .append("微信UnionID[").append(getUnionID()).append("],")
                .append("头像地址[").append(getAvatar()).append("],")
                .append("0会员,1员工,2店长收银,9系统管理员").append(getUserType()).append("],")
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

        if (obj instanceof User == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        User other = (User) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }


    @Transient
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Transient
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
