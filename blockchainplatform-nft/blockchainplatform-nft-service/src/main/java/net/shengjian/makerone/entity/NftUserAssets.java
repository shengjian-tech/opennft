package net.shengjian.makerone.entity;
import javax.persistence.Id;
import javax.persistence.Table;


import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;




/**
 * 用户资产信息
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:02
 */
@Table(name="nft_user_assets")
public class NftUserAssets  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "用户资产信息";
	public static final String ALIAS_ID = "唯一标识";
	public static final String ALIAS_USERID = "用户标识";
	public static final String ALIAS_ASSETSID = "资源标识";
	public static final String ALIAS_TYPE = "资源类型0:合集,1:作品";
	public static final String ALIAS_ORIGIN = "资产来源(0:自己创作,1购买其他作者)";
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
     * 唯一标识
     */
    private String id;
    /**
     * 用户标识
     */
    private String userId;
    /**
     * 资源标识
     */
    private String assetsId;
    /**
     * 资源类型0:合集,1:作品
     */
    private Integer type;
    /**
     * 资产来源(0:自己创作,1购买其他作者)
     */
    private Integer origin;
    /**
     * 出售状态,0:不出售,1:售卖中,2:已生成订单
     */
    private Integer sellState;
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
	public NftUserAssets(){
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
     @WhereSQL(sql="id=:NftUserAssets_id")
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
     @WhereSQL(sql="userId=:NftUserAssets_userId")
	public String getUserId() {
		return this.userId;
	}

     /**
	  * 资源标识
	  * @param value
	  */
	public void setAssetsId(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.assetsId = value;
	}
	
	
	
	/**
	 * 资源标识
	 */
     @WhereSQL(sql="assetsId=:NftUserAssets_assetsId")
	public String getAssetsId() {
		return this.assetsId;
	}

     /**
	  * 资源类型0:合集,1:作品
	  * @param value
	  */
	public void setType(Integer value) {
		this.type = value;
	}
	
	
	
	/**
	 * 资源类型0:合集,1:作品
	 */
     @WhereSQL(sql="type=:NftUserAssets_type")
	public Integer getType() {
		return this.type;
	}

     /**
	  * 资产来源(0:自己创作,1购买其他作者)
	  * @param value
	  */
	public void setOrigin(Integer value) {
		this.origin = value;
	}
	
	
	
	/**
	 * 资产来源(0:自己创作,1购买其他作者)
	 */
     @WhereSQL(sql="origin=:NftUserAssets_origin")
	public Integer getOrigin() {
		return this.origin;
	}
		/*
	public String getcreateTimeString() {
		return DateUtils.convertDate2String(FORMAT_CREATETIME, getcreateTime());
	}
	public void setcreateTimeString(String value) throws ParseException{
		setcreateTime(DateUtils.convertString2Date(FORMAT_CREATETIME,value));
	}*/

    @WhereSQL(sql="sellState=:NftUserAssets_sellState")
    public Integer getSellState() {
        return sellState;
    }

    public void setSellState(Integer sellState) {
        this.sellState = sellState;
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
     @WhereSQL(sql="createTime=:NftUserAssets_createTime")
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
     @WhereSQL(sql="updateTime=:NftUserAssets_updateTime")
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
     @WhereSQL(sql="bak1=:NftUserAssets_bak1")
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
     @WhereSQL(sql="bak2=:NftUserAssets_bak2")
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
     @WhereSQL(sql="bak3=:NftUserAssets_bak3")
	public String getBak3() {
		return this.bak3;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("唯一标识[").append(getId()).append("],")
			.append("用户标识[").append(getUserId()).append("],")
			.append("资源标识[").append(getAssetsId()).append("],")
			.append("资源类型0:合集,1:作品[").append(getType()).append("],")
			.append("资产来源(0:自己创作,1购买其他作者)[").append(getOrigin()).append("],")
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
		
		if (obj instanceof NftUserAssets == false){
			return false;
		}
			
		if (this == obj){
			return true;
		}
		
		NftUserAssets other = (NftUserAssets)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
