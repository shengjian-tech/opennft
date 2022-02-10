package net.shengjian.frame.util;

import java.io.Serializable;
import java.util.Map;

/**
 * 返回对象的封装
 *
 * @author caomei
 */

public class  ReturnDatas<T> implements Serializable {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String TOKEN_TIME_OUT = "tokenTimeOut";
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //public static final String WARNING = "warning";
    //

    /**
     * 成功       200
     * 异常       500
     * token过期  401
     */
    private Integer statusCode = 200;
    /**
     * 状态信息
     */
    private String status;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 结果数据
     */
    private T result;

    private Map<String, Object> map;
    private Page<?> page;
    //private Object queryBean;

    public ReturnDatas() {

    }

    public ReturnDatas(String status) {
        setStatus(status);
    }

    public ReturnDatas(String status, String message) {
        setStatus(status);
        setMessage(message);
    }

    public ReturnDatas(String status, String message, T data) {
        setStatus(status);
        setMessage(message);
        setResult(data);
    }

    public static <T> ReturnDatas<T> getSuccessReturnDatas() {
        return new ReturnDatas<>(ReturnDatas.SUCCESS);
    }

    public static <T> ReturnDatas<T> getErrorReturnDatas() {
        return new ReturnDatas<>(ReturnDatas.ERROR);
    }

    public static <T> ReturnDatas<T> getErrorReturnDatas(String message) {
        return new ReturnDatas<>(ReturnDatas.ERROR, message);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        if (SUCCESS.equalsIgnoreCase(status)) {
            setStatusCode(200);
        } else if (TOKEN_TIME_OUT.equalsIgnoreCase(status)) {
            setStatusCode(401);
        } else {
            setStatusCode(500);
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T obj) {
        this.result = obj;
    }

    public Page<?> getPage() {
        return page;
    }

    public void setPage(Page<?> page) {
        this.page = page;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    /*
    public Object getQueryBean() {
        return queryBean;
    }

    public void setQueryBean(Object queryBean) {
        this.queryBean = queryBean;
    }
     */

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
