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
 * 附件信息
 *
 * @author springrain<Auto generate>
 * @version 2021-06-25 15:55:39
 */
@Table(name = "t_attachment")
public class Attachment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //alias
	/*
	public static final String TABLE_ALIAS = "附件表(产业大脑)";
	public static final String ALIAS_ID = "id主键";
	public static final String ALIAS_ORGID = "部门id";
	public static final String ALIAS_ORGTYPEPATHKEY = "URL路径中的部门类型,例如 URL路径中的 kjj ";
	public static final String ALIAS_BUSINESSID = "业务ID,用于业务关联查询";
	public static final String ALIAS_ATTACHMENTTYPE = "附件类型,0.其他文件,1.政策附件 2.企业认证文件 3.专家认证文件  4.企业个人认证文件,5... , 6... ~
	public static final String ALIAS_FILENAME = "附件名称";
	public static final String ALIAS_FILEURL = "路径";
	public static final String ALIAS_SUFFIX = "文件后缀";
	public static final String ALIAS_FILESIZE = "文件大小,单位K";
	public static final String ALIAS_LASTDOWNTIME = "最后下载时间";
	public static final String ALIAS_SORTNO = "排序,查询时倒叙排列";
	public static final String ALIAS_ACTIVE = "是否有效(0否,1是)";
	public static final String ALIAS_CREATEUSER = "创建者";
	public static final String ALIAS_CREATETIME = "上传时间";
	public static final String ALIAS_UPDATEUSER = "更新者";
	public static final String ALIAS_UPDATETIME = "更新时间";
    */
    //date formats
    //public static final String FORMAT_LASTDOWNTIME = DateUtils.DATETIME_FORMAT;
    //public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
    //public static final String FORMAT_UPDATETIME = DateUtils.DATETIME_FORMAT;

    //columns START
    /**
     * id主键
     */
    private String id;
    /**
     * 部门id
     */
    private String orgId;
    /**
     * URL路径中的部门类型,例如 URL路径中的 kjj
     */
    private String orgTypePathKey;
    /**
     * 业务ID,用于业务关联查询
     */
    private String businessId;
    /**
     * 0.其他文件,1.政策附件 2.企业认证文件 3.专家认证文件  4.企业个人认证文件,5... , 6... ~
     */
    private Integer attachmentType;
    /**
     * 附件名称
     */
    private String fileName;
    /**
     * 路径
     */
    private String fileURL;
    /**
     * 项目前缀拼接
     */
    private String url;
    /**
     * 文件后缀
     */
    private String suffix;
    /**
     * 文件大小,单位K
     */
    private Integer fileSize;
    /**
     * 最后下载时间
     */
    private java.util.Date lastDownTime;
    /**
     * 排序,查询时倒叙排列
     */
    private Integer sortno;
    /**
     * 是否有效(0否,1是)
     */
    private Integer active;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 上传时间
     */
    private java.util.Date createTime;
    /**
     * 更新者
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private java.util.Date updateTime;

    /**
     * MD5 code值
     */
    private String md5Code;
    //columns END 数据库字段结束

    //concstructor
    public Attachment() {
    }

    @Transient
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @WhereSQL(sql = "md5Code=:Attachment_md5Code")
    public String getMd5Code() {
        return md5Code;
    }

    public void setMd5Code(String md5Code) {
        this.md5Code = md5Code;
    }

    //get and set

    /**
     * id主键
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
     * id主键
     */
    @Id
    @WhereSQL(sql = "id=:Attachment_id")
    public String getId() {
        return this.id;
    }

    /**
     * 部门id
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
     * 部门id
     */
    @WhereSQL(sql = "orgId=:Attachment_orgId")
    public String getOrgId() {
        return this.orgId;
    }

    /**
     * URL路径中的部门类型,例如 URL路径中的 kjj
     *
     * @param value
     */
    public void setOrgTypePathKey(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.orgTypePathKey = value;
    }


    /**
     * URL路径中的部门类型,例如 URL路径中的 kjj
     */
    @WhereSQL(sql = "orgTypePathKey=:Attachment_orgTypePathKey")
    public String getOrgTypePathKey() {
        return this.orgTypePathKey;
    }

    /**
     * 业务ID,用于业务关联查询
     *
     * @param value
     */
    public void setBusinessId(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.businessId = value;
    }


    /**
     * 业务ID,用于业务关联查询
     */
    @WhereSQL(sql = "businessId=:Attachment_businessId")
    public String getBusinessId() {
        return this.businessId;
    }

    /**
     * 附件类型,1.政策附件.2.企业认证文件3.专家认证文件.4.企业个人认证文件.0.其他文件
     *
     * @param value
     */
    public void setAttachmentType(Integer value) {
        this.attachmentType = value;
    }


    /**
     * 附件类型,1.政策附件.2.企业认证文件3.专家认证文件.4.企业个人认证文件.0.其他文件
     */
    @WhereSQL(sql = "attachmentType=:Attachment_attachmentType")
    public Integer getAttachmentType() {
        return this.attachmentType;
    }

    /**
     * 附件名称
     *
     * @param value
     */
    public void setFileName(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.fileName = value;
    }


    /**
     * 附件名称
     */
    @WhereSQL(sql = "fileName=:Attachment_fileName")
    public String getFileName() {
        return this.fileName;
    }

    /**
     * 路径
     *
     * @param value
     */
    public void setFileURL(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.fileURL = value;
    }


    /**
     * 路径
     */
    @WhereSQL(sql = "fileURL=:Attachment_fileURL")
    public String getFileURL() {
        return this.fileURL;
    }

    /**
     * 文件后缀
     *
     * @param value
     */
    public void setSuffix(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.suffix = value;
    }


    /**
     * 文件后缀
     */
    @WhereSQL(sql = "suffix=:Attachment_suffix")
    public String getSuffix() {
        return this.suffix;
    }

    /**
     * 文件大小,单位K
     *
     * @param value
     */
    public void setFileSize(Integer value) {
        this.fileSize = value;
    }


    /**
     * 文件大小,单位K
     */
    @WhereSQL(sql = "fileSize=:Attachment_fileSize")
    public Integer getFileSize() {
        return this.fileSize;
    }
		/*
	public String getlastDownTimeString() {
		return DateUtils.convertDate2String(FORMAT_LASTDOWNTIME, getlastDownTime());
	}
	public void setlastDownTimeString(String value) throws ParseException{
		setlastDownTime(DateUtils.convertString2Date(FORMAT_LASTDOWNTIME,value));
	}*/

    /**
     * 最后下载时间
     *
     * @param value
     */
    public void setLastDownTime(java.util.Date value) {
        this.lastDownTime = value;
    }


    /**
     * 最后下载时间
     */
    @WhereSQL(sql = "lastDownTime=:Attachment_lastDownTime")
    public java.util.Date getLastDownTime() {
        return this.lastDownTime;
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
     * 排序,查询时倒叙排列
     */
    @WhereSQL(sql = "sortno=:Attachment_sortno")
    public Integer getSortno() {
        return this.sortno;
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
     * 是否有效(0否,1是)
     */
    @WhereSQL(sql = "active=:Attachment_active")
    public Integer getActive() {
        return this.active;
    }

    /**
     * 创建者
     *
     * @param value
     */
    public void setCreateUser(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.createUser = value;
    }


    /**
     * 创建者
     */
    @WhereSQL(sql = "createUser=:Attachment_createUser")
    public String getCreateUser() {
        return this.createUser;
    }
		/*
	public String getcreateTimeString() {
		return DateUtils.convertDate2String(FORMAT_CREATETIME, getcreateTime());
	}
	public void setcreateTimeString(String value) throws ParseException{
		setcreateTime(DateUtils.convertString2Date(FORMAT_CREATETIME,value));
	}*/

    /**
     * 上传时间
     *
     * @param value
     */
    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }


    /**
     * 上传时间
     */
    @WhereSQL(sql = "createTime=:Attachment_createTime")
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 更新者
     *
     * @param value
     */
    public void setUpdateUser(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.updateUser = value;
    }


    /**
     * 更新者
     */
    @WhereSQL(sql = "updateUser=:Attachment_updateUser")
    public String getUpdateUser() {
        return this.updateUser;
    }
		/*
	public String getupdateTimeString() {
		return DateUtils.convertDate2String(FORMAT_UPDATETIME, getupdateTime());
	}
	public void setupdateTimeString(String value) throws ParseException{
		setupdateTime(DateUtils.convertString2Date(FORMAT_UPDATETIME,value));
	}*/

    /**
     * 更新时间
     *
     * @param value
     */
    public void setUpdateTime(java.util.Date value) {
        this.updateTime = value;
    }


    /**
     * 更新时间
     */
    @WhereSQL(sql = "updateTime=:Attachment_updateTime")
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("id主键[").append(getId()).append("],")
                .append("部门id[").append(getOrgId()).append("],")
                .append("URL路径中的部门类型,例如 URL路径中的 kjj [").append(getOrgTypePathKey()).append("],")
                .append("业务ID,用于业务关联查询[").append(getBusinessId()).append("],")
                .append("附件类型,1.政策附件.2.企业认证文件3.专家认证文件.4.企业个人认证文件.0.其他文件[").append(getAttachmentType()).append("],")
                .append("附件名称[").append(getFileName()).append("],")
                .append("路径[").append(getFileURL()).append("],")
                .append("文件后缀[").append(getSuffix()).append("],")
                .append("文件大小,单位K[").append(getFileSize()).append("],")
                .append("最后下载时间[").append(getLastDownTime()).append("],")
                .append("排序,查询时倒叙排列[").append(getSortno()).append("],")
                .append("是否有效(0否,1是)[").append(getActive()).append("],")
                .append("创建者[").append(getCreateUser()).append("],")
                .append("上传时间[").append(getCreateTime()).append("],")
                .append("更新者[").append(getUpdateUser()).append("],")
                .append("更新时间[").append(getUpdateTime()).append("],")
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

        if (obj instanceof Attachment == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Attachment other = (Attachment) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

	
