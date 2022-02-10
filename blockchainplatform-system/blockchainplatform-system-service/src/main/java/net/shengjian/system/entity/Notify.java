package net.shengjian.system.entity;
import javax.persistence.Id;
import javax.persistence.Table;


import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Date;


/**
 * 消息通知
 * @author springrain<Auto generate>
 * @version  2021-09-14 13:36:14
 */
@Table(name="t_notify")
public class Notify  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "Notify";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_NODIFYID = "通知者";
	public static final String ALIAS_NODIFYTIME = "通知时间";
	public static final String ALIAS_ACCEPTID = "接受者";
	public static final String ALIAS_ACCEPTTIME = "接受时间(查看时间)";
	public static final String ALIAS_TITLE = "消息标题";
	public static final String ALIAS_CONTENT = "消息内容";
	public static final String ALIAS_STATUS = "消息状态(0未读,1已读)";
	public static final String ALIAS_TYPE = "消息类型(1系统运行通知,2推广消息)";
	public static final String ALIAS_URL = "推广消息跳转的链接";
    */
	//date formats
	//public static final String FORMAT_NODIFYTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_ACCEPTTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
    /**
     * 主键
     */
    private String id;
    /**
     * 通知者
     */
    private String nodifyId;
    /**
     * 通知时间
     */
    private java.util.Date nodifyTime;
    /**
     * 接受者
     */
    private String acceptId;
    /**
     * 接受时间(查看时间)
     */
    private java.util.Date acceptTime;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息状态(0未读,1已读)
     */
    private Integer status;
    /**
     * 消息类型(1系统运行通知,2推广消息)
     */
    private Integer type;
    /**
     * 推广消息跳转的链接
     */
    private String url;
	//columns END 数据库字段结束
	
	//concstructor
	public Notify(){
	}

    public Notify(String id, String nodifyId, Date nodifyTime, String acceptId, Date acceptTime, String title, String content, int status, Integer type, String url) {
        this.id = id;
        this.nodifyId = nodifyId;
        this.nodifyTime = nodifyTime;
        this.acceptId = acceptId;
        this.acceptTime = acceptTime;
        this.title = title;
        this.content = content;
        this.status = status;
        this.type = type;
        this.url = url;
    }
    //get and set

     /**
	  * 主键
	  * @param value
	  */
	public void setId(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.id = value;
	}
	
	
	
	/**
	 * 主键
	 */
	@Id
     @WhereSQL(sql="id=:Notify_id")
	public String getId() {
		return this.id;
	}

     /**
	  * 通知者
	  * @param value
	  */
	public void setNodifyId(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.nodifyId = value;
	}
	
	
	
	/**
	 * 通知者
	 */
     @WhereSQL(sql="nodifyId=:Notify_nodifyId")
	public String getNodifyId() {
		return this.nodifyId;
	}
		/*
	public String getnodifyTimeString() {
		return DateUtils.convertDate2String(FORMAT_NODIFYTIME, getnodifyTime());
	}
	public void setnodifyTimeString(String value) throws ParseException{
		setnodifyTime(DateUtils.convertString2Date(FORMAT_NODIFYTIME,value));
	}*/

     /**
	  * 通知时间
	  * @param value
	  */
	public void setNodifyTime(java.util.Date value) {
		this.nodifyTime = value;
	}
	
	
	
	/**
	 * 通知时间
	 */
     @WhereSQL(sql="nodifyTime=:Notify_nodifyTime")
	public java.util.Date getNodifyTime() {
		return this.nodifyTime;
	}

     /**
	  * 接受者
	  * @param value
	  */
	public void setAcceptId(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.acceptId = value;
	}
	
	
	
	/**
	 * 接受者
	 */
     @WhereSQL(sql="acceptId=:Notify_acceptId")
	public String getAcceptId() {
		return this.acceptId;
	}
		/*
	public String getacceptTimeString() {
		return DateUtils.convertDate2String(FORMAT_ACCEPTTIME, getacceptTime());
	}
	public void setacceptTimeString(String value) throws ParseException{
		setacceptTime(DateUtils.convertString2Date(FORMAT_ACCEPTTIME,value));
	}*/

     /**
	  * 接受时间(查看时间)
	  * @param value
	  */
	public void setAcceptTime(java.util.Date value) {
		this.acceptTime = value;
	}
	
	
	
	/**
	 * 接受时间(查看时间)
	 */
     @WhereSQL(sql="acceptTime=:Notify_acceptTime")
	public java.util.Date getAcceptTime() {
		return this.acceptTime;
	}

     /**
	  * 消息标题
	  * @param value
	  */
	public void setTitle(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.title = value;
	}
	
	
	
	/**
	 * 消息标题
	 */
     @WhereSQL(sql="title=:Notify_title")
	public String getTitle() {
		return this.title;
	}

     /**
	  * 消息内容
	  * @param value
	  */
	public void setContent(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.content = value;
	}
	
	
	
	/**
	 * 消息内容
	 */
     @WhereSQL(sql="content=:Notify_content")
	public String getContent() {
		return this.content;
	}

     /**
	  * 消息状态(0未读,1已读)
	  * @param value
	  */
	public void setStatus(int value) {
		this.status = value;
	}
	
	
	
	/**
	 * 消息状态(0未读,1已读)
	 */
     @WhereSQL(sql="status=:Notify_status")
	public int getStatus() {
		return this.status;
	}

     /**
	  * 消息类型(1系统运行通知,2推广消息)
	  * @param value
	  */
	public void setType(Integer value) {
		this.type = value;
	}
	
	
	
	/**
	 * 消息类型(1系统运行通知,2推广消息)
	 */
     @WhereSQL(sql="type=:Notify_type")
	public Integer getType() {
		return this.type;
	}

     /**
	  * 推广消息跳转的链接
	  * @param value
	  */
	public void setUrl(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.url = value;
	}
	
	
	
	/**
	 * 推广消息跳转的链接
	 */
     @WhereSQL(sql="url=:Notify_url")
	public String getUrl() {
		return this.url;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("主键[").append(getId()).append("],")
			.append("通知者[").append(getNodifyId()).append("],")
			.append("通知时间[").append(getNodifyTime()).append("],")
			.append("接受者[").append(getAcceptId()).append("],")
			.append("接受时间(查看时间)[").append(getAcceptTime()).append("],")
			.append("消息标题[").append(getTitle()).append("],")
			.append("消息内容[").append(getContent()).append("],")
			.append("消息状态(0未读,1已读)[").append(getStatus()).append("],")
			.append("消息类型(1系统运行通知,2推广消息)[").append(getType()).append("],")
			.append("推广消息跳转的链接[").append(getUrl()).append("],")
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
		
		if (obj == null){
			return false;
		}
		
		if (obj instanceof Notify == false){
			return false;
		}
			
		if (this == obj){
			return true;
		}
		
		Notify other = (Notify)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
