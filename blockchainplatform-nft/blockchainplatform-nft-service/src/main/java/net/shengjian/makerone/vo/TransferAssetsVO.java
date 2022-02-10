package net.shengjian.makerone.vo;

import java.io.Serializable;

/**
 * @descriptions: 转移资产参数
 * @author: YSK
 * @date: 2022/1/23 13:25
 * @version: 1.0
 */
public class TransferAssetsVO implements Serializable {
    /**
     * 手机验证码
     */
    private String verificationCode;
    /**
     * 资产id
     */
    private String assetsId;
    /**
     * 转移到的address
     */
    private String toAddress;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(String assetsId) {
        this.assetsId = assetsId;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }
}
