package net.shengjian.system.api.vo;

import java.util.List;

/**
 * @descriptions: 用户角色更新参数对象
 * @author: YSK
 * @date: 2021/5/8 11:23
 * @version: 1.0
 */
public class UserRoleUpdateVO {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色ids
     */
    private List<String> roleIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
}
