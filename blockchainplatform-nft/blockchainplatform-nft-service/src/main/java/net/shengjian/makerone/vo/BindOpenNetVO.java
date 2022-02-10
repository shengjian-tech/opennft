package net.shengjian.makerone.vo;

import java.io.Serializable;

/**
 * @descriptions: 绑定百度超级链开放网络VO
 * @author: YSK
 * @date: 2022/1/17 16:05
 * @version: 1.0
 */
public class BindOpenNetVO implements Serializable {
    /**
     * 手机验证码
     */
    private String verificationCode;
    /**
     * 百度超级链开放网络的address
     */
    private String openNetAddress;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 作者名称
     */
    private String authorName;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getOpenNetAddress() {
        return openNetAddress;
    }

    public void setOpenNetAddress(String openNetAddress) {
        this.openNetAddress = openNetAddress;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
