package net.shengjian.frame.util;

import org.nustaq.serialization.FSTConfiguration;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Description: Fst 序列化.<br>
 *
 * @author springrain
 */
public class FstSerializer implements RedisSerializer<Object> {

    private static FSTConfiguration fstConfiguration = FSTConfiguration.createStructConfiguration();

    @Override
    public byte[] serialize(Object obj) {
        if (obj == null) {
            return null;
        }
        return fstConfiguration.asByteArray(obj);
    }

    @Override
    public Object deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return fstConfiguration.asObject(bytes);
    }
}
