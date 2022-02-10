package net.shengjian.makerone.enums;


import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 交易类型
 * @author: YSK
 * @date: 2021/12/21 17:28
 * @version: 1.0
 */
public enum ETradeType implements BaseEnum<Integer>{
    KVPair(),
    充值(0,"充值"),
    上架合集(1,"上架合集!"),
    下架合集(2,"下架合集!"),
    上架商品(3,"上架商品!"),
    下架商品(4,"下架商品!"),
    交易作品(5,"交易作品!"),
    转移作品(6,"转移作品!");

    private Integer code;
    private String value;

    ETradeType() {
    }

    ETradeType(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static ETradeType codeOf(Integer code) {
        if(code==null){
            return null;
        }
        ETradeType[] values = ETradeType.values();
        for (ETradeType item : values) {
            if (code.equals(item.getCode())) {
                return item;
            }
        }
        return null;
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
        ETradeType[] values = ETradeType.values();
        for (ETradeType item : values) {
            kv.put(item.code,item.value);
        }
        return kv;
    }
}
