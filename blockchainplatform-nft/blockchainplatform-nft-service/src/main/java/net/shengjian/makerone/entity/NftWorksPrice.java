package net.shengjian.makerone.entity;
import javax.persistence.Id;
import javax.persistence.Table;


import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;




/**
 * 作品历史价格
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:29
 */
@Table(name="nft_works_price")
public class NftWorksPrice  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "作品历史价格";
	public static final String ALIAS_ID = "唯一标识";
	public static final String ALIAS_WORKSID = "作品标识";
	public static final String ALIAS_DATETIME = "时间";
	public static final String ALIAS_PRICE = "价格";
	public static final String ALIAS_RATIO = "浮动比例%";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_UPDATETIME = "updateTime";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
    */
	//date formats
	//public static final String FORMAT_DATETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_UPDATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
    /**
     * 唯一标识
     */
    private String id;
    /**
     * 作品标识
     */
    private String worksId;
    /**
     * 时间
     */
    private java.util.Date dateTime;
    /**
     * 价格
     */
    private java.math.BigDecimal price;
    /**
     * 浮动比例%
     */
    private String ratio;
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
	public NftWorksPrice(){
	}


	//get and set

     /**
	  * 唯一标识
	  * @param value
	  */
	public void setId(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.id = value;
	}
	
	
	
	/**
	 * 唯一标识
	 */
	@Id
     @WhereSQL(sql="id=:NftWorksPrice_id")
	public String getId() {
		return this.id;
	}

     /**
	  * 作品标识
	  * @param value
	  */
	public void setWorksId(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.worksId = value;
	}
	
	
	
	/**
	 * 作品标识
	 */
     @WhereSQL(sql="worksId=:NftWorksPrice_worksId")
	public String getWorksId() {
		return this.worksId;
	}
		/*
	public String getdateTimeString() {
		return DateUtils.convertDate2String(FORMAT_DATETIME, getdateTime());
	}
	public void setdateTimeString(String value) throws ParseException{
		setdateTime(DateUtils.convertString2Date(FORMAT_DATETIME,value));
	}*/

     /**
	  * 时间
	  * @param value
	  */
	public void setDateTime(java.util.Date value) {
		this.dateTime = value;
	}
	
	
	
	/**
	 * 时间
	 */
     @WhereSQL(sql="dateTime=:NftWorksPrice_dateTime")
	public java.util.Date getDateTime() {
		return this.dateTime;
	}

     /**
	  * 价格
	  * @param value
	  */
	public void setPrice(java.math.BigDecimal value) {
		this.price = value;
	}
	
	
	
	/**
	 * 价格
	 */
     @WhereSQL(sql="price=:NftWorksPrice_price")
	public java.math.BigDecimal getPrice() {
		return this.price;
	}

     /**
	  * 浮动比例%
	  * @param value
	  */
	public void setRatio(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.ratio = value;
	}
	
	
	
	/**
	 * 浮动比例%
	 */
     @WhereSQL(sql="ratio=:NftWorksPrice_ratio")
	public String getRatio() {
		return this.ratio;
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
     @WhereSQL(sql="createTime=:NftWorksPrice_createTime")
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
     @WhereSQL(sql="updateTime=:NftWorksPrice_updateTime")
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
     @WhereSQL(sql="bak1=:NftWorksPrice_bak1")
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
     @WhereSQL(sql="bak2=:NftWorksPrice_bak2")
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
     @WhereSQL(sql="bak3=:NftWorksPrice_bak3")
	public String getBak3() {
		return this.bak3;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("唯一标识[").append(getId()).append("],")
			.append("作品标识[").append(getWorksId()).append("],")
			.append("时间[").append(getDateTime()).append("],")
			.append("价格[").append(getPrice()).append("],")
			.append("浮动比例%[").append(getRatio()).append("],")
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
			.append(getId())
			.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null){
			return false;
		}
		
		if (obj instanceof NftWorksPrice == false){
			return false;
		}
			
		if (this == obj){
			return true;
		}
		
		NftWorksPrice other = (NftWorksPrice)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
