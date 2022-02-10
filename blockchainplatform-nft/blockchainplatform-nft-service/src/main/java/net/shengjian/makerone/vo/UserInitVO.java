package net.shengjian.makerone.vo;

import net.shengjian.system.entity.User;

import java.io.Serializable;

/**
 * @descriptions: 用户初始化基本信息VO
 * @author: YSK
 * @date: 2022/1/10 10:52
 * @version: 1.0
 */
public class UserInitVO extends User implements Serializable {
    /**
     * 手机验证码
     */
    private String verificationCode;

    /**
     * 密码
     */
    private String password;

    /**
     * 绑定的百度超级链address
     */
    private String bindAddress;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getBindAddress() {
        return bindAddress;
    }

    public void setBindAddress(String bindAddress) {
        this.bindAddress = bindAddress;
    }
}
