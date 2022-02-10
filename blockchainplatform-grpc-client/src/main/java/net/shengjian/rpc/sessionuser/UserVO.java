package net.shengjian.rpc.sessionuser;

/**
 * 用户信息的VO,用于记录和RPC传递当前登陆用户的基础信息
 *
 * @author zyf
 * @version 2019-07-16 18:04
 * @copyright
 * @see UserVO
 */

public class UserVO {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 账号
     *
     * @required
     */
    private String account;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     *
     * @required
     */
    private String password;
    /**
     * 校验验证码的key
     *
     * @required
     */
    private String captchaKey;

    /**
     * 图片验证码
     */
    private String imgcaptcha;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 头像
     */
    private Integer active;

    /**
     * 私有的部门权限,用于处理单独url的特殊权限,调用 IUserRoleOrgService.wrapOrgIdFinderByPrivateOrgRoleId(String userId) 获取权限的finder;
     */
    private String privateOrgRoleId;
    /**
     * 验证码
     *
     * @required
     */
    private String captcha;

    /**
     * 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }


    public String getImgcaptcha() {
        return imgcaptcha;
    }

    public void setImgcaptcha(String imgcaptcha) {
        this.imgcaptcha = imgcaptcha;
    }

    public String getPrivateOrgRoleId() {
        return privateOrgRoleId;
    }

    public void setPrivateOrgRoleId(String privateOrgRoleId) {
        this.privateOrgRoleId = privateOrgRoleId;
    }

}
