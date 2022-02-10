package net.shengjian.makerone.entity;
import javax.persistence.Id;
import javax.persistence.Table;


import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.math.BigDecimal;


/**
 * 用户合集信息
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:57:43
 */
@Table(name="nft_collection")
public class NftCollection  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "用户合集信息";
	public static final String ALIAS_ID = "唯一标识";
	public static final String ALIAS_USERID = "用户标识";
	public static final String ALIAS_LOGOPATH = "logo图地址";
	public static final String ALIAS_COVERPATH = "合集封面地址";
	public static final String ALIAS_BANNERPATH = "banner图地址";
	public static final String ALIAS_NAME = "合集名称";
	public static final String ALIAS_LINK = "自定义链接";
	public static final String ALIAS_DETAILS = "合集简介";
	public static final String ALIAS_TYPE = "合集类别";
	public static final String ALIAS_ROYALTIES = "合集版税";
	public static final String ALIAS_ISIN = "是否上架 0:否,1:是";
	public static final String ALIAS_INTIME = "上架时间";
	public static final String ALIAS_OUTTIME = "下架时间";
	public static final String ALIAS_ISCERT = "是否平台认证,0:否,1是";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_UPDATETIME = "updateTime";
	public static final String ALIAS_FIRE = "合集热度值";
	public static final String ALIAS_SUMPRICE = "合集交易总额";
	public static final String ALIAS_LOWPRICE = "最低价";
	public static final String ALIAS_BUYERSNUM = "拥有者数量";
    */
	//date formats
	//public static final String FORMAT_INTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_OUTTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_UPDATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
    /**
     * 唯一标识
     */
    private String id;
    /**
     * 用户标识
     */
    private String userId;
    /**
     * logo图地址
     */
    private String logoPath;
    /**
     * 合集封面地址
     */
    private String coverPath;
    /**
     * banner图地址
     */
    private String bannerPath;
    /**
     * 合集名称
     */
    private String name;
    /**
     * 自定义链接
     */
    private String link;
    /**
     * 合集简介
     */
    private String details;
    /**
     * 合集类别
     */
    private Integer type;
    /**
     * 合集版税 百分比
     */
    private BigDecimal royalties;
    /**
     * 上架后,在不同链平台上链,认证后的数据-json
     */
    private String chainPlatCert;
    /**
     * 是否上架 0:否,1:是
     */
    private Integer isIn;
    /**
     * 上架时间
     */
    private java.util.Date inTime;
    /**
     * 下架时间
     */
    private java.util.Date outTime;
    /**
     * 是否平台认证,0:否,1是
     */
    private Integer isCert;
    /**
     * createTime
     */
    private java.util.Date createTime;
    /**
     * updateTime
     */
    private java.util.Date updateTime;
    /**
     * 热度值
     */
    private Integer fire;
    /**
     * 交易总额
     */
    private BigDecimal sumprice;
    /**
     * 地板价
     */
    private BigDecimal lowprice;

	/**
	 * 拥有者总数
	 */
	private Integer buyersnum;

	/**
	 * 7天涨跌幅
	 */
	private Double weekchange;


	/**
	 * 1天涨跌幅
	 */
	private Double daychange;
	//columns END 数据库字段结束
	
	//concstructor
	public NftCollection(){
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
     @WhereSQL(sql="id=:NftCollection_id")
	public String getId() {
		return this.id;
	}

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
     @WhereSQL(sql="userId=:NftCollection_userId")
	public String getUserId() {
		return this.userId;
	}

     /**
	  * logo图地址
	  * @param value
	  */
	public void setLogoPath(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.logoPath = value;
	}
	
	
	
	/**
	 * logo图地址
	 */
     @WhereSQL(sql="logoPath=:NftCollection_logoPath")
	public String getLogoPath() {
		return this.logoPath;
	}

     /**
	  * 合集封面地址
	  * @param value
	  */
	public void setCoverPath(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.coverPath = value;
	}
	
	
	
	/**
	 * 合集封面地址
	 */
     @WhereSQL(sql="coverPath=:NftCollection_coverPath")
	public String getCoverPath() {
		return this.coverPath;
	}

     /**
	  * banner图地址
	  * @param value
	  */
	public void setBannerPath(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.bannerPath = value;
	}
	
	
	
	/**
	 * banner图地址
	 */
     @WhereSQL(sql="bannerPath=:NftCollection_bannerPath")
	public String getBannerPath() {
		return this.bannerPath;
	}

     /**
	  * 合集名称
	  * @param value
	  */
	public void setName(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.name = value;
	}
	
	
	
	/**
	 * 合集名称
	 */
     @WhereSQL(sql="name=:NftCollection_name")
	public String getName() {
		return this.name;
	}

     /**
	  * 自定义链接
	  * @param value
	  */
	public void setLink(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.link = value;
	}
	
	
	
	/**
	 * 自定义链接
	 */
     @WhereSQL(sql="link=:NftCollection_link")
	public String getLink() {
		return this.link;
	}

     /**
	  * 合集简介
	  * @param value
	  */
	public void setDetails(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.details = value;
	}
	
	
	
	/**
	 * 合集简介
	 */
     @WhereSQL(sql="details=:NftCollection_details")
	public String getDetails() {
		return this.details;
	}

     /**
	  * 合集类别
	  * @param value
	  */
	public void setType(Integer value) {
		this.type = value;
	}
	
	
	
	/**
	 * 合集类别
	 */
     @WhereSQL(sql="type=:NftCollection_type")
	public Integer getType() {
		return this.type;
	}

     /**
	  * 合集版税
	  * @param value
	  */
	public void setRoyalties(BigDecimal value) {
		this.royalties = value;
	}
	
	
	
	/**
	 * 合集版税
	 */
     @WhereSQL(sql="royalties=:NftCollection_royalties")
	public BigDecimal getRoyalties() {
		return this.royalties;
	}

    /**
     * 上架后,在不同链平台上链,认证后的数据-json
     */
	@WhereSQL(sql = "chainPlatCert=:NftCollection_chainPlatCert")
    public String getChainPlatCert() {
        return chainPlatCert;
    }

    public void setChainPlatCert(String chainPlatCert) {
        this.chainPlatCert = chainPlatCert;
    }

    /**
	  * 是否上架 0:否,1:是
	  * @param value
	  */
	public void setIsIn(Integer value) {
		this.isIn = value;
	}
	
	
	
	/**
	 * 是否上架 0:否,1:是
	 */
     @WhereSQL(sql="isIn=:NftCollection_isIn")
	public Integer getIsIn() {
		return this.isIn;
	}
		/*
	public String getinTimeString() {
		return DateUtils.convertDate2String(FORMAT_INTIME, getinTime());
	}
	public void setinTimeString(String value) throws ParseException{
		setinTime(DateUtils.convertString2Date(FORMAT_INTIME,value));
	}*/

     /**
	  * 上架时间
	  * @param value
	  */
	public void setInTime(java.util.Date value) {
		this.inTime = value;
	}
	
	
	
	/**
	 * 上架时间
	 */
     @WhereSQL(sql="inTime=:NftCollection_inTime")
	public java.util.Date getInTime() {
		return this.inTime;
	}
		/*
	public String getoutTimeString() {
		return DateUtils.convertDate2String(FORMAT_OUTTIME, getoutTime());
	}
	public void setoutTimeString(String value) throws ParseException{
		setoutTime(DateUtils.convertString2Date(FORMAT_OUTTIME,value));
	}*/

     /**
	  * 下架时间
	  * @param value
	  */
	public void setOutTime(java.util.Date value) {
		this.outTime = value;
	}
	
	
	
	/**
	 * 下架时间
	 */
     @WhereSQL(sql="outTime=:NftCollection_outTime")
	public java.util.Date getOutTime() {
		return this.outTime;
	}

     /**
	  * 是否平台认证,0:否,1是
	  * @param value
	  */
	public void setIsCert(Integer value) {
		this.isCert = value;
	}
	
	
	
	/**
	 * 是否平台认证,0:否,1是
	 */
     @WhereSQL(sql="isCert=:NftCollection_isCert")
	public Integer getIsCert() {
		return this.isCert;
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
     @WhereSQL(sql="createTime=:NftCollection_createTime")
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
     @WhereSQL(sql="updateTime=:NftCollection_updateTime")
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

     /**
	  * 热度值
	  * @param value
	  */
	public void setFire(Integer value) {
		this.fire = value;
	}
	
	
	
	/**
	 * 热度值
	 */
     @WhereSQL(sql="fire=:NftCollection_fire")
	public Integer getFire() {
		return this.fire;
	}

     /**
	  * 交易总额
	  * @param value
	  */
	public void setSumprice(BigDecimal value) {
		this.sumprice = value;
	}
	
	
	
	/**
	 * 交易总额
	 */
     @WhereSQL(sql="sumprice=:NftCollection_sumprice")
	public BigDecimal getSumprice() {
		return this.sumprice;
	}

     /**
	  * 最低价
	  * @param value
	  */
	public void setLowprice(BigDecimal value) {

		this.lowprice = value;
	}
	/**
	 * 最低价
	 */
     @WhereSQL(sql="lowprice=:NftCollection_lowprice")
	public BigDecimal getLowprice() {
		return this.lowprice;
	}

	/**
	 * 拥有者数量
	 * @param value
	 */
	public void setBuyersnum(Integer value) {

		this.buyersnum = value;
	}
	/**
	 * 拥有者数量
	 */
	@WhereSQL(sql="buyersnum=:NftCollection_buyersnum")
	public Integer getBuyersnum() {
		return this.buyersnum;
	}

	/**
	 * 7天涨跌幅
	 * @param value
	 */
	public void setWeekchange(Double value) {

		this.weekchange = value;
	}
	/**
	 * 7天涨跌幅
	 */
	@WhereSQL(sql="weekchange=:NftCollection_weekchange")
	public Double getWeekchange() {
		return this.weekchange;
	}

	/**
	 * 1天涨跌幅
	 * @param value
	 */
	public void setDaychange(Double value) {

		this.daychange = value;
	}
	/**
	 * 1天涨跌幅
	 */
	@WhereSQL(sql="weekchange=:NftCollection_weekchange")
	public Double getDaychange() {
		return this.daychange;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("唯一标识[").append(getId()).append("],")
			.append("用户标识[").append(getUserId()).append("],")
			.append("logo图地址[").append(getLogoPath()).append("],")
			.append("合集封面地址[").append(getCoverPath()).append("],")
			.append("banner图地址[").append(getBannerPath()).append("],")
			.append("合集名称[").append(getName()).append("],")
			.append("自定义链接[").append(getLink()).append("],")
			.append("合集简介[").append(getDetails()).append("],")
			.append("合集类别[").append(getType()).append("],")
			.append("合集版税[").append(getRoyalties()).append("],")
			.append("是否上架 0:否,1:是[").append(getIsIn()).append("],")
			.append("上架时间[").append(getInTime()).append("],")
			.append("下架时间[").append(getOutTime()).append("],")
			.append("是否平台认证,0:否,1是[").append(getIsCert()).append("],")
			.append("createTime[").append(getCreateTime()).append("],")
			.append("updateTime[").append(getUpdateTime()).append("],")
			.append("热度值[").append(getFire()).append("],")
			.append("交易总额[").append(getSumprice()).append("],")
			.append("地板价[").append(getLowprice()).append("],")
			.append("拥有者总数[").append(getBuyersnum()).append("],")
			.append("7天涨跌幅[").append(getWeekchange()).append("],")
			.append("24h涨跌幅[").append(getDaychange()).append("],")
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
		
		if (obj instanceof NftCollection == false){
			return false;
		}
			
		if (this == obj){
			return true;
		}
		
		NftCollection other = (NftCollection)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
