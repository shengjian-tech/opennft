package net.shengjian.system.api.vo;

import java.io.Serializable;

/**
 * @descriptions: 验证码vo类
 * @author: YSK
 * @date: 2021/5/8 17:12
 * @version: 1.0
 */
public class CaptchaVO implements Serializable {
    /**
     * 验证码的key
     */
    private String captchaKey;
    /**
     * 验证码图片
     */
    private String imageBase64;

    public CaptchaVO() {
    }

    public CaptchaVO(String captchaKey, String imageBase64) {
        this.captchaKey = captchaKey;
        this.imageBase64 = imageBase64;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
