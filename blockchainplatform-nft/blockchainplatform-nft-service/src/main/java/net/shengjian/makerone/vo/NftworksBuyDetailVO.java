package net.shengjian.makerone.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class NftworksBuyDetailVO implements Serializable {
    private static final long serialVersionUID = 42L;
    /**
     * 当前登录用户的id
     */

    private String userId;

    /**
     * 作者id
     */
    private String authorId;

    /**
     * 是否上架的状态
     */
    private Integer state;
    /**
     * 作品logo图地址
     */
    private String logoPath;

    /**
     * 合集名称
     */
    private String collectionName;

    /**
     * 作品名称
     */
    private String worksName;
    /**
     * 作品编号
     */
    private String worksNum;

    /**
     * 作品持有者
     */
    private List<String> buyer;

    /**
     * 当前价格
     */
    private BigDecimal price;

    /**
     * 剩余数量
     */
    private Integer remainingNum;

    /**
     * 发行总量
     */
    private Integer totalNumber;


    @Override
    public String toString() {
        return "NftworksBuyDetailVO{" +
                "userId='" + userId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", state='" + state + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", collectionName='" + collectionName + '\'' +
                ", worksNum='" + worksNum + '\'' +
                ", buyer=" + buyer +
                ", price=" + price +
                '}';
    }

    public String getWorksName() {
        return worksName;
    }

    public void setWorksName(String worksName) {
        this.worksName = worksName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getWorksNum() {
        return worksNum;
    }

    public void setWorksNum(String worksNum) {
        this.worksNum = worksNum;
    }

    public List<String> getBuyer() {
        return buyer;
    }

    public void setBuyer(List<String> buyer) {
        this.buyer = buyer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getRemainingNum() {
        return remainingNum;
    }

    public void setRemainingNum(Integer remainingNum) {
        this.remainingNum = remainingNum;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }
}
