package net.shengjian.frame.util;
/**
 * RedisSearch工具类
 *
 * @copyright
 * @author AngeryFeather
 * @version 2021年4月14日 上午9:20:48
 * @see net.shengjian.frame.util.RedisSearchUtils
 */

import io.redisearch.client.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisSearchUtils {

    private static String host;
    private static int port;
    private static final String SEARCH_INDEX = "searchIndex";

    @Value("${spring.redis.host}")
    public void setHost(String host) {
        RedisSearchUtils.host = host;
    }

    @Value("${spring.redis.port}")
    public void setPort(int port) {
        RedisSearchUtils.port = port;
    }

    public static Client getClient() {
        return new Client(SEARCH_INDEX, host, port);
    }

    public static Client getClient(String indexName) {
        return new Client(indexName, host, port);
    }
}
