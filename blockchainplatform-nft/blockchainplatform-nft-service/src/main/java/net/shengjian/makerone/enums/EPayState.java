package net.shengjian.makerone.enums;


import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 支付状态枚举
 * @author: YSK
 * @date: 2021/12/21 17:28
 * @version: 1.0
 */
public enum EPayState implements BaseEnum<Integer>{
    KVPair(),
    未支付(0,"未支付"),
    已支付(1,"已支付"),
    已取消(2,"已取消");

    private Integer code;
    private String value;

    EPayState() {
    }

    EPayState(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static EPayState codeOf(Integer code) throws Exception {
        if(code==null){
            return EPayState.未支付;
        }
        EPayState[] values = EPayState.values();
        for (EPayState value : values) {
            if (code.equals(value.getCode())){
                return value;
            }
        }
        return EPayState.未支付;
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
        EPayState[] values = EPayState.values();
        for (EPayState item : values) {
            kv.put(item.code,item.value);
        }
        return kv;
    }
}
