package net.shengjian.makerone.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @descriptions: 用户收藏的作品
 * @author: YSK
 * @date: 2021/12/31 14:08
 * @version: 1.0
 */
public class UserCollectionWorksDTO implements Serializable {
    /**
     * id
     */
    private String id;
    /**
     * 作品id
     */
    private String worksId;
    /**
     * 数据路径
     */
    private String dataPath;
    /**
     * 名称
     */
    private String name;
    /**
     * 链上编号
     */
    private String no;
    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 冷却期 剩余天数
     */
    private Long day;

    public String getWorksId() {
        return worksId;
    }

    public void setWorksId(String worksId) {
        this.worksId = worksId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }
}
