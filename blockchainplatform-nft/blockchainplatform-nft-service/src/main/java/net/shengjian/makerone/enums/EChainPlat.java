package net.shengjian.makerone.enums;


import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 链平台枚举类
 * @author: YSK
 * @date: 2021/12/21 17:28
 * @version: 1.0
 */
public enum EChainPlat implements BaseEnum<String>{
    KVPair(),
    百度超级链("xuperChain","百度超级链"),
    蚂蚁链("antChain","蚂蚁链");

    private String code;
    private String value;

    EChainPlat() {
        code="";
        value="";
    }
    public static EChainPlat codeOf(String code){
        EChainPlat[] values = EChainPlat.values();
        for (EChainPlat value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    EChainPlat(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return this.code ;
    }
    public String getCode(String key) {
        return this.code + key;
    }

    @Override
    public String getValue() {
        return this.value ;
    }

    @Override
    public Map<String, String> KVPair() {
        Map<String, String> kv = new HashMap<>();
        EChainPlat[] values = EChainPlat.values();
        for (EChainPlat item : values) {
            kv.put(item.code,item.value);
        }
        return kv;
    }
}
