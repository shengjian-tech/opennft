package net.shengjian.system.vo;

/**
 * @descriptions: 登录成功VO类
 * @author: YSK
 * @date: 2021/5/17 18:23
 * @version: 1.0
 */
public class LoginSuccessVO {
    private String jwttoken;
    private LoginUserVO user;
    private boolean showCaptcha;

    public LoginSuccessVO() {
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public void setJwttoken(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public LoginUserVO getUser() {
        return user;
    }

    public void setUser(LoginUserVO user) {
        this.user = user;
    }

    public boolean isShowCaptcha() {
        return showCaptcha;
    }

    public void setShowCaptcha(boolean showCaptcha) {
        this.showCaptcha = showCaptcha;
    }
}
