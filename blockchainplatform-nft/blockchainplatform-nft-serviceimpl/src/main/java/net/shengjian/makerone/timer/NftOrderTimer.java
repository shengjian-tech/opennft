package net.shengjian.makerone.timer;

import net.shengjian.frame.cache.RedisOperation;
import net.shengjian.makerone.constant.CachePrefixConst;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.entity.NftOrder;
import net.shengjian.makerone.enums.EPayState;
import net.shengjian.makerone.service.INftOrderService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @descriptions: 监听刷新订单状态定时器
 * @author: YSK
 * @date: 2022/1/12 10:54
 * @version: 1.0
 */
@Component
public class NftOrderTimer {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private INftOrderService nftOrderService;

    @Resource
    private RedisOperation redisOperation;

    /**
     * 取消订单
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void cancelOrder() throws Exception {
        //定时任务锁
        final boolean lock = redisOperation.lock(CachePrefixConst.LOCK_PREFIX+CommonConst.CANCEL_ORDER_TIMED_TASK_LOCK, 1000 * 5);
        if(!lock){
            return;
        }
        System.out.println("net.shengjian.makerone.timer.NftOrderTimer.cancelOrder=>[0/10 * * * * ?],当前时间==>["+new Date()+"]");
        //查詢15分钟前未支付的订单
        List<NftOrder> listBy = nftOrderService.findListByMinuteBefore(EPayState.未支付,15);
        if(CollectionUtils.isEmpty(listBy)){
            return;
        }
        for (NftOrder order : listBy) {
            Boolean ok = nftOrderService.updateCancelOrder(order);
            if(ok){
                logger.info("订单编号:[{}],超过15分钟未支付!已被更新为取消状态!",order.getId());
            }
        }
        redisOperation.unlock(CachePrefixConst.LOCK_PREFIX+CommonConst.CANCEL_ORDER_TIMED_TASK_LOCK);
    }
}
