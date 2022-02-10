package net.shengjian.frame.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存的配置,自定义 cacheManager 用于实现替换.
 *
 * @author caomei
 */
@Configuration("configuration-MemoryCacheConfig")
public class MemoryCacheConfig {

    @Bean("cacheManager")
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
        //设置使用值的序列化器,也就是使用SerializationDelegate把get的结果重新序列化一次,否则所有程序操作的是一个内存对象,互相修改就会互相影响
        concurrentMapCacheManager.setStoreByValue(true);
        CacheManager cacheManager = concurrentMapCacheManager;

        return cacheManager;
    }

}
