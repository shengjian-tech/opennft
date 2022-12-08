package net.shengjian.makerone.entity;
import javax.persistence.Id;
import javax.persistence.Table;


import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;




/**
 * 链平台
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:57:22
 */
@Table(name="nft_chain_plat")
public class NftChainPlat  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "链平台";
	public static final String ALIAS_ID = "唯一标识";
	public static final String ALIAS_ENGLISHNAME = "英文名称";
	public static final String ALIAS_NAME = "链平台名称";
	public static final String ALIAS_DETAILS = "平台描述";
	public static final String ALIAS_NODEHOST = "节点链接地址host=ip+port";
	public static final String ALIAS_AKPATH = "链接节点秘钥路径(classpath路径下)";
	public static final String ALIAS_CONTRACTACCOUNT = "合约账户";
	public static final String ALIAS_CONTRACTADDRESS = "合约地址";
	public static final String ALIAS_BROWSERADDRESS = "区块链浏览器地址";
	public static final String ALIAS_CURRENTSTATE = "当前适配状态 0:适配中,1:适配完成";
	public static final String ALIAS_BALANCE = "root账户余额";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_BAK1 = "bak";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_UPDATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
    /**
     * 唯一标识
     */
    private String id;
    /**
     * 英文名称
     */
    private String englishName;
    /**
     * 链名
     */
    private String chainName;
    /**
     * 链平台名称
     */
    private String name;
    /**
     * 平台描述
     */
    private String details;
    /**
     * 节点链接地址host=ip+port
     */
    private String nodeHost;
    /**
     * ak路径(classpath路径下)
     */
    private String addressPath;

    /**
     * 私钥路径(classpath路径下)
     */
    private String privatePath;
    /**
     * 密码
     */
    private String passwd;
    /**
     * 公钥路径(classpath路径下)
     */
    private String publicPath;
    /**
     * 合约账户
     */
    private String contractAccount;
    /**
     * 合约地址
     */
    private String contractAddress;
    /**
     * 区块链浏览器地址
     */
    private String browserAddress;
    /**
     * 当前适配状态 0:适配中,1:适配完成
     */
    private Integer currentState;
    /**
     * root账户余额
     */
    private java.math.BigDecimal balance;
    /**
     * 创建时间
     */
    private java.util.Date createTime;
    /**
     * 更新时间
     */
    private java.util.Date updateTime;
    /**
     * bak
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
	public NftChainPlat(){
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
     @WhereSQL(sql="id=:NftChainPlat_id")
	public String getId() {
		return this.id;
	}

     /**
	  * 英文名称
	  * @param value
	  */
	public void setEnglishName(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.englishName = value;
	}
	
	
	
	/**
	 * 英文名称
	 */
     @WhereSQL(sql="englishName=:NftChainPlat_englishName")
	public String getEnglishName() {
		return this.englishName;
	}

    /**
     * 链名
     */
    @WhereSQL(sql="chainName=:NftChainPlat_chainName")
    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    /**
	  * 链平台名称
	  * @param value
	  */
	public void setName(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.name = value;
	}
	
	
	
	/**
	 * 链平台名称
	 */
     @WhereSQL(sql="name=:NftChainPlat_name")
	public String getName() {
		return this.name;
	}

     /**
	  * 平台描述
	  * @param value
	  */
	public void setDetails(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.details = value;
	}
	
	
	
	/**
	 * 平台描述
	 */
     @WhereSQL(sql="details=:NftChainPlat_details")
	public String getDetails() {
		return this.details;
	}

     /**
	  * 节点链接地址host=ip+port
	  * @param value
	  */
	public void setNodeHost(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.nodeHost = value;
	}
	
	
	
	/**
	 * 节点链接地址host=ip+port
	 */
     @WhereSQL(sql="nodeHost=:NftChainPlat_nodeHost")
	public String getNodeHost() {
		return this.nodeHost;
	}

    @WhereSQL(sql="addressPath=:NftChainPlat_addressPath")
    public String getAddressPath() {
        return addressPath;
    }

    public void setAddressPath(String addressPath) {
        this.addressPath = addressPath;
    }

    @WhereSQL(sql="privatePath=:NftChainPlat_privatePath")
    public String getPrivatePath() {
        return privatePath;
    }

    public void setPrivatePath(String privatePath) {
        this.privatePath = privatePath;
    }

    @WhereSQL(sql="passwd=:NftChainPlat_passwd")
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @WhereSQL(sql="publicPath=:NftChainPlat_publicPath")
    public String getPublicPath() {
        return publicPath;
    }

    public void setPublicPath(String publicPath) {
        this.publicPath = publicPath;
    }

    /**
	  * 合约账户
	  * @param value
	  */
	public void setContractAccount(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.contractAccount = value;
	}
	
	
	
	/**
	 * 合约账户
	 */
     @WhereSQL(sql="contractAccount=:NftChainPlat_contractAccount")
	public String getContractAccount() {
		return this.contractAccount;
	}

     /**
	  * 合约地址
	  * @param value
	  */
	public void setContractAddress(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.contractAddress = value;
	}
	
	
	
	/**
	 * 合约地址
	 */
     @WhereSQL(sql="contractAddress=:NftChainPlat_contractAddress")
	public String getContractAddress() {
		return this.contractAddress;
	}

     /**
	  * 区块链浏览器地址
	  * @param value
	  */
	public void setBrowserAddress(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.browserAddress = value;
	}
	
	
	
	/**
	 * 区块链浏览器地址
	 */
     @WhereSQL(sql="browserAddress=:NftChainPlat_browserAddress")
	public String getBrowserAddress() {
		return this.browserAddress;
	}

     /**
	  * 当前适配状态 0:适配中,1:适配完成
	  * @param value
	  */
	public void setCurrentState(Integer value) {
		this.currentState = value;
	}
	
	
	
	/**
	 * 当前适配状态 0:适配中,1:适配完成
	 */
     @WhereSQL(sql="currentState=:NftChainPlat_currentState")
	public Integer getCurrentState() {
		return this.currentState;
	}

     /**
	  * root账户余额
	  * @param value
	  */
	public void setBalance(java.math.BigDecimal value) {
		this.balance = value;
	}
	
	
	
	/**
	 * root账户余额
	 */
     @WhereSQL(sql="balance=:NftChainPlat_balance")
	public java.math.BigDecimal getBalance() {
		return this.balance;
	}
		/*
	public String getcreateTimeString() {
		return DateUtils.convertDate2String(FORMAT_CREATETIME, getcreateTime());
	}
	public void setcreateTimeString(String value) throws ParseException{
		setcreateTime(DateUtils.convertString2Date(FORMAT_CREATETIME,value));
	}*/

     /**
	  * 创建时间
	  * @param value
	  */
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	
	
	/**
	 * 创建时间
	 */
     @WhereSQL(sql="createTime=:NftChainPlat_createTime")
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
	  * 更新时间
	  * @param value
	  */
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	
	
	/**
	 * 更新时间
	 */
     @WhereSQL(sql="updateTime=:NftChainPlat_updateTime")
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

     /**
	  * bak
	  * @param value
	  */
	public void setBak1(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.bak1 = value;
	}
	
	
	
	/**
	 * bak
	 */
     @WhereSQL(sql="bak1=:NftChainPlat_bak1")
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
     @WhereSQL(sql="bak2=:NftChainPlat_bak2")
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
     @WhereSQL(sql="bak3=:NftChainPlat_bak3")
	public String getBak3() {
		return this.bak3;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("唯一标识[").append(getId()).append("],")
			.append("英文名称[").append(getEnglishName()).append("],")
			.append("链平台名称[").append(getName()).append("],")
			.append("平台描述[").append(getDetails()).append("],")
			.append("节点链接地址host=ip+port[").append(getNodeHost()).append("],")
			.append("ak路径(classpath路径下)[").append(getAddressPath()).append("],")
			.append("私钥路径(classpath路径下)[").append(getPrivatePath()).append("],")
			.append("公钥路径(classpath路径下)[").append(getPublicPath()).append("],")
			.append("合约账户[").append(getContractAccount()).append("],")
			.append("合约地址[").append(getContractAddress()).append("],")
			.append("区块链浏览器地址[").append(getBrowserAddress()).append("],")
			.append("当前适配状态 0:适配中,1:适配完成[").append(getCurrentState()).append("],")
			.append("root账户余额[").append(getBalance()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("更新时间[").append(getUpdateTime()).append("],")
			.append("bak[").append(getBak1()).append("],")
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
		
		if (obj instanceof NftChainPlat == false){
			return false;
		}
			
		if (this == obj){
			return true;
		}
		
		NftChainPlat other = (NftChainPlat)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
