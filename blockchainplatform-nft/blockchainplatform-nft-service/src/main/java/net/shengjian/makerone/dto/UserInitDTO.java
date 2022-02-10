package net.shengjian.makerone.dto;

import java.io.Serializable;

/**
 * @descriptions: 用户基本信息初始化
 * @author: YSK
 * @date: 2022/1/10 10:47
 * @version: 1.0
 */
public class UserInitDTO implements Serializable {
    /**
     * 用戶id
     */
    private String userId;
    /**
     * 链平台名称
     */
    private String chainPlatName;
    /**
     * 助记词
     */
    private String mnemonic;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChainPlatName() {
        return chainPlatName;
    }

    public void setChainPlatName(String chainPlatName) {
        this.chainPlatName = chainPlatName;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }
}
