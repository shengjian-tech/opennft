package net.shengjian.makerone.enums;


import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 资产类型枚举类
 * @author: YSK
 * @date: 2021/12/21 17:28
 * @version: 1.0
 */
public enum EAssetsType implements BaseEnum<Integer>{
    KVPair(),
    合集(0,"合集"),
    作品(1,"作品!");

    private Integer code;
    private String value;

    EAssetsType() {
    }

    EAssetsType(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public Integer getCode() {
        return this.code ;
    }

    @Override
    public String getValue() {
        return this.value ;
    }

    @Override
    public Map<Integer, String> KVPair() {
        Map<Integer, String> kv = new HashMap<>();
        EAssetsType[] values = EAssetsType.values();
        for (EAssetsType item : values) {
            kv.put(item.code,item.value);
        }
        return kv;
    }
}
