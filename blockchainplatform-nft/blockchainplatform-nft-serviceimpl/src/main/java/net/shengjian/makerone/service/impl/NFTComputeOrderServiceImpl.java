package net.shengjian.makerone.service.impl;

import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.constant.NFTExceptionConst;
import net.shengjian.makerone.constant.StrategyBeanKeyConst;
import net.shengjian.makerone.dto.ComputeOrderDTO;
import net.shengjian.makerone.service.INFTComputeOrderService;
import net.shengjian.makerone.utils.NFTUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @descriptions: 计算订单价格,默认实现
 * @author: YSK
 * @date: 2021/12/24 16:12
 * @version: 1.0
 */
@Service("default" + StrategyBeanKeyConst.NFT_COMPUTE_SERVICE_IMPL)
public class NFTComputeOrderServiceImpl implements INFTComputeOrderService {

    @Override
    public ComputeOrderDTO computeOrder(BigDecimal price, Integer num, BigDecimal royalties,BigDecimal gasUsed) throws Exception {
        if (price == null || num == null || royalties == null) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        ComputeOrderDTO dto = new ComputeOrderDTO();
        //计算订单实现
        //商品总额
        BigDecimal total = price.multiply(new BigDecimal(num));
        //佣金(支付给平台的费用)=商品总额*全局交易手续费
        BigDecimal commission = total.multiply(CommonConst.getHandlingFee().divide(CommonConst.HUNDRED,CommonConst.DECIMAL_PLACES,RoundingMode.UP))
                .setScale(CommonConst.DECIMAL_PLACES,RoundingMode.UP);
        //税费(二次交易,支付给作者的费用)=商品总额*版税
        BigDecimal taxes = total.multiply(royalties.divide(CommonConst.HUNDRED,CommonConst.DECIMAL_PLACES,RoundingMode.UP))
                .setScale(CommonConst.DECIMAL_PLACES,RoundingMode.UP);
        //支付卖家的金额=商品总额-佣金-税费
        BigDecimal sellerAmount = total.subtract(commission).subtract(taxes);
        //gas转换成RMB
        BigDecimal gasAmount = NFTUtils.gasToRmb(gasUsed);
        //交易总额=商品总额+gas费用
        BigDecimal tradeTotal = total.add(gasAmount);

        dto.setTotal(total);
        dto.setCommission(commission);
        dto.setTaxes(taxes);
        dto.setTradeTotal(tradeTotal);
        dto.setSellerAmount(sellerAmount);
        dto.setGasUsed(gasAmount);
        return dto;
    }
}
