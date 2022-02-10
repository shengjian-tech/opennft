package net.shengjian.makerone.vo;

import java.io.Serializable;

/**
 * @descriptions: 生成订单参数对象
 * @author: YSK
 * @date: 2021/12/28 17:58
 * @version: 1.0
 */
public class GenerateOrderVO implements Serializable {
    /**
     * 作品id
     */
    private String worksId;
    /**
     * 卖方
     */
    private String sellerId;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 交易类型
     */
    private Integer tradeType;

    public String getWorksId() {
        return worksId;
    }

    public void setWorksId(String worksId) {
        this.worksId = worksId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }
}
