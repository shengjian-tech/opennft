package net.shengjian.makerone.vo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class CollectionDetailVO implements Serializable {      //alias

    private static final long serialVersionUID = 42L;
    /**
     * 唯一标识
     */
    private String id;
    /**
     * 用户标识
     */
    private String userId;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 作品总数
     */
    private Integer counts;
    /**
     * 拥有数
     */
    private Integer possessnum;
    /**
     * 地板价
     */
    private BigDecimal lowPrice;
    /**
     * 交易总额
     */
    private java.math.BigDecimal tradesum;

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
     * 合集版税
     */
    private java.math.BigDecimal royalties;
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
     * 产品列表
     */
    private List<NftDetialWorksVO> nftWorksList;

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
    public CollectionDetailVO() {
    }


    //get and set

    /**
     * 唯一标识
     *
     * @param value
     */
    public void setId(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.id = value;
    }


    /**
     * 唯一标识
     */
    @Id
    public String getId() {
        return this.id;
    }

    /**
     * 用户标识
     *
     * @param value
     */
    public void setUserId(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.userId = value;
    }


    /**
     * 用户标识
     */
    public String getUserId() {
        return this.userId;
    }


    /**
     * 获取用户名称
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名称
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取用户头像
     * @return
     */

    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置用户头像
     * @param avatar
     */

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getPossessnum() {
        return possessnum;
    }

    public void setPossessnum(Integer possessnum) {
        this.possessnum = possessnum;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public java.math.BigDecimal getTradesum() {
        return tradesum;
    }

    public void setTradesum(java.math.BigDecimal tradesum) {
        this.tradesum = tradesum;
    }

    /**
     * logo图地址
     *
     * @param value
     */
    public void setLogoPath(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.logoPath = value;
    }


    /**
     * logo图地址
     */
    public String getLogoPath() {
        return this.logoPath;
    }

    /**
     * 合集封面地址
     *
     * @param value
     */
    public void setCoverPath(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.coverPath = value;
    }


    /**
     * 合集封面地址
     */
    public String getCoverPath() {
        return this.coverPath;
    }

    /**
     * banner图地址
     *
     * @param value
     */
    public void setBannerPath(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bannerPath = value;
    }


    /**
     * banner图地址
     */
    public String getBannerPath() {
        return this.bannerPath;
    }

    /**
     * 合集名称
     *
     * @param value
     */
    public void setName(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.name = value;
    }


    /**
     * 合集名称
     */

    public String getName() {
        return this.name;
    }

    /**
     * 自定义链接
     *
     * @param value
     */
    public void setLink(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.link = value;
    }

    public List<NftDetialWorksVO> getNftWorksList() {
        return nftWorksList;
    }

    public void setNftWorksList(List<NftDetialWorksVO> nftWorksList) {
        this.nftWorksList = nftWorksList;
    }

    /**
     * 自定义链接
     */
    public String getLink() {
        return this.link;
    }

    /**
     * 合集简介
     *
     * @param value
     */
    public void setDetails(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.details = value;
    }


    /**
     * 合集简介
     */
    public String getDetails() {
        return this.details;
    }

    /**
     * 合集类别
     *
     * @param value
     */
    public void setType(Integer value) {
        this.type = value;
    }


    /**
     * 合集类别
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * 合集版税
     *
     * @param value
     */
    public void setRoyalties(java.math.BigDecimal value) {
        this.royalties = value;
    }


    /**
     * 合集版税
     */
    public java.math.BigDecimal getRoyalties() {
        return this.royalties;
    }

    /**
     * 上架后,在不同链平台上链,认证后的数据-json
     */
    public String getChainPlatCert() {
        return chainPlatCert;
    }

    public void setChainPlatCert(String chainPlatCert) {
        this.chainPlatCert = chainPlatCert;
    }

    /**
     * 是否上架 0:否,1:是
     *
     * @param value
     */
    public void setIsIn(Integer value) {
        this.isIn = value;
    }


    /**
     * 是否上架 0:否,1:是
     */
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
     *
     * @param value
     */
    public void setInTime(java.util.Date value) {
        this.inTime = value;
    }


    /**
     * 上架时间
     */
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
     *
     * @param value
     */
    public void setOutTime(java.util.Date value) {
        this.outTime = value;
    }


    /**
     * 下架时间
     */
    public java.util.Date getOutTime() {
        return this.outTime;
    }

    /**
     * 是否平台认证,0:否,1是
     *
     * @param value
     */
    public void setIsCert(Integer value) {
        this.isCert = value;
    }


    /**
     * 是否平台认证,0:否,1是
     */
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
     *
     * @param value
     */
    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }


    /**
     * createTime
     */
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
     *
     * @param value
     */
    public void setUpdateTime(java.util.Date value) {
        this.updateTime = value;
    }


    /**
     * updateTime
     */
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * bak1
     *
     * @param value
     */
    public void setBak1(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak1 = value;
    }


    /**
     * bak1
     */
    public String getBak1() {
        return this.bak1;
    }

    /**
     * bak2
     *
     * @param value
     */
    public void setBak2(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak2 = value;
    }


    /**
     * bak2
     */
    public String getBak2() {
        return this.bak2;
    }

    /**
     * bak3
     *
     * @param value
     */
    public void setBak3(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak3 = value;
    }


    /**
     * bak3
     */
    public String getBak3() {
        return this.bak3;
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

        if (obj == null) {
            return false;
        }

        if (obj instanceof net.shengjian.makerone.entity.NftCollection == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        net.shengjian.makerone.entity.NftCollection other = (net.shengjian.makerone.entity.NftCollection) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}




