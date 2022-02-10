package net.shengjian.system.vo;

import net.shengjian.system.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @descriptions: 登录用户信息, 用于前端展示
 * @author: YSK
 * @date: 2021/5/7 10:51
 * @version: 1.0
 */
public class LoginUserVO extends User {

    private String roleName;
    private String roleCode;
    private List<String> codes;
    private Map<String, String> codeMap;
    /**
     * 登录后默认进入的页面
     */
    private String indexPage;

    public LoginUserVO() {
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public Map<String, String> getCodeMap() {
        return codeMap;
    }

    public void setCodeMap(Map<String, String> codeMap) {
        this.codeMap = codeMap;
    }

    public String getRoleName() {
        return roleName;
    }

    public LoginUserVO setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public LoginUserVO setRoleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }

    public String getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(String indexPage) {
        this.indexPage = indexPage;
    }
}
