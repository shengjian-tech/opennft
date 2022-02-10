package net.shengjian.makerone.utils;

import net.shengjian.frame.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @descriptions:
 * @author: YSK
 * @date: 2022/1/6 9:53
 * @version: 1.0
 */
public class NFTMapUtils {
    private NFTMapUtils(){}
    /**
     * map的value转字节数组
     * value需要是字符串
     * @return
     */
    public static Map<String,byte[]> oConvertBytes(Map map){
        Map<String,byte[]> byteMap = new HashMap<>();
        Set set = map.keySet();
        for (Object key : set) {
            Object value = map.get(key);
            byteMap.put(key.toString(),JsonUtils.writeValueAsString(value).getBytes());
        }
        return byteMap;
    }
    public static Map<String,String> oConvertStr(Map map){
        Map<String,String> strMap = new HashMap<>();
        Set set = map.keySet();
        for (Object key : set) {
            Object value = map.get(key);
            strMap.put(key.toString(), value.toString());
        }
        return strMap;
    }
}
