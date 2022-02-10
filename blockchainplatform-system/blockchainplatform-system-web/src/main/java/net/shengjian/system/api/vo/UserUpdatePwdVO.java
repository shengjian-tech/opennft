package net.shengjian.system.api.vo;

/**
 * @descriptions: 用户更新密码VO
 * @author: YSK
 * @date: 2021/5/10 16:19
 * @version: 1.0
 */
public class UserUpdatePwdVO {
    /**
     * 用户账号
     *
     * @required
     */
    private String account;
    /**
     * 旧密码
     *
     * @required
     */
    private String oldPwd;
    /**
     * 新密码
     *
     * @required
     */
    private String newPwd;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
