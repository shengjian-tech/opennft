package net.shengjian.makerone.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class NftDetialWorksVO implements Serializable {

    private static final long serialVersionUID = 42L;
    /**
     * 作品标识
     */
    private String id;
    /**
     * 作品合集
     */
    private String collectionName;
    /**
     * 图片地址
     */
    private String dataPath;
    /**
     * 作品名称
     */
    private String name;
    /**
     * 作品价格
     */
    private java.math.BigDecimal price;

    /**
     * 当前用户id
     * @return
     */
    private String userId;

    /**
     * 作者Id
     * @return
     */
    private String authorId;

    /**
     * 当前状态
     * @return
     */
    private Integer state;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
}
