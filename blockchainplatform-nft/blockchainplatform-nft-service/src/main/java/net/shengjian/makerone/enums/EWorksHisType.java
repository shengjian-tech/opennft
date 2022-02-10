package net.shengjian.makerone.enums;


import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 作品状态枚举类
 * @author: YSK
 * @date: 2021/12/21 17:28
 * @version: 1.0
 */
public enum EWorksHisType implements BaseEnum<Integer>{
    KVPair(),
    售卖(0,"售卖"),
    转移(1,"转移");

    private Integer code;
    private String value;

    EWorksHisType() {
    }

    EWorksHisType(Integer code, String value) {
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
        EWorksHisType[] values = EWorksHisType.values();
        for (EWorksHisType item : values) {
            kv.put(item.code,item.value);
        }
        return kv;
    }
}
