package net.shengjian.weixin.sdk.common;

import net.shengjian.frame.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;


/**
 * 封装 API 响应结果，将 json 字符串转换成 java 数据类型
 * <p>
 * jackson 中 json 类型与 java 类型对应关系如下：
 * <pre>
 *  http://wiki.fasterxml.com/JacksonInFiveMinutes
 *  JSON TYPE                JAVA TYPE
 *  object                    LinkedHashMap&lt;String,Object&gt;
 *  array                    ArrayList&lt;Object&gt;
 *  string                    String
 *  number (no fraction)    Integer, Long or BigInteger (smallest applicable)
 *  number (fraction)        Double (configurable to use BigDecimal)
 *  true|false                Boolean
 *  null                    null
 *  </pre>
 */
public class ApiResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(ApiResult.class);

    private Map<String, Object> attrs;
    private String json;


    private File file;

    public ApiResult() {
    }

    /**
     * 通过 json 构造 ApiResult，注意返回结果不为 json 的 api（如果有的话）
     *
     * @param jsonStr json字符串
     */
    public ApiResult(String jsonStr) {
        this.json = jsonStr;

        try {
            Map<String, Object> temp = JsonUtils.readValue(jsonStr, Map.class);
            this.attrs = temp;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String getJson() {
        return json;
    }


    public String toString() {
        return getJson();
    }

    /**
     * APi 请求是否成功返回
     *
     * @return {boolean}
     */
    public boolean isSucceed() {
        Integer errorCode = getErrorCode();
        // errorCode 为 0 时也可以表示为成功,详见: https://developers.weixin.qq.com/doc/offiaccount/Getting_Started/Global_Return_Code.html
        return (errorCode == null || errorCode == 0);
    }

    public Integer getErrorCode() {
        return getInt("errcode");
    }

    public String getErrorMsg() {
        Integer errorCode = getErrorCode();
        if (errorCode != null) {
            String result = WxConsts.getErrMsgByCode(errorCode);
            if (result != null) {
                return result;
            }
            logger.warn("未知返回码：" + errorCode);
        }
        return (String) attrs.get("errmsg");
    }


    public String getOpenId() {
        return get("openid");
    }

    public String getUnionid() {
        return get("unionid");
    }

    public String getSessionKey() {
        return get("session_key");
    }

    public String getScope() {
        return get("scope");
    }

    public String getAccessToken() {
        return get("access_token");
    }

    public Integer getExpiresIn() {
        return getInt("expires_in");
    }

    public <T> T get(String name) {
        if (attrs == null) {
            return null;
        }
        return (T) attrs.get(name);
    }

    public String getStr(String name) {
        if (attrs == null) {
            return null;
        }

        return (String) attrs.get(name);
    }

    public Integer getInt(String name) {
        if (attrs == null) {
            return null;
        }

        Number number = (Number) attrs.get(name);
        return number == null ? null : number.intValue();
    }

    public Long getLong(String name) {
        if (attrs == null) {
            return null;
        }
        Number number = (Number) attrs.get(name);
        return number == null ? null : number.longValue();
    }

    public BigInteger getBigInteger(String name) {
        if (attrs == null) {
            return null;
        }
        return (BigInteger) attrs.get(name);
    }

    public Double getDouble(String name) {
        if (attrs == null) {
            return null;
        }
        return (Double) attrs.get(name);
    }

    public BigDecimal getBigDecimal(String name) {
        if (attrs == null) {
            return null;
        }

        return (BigDecimal) attrs.get(name);
    }

    public Boolean getBoolean(String name) {
        if (attrs == null) {
            return null;
        }
        return (Boolean) attrs.get(name);
    }

    @SuppressWarnings("rawtypes")
    public List getList(String name) {
        if (attrs == null) {
            return null;
        }
        return (List) attrs.get(name);
    }

    public Map getMap(String name) {
        if (attrs == null) {
            return null;
        }
        return (Map) attrs.get(name);
    }

    public Map<String, Object> getAttrs() {
        return this.attrs;
    }

    /**
     * 判断 API 请求结果失败是否由于 access_token 无效引起的
     * 无效可能的情况 error_code 值：
     * 40001 = 获取access_token时AppSecret错误，或者access_token无效(刷新后也可以引起老access_token失效)
     * 42001 = access_token超时
     * 42002 = refresh_token超时
     * 40014 = 不合法的access_token
     *
     * @return {boolean}
     */
    public boolean isAccessTokenInvalid() {
        Integer ec = getErrorCode();
        return ec != null && (ec == 40001 || ec == 42001 || ec == 42002 || ec == 40014);
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}









