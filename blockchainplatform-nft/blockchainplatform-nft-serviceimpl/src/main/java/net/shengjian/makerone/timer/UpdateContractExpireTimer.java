package net.shengjian.makerone.timer;

import net.shengjian.frame.cache.RedisOperation;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.makerone.constant.CachePrefixConst;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.service.INftWorksService;
import net.shengjian.makerone.strategy.context.ChainExecStrategyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @descriptions: 更新合约时间定时器
 * @author: YSK
 * @date: 2022/1/22 17:24
 * @version: 1.0
 */
@Component
@DependsOn(value={"commonConst"})
public class UpdateContractExpireTimer {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ChainExecStrategyContext chainExecStrategyContext;

    @Resource
    private RedisOperation redisOperation;

    @Resource
    private INftWorksService nftWorksService;

    @PostConstruct
    public void init(){
        try {
            Map<String, String> rs = chainExecStrategyContext.rootQuery(EChainPlat.百度超级链, CommonConst.getNftContractName(), "getTimeStamp", new HashMap<>());
            List list = JsonUtils.readValue(rs.get("bodyStr"), List.class);
            String timpstamp = ((Map) list.get(0)).get("0").toString();
            System.out.println("timpstamp = " + timpstamp);
            CommonConst.CONTRACT_NOW_TIMESTAMP = Long.parseLong(timpstamp);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }
    /**
     * 定时更新合约内的时间
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void updateContractExpire() throws Exception {
        //定时任务锁
        final boolean lock = redisOperation.lock(CachePrefixConst.LOCK_PREFIX+CommonConst.UPDATE_CONTRACT_EXPIRE_LOCK, 1000 * 5);
        if(!lock){
            return;
        }
        System.out.println("net.shengjian.makerone.timer.UpdateContractExpireTimer.updateContractExpire=>[0 0 1 * * ?],当前时间==>["+new Date()+"]");
        try {
            Map<String,Object> args = new HashMap<>();
            long currentTimeMillis = System.currentTimeMillis();
            args.put("_time",currentTimeMillis+"");
            chainExecStrategyContext.rootExec(EChainPlat.百度超级链, CommonConst.getNftContractName(),"setTimeStamp",args);
            CommonConst.CONTRACT_NOW_TIMESTAMP = currentTimeMillis;

            nftWorksService.cleanCache(CachePrefixConst.USER_ASSETS);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }finally {
            redisOperation.unlock(CachePrefixConst.LOCK_PREFIX+CommonConst.UPDATE_CONTRACT_EXPIRE_LOCK);
        }
    }
}
