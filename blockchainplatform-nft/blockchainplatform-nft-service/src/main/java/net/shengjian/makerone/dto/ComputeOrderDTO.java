package net.shengjian.makerone.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @descriptions: 计算订单金额
 * @author: YSK
 * @date: 2021/12/28 17:13
 * @version: 1.0
 */
public class ComputeOrderDTO implements Serializable {
    /**
     * 商品总额
     */
    private BigDecimal total;
    /**
     * 转换成RMB后的gas费用
     */
    private BigDecimal gasUsed;
    /**
     * 佣金(支付给平台的费用)
     */
    private BigDecimal commission;
    /**
     * 税费(二次交易,支付给作者的费用)
     */
    private BigDecimal taxes;
    /**
     * 支付给卖家的费用
     */
    private BigDecimal sellerAmount;
    /**
     * 交易总额
     */
    private BigDecimal tradeTotal;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(BigDecimal gasUsed) {
        this.gasUsed = gasUsed;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public BigDecimal getSellerAmount() {
        return sellerAmount;
    }

    public void setSellerAmount(BigDecimal sellerAmount) {
        this.sellerAmount = sellerAmount;
    }

    public BigDecimal getTradeTotal() {
        return tradeTotal;
    }

    public void setTradeTotal(BigDecimal tradeTotal) {
        this.tradeTotal = tradeTotal;
    }
}
