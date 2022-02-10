package net.shengjian.frame.cache;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 基于 Redisson的 redis操作,包含lock和自增,基于stream的mq <br>
 * 要求:redis 5.0+ ,springBoot 2.3+
 *
 * @author springrain
 */

@Component("redisOperation")
public class RedisOperation {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 根据Key 和超时时间加锁
     *
     * @param key         锁的key
     * @param expireMilli 超时时间毫秒数
     * @return
     */
    public boolean lock(String key, long expireMilli) {
        if (StringUtils.isBlank(key)) {
            return false;
        }

        try {
            Boolean lock = redisTemplate.opsForValue().setIfAbsent(key, System.currentTimeMillis() + expireMilli, expireMilli, TimeUnit.MILLISECONDS);
            return lock;
        } catch (Exception e) {
            logger.error("locking error", e);
            return false;
        }


    }

    /**
     * 根据Key解锁
     *
     * @param key 锁的key
     */
    public boolean unlock(String key) {

        if (StringUtils.isBlank(key)) {
            return false;
        }
        try {
            redisTemplate.delete(key);
            return true;

        } catch (Throwable e) {
            logger.error("unlock error", e);
            return false;
        }
    }


    /**
     * 原子自增
     *
     * @param name 自增的名称
     * @return
     */
    public Long getAtomicLong(String name) {
        Long increment = redisTemplate.opsForValue().increment(name);
        return increment;
    }

    /**
     * 原子自增
     *
     * @param name      自增的名称
     * @param initValue 自增的初始值
     * @return
     */
    public Long getAtomicLong(String name, Long initValue) {
        Long increment = redisTemplate.opsForValue().increment(name, initValue);
        return increment;

    }


}
