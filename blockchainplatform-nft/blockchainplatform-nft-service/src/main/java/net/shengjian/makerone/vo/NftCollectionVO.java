package net.shengjian.makerone.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @descriptions:
 * @author: YSK
 * @date: 2021/12/24 9:51
 * @version: 1.0
 */
public class NftCollectionVO implements Serializable {
    /**
     * 主键
     */
    private String id;
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
    private BigDecimal royalties;
    /**
     * 上架时间
     */
    private java.util.Date inTime;
    /**
     * 下架时间
     */
    private java.util.Date outTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getBannerPath() {
        return bannerPath;
    }

    public void setBannerPath(String bannerPath) {
        this.bannerPath = bannerPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getRoyalties() {
        return royalties;
    }

    public void setRoyalties(BigDecimal royalties) {
        this.royalties = royalties;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }
}
