package net.shengjian.makerone.entity;
import javax.persistence.Id;
import javax.persistence.Table;


import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;




/**
 * 作品交易历史
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:22
 */
@Table(name="nft_works_his")
public class NftWorksHis  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "作品交易历史";
	public static final String ALIAS_ID = "唯一标识";
	public static final String ALIAS_WORKSID = "作品标识";
	public static final String ALIAS_PRICE = "价格";
	public static final String ALIAS_FROM = "卖方";
	public static final String ALIAS_TO = "买方";
	public static final String ALIAS_TRADETIME = "交易时间";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_UPDATETIME = "updateTime";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
    */
	//date formats
	//public static final String FORMAT_TRADETIME = DateUtils.DATETIME_FORMAT;
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
     * 类型(0:售卖,1:转移)
     */
    private Integer type;
    /**
     * 在不同链平台上链,认证后的数据json(使用区块链浏览器查询)
     */
    private String chainPlatCert;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 价格
     */
    private java.math.BigDecimal price;
    /**
     * 卖方
     */
    private String fromUser;
    /**
     * 买方
     */
    private String toUser;
    /**
     * 交易时间
     */
    private java.util.Date tradeTime;
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
	public NftWorksHis(){
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
     @WhereSQL(sql="id=:NftWorksHis_id")
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

	@WhereSQL(sql = "type=:NftWorksHis_type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    /**
	 * 作品标识
	 */
     @WhereSQL(sql="worksId=:NftWorksHis_worksId")
	public String getWorksId() {
		return this.worksId;
	}

	@WhereSQL(sql = "chainPlatCert=:NftWorksHis_chainPlatCert")
    public String getChainPlatCert() {
        return chainPlatCert;
    }

    public void setChainPlatCert(String chainPlatCert) {
        this.chainPlatCert = chainPlatCert;
    }

    @WhereSQL(sql = "num=:NftWorksHis_num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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
     @WhereSQL(sql="price=:NftWorksHis_price")
	public java.math.BigDecimal getPrice() {
		return this.price;
	}

     /**
	  * 卖方
	  * @param value
	  */
	public void setFromUser(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.fromUser = value;
	}
	
	
	
	/**
	 * 卖方
	 */
     @WhereSQL(sql="fromUser=:NftWorksHis_fromUser")
	public String getFromUser() {
		return this.fromUser;
	}

     /**
	  * 买方
	  * @param value
	  */
	public void setToUser(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.toUser = value;
	}
	
	
	
	/**
	 * 买方
	 */
     @WhereSQL(sql="toUser=:NftWorksHis_toUser")
	public String getToUser() {
		return this.toUser;
	}
		/*
	public String gettradeTimeString() {
		return DateUtils.convertDate2String(FORMAT_TRADETIME, gettradeTime());
	}
	public void settradeTimeString(String value) throws ParseException{
		settradeTime(DateUtils.convertString2Date(FORMAT_TRADETIME,value));
	}*/

     /**
	  * 交易时间
	  * @param value
	  */
	public void setTradeTime(java.util.Date value) {
		this.tradeTime = value;
	}
	
	
	
	/**
	 * 交易时间
	 */
     @WhereSQL(sql="tradeTime=:NftWorksHis_tradeTime")
	public java.util.Date getTradeTime() {
		return this.tradeTime;
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
     @WhereSQL(sql="createTime=:NftWorksHis_createTime")
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
     @WhereSQL(sql="updateTime=:NftWorksHis_updateTime")
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
     @WhereSQL(sql="bak1=:NftWorksHis_bak1")
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
     @WhereSQL(sql="bak2=:NftWorksHis_bak2")
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
     @WhereSQL(sql="bak3=:NftWorksHis_bak3")
	public String getBak3() {
		return this.bak3;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("唯一标识[").append(getId()).append("],")
			.append("作品标识[").append(getWorksId()).append("],")
			.append("交易id(使用区块链浏览器查询)[").append(getChainPlatCert()).append("],")
			.append("价格[").append(getPrice()).append("],")
			.append("卖方[").append(getFromUser()).append("],")
			.append("买方[").append(getToUser()).append("],")
			.append("交易时间[").append(getTradeTime()).append("],")
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
		
		if (obj instanceof NftWorksHis == false){
			return false;
		}
			
		if (this == obj){
			return true;
		}
		
		NftWorksHis other = (NftWorksHis)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
