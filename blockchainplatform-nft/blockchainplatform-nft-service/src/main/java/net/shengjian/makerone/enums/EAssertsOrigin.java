package net.shengjian.makerone.enums;


import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 资产来源枚举类
 * @author: YSK
 * @date: 2021/12/21 17:28
 * @version: 1.0
 */
public enum EAssertsOrigin implements BaseEnum<Integer>{
    KVPair(),
    自己创作(0,"自己创作"),
    购买其他作者(1,"购买其他作者");

    private Integer code;
    private String value;

    EAssertsOrigin() {
    }

    EAssertsOrigin(Integer code, String value) {
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
        EAssertsOrigin[] values = EAssertsOrigin.values();
        for (EAssertsOrigin item : values) {
            kv.put(item.code,item.value);
        }
        return kv;
    }
}
