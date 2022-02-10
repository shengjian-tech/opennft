package net.shengjian.test;

import net.shengjian.frame.cache.RedisOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @descriptions: 测试
 * @author: YSK
 * @date: 2022/1/17 13:27
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Resource
    private RedisOperation redisOperation;

    @Test
    public void testRedisOperation(){
        final boolean lock = redisOperation.lock("10001", 1000 * 10);
        System.out.println(lock);
        final boolean lock1 = redisOperation.lock("10001", 1000 * 10);
        System.out.println("lock1 = " + lock1);
    }
    @Test
    public void testgetAtomicLong(){
        final Long abc = redisOperation.getAtomicLong("abc",-10L);
        System.out.println(abc);
        if(abc<0){
            final Long abc1 = redisOperation.getAtomicLong("abc", 10L);
            System.out.println(abc1);
        }
    }
}
