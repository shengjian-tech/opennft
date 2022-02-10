package net.shengjian.makerone.entity;

import java.io.Serializable;

/**
 * @descriptions: 市场列表
 * @author: YSK
 * @date: 2022/1/4 13:30
 * @version: 1.0
 */
public class NFTMarketDTO implements Serializable {

    private static final long serialVersionUID = 42L;
    /**
     * 集合id
     */
    private String collectionId;
    /**
     * logo图路径
     */
    private String logoPath;
    /**
     * 封面图路径
     */
    private String coverPath;
    /**
     * 合集名称
     */
    private String name;
    /**
     * 作者名称
     */
    private String authorName;
    /**
     * 详情,简介
     */
    private String details;

    /**
     * 作者id
     * @return
     */
    private String authorId;

    /**
     * 当前登录者Id
     * @return
     */
    private String userId;

    /**
     * 当前状态
     * @return
     */
    private Integer state;

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
