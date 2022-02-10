package net.shengjian.makerone.entity;
import javax.persistence.Id;
import javax.persistence.Table;


import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;




/**
 * 用户实名认证信息
 * @author springrain<Auto generate>
 * @version  2021-12-22 09:18:06
 */
@Table(name="nft_user_real")
public class NftUserReal  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "用户实名认证信息";
	public static final String ALIAS_USERID = "用户标识";
	public static final String ALIAS_IDENTITYNUM = "身份证号";
	public static final String ALIAS_NAME = "姓名";
	public static final String ALIAS_MOBLE = "手机号";
	public static final String ALIAS_ADDRESS = "现居住址";
	public static final String ALIAS_FRONTPATH = "身份证正面图片";
	public static final String ALIAS_BACKPATH = "身份证反面图片";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_UPDATETIME = "updateTime";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_UPDATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
    /**
     * 用户标识
     */
    private String userId;
    /**
     * 身份证号
     */
    private String identityNum;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String moble;
    /**
     * 现居住址
     */
    private String address;
    /**
     * 身份证正面图片
     */
    private String frontPath;
    /**
     * 身份证反面图片
     */
    private String backPath;
    /**
     * createTime
     */
    private java.util.Date createTime;
    /**
     * updateTime
     */
    private java.util.Date updateTime;
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
	//columns END 数据库字段结束
	
	//concstructor
	public NftUserReal(){
	}


	//get and set

     /**
	  * 用户标识
	  * @param value
	  */
	public void setUserId(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.userId = value;
	}
	
	
	
	/**
	 * 用户标识
	 */
	@Id
     @WhereSQL(sql="userId=:NftUserReal_userId")
	public String getUserId() {
		return this.userId;
	}

     /**
	  * 身份证号
	  * @param value
	  */
	public void setIdentityNum(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.identityNum = value;
	}
	
	
	
	/**
	 * 身份证号
	 */
     @WhereSQL(sql="identityNum=:NftUserReal_identityNum")
	public String getIdentityNum() {
		return this.identityNum;
	}

     /**
	  * 姓名
	  * @param value
	  */
	public void setName(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.name = value;
	}
	
	
	
	/**
	 * 姓名
	 */
     @WhereSQL(sql="name=:NftUserReal_name")
	public String getName() {
		return this.name;
	}

     /**
	  * 手机号
	  * @param value
	  */
	public void setMoble(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.moble = value;
	}
	
	
	
	/**
	 * 手机号
	 */
     @WhereSQL(sql="moble=:NftUserReal_moble")
	public String getMoble() {
		return this.moble;
	}

     /**
	  * 现居住址
	  * @param value
	  */
	public void setAddress(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.address = value;
	}
	
	
	
	/**
	 * 现居住址
	 */
     @WhereSQL(sql="address=:NftUserReal_address")
	public String getAddress() {
		return this.address;
	}

     /**
	  * 身份证正面图片
	  * @param value
	  */
	public void setFrontPath(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.frontPath = value;
	}
	
	
	
	/**
	 * 身份证正面图片
	 */
     @WhereSQL(sql="frontPath=:NftUserReal_frontPath")
	public String getFrontPath() {
		return this.frontPath;
	}

     /**
	  * 身份证反面图片
	  * @param value
	  */
	public void setBackPath(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.backPath = value;
	}
	
	
	
	/**
	 * 身份证反面图片
	 */
     @WhereSQL(sql="backPath=:NftUserReal_backPath")
	public String getBackPath() {
		return this.backPath;
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
	  * @param value
	  */
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	
	
	/**
	 * createTime
	 */
     @WhereSQL(sql="createTime=:NftUserReal_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
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
	  * @param value
	  */
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	
	
	/**
	 * updateTime
	 */
     @WhereSQL(sql="updateTime=:NftUserReal_updateTime")
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

     /**
	  * bak1
	  * @param value
	  */
	public void setBak1(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.bak1 = value;
	}
	
	
	
	/**
	 * bak1
	 */
     @WhereSQL(sql="bak1=:NftUserReal_bak1")
	public String getBak1() {
		return this.bak1;
	}

     /**
	  * bak2
	  * @param value
	  */
	public void setBak2(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.bak2 = value;
	}
	
	
	
	/**
	 * bak2
	 */
     @WhereSQL(sql="bak2=:NftUserReal_bak2")
	public String getBak2() {
		return this.bak2;
	}

     /**
	  * bak3
	  * @param value
	  */
	public void setBak3(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.bak3 = value;
	}
	
	
	
	/**
	 * bak3
	 */
     @WhereSQL(sql="bak3=:NftUserReal_bak3")
	public String getBak3() {
		return this.bak3;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("用户标识[").append(getUserId()).append("],")
			.append("身份证号[").append(getIdentityNum()).append("],")
			.append("姓名[").append(getName()).append("],")
			.append("手机号[").append(getMoble()).append("],")
			.append("现居住址[").append(getAddress()).append("],")
			.append("身份证正面图片[").append(getFrontPath()).append("],")
			.append("身份证反面图片[").append(getBackPath()).append("],")
			.append("createTime[").append(getCreateTime()).append("],")
			.append("updateTime[").append(getUpdateTime()).append("],")
			.append("bak1[").append(getBak1()).append("],")
			.append("bak2[").append(getBak2()).append("],")
			.append("bak3[").append(getBak3()).append("],")
			.toString();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserId())
			.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null){
			return false;
		}
		
		if (obj instanceof NftUserReal == false){
			return false;
		}
			
		if (this == obj){
			return true;
		}
		
		NftUserReal other = (NftUserReal)obj;
		return new EqualsBuilder()
			.append(getUserId(),other.getUserId())
			.isEquals();
	}
}

	
