package net.shengjian.makerone.enums;

import net.shengjian.makerone.constant.CommonConst;

import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 链平台适配状态
 * @author: YSK
 * @date: 2021/12/21 17:12
 * @version: 1.0
 */
public enum EChainPlatState implements BaseEnum<Integer> {
    KVPair(),
    适配中(CommonConst.FALSE,"适配中..."),
    适配完成(CommonConst.TRUE,"适配完成!");

    private Integer code;
    private String value;

    EChainPlatState() {
    }

    EChainPlatState(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public Map<Integer, String> KVPair() {
        Map<Integer, String> kv = new HashMap<>();
        EChainPlatState[] values = EChainPlatState.values();
        for (EChainPlatState item : values) {
            kv.put(item.code,item.value);
        }
        return kv;
    }
}
