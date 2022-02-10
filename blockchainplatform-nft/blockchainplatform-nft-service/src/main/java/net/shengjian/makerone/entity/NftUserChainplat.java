package net.shengjian.makerone.entity;
import javax.persistence.Id;
import javax.persistence.Table;


import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;




/**
 * 用户所拥有的链平台账号
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:57:36
 */
@Table(name="nft_user_chainplat")
public class NftUserChainplat  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "用户所拥有的链平台账号";
	public static final String ALIAS_ID = "唯一标识";
	public static final String ALIAS_USERID = "用户标识";
	public static final String ALIAS_CHANPLATID = "链平台标识";
	public static final String ALIAS_PRIVATEPATH = "私钥地址";
	public static final String ALIAS_PUBLICPATH = "公钥地址";
	public static final String ALIAS_ADDRESSPATH = "ak地址";
	public static final String ALIAS_PREVTIME = "上次链接时间";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_UPADATETIME = "upadateTime";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
    */
	//date formats
	//public static final String FORMAT_PREVTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_UPADATETIME = DateUtils.DATETIME_FORMAT;
	
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
     * 链平台标识
     */
    private String chainPlatName;
    /**
     * 私钥地址
     */
    private String privatePath;
    /**
     * 超级链开放网络密码
     */
    private String passwd;
    /**
     * 公钥地址
     */
    private String publicPath;
    /**
     * ak地址文件路径
     */
    private String addressPath;
    /**
     * EVMaddress(由address转换得来的.account.xchainAKToEVMAddress)
     */
    /**
     * ak地址文件
     */
    private String address;
    private String EVMAddress;
    /**
     * 助记词
     */
    private String mnemonic;
    /**
     * 上次链接时间
     */
    private java.util.Date prevTime;
    /**
     * createTime
     */
    private java.util.Date createTime;
    /**
     * upadateTime
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
	public NftUserChainplat(){
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
     @WhereSQL(sql="id=:NftUserChainplat_id")
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
     @WhereSQL(sql="userId=:NftUserChainplat_userId")
	public String getUserId() {
		return this.userId;
	}

     /**
	  * 链平台标识
	  * @param value
	  */
	public void setChainPlatName(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.chainPlatName = value;
	}
	
	
	
	/**
	 * 链平台标识
	 */
     @WhereSQL(sql="chainPlatName=:NftUserChainplat_chainPlatName")
	public String getChainPlatName() {
		return this.chainPlatName;
	}

     /**
	  * 私钥地址
	  * @param value
	  */
	public void setPrivatePath(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.privatePath = value;
	}
	
	
	
	/**
	 * 私钥地址
	 */
     @WhereSQL(sql="privatePath=:NftUserChainplat_privatePath")
	public String getPrivatePath() {
		return this.privatePath;
	}

    @WhereSQL(sql="passwd=:NftUserChainplat_passwd")
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
	  * 公钥地址
	  * @param value
	  */
	public void setPublicPath(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.publicPath = value;
	}
	
	
	
	/**
	 * 公钥地址
	 */
     @WhereSQL(sql="publicPath=:NftUserChainplat_publicPath")
	public String getPublicPath() {
		return this.publicPath;
	}

     /**
	  * ak地址
	  * @param value
	  */
	public void setAddressPath(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.addressPath = value;
	}
	
	
	
	/**
	 * ak地址
	 */
     @WhereSQL(sql="addressPath=:NftUserChainplat_addressPath")
	public String getAddressPath() {
		return this.addressPath;
	}

    @WhereSQL(sql="address=:NftUserChainplat_address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * EVMaddress(由address转换得来的.account.xchainAKToEVMAddress)
     */
	@WhereSQL(sql="EVMAddress=:NftUserChainplat_EVMAddress")
    public String getEVMAddress() {
        return EVMAddress;
    }

    public void setEVMAddress(String EVMAddress) {
        this.EVMAddress = EVMAddress;
    }

    /**
     * 助记词
     */
    @WhereSQL(sql="mnemonic=:NftUserChainplat_mnemonic")
    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

	
	
		/*
	public String getprevTimeString() {
		return DateUtils.convertDate2String(FORMAT_PREVTIME, getprevTime());
	}
	public void setprevTimeString(String value) throws ParseException{
		setprevTime(DateUtils.convertString2Date(FORMAT_PREVTIME,value));
	}*/

     /**
	  * 上次链接时间
	  * @param value
	  */
	public void setPrevTime(java.util.Date value) {
		this.prevTime = value;
	}
	
	
	
	/**
	 * 上次链接时间
	 */
     @WhereSQL(sql="prevTime=:NftUserChainplat_prevTime")
	public java.util.Date getPrevTime() {
		return this.prevTime;
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
     @WhereSQL(sql="createTime=:NftUserChainplat_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
		/*
	public String getupadateTimeString() {
		return DateUtils.convertDate2String(FORMAT_UPADATETIME, getupadateTime());
	}
	public void setupadateTimeString(String value) throws ParseException{
		setupadateTime(DateUtils.convertString2Date(FORMAT_UPADATETIME,value));
	}*/

     /**
	  * upadateTime
	  * @param value
	  */
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	
	
	/**
	 * upadateTime
	 */
     @WhereSQL(sql="upadateTime=:NftUserChainplat_upadateTime")
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
     @WhereSQL(sql="bak1=:NftUserChainplat_bak1")
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
     @WhereSQL(sql="bak2=:NftUserChainplat_bak2")
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
     @WhereSQL(sql="bak3=:NftUserChainplat_bak3")
	public String getBak3() {
		return this.bak3;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("唯一标识[").append(getId()).append("],")
			.append("用户标识[").append(getUserId()).append("],")
			.append("链平台标识[").append(getChainPlatName()).append("],")
			.append("私钥地址[").append(getPrivatePath()).append("],")
			.append("公钥地址[").append(getPublicPath()).append("],")
			.append("ak地址[").append(getAddressPath()).append("],")
			.append("上次链接时间[").append(getPrevTime()).append("],")
			.append("createTime[").append(getCreateTime()).append("],")
			.append("upadateTime[").append(getUpdateTime()).append("],")
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
		
		if (obj instanceof NftUserChainplat == false){
			return false;
		}
			
		if (this == obj){
			return true;
		}
		
		NftUserChainplat other = (NftUserChainplat)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
