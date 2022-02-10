package net.shengjian.makerone.entity;
import javax.persistence.Id;
import javax.persistence.Table;


import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;




/**
 * 用户作品信息
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:08
 */
@Table(name="nft_works")
public class NftWorks  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "用户作品信息";
	public static final String ALIAS_ID = "唯一标识";
	public static final String ALIAS_AUTHORID = "作者标识=用户标识";
	public static final String ALIAS_COLLECTIONID = "合集标识";
	public static final String ALIAS_DATAPATH = "作品数据地址(图片Base64,上链)";
	public static final String ALIAS_NAME = "作品名称";
	public static final String ALIAS_TYPE = "作品类型";
	public static final String ALIAS_LINK = "自定义链接";
	public static final String ALIAS_DETAILS = "作品简介";
	public static final String ALIAS_PRICE = "作品价格(初始价格)";
	public static final String ALIAS_NUM = "发行数量";
	public static final String ALIAS_BUYERS = "作品购买者(用户标识,标识之间使用逗号隔开)";
	public static final String ALIAS_INTIME = "上架时间";
	public static final String ALIAS_OUTTIME = "下架时间";
	public static final String ALIAS_WAITINGTIME = "发布等待期,日期之后才可购买";
	public static final String ALIAS_STATE = "作品状态(0:下架,1:上架,2:售卖中,3已售停)";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_UPDATETIME = "updateTime";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
    */
	//date formats
	//public static final String FORMAT_INTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_OUTTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_WAITINGTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_UPDATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
    /**
     * 唯一标识
     */
    private String id;
    /**
     * 作者标识=用户标识
     */
    private String authorId;
    /**
     * 合集标识
     */
    private String collectionId;
    /**
     * 作品数据地址(图片Base64,上链)
     */
    private String dataPath;
    /**
     * 作品名称
     */
    private String name;
    /**
     * 作品类型
     */
    private Integer type;
    /**
     * 自定义链接
     */
    private String link;
    /**
     * 作品简介
     */
    private String details;
    /**
     * 作品价格(初始价格)
     */
    private java.math.BigDecimal price;
    /**
     * 发行数量
     */
    private Integer num;
    /**
     * 作品购买者(用户标识,标识之间使用逗号隔开)
     */
    private String buyers;
    /**
     * 上架时间
     */
    private java.util.Date inTime;
    /**
     * 下架时间
     */
    private java.util.Date outTime;
    /**
     * 发布等待期,日期之后才可购买
     */
    private java.util.Date waitingTime;
    /**
     * 上架后,在不同链平台上链,认证后的数据-json
     */
    private String chainPlatCert;
    /**
     * 作品状态(0:下架,1:上架,2:售卖中,3已售停)
     */
    private Integer state;
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
	public NftWorks(){
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
     @WhereSQL(sql="id=:NftWorks_id")
	public String getId() {
		return this.id;
	}

     /**
	  * 作者标识=用户标识
	  * @param value
	  */
	public void setAuthorId(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.authorId = value;
	}
	
	
	
	/**
	 * 作者标识=用户标识
	 */
     @WhereSQL(sql="authorId=:NftWorks_authorId")
	public String getAuthorId() {
		return this.authorId;
	}

     /**
	  * 合集标识
	  * @param value
	  */
	public void setCollectionId(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.collectionId = value;
	}
	
	
	
	/**
	 * 合集标识
	 */
     @WhereSQL(sql="collectionId=:NftWorks_collectionId")
	public String getCollectionId() {
		return this.collectionId;
	}

     /**
	  * 作品数据地址(图片Base64,上链)
	  * @param value
	  */
	public void setDataPath(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.dataPath = value;
	}
	
	
	
	/**
	 * 作品数据地址(图片Base64,上链)
	 */
     @WhereSQL(sql="dataPath=:NftWorks_dataPath")
	public String getDataPath() {
		return this.dataPath;
	}

     /**
	  * 作品名称
	  * @param value
	  */
	public void setName(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.name = value;
	}
	
	
	
	/**
	 * 作品名称
	 */
     @WhereSQL(sql="name=:NftWorks_name")
	public String getName() {
		return this.name;
	}

     /**
	  * 作品类型
	  * @param value
	  */
	public void setType(Integer value) {
		this.type = value;
	}
	
	
	
	/**
	 * 作品类型
	 */
     @WhereSQL(sql="type=:NftWorks_type")
	public Integer getType() {
		return this.type;
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
     @WhereSQL(sql="link=:NftWorks_link")
	public String getLink() {
		return this.link;
	}

     /**
	  * 作品简介
	  * @param value
	  */
	public void setDetails(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.details = value;
	}
	
	
	
	/**
	 * 作品简介
	 */
     @WhereSQL(sql="details=:NftWorks_details")
	public String getDetails() {
		return this.details;
	}

     /**
	  * 作品价格(初始价格)
	  * @param value
	  */
	public void setPrice(java.math.BigDecimal value) {
		this.price = value;
	}
	
	
	
	/**
	 * 作品价格(初始价格)
	 */
     @WhereSQL(sql="price=:NftWorks_price")
	public java.math.BigDecimal getPrice() {
		return this.price;
	}

     /**
	  * 发行数量
	  * @param value
	  */
	public void setNum(Integer value) {
		this.num = value;
	}
	
	
	
	/**
	 * 发行数量
	 */
     @WhereSQL(sql="num=:NftWorks_num")
	public Integer getNum() {
		return this.num;
	}

     /**
	  * 作品购买者(用户标识,标识之间使用逗号隔开)
	  * @param value
	  */
	public void setBuyers(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.buyers = value;
	}
	
	
	
	/**
	 * 作品购买者(用户标识,标识之间使用逗号隔开)
     * @deprecated 数据表中作品购买者字段使用不合理,已启用,查询作品购买者,修改成从订单表中检索
	 */
     @WhereSQL(sql="buyers=:NftWorks_buyers")
	@Deprecated
	public String getBuyers() {
		return this.buyers;
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
     @WhereSQL(sql="inTime=:NftWorks_inTime")
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
     @WhereSQL(sql="outTime=:NftWorks_outTime")
	public java.util.Date getOutTime() {
		return this.outTime;
	}
		/*
	public String getwaitingTimeString() {
		return DateUtils.convertDate2String(FORMAT_WAITINGTIME, getwaitingTime());
	}
	public void setwaitingTimeString(String value) throws ParseException{
		setwaitingTime(DateUtils.convertString2Date(FORMAT_WAITINGTIME,value));
	}*/

     /**
	  * 发布等待期,日期之后才可购买
	  * @param value
	  */
	public void setWaitingTime(java.util.Date value) {
		this.waitingTime = value;
	}
	
	
	
	/**
	 * 发布等待期,日期之后才可购买
	 */
     @WhereSQL(sql="waitingTime=:NftWorks_waitingTime")
	public java.util.Date getWaitingTime() {
		return this.waitingTime;
	}

    /**
     * 上架后,在不同链平台上链,认证后的数据-json
     */
	@WhereSQL(sql = "chainPlatCert=:NftWorks_chainPlatCert")
    public String getChainPlatCert() {
        return chainPlatCert;
    }

    public void setChainPlatCert(String chainPlatCert) {
        this.chainPlatCert = chainPlatCert;
    }

    /**
	  * 作品状态(0:下架,1:上架,2:售卖中,3已售停)
	  * @param value
	  */
	public void setState(Integer value) {
		this.state = value;
	}
	
	
	
	/**
	 * 作品状态(0:下架,1:上架,2:售卖中,3已售停)
	 */
     @WhereSQL(sql="state=:NftWorks_state")
	public Integer getState() {
		return this.state;
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
     @WhereSQL(sql="createTime=:NftWorks_createTime")
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
     @WhereSQL(sql="updateTime=:NftWorks_updateTime")
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
     @WhereSQL(sql="bak1=:NftWorks_bak1")
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
     @WhereSQL(sql="bak2=:NftWorks_bak2")
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
     @WhereSQL(sql="bak3=:NftWorks_bak3")
	public String getBak3() {
		return this.bak3;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("唯一标识[").append(getId()).append("],")
			.append("作者标识=用户标识[").append(getAuthorId()).append("],")
			.append("合集标识[").append(getCollectionId()).append("],")
			.append("作品数据地址(图片Base64,上链)[").append(getDataPath()).append("],")
			.append("作品名称[").append(getName()).append("],")
			.append("作品类型[").append(getType()).append("],")
			.append("自定义链接[").append(getLink()).append("],")
			.append("作品简介[").append(getDetails()).append("],")
			.append("作品价格(初始价格)[").append(getPrice()).append("],")
			.append("发行数量[").append(getNum()).append("],")
			.append("作品购买者(用户标识,标识之间使用逗号隔开)[").append(getBuyers()).append("],")
			.append("上架时间[").append(getInTime()).append("],")
			.append("下架时间[").append(getOutTime()).append("],")
			.append("发布等待期,日期之后才可购买[").append(getWaitingTime()).append("],")
			.append("作品状态(0:下架,1:上架,2:售卖中,3已售停)[").append(getState()).append("],")
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
		
		if (obj instanceof NftWorks == false){
			return false;
		}
			
		if (this == obj){
			return true;
		}
		
		NftWorks other = (NftWorks)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
