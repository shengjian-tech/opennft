package net.shengjian.frame.config;

import net.shengjian.frame.util.FstSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * 缓存的配置,自定义 cacheManager 用于实现替换.
 *
 * @author springrain
 */
@Configuration("configuration-RedisCacheConfig")
public class RedisCacheConfig {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    // 序列化配置 解析任意对象
    public static FstSerializer fstSerializer = new FstSerializer();
    // 2.序列化String类型
    public static StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

    /**
     * 实际使用的redisTemplate,可以注入到代码中,操作redis
     *
     * @return
     */
    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);


        //设置默认的序列化器
        redisTemplate.setDefaultSerializer(fstSerializer);


        // Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        // json序列化利用ObjectMapper进行转义
        // jackson2JsonRedisSerializer.setObjectMapper(new FrameObjectMapper());
        // value序列化方式采用jackson
        // redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        // redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);


        // value序列化方式采用fstSerializer
        redisTemplate.setValueSerializer(fstSerializer);
        // hash的value序列化方式采用fstSerializer
        redisTemplate.setHashValueSerializer(fstSerializer);


        // key采用String的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);


        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }


    /**
     * 基于redis的cacheManager,使用spring-data-redis的RedisCacheManager
     *
     * @return CacheManager 缓存管理器
     */

    @Bean("cacheManager")
    public CacheManager cacheManager() {
        RedisCacheManager redisCacheManager =
                RedisCacheManager.builder(redisConnectionFactory)
                        .cacheDefaults(defaultCacheConfig(-1))
                        .transactionAware()
                        .build();
        return redisCacheManager;
    }

    /**
     * 默认的配置
     *
     * @param millis 默认的超时时间,单位毫秒
     * @return RedisCacheConfiguration 默认的配置
     */
    private RedisCacheConfiguration defaultCacheConfig(long millis) {
        //Jackson2JsonRedisSerializer<Object> jacksonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        //设置解析器
        // jacksonSerializer.setObjectMapper(new FrameObjectMapper());

        // fst 序列化
        // FstSerializer fstSerializer =  new FstSerializer();
        //默认配置
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();

        //设置默认的失效时间,单位毫秒
        if (millis > 0) {
            defaultCacheConfig.entryTtl(Duration.ofMillis(millis));
        }
        //设置序列化方式
        defaultCacheConfig.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fstSerializer))
                .disableCachingNullValues();
        return defaultCacheConfig;
    }


}
