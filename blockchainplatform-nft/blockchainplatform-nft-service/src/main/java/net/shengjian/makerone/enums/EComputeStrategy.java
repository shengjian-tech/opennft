package net.shengjian.makerone.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 不同的计算策略
 * @author: YSK
 * @date: 2021/12/24 16:23
 * @version: 1.0
 */
public enum EComputeStrategy implements BaseEnum<String> {
    KVPair(),
    DEFAULT("default", "默认"),
    VIP("vip", "会员");

    private String code;
    private String value;

    EComputeStrategy(String code, String value) {
        this.code = code;
        this.value = value;
    }

    EComputeStrategy() {
    }

    @Override
    public String getCode() {
        return this.code;
    }

    public String getCode(String key) {
        return this.code + key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public Map<String, String> KVPair() {
        Map<String, String> kv = new HashMap<>();
        EComputeStrategy[] values = EComputeStrategy.values();
        for (EComputeStrategy item : values) {
            kv.put(item.code, item.value);
        }
        return kv;
    }
}
