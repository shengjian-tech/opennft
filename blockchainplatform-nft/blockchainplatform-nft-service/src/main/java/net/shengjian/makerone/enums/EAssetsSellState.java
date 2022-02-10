package net.shengjian.makerone.enums;


import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 资产出售状态
 * @author: YSK
 * @date: 2021/12/21 17:28
 * @version: 1.0
 */
public enum EAssetsSellState implements BaseEnum<Integer>{
    KVPair(),
    不出售(0,"不出售"),
    售卖中(1,"售卖中"),
    已生成订单(2,"已生成订单"),
    已删除(3,"已删除");

    private Integer code;
    private String value;

    EAssetsSellState() {
    }

    EAssetsSellState(Integer code, String value) {
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
        EAssetsSellState[] values = EAssetsSellState.values();
        for (EAssetsSellState item : values) {
            kv.put(item.code,item.value);
        }
        return kv;
    }
}
