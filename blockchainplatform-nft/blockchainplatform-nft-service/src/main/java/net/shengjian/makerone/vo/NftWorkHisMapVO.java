package net.shengjian.makerone.vo;

import java.math.BigDecimal;
import java.util.Date;

public class NftWorkHisMapVO {
    /**
     *时间
     */
    private Date date;

    /**
     * 价格
     */
    private BigDecimal price;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "NftWorkHisMapVO{" +
                "date='" + date + '\'' +
                ", price=" + price +
                '}';
    }
}
