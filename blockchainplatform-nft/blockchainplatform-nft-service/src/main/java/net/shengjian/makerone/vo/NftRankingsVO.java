package net.shengjian.makerone.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class NftRankingsVO implements Serializable {

    private static final long serialVersionUID = 42L;
    /**
     * 合集名称
     */
    private String collectionName;
    /**
     * 交易额
     */
    private BigDecimal sumPrice;
    /**
     * 涨跌幅（24h）
     */
    private double dayChange;
    /**
     * 涨跌幅(7day)
     */
    private double weekChange;
    /**
     * 地板价
     */
    private BigDecimal lowPrice;
    /**
     * 持有人数
     */
    private Integer buyerNum;
    /**
     * 作品数量
     */
    private Integer worksNum;

    /**
     * logo地址
     * @return
     */
    private String logoPath;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public double getDayChange() {
        return dayChange;
    }

    public void setDayChange(double dayChange) {
        this.dayChange = dayChange;
    }

    public double getWeekChange() {
        return weekChange;
    }

    public void setWeekChange(double weekChange) {
        this.weekChange = weekChange;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Integer getBuyerNum() {
        return buyerNum;
    }

    public void setBuyerNum(Integer buyerNum) {
        this.buyerNum = buyerNum;
    }

    public Integer getWorksNum() {
        return worksNum;
    }

    public void setWorksNum(Integer worksNum) {
        this.worksNum = worksNum;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NftRankingsVO)) return false;
        NftRankingsVO that = (NftRankingsVO) o;
        return Double.compare(that.dayChange, dayChange) == 0 && Double.compare(that.weekChange, weekChange) == 0 && Objects.equals(collectionName, that.collectionName) && Objects.equals(sumPrice, that.sumPrice) && Objects.equals(lowPrice, that.lowPrice) && Objects.equals(buyerNum, that.buyerNum) && Objects.equals(worksNum, that.worksNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collectionName, sumPrice, dayChange, weekChange, lowPrice, buyerNum, worksNum);
    }

    @Override
    public String toString() {
        return "NftRankingsVO{" +
                "collectionName='" + collectionName + '\'' +
                ", sumPrice=" + sumPrice +
                ", dayChange=" + dayChange +
                ", weekChange=" + weekChange +
                ", lowPrice=" + lowPrice +
                ", buyerNum=" + buyerNum +
                ", worksNum=" + worksNum +
                '}';
    }
}
