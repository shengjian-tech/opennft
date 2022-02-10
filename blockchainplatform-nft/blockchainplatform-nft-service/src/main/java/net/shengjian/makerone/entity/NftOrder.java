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
 * 用户交易订单
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:34
 */
@Table(name="nft_order")
public class NftOrder  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "用户交易订单";
	public static final String ALIAS_ID = "唯一标识";
	public static final String ALIAS_CHAINPLATCERT = "交易标识(属于哪一个链平台)";
	public static final String ALIAS_GAS = "gas费";
	public static final String ALIAS_FROM = "卖方";
	public static final String ALIAS_TO = "买方";
	public static final String ALIAS_ASSETSID = "交易品标识";
	public static final String ALIAS_NUM = "交易品数量";
	public static final String ALIAS_PRICE = "单价";
	public static final String ALIAS_TOTAL = "商品总额";
	public static final String ALIAS_COMMISSION = "佣金(支付给平台的费用)";
	public static final String ALIAS_TAXES = "税费(二次交易,支付给作者的费用)";
	public static final String ALIAS_TRADETOTAL = "交易总额";
	public static final String ALIAS_PAYPLAT = "支付平台(0:微信支付,1支付宝...)";
	public static final String ALIAS_TRADETYPE = "交易类型(0:充值,1:上架合集,2:下架合集,3:上架商品,4:下架商品,5:购买商品6:卖出商品)";
	public static final String ALIAS_TXTIME = "交易时间";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_UPDATETIME = "updateTime";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
    */
	//date formats
	//public static final String FORMAT_TXTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_UPDATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
    /**
     * 唯一标识
     */
    private String id;
    /**
     * 在不同链平台上链,认证后的数据json
     */
    private String chainPlatCert;
    /**
     * gas费
     */
    private BigDecimal gas;
    /**
     * 卖方
     */
    private String fromUser;
    /**
     * 买方
     */
    private String toUser;
    /**
     * 交易品标识
     */
    private String assetsId;
    /**
     * 交易品数量
     */
    private Integer num;
    /**
     * 单价
     */
    private java.math.BigDecimal price;
    /**
     * 商品总额
     */
    private java.math.BigDecimal total;
    /**
     * 佣金(支付给平台的费用)
     */
    private java.math.BigDecimal commission;
    /**
     * 税费(二次交易,支付给作者的费用)
     */
    private java.math.BigDecimal taxes;
    /**
     * 卖家收到的金额
     */
    private java.math.BigDecimal sellerAmount;
    /**
     * 交易总额
     */
    private java.math.BigDecimal tradeTotal;
    /**
     * 支付平台(0:微信支付,1支付宝...)
     */
    private Integer payPlat;
    /**
     * 交易类型(0:充值,1:上架合集,2:下架合集,3:上架商品,4:下架商品,5:购买商品6:卖出商品)
     */
    private Integer tradeType;
    /**
     * 交易时间
     */
    private java.util.Date txTime;

    /**
     * 支付平台支付订单号
     */
    private String transactionId;
    /**
     * 支付状态.0:未支付,1:已支付
     */
    private Integer payState;
    /**
     * 支付二维码
     */
    private String codeUrl;
    /**
     * 预支付id
     */
    private String prepayId;
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
	public NftOrder(){
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
     @WhereSQL(sql="id=:NftOrder_id")
	public String getId() {
		return this.id;
	}

	@WhereSQL(sql = "chainPlatCert=:NftOrder_chainPlatCert")
    public String getChainPlatCert() {
        return chainPlatCert;
    }

    public void setChainPlatCert(String chainPlatCert) {
        this.chainPlatCert = chainPlatCert;
    }

    /**
	  * gas费
	  * @param value
	  */
	public void setGas(BigDecimal value) {
		this.gas = value;
	}
	
	
	
	/**
	 * gas费
	 */
     @WhereSQL(sql="gas=:NftOrder_gas")
	public BigDecimal getGas() {
		return this.gas;
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
     @WhereSQL(sql="fromUser=:NftOrder_fromUser")
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
     @WhereSQL(sql="toUser=:NftOrder_toUser")
	public String getToUser() {
		return this.toUser;
	}

     /**
	  * 交易品标识
	  * @param value
	  */
	public void setAssetsId(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.assetsId = value;
	}
	
	
	
	/**
	 * 交易品标识
	 */
     @WhereSQL(sql="assetsId=:NftOrder_assetsId")
	public String getAssetsId() {
		return this.assetsId;
	}

     /**
	  * 交易品数量
	  * @param value
	  */
	public void setNum(Integer value) {
		this.num = value;
	}
	
	
	
	/**
	 * 交易品数量
	 */
     @WhereSQL(sql="num=:NftOrder_num")
	public Integer getNum() {
		return this.num;
	}

     /**
	  * 单价
	  * @param value
	  */
	public void setPrice(java.math.BigDecimal value) {
		this.price = value;
	}
	
	
	
	/**
	 * 单价
	 */
     @WhereSQL(sql="price=:NftOrder_price")
	public java.math.BigDecimal getPrice() {
		return this.price;
	}

     /**
	  * 商品总额
	  * @param value
	  */
	public void setTotal(java.math.BigDecimal value) {
		this.total = value;
	}
	
	
	
	/**
	 * 商品总额
	 */
     @WhereSQL(sql="total=:NftOrder_total")
	public java.math.BigDecimal getTotal() {
		return this.total;
	}

     /**
	  * 佣金(支付给平台的费用)
	  * @param value
	  */
	public void setCommission(java.math.BigDecimal value) {
		this.commission = value;
	}
	
	
	
	/**
	 * 佣金(支付给平台的费用)
	 */
     @WhereSQL(sql="commission=:NftOrder_commission")
	public java.math.BigDecimal getCommission() {
		return this.commission;
	}

     /**
	  * 税费(二次交易,支付给作者的费用)
	  * @param value
	  */
	public void setTaxes(java.math.BigDecimal value) {
		this.taxes = value;
	}
	
	
	
	/**
	 * 税费(二次交易,支付给作者的费用)
	 */
     @WhereSQL(sql="taxes=:NftOrder_taxes")
	public java.math.BigDecimal getTaxes() {
		return this.taxes;
	}

     /**
	  * 交易总额
	  * @param value
	  */
	public void setTradeTotal(java.math.BigDecimal value) {
		this.tradeTotal = value;
	}
	
	
	
	/**
	 * 交易总额
	 */
     @WhereSQL(sql="tradeTotal=:NftOrder_tradeTotal")
	public java.math.BigDecimal getTradeTotal() {
		return this.tradeTotal;
	}

    @WhereSQL(sql="sellerAmount=:NftOrder_sellerAmount")
    public BigDecimal getSellerAmount() {
        return sellerAmount;
    }

    public void setSellerAmount(BigDecimal sellerAmount) {
        this.sellerAmount = sellerAmount;
    }

    /**
	  * 支付平台(0:微信支付,1支付宝...)
	  * @param value
	  */
	public void setPayPlat(Integer value) {
		this.payPlat = value;
	}
	
	
	
	/**
	 * 支付平台(0:微信支付,1支付宝...)
	 */
     @WhereSQL(sql="payPlat=:NftOrder_payPlat")
	public Integer getPayPlat() {
		return this.payPlat;
	}

     /**
	  * 交易类型(0:充值,1:上架合集,2:下架合集,3:上架商品,4:下架商品,5:购买商品6:卖出商品)
	  * @param value
	  */
	public void setTradeType(Integer value) {
		this.tradeType = value;
	}
	
	
	
	/**
	 * 交易类型(0:充值,1:上架合集,2:下架合集,3:上架商品,4:下架商品,5:购买商品6:卖出商品)
	 */
     @WhereSQL(sql="tradeType=:NftOrder_tradeType")
	public Integer getTradeType() {
		return this.tradeType;
	}
		/*
	public String gettxTimeString() {
		return DateUtils.convertDate2String(FORMAT_TXTIME, gettxTime());
	}
	public void settxTimeString(String value) throws ParseException{
		settxTime(DateUtils.convertString2Date(FORMAT_TXTIME,value));
	}*/

     /**
	  * 交易时间
	  * @param value
	  */
	public void setTxTime(java.util.Date value) {
		this.txTime = value;
	}
	
	
	
	/**
	 * 交易时间
	 */
     @WhereSQL(sql="txTime=:NftOrder_txTime")
	public java.util.Date getTxTime() {
		return this.txTime;
	}
		/*
	public String getcreateTimeString() {
		return DateUtils.convertDate2String(FORMAT_CREATETIME, getcreateTime());
	}
	public void setcreateTimeString(String value) throws ParseException{
		setcreateTime(DateUtils.convertString2Date(FORMAT_CREATETIME,value));
	}*/

    /**
     * 支付状态.0:未支付,1:已支付
     * @return
     */
    @WhereSQL(sql="payState=:NftOrder_payState")
    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    @WhereSQL(sql="transactionId=:NftOrder_transactionId")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @WhereSQL(sql="codeUrl=:NftOrder_codeUrl")
    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    @WhereSQL(sql="prepayId=:NftOrder_prepayId")
    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

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
     @WhereSQL(sql="createTime=:NftOrder_createTime")
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
     @WhereSQL(sql="updateTime=:NftOrder_updateTime")
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
     @WhereSQL(sql="bak1=:NftOrder_bak1")
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
     @WhereSQL(sql="bak2=:NftOrder_bak2")
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
     @WhereSQL(sql="bak3=:NftOrder_bak3")
	public String getBak3() {
		return this.bak3;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("唯一标识[").append(getId()).append("],")
			.append("gas费[").append(getGas()).append("],")
			.append("卖方[").append(getFromUser()).append("],")
			.append("买方[").append(getToUser()).append("],")
			.append("交易品标识[").append(getAssetsId()).append("],")
			.append("交易品数量[").append(getNum()).append("],")
			.append("单价[").append(getPrice()).append("],")
			.append("商品总额[").append(getTotal()).append("],")
			.append("佣金(支付给平台的费用)[").append(getCommission()).append("],")
			.append("税费(二次交易,支付给作者的费用)[").append(getTaxes()).append("],")
			.append("交易总额[").append(getTradeTotal()).append("],")
			.append("支付平台(0:微信支付,1支付宝...)[").append(getPayPlat()).append("],")
			.append("交易类型(0:充值,1:上架合集,2:下架合集,3:上架商品,4:下架商品,5:购买商品6:卖出商品)[").append(getTradeType()).append("],")
			.append("交易时间[").append(getTxTime()).append("],")
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
		
		if (obj instanceof NftOrder == false){
			return false;
		}
			
		if (this == obj){
			return true;
		}
		
		NftOrder other = (NftOrder)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
