package net.shengjian.makerone.enums;


import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 作品状态枚举类
 * @author: YSK
 * @date: 2021/12/21 17:28
 * @version: 1.0
 */
public enum EWorksState implements BaseEnum<Integer>{
    KVPair(),
    未上架(0,"未上架"),
    售卖中(1,"(已上架)售卖中"),
    已售停(2,"已售停"),
    已下架(3,"已下架"),
    已删除(4,"已删除");

    private Integer code;
    private String value;

    EWorksState() {
    }

    EWorksState(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static EWorksState codeOf(Integer integer) {
        if(integer==null){
            return null;
        }
        EWorksState[] values = EWorksState.values();
        for (EWorksState item : values) {
            if (integer.equals(item.getCode())) {
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
        EWorksState[] values = EWorksState.values();
        for (EWorksState item : values) {
            kv.put(item.code,item.value);
        }
        return kv;
    }
}
