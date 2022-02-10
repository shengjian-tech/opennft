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
@Table(name = "t_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //alias
	/*
	public static final String TABLE_ALIAS = "菜单";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "菜单名称";
	public static final String ALIAS_TITLE = "vue使用 meta.title";
	public static final String ALIAS_PID = "pid";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_PAGEURL = "pageurl";
	public static final String ALIAS_MENUTYPE = "0.功能按钮,1.导航菜单";
	public static final String ALIAS_PATH = "vue路由地址";
	public static final String ALIAS_KEEPALIVE = "vue组件使用";
	public static final String ALIAS_COMPONENT = "vue组件使用";
	public static final String ALIAS_PERMISSION = "vue组件使用";
	public static final String ALIAS_REDIRECT = "vue组件使用";
	public static final String ALIAS_ICON = "icon";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_CREATEUSERID = "createUserId";
	public static final String ALIAS_UPDATETIME = "updateTime";
	public static final String ALIAS_UPDATEUSERID = "updateUserId";
	public static final String ALIAS_SORTNO = "排序,查询时倒叙排列";
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
     * id
     *
     * @required
     */
    private String id;
    /**
     * 菜单名称
     *
     * @required
     */
    private String name;
    /**
     * 代码
     */
    //
    private String comcode;
    /**
     * vue使用 meta.title
     */
    private String title;
    /**
     * pid
     *
     * @required
     */
    private String pid;
    /**
     * 描述
     */
    private String remark;
    /**
     * pageurl
     *
     * @required
     */
    private String pageurl;
    /**
     * 权限显示code,功能 ,用于按钮显示判断
     *
     * @required
     */
    private String code;
    /**
     * 0.功能按钮,1.导航菜单
     *
     * @required
     */
    private Integer menuType;
    /**
     * vue路由地址
     */
    private String path;
    /**
     * vue组件使用
     */
    private Integer keepAlive;
    /**
     * vue组件使用
     */
    private String component;
    /**
     * vue组件使用
     */
    private String permission;
    /**
     * vue组件使用
     */
    private String redirect;
    /**
     * icon
     */
    private String icon;
    /**
     * 站外路径
     */
    private String url;
    /**
     * 窗口标识
     */
    private String target;
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
     *
     * @required
     */
    private Integer sortno;
    /**
     * 是否有效(0否,1是)
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
     * 菜单是由哪个角色产生的,用于强制部门权限的判定
     */
    private String roleId;
    /**
     * 菜单下的子菜单
     */
    @Transient
    private List<Menu> children;

    //concstructor
    public Menu() {
    }


    //get and set

    /**
     * id
     */
    @Id
    @WhereSQL(sql = "id=:Menu_id")
    public String getId() {
        return this.id;
    }

    /**
     * id
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
     * 菜单名称
     */
    @WhereSQL(sql = "name like :%Menu_name%")
    public String getName() {
        return this.name;
    }

    /**
     * 菜单名称
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
    @WhereSQL(sql = "comcode=:Org_comcode")
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
     * vue使用 meta.title
     */
    @WhereSQL(sql = "title like :%Menu_title%")
    public String getTitle() {
        return this.title;
    }

    /**
     * vue使用 meta.title
     *
     * @param value
     */
    public void setTitle(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.title = value;
    }

    /**
     * pid
     */
    @WhereSQL(sql = "pid=:Menu_pid")
    public String getPid() {
        return this.pid;
    }

    /**
     * pid
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
     * 描述
     */
    @WhereSQL(sql = "remark=:Menu_description")
    public String getRemark() {
        return this.remark;
    }

    /**
     * 描述
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
     * pageurl
     */
    @WhereSQL(sql = "pageurl like :%Menu_pageurl%")
    public String getPageurl() {
        return this.pageurl;
    }

    /**
     * 权限显示code,功能，用于按钮显示判断
     *
     * @return
     */
    @WhereSQL(sql = "code like :%Menu_code%")
    public String getCode() {
        return code;
    }

    /**
     * 权限显示code,功能，用于按钮显示判断
     *
     * @return
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * pageurl
     *
     * @param value
     */
    public void setPageurl(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.pageurl = value;
    }

    /**
     * 0.功能按钮,1.导航菜单
     */
    @WhereSQL(sql = "menuType=:Menu_menuType")
    public Integer getMenuType() {
        return this.menuType;
    }

    /**
     * 0.功能按钮,1.导航菜单
     *
     * @param value
     */
    public void setMenuType(Integer value) {
        this.menuType = value;
    }

    /**
     * vue路由地址
     */
    @WhereSQL(sql = "path=:Menu_path")
    public String getPath() {
        return this.path;
    }

    /**
     * vue路由地址
     *
     * @param value
     */
    public void setPath(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.path = value;
    }

    /**
     * vue组件使用
     */
    @WhereSQL(sql = "keepAlive=:Menu_keepAlive")
    public Integer getKeepAlive() {
        return this.keepAlive;
    }

    /**
     * vue组件使用
     *
     * @param value
     */
    public void setKeepAlive(Integer value) {
        this.keepAlive = value;
    }

    /**
     * vue组件使用
     */
    @WhereSQL(sql = "component=:Menu_component")
    public String getComponent() {
        return this.component;
    }

    /**
     * vue组件使用
     *
     * @param value
     */
    public void setComponent(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.component = value;
    }

    /**
     * vue组件使用
     */
    @WhereSQL(sql = "permission=:Menu_permission")
    public String getPermission() {
        return this.permission;
    }

    /**
     * vue组件使用
     *
     * @param value
     */
    public void setPermission(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.permission = value;
    }

    /**
     * vue组件使用
     */
    @WhereSQL(sql = "redirect=:Menu_redirect")
    public String getRedirect() {
        return this.redirect;
    }

    /**
     * vue组件使用
     *
     * @param value
     */
    public void setRedirect(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.redirect = value;
    }

    /**
     * icon
     */
    @WhereSQL(sql = "icon=:Menu_icon")
    public String getIcon() {
        return this.icon;
    }

    /**
     * icon
     *
     * @param value
     */
    public void setIcon(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.icon = value;
    }

    /**
     * 站外地址
     *
     * @return
     */
    @WhereSQL(sql = "url=:Menu_url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 窗口标识
     *
     * @return
     */
    @WhereSQL(sql = "target=:Menu_target")
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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
    @WhereSQL(sql = "createTime=:Menu_createTime")
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
    @WhereSQL(sql = "createUserId=:Menu_createUserId")
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
    @WhereSQL(sql = "updateTime=:Menu_updateTime")
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
    @WhereSQL(sql = "updateUserId=:Menu_updateUserId")
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
    @WhereSQL(sql = "sortno=:Menu_sortno")
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
     * 是否有效(0否,1是)
     */
    @WhereSQL(sql = "active=:Menu_active")
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
    @WhereSQL(sql = "bak1=:Menu_bak1")
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
    @WhereSQL(sql = "bak2=:Menu_bak2")
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
    @WhereSQL(sql = "bak3=:Menu_bak3")
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
    @WhereSQL(sql = "bak4=:Menu_bak4")
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
    @WhereSQL(sql = "bak5=:Menu_bak5")
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
                .append("id[").append(getId()).append("],")
                .append("菜单名称[").append(getName()).append("],")
                .append("vue使用 meta.title[").append(getTitle()).append("],")
                .append("pid[").append(getPid()).append("],")
                .append("描述[").append(getRemark()).append("],")
                .append("pageurl[").append(getPageurl()).append("],")
                .append("0.功能按钮,1.导航菜单[").append(getMenuType()).append("],")
                .append("vue路由地址[").append(getPath()).append("],")
                .append("vue组件使用[").append(getKeepAlive()).append("],")
                .append("vue组件使用[").append(getComponent()).append("],")
                .append("vue组件使用[").append(getPermission()).append("],")
                .append("vue组件使用[").append(getRedirect()).append("],")
                .append("icon[").append(getIcon()).append("],")
                .append("createTime[").append(getCreateTime()).append("],")
                .append("createUserId[").append(getCreateUserId()).append("],")
                .append("updateTime[").append(getUpdateTime()).append("],")
                .append("updateUserId[").append(getUpdateUserId()).append("],")
                .append("排序,查询时倒叙排列[").append(getSortno()).append("],")
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

        if (obj instanceof Menu == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Menu other = (Menu) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }


    @Transient
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Transient
    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }


}

	
