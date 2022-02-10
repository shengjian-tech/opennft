package net.shengjian.makerone.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @descriptions: 用户订单交易历史
 * @author: YSK
 * @date: 2021/12/31 17:00
 * @version: 1.0
 */
public class UserOrderTransHisDTO implements Serializable {
    /**
     * 订单id
     */
    private String id;
    /**
     * 卖方名称
     */
    private String fromUserName;
    /**
     * 买方名称
     */
    private String toUserName;
    /**
     * 交易品名称
     */
    private String assetsName;
    /**
     * 交易品数量
     */
    private Integer num;
    /**
     * 交易总额
     */
    private BigDecimal tradeTotal;
    /**
     * 支付状态
     */
    private Integer payState;

    private Date createTime;

    /**
     * 数据路径
     */
    private String dataPath;
    /**
     * 预支付id
     */
    private String prepayId;
    /**
     * gas费用金额
     */
    private BigDecimal gas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public BigDecimal getTradeTotal() {
        return tradeTotal;
    }

    public void setTradeTotal(BigDecimal tradeTotal) {
        this.tradeTotal = tradeTotal;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDataPath() {
        return dataPath;
    }

    public BigDecimal getGas() {
        return gas;
    }

    public void setGas(BigDecimal gas) {
        this.gas = gas;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }
}
