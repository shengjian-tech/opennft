package net.shengjian.rpc.grpcimpl;

import net.shengjian.rpc.sessionuser.UserVO;

import java.io.Serializable;

/**
 * 接受请求,所有的请求都封装成springbean的调用. 序列化成二进制,然后再经过grpc传输
 *
 * @author caomei
 */
public class GrpcCommonRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口
     */
    private String clazz;

    /**
     * spring 的bean Name
     */
    private String beanName;

    /**
     * 方法
     */
    private String method;

    /**
     * service 方法参数
     */
    private Object[] args;
    /**
     * 方法参数类型
     */
    private Class[] argTypes;


    // 事务组Id,一次完整的请求链,groupId是唯一的.
    private String txGroupId = null;

    // 版本的编号,用于处理不同的版本
    private Integer versionCode;

    // 超时时间,超时之后,事务回滚
    private Integer timeout;

    // 事务自动提交,默认true,如果是false就需要等待入口通知提交.
    private Boolean autocommit;

    // 当前登录用户UserVO
    private UserVO userVO;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getTxGroupId() {
        return txGroupId;
    }

    public void setTxGroupId(String txGroupId) {
        this.txGroupId = txGroupId;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public Boolean getAutocommit() {
        return autocommit;
    }

    public void setAutocommit(Boolean autocommit) {
        this.autocommit = autocommit;
    }

    public Class[] getArgTypes() {
        return argTypes;
    }

    public void setArgTypes(Class[] argTypes) {
        this.argTypes = argTypes;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

}
