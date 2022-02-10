package net.shengjian.rpc.springbind;

public class RemoteRpcTxDto {

    private String rpcHost = null;
    private Integer rpcPort = null;
    private String txGroupId;
    // 版本的编号,用于处理不同的版本
    private Integer versionCode;

    // 超时时间,超时之后,事务回滚
    private Integer timeout;

    // 事务自动提交,默认true,如果是false就需要等待入口通知提交.
    private Boolean autocommit;

    public String getRpcHost() {
        return rpcHost;
    }

    public void setRpcHost(String rpcHost) {
        this.rpcHost = rpcHost;
    }

    public Integer getRpcPort() {
        return rpcPort;
    }

    public void setRpcPort(Integer rpcPort) {
        this.rpcPort = rpcPort;
    }

    public String getTxGroupId() {
        return txGroupId;
    }

    public void setTxGroupId(String txGroupId) {
        this.txGroupId = txGroupId;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Boolean getAutocommit() {
        return autocommit;
    }

    public void setAutocommit(Boolean autocommit) {
        this.autocommit = autocommit;
    }
}
