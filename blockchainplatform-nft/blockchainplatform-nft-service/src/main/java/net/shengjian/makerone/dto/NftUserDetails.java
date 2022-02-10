package net.shengjian.makerone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @descriptions: 用户个人详情
 * @author: YSK
 * @date: 2021/12/30 13:33
 * @version: 1.0
 */
public class NftUserDetails implements Serializable {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 头像地址
     */
    private String avatarPath;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 地址
     */
    private String address;
    /**
     * 地址
     */
    @JsonProperty("EVMAddress")
    private String EVMAddress;
    /**
     * 加入日期
     */
    private Date joinDate;
    /**
     * 藏品数量
     */
    private Integer assetsCount;
    /**
     * 创建数
     */
    private Integer createCount;
    /**
     * 合集数
     */
    private Integer collectionCount;
    /**
     * 交易数
     */
    private Integer transCount;
    /**
     * 关注数
     */
    private Integer likeCount;

    /**
     * 手机号
     */
    private String phone;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEVMAddress() {
        return EVMAddress;
    }

    public void setEVMAddress(String EVMAddress) {
        this.EVMAddress = EVMAddress;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Integer getAssetsCount() {
        return assetsCount;
    }

    public void setAssetsCount(Integer assetsCount) {
        this.assetsCount = assetsCount;
    }

    public Integer getCreateCount() {
        return createCount;
    }

    public void setCreateCount(Integer createCount) {
        this.createCount = createCount;
    }

    public Integer getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
    }

    public Integer getTransCount() {
        return transCount;
    }

    public void setTransCount(Integer transCount) {
        this.transCount = transCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
