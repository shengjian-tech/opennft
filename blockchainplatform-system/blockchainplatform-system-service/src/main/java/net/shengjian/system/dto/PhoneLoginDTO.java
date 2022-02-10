package net.shengjian.system.dto;

import java.io.Serializable;

/**
 * @descriptions: 手机号登录参数
 * @author: YSK
 * @date: 2021/7/6 10:55
 * @version: 1.0
 */
public class PhoneLoginDTO implements Serializable {

    /**
     * 手机号
     */
    private String phone;
    /**
     * 验证码的key
     */
    private String captchaKey;
    /**
     * 验证码
     */
    private String code;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }
}
