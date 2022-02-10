package net.shengjian.makerone.vo;

/**
 * @descriptions: 作品参数对象
 * @author: YSK
 * @date: 2021/12/24 11:08
 * @version: 1.0
 */
public class NftWorksVO {
    /**
     * id
     */
    private String id;
    /**
     * 合集标识
     */
    private String collectionId;
    /**
     * 作品数据地址(图片Base64,上链)
     */
    private String dataPath;
    /**
     * 作品名称
     */
    private String name;
    /**
     * 作品类型
     */
    private Integer type;
    /**
     * 自定义链接
     */
    private String link;
    /**
     * 作品简介
     */
    private String details;
    /**
     * 作品价格(初始价格)
     */
    //private java.math.BigDecimal price;
    /**
     * 发行数量
     */
    private Integer num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    /*public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }*/

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
