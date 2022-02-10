package net.shengjian.makerone.enums;


import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 支付方式枚举
 * @author: YSK
 * @date: 2021/12/21 17:28
 * @version: 1.0
 */
public enum EPayPlat implements BaseEnum<Integer>{
    KVPair(),
    微信支付(0,"微信支付!"),
    支付宝支付(1,"支付宝支付!");

    private Integer code;
    private String value;

    EPayPlat() {
    }

    EPayPlat(Integer code, String value) {
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
        EPayPlat[] values = EPayPlat.values();
        for (EPayPlat item : values) {
            kv.put(item.code,item.value);
        }
        return kv;
    }
}
