package net.shengjian.makerone.vo;

import java.io.Serializable;
import java.util.Objects;

public class WorksInfoVO implements Serializable {

    private static final long serialVersionUID = 42L;
    /**
     * 作者
     */

    private String author;

    /**
     * 作品特性
     */
    private String detail;

    /**
     * 合集简介
     */
    private String collectionDetail;

    /**
     * 链上信息
     */
    private String chainInfo;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCollectionDetail() {
        return collectionDetail;
    }

    public void setCollectionDetail(String collectionDetail) {
        this.collectionDetail = collectionDetail;
    }

    public String getChainInfo() {
        return chainInfo;
    }

    public void setChainInfo(String chainInfo) {
        this.chainInfo = chainInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorksInfoVO)) return false;
        WorksInfoVO that = (WorksInfoVO) o;
        return Objects.equals(author, that.author) && Objects.equals(detail, that.detail) && Objects.equals(collectionDetail, that.collectionDetail) && Objects.equals(chainInfo, that.chainInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, detail, collectionDetail, chainInfo);
    }

    @Override
    public String toString() {
        return "WorksInfoVO{" +
                "author='" + author + '\'' +
                ", detail='" + detail + '\'' +
                ", collectionDetail='" + collectionDetail + '\'' +
                ", chainInfo='" + chainInfo + '\'' +
                '}';
    }
}
