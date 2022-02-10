package net.shengjian.makerone.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class WorksSameVO implements Serializable {

    private static final long serialVersionUID = 42L;
    /**
     * 作品地址
     */
    private String path;
    /**
     * 作品合集名称
     */
    private String collectionName;
    /**
     * 作品编号
     */
    private String workcode;
    /**
     * 作品价格
     */
    private BigDecimal price;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getWorkcode() {
        return workcode;
    }

    public void setWorkcode(String workcode) {
        this.workcode = workcode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof WorksSameVO)) return false;
        WorksSameVO that = (WorksSameVO) o;
        return Objects.equals(path, that.path) && Objects.equals(collectionName, that.collectionName) && Objects.equals(workcode, that.workcode) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, collectionName, workcode, price);
    }

    @Override
    public String toString() {
        return "WorksSameVO{" +
                "path='" + path + '\'' +
                ", collectionName='" + collectionName + '\'' +
                ", workcode='" + workcode + '\'' +
                ", price=" + price +
                '}';
    }
}
