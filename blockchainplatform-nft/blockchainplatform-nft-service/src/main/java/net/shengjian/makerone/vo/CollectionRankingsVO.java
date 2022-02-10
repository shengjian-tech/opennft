package net.shengjian.makerone.vo;

import java.util.Objects;

public class CollectionRankingsVO {
    //天数
    private Integer date;
    //类型
    private Integer type;
    //链类型
    private String chainType;

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getChainType() {
        return chainType;
    }

    public void setChainType(String chainType) {
        this.chainType = chainType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollectionRankingsVO)) return false;
        CollectionRankingsVO that = (CollectionRankingsVO) o;
        return Objects.equals(date, that.date) && Objects.equals(type, that.type) && Objects.equals(chainType, that.chainType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, type, chainType);
    }

    @Override
    public String toString() {
        return "CollectionRankingsVO{" +
                "date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", chainType='" + chainType + '\'' +
                '}';
    }



}
