package net.shengjian.makerone.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @descriptions: 上架参数对象
 * @author: YSK
 * @date: 2021/12/28 9:19
 * @version: 1.0
 */
public class InShelfVO implements Serializable {
    /**
     * 唯一标识
     */
    private String id;
    /**
     * 上架时间
     */
    //private Date inTime;
    /**
     * 下架时间
     */
    private Date outTime;
    /**
     * 发布等待期,日期之后才可购买
     */
    private Date waitingTime;
    /**
     * 价格
     */
    private BigDecimal price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }*/

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Date getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Date waitingTime) {
        this.waitingTime = waitingTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
