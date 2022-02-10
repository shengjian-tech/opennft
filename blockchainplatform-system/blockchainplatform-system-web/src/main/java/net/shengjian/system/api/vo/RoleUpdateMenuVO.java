package net.shengjian.system.api.vo;

import java.util.List;

/**
 * @descriptions: 角色更新猜到VO
 * @author: YSK
 * @date: 2021/5/8 17:54
 * @version: 1.0
 */
public class RoleUpdateMenuVO {
    /**
     * 角色编号
     *
     * @required
     */
    private String roleId;
    /**
     * 菜单编号集合
     *
     * @required
     */
    private List<String> menuIds;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }
}
