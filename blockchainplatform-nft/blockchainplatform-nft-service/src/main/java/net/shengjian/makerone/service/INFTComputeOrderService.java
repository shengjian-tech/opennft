package net.shengjian.makerone.service;

import net.shengjian.makerone.dto.ComputeOrderDTO;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;

import java.math.BigDecimal;

/**
 * @descriptions: nft订单价格计算接口
 * @author: YSK
 * @date: 2021/12/24 16:11
 * @version: 1.0
 */
@RpcServiceAnnotation
public interface INFTComputeOrderService {

    /**
     * 计算订单金额
     * @param price 商品单价
     * @param num 购买数量
     * @param royalties 合集版税
     * @return 计算后的金额
     * @throws Exception 计算错误抛出的异常
     */
    ComputeOrderDTO computeOrder(BigDecimal price, Integer num, BigDecimal royalties,BigDecimal gasUsed) throws Exception;
}
