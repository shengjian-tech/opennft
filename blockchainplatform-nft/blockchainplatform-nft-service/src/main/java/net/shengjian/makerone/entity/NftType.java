package net.shengjian.makerone.entity;
import javax.persistence.Id;
import javax.persistence.Table;


import net.shengjian.frame.annotation.WhereSQL;
import net.shengjian.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;




/**
 * 作品类型
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:16
 */
@Table(name="nft_type")
public class NftType  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "作品类型";
	public static final String ALIAS_ID = "唯一标识";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_VALUE = "值";
    */
	//date formats
	
	//columns START
    /**
     * 唯一标识
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 值
     */
    private Integer value;
	//columns END 数据库字段结束
	
	//concstructor
	public NftType(){
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
     @WhereSQL(sql="id=:NftType_id")
	public String getId() {
		return this.id;
	}

     /**
	  * 名称
	  * @param value
	  */
	public void setName(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.name = value;
	}
	
	
	
	/**
	 * 名称
	 */
     @WhereSQL(sql="name=:NftType_name")
	public String getName() {
		return this.name;
	}

     /**
	  * 值
	  * @param value
	  */
	public void setValue(Integer value) {
		this.value = value;
	}
	
	
	
	/**
	 * 值
	 */
     @WhereSQL(sql="value=:NftType_value")
	public Integer getValue() {
		return this.value;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("唯一标识[").append(getId()).append("],")
			.append("名称[").append(getName()).append("],")
			.append("值[").append(getValue()).append("],")
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
		
		if (obj instanceof NftType == false){
			return false;
		}
			
		if (this == obj){
			return true;
		}
		
		NftType other = (NftType)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
