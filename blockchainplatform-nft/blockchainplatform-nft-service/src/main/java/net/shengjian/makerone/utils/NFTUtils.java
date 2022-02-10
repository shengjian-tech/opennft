package net.shengjian.makerone.utils;

import net.shengjian.makerone.constant.CommonConst;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @descriptions:
 * @author: YSK
 * @date: 2022/1/14 10:45
 * @version: 1.0
 */
public class NFTUtils {
    /**
     * gas转换成RMB
     * @param gas gas
     * @return rmb 元
     */
    public static BigDecimal gasToRmb(BigDecimal gas){
        return gas.divide(CommonConst.GAS_RATIO,CommonConst.DECIMAL_PLACES, RoundingMode.UP);
    }
}
