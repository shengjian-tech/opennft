package net.shengjian.makerone.enums;

import java.util.Map;

/**
 * @descriptions:
 * @author: YSK
 * @date: 2021/12/21 17:10
 * @version: 1.0
 */
public interface BaseEnum<T>{
    T getCode();
    String getValue();
    //枚举键值对
    Map<T,String> KVPair();
}
