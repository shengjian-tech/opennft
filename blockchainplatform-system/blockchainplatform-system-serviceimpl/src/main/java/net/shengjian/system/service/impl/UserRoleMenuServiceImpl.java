package net.shengjian.system.service.impl;

import net.shengjian.frame.util.CommonEnum;
import net.shengjian.frame.util.Finder;
import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.entity.*;
import net.shengjian.system.service.IMenuService;
import net.shengjian.system.service.IRoleService;
import net.shengjian.system.service.IUserRoleMenuService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户角色权限菜单,用于处理用户,角色,菜单之间的权限关系
 *
 * @author springrain<Auto generate>
 * @version 2013-07-06 16:03:00
 */
@Service("userRoleMenuService")
public class UserRoleMenuServiceImpl extends BaseSpringrainServiceImpl implements IUserRoleMenuService {


    @Resource
    private IMenuService menuService;

    @Resource
    private IRoleService roleService;

    @Override
    public List<Role> findRoleByUserId(String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return null;
        }

        String cacheKey = "findRoleByUserId_" + userId;
        List<Role> list = super.getByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, cacheKey, List.class);
        if (list != null) {
            if (list.size() == 0) {
                return null;
            }
            return list;
        }


        //按照 r.privateOrg,r.sortno desc 先处理强制部门权限的角色
        Finder finder = new Finder("SELECT r.* from ").append(Finder.getTableName(Role.class)).append(" r,")
                .append(Finder.getTableName(UserRole.class))
                .append("  re where re.userId=:userId and re.roleId=r.id and r.active=1 order by r.privateOrg desc,r.sortno desc");
        finder.setParam("userId", userId);

        list = super.queryForList(finder, Role.class);

        if (CollectionUtils.isEmpty(list)) {
            list = new ArrayList<>();
        }

        // 加上缓存
        super.putByCache(GlobalStatic.qxCacheKey, cacheKey, list);

        return list;
    }


    @Override
    public List<Menu> findMenuByRoleId(String roleId) throws Exception {
        if (StringUtils.isBlank(roleId)) {
            return null;
        }

        String cacheKey = "findMenuByRoleId_" + roleId;
        List<Menu> list = super.getByCache(GlobalStatic.qxCacheKey, cacheKey, List.class);
        if (list != null) {
            if (list.size() == 0) {
                return null;
            }
            return list;
        }

        Finder finder = new Finder("SELECT m.* from ").append(Finder.getTableName(Menu.class)).append(" m,")
                .append(Finder.getTableName(RoleMenu.class))
                .append("  re where re.roleId=:roleId and re.menuId=m.id and m.active=1 order by m.sortno desc ");
        finder.setParam("roleId", roleId);
        list = super.queryForList(finder, Menu.class);

        if (CollectionUtils.isEmpty(list)) {
            list = new ArrayList<>();
        }

        // 加上缓存
        super.putByCache(GlobalStatic.qxCacheKey, cacheKey, list);

        return list;
    }

    @Override
    public List<Menu> findMenuTreeByRoleId(String roleId) throws Exception {
        if (StringUtils.isBlank(roleId)) {
            return null;
        }

        String cacheKey = "findMenuTreeByRoleId_" + roleId;
        List<Menu> list = super.getByCache(GlobalStatic.qxCacheKey, cacheKey, List.class);
        if (list != null) {
            if (list.size() == 0) {
                return null;
            }
            return list;
        }

        Finder finder = new Finder("SELECT m.* from ").append(Finder.getTableName(Menu.class)).append(" m,")
                .append(Finder.getTableName(RoleMenu.class))
                .append("  re where re.roleId=:roleId and re.menuId=m.id and m.active=1 order by m.sortno desc ");
        finder.setParam("roleId", roleId);
        list = super.queryForList(finder, Menu.class);
        List<Menu> menuListTree = menuService.menuList2Tree(list);
        if (CollectionUtils.isEmpty(menuListTree)) {
            menuListTree = new ArrayList<>();
        }

        // 加上缓存
        super.putByCache(GlobalStatic.qxCacheKey, cacheKey, menuListTree);

        return menuListTree;
    }

    @Override
    public List<User> findUserByRoleId(String roleId) throws Exception {
        if (StringUtils.isBlank(roleId)) {
            return null;
        }
        String cacheKey = "findUserByRoleId_" + roleId;
        List<User> list = super.getByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, cacheKey, List.class);
        if (list != null) {
            if (list.size() == 0) {
                return null;
            }
            return list;
        }

        Finder finder = new Finder("SELECT u.* from ").append(Finder.getTableName(User.class)).append(" u,")
                .append(Finder.getTableName(UserRole.class)).append("  re where re.roleId=:roleId and re.userId=u.id and u.active=1 ");
        finder.setParam("roleId", roleId);
        list = super.queryForList(finder, User.class);

        if (CollectionUtils.isEmpty(list)) {
            list = new ArrayList<>();
        }

        // 加上缓存
        super.putByCache(GlobalStatic.qxCacheKey, cacheKey, list);
        return list;
    }


    @Override
    public List<Menu> findMenuByUserId(String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return null;
        }


        String cacheKey = "findMenuByUserId_" + userId;
        List<Menu> list = super.getByCache(GlobalStatic.qxCacheKey, cacheKey, List.class);
        if (list != null) {
            /*if (list.size() == 0) {
                return null;
            }*/
            return list;
        } else {
            list = new ArrayList<>();
        }

        //获取用户所有的角色
        List<Role> roleList = findRoleByUserId(userId);

        if (CollectionUtils.isEmpty(roleList)) {
            return null;
        }

        //根据角色,遍历用户的所有菜单
        for (Role role : roleList) {
            List<Menu> menuByRoleId = findMenuByRoleId(role.getId());
            if (CollectionUtils.isEmpty(menuByRoleId)) {
                continue;
            }
            for (Menu menu : menuByRoleId) {
                if (list.contains(menu)) {
                    continue;
                }
                menu.setRoleId(role.getId());
                list.add(menu);
            }
        }
        // 加上缓存11 = {Menu@10611} "id[t_menu_list],菜单名称[菜单管理],vue使用 meta.title[菜单管理],pid[system_manager],描述[],pageurl[/api/system/menu/list],0.功能按钮,1.导航菜单[1],vue路由地址[/menus],vue组件使用[null],vue组件使用[null],vue组件使用[null],vue组件使用[null],icon[/img/iconImg/icon11.png],createTime[2019-07-24 11:33:44.0],createUserId[],updateTime[2019-07-24 11:33:44.0],updateUserId[],排序,查询时倒叙排列[3],是否有效(0否,1是)[1],bak1[null],bak2[null],bak3[null],bak4[null],bak5[null],"
        super.putByCache(GlobalStatic.qxCacheKey, cacheKey, list);
        return list;
    }

    @Override
    public List<Menu> findMenuTreeByUsreId(String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return null;
        }

        List<Menu> listMenu = findMenuByUserId(userId);
        List<Menu> list = menuService.menuList2Tree(listMenu);
        return list;
    }

    @Override
    public List<Menu> findAllMenuTree() throws Exception {
        String cacheKey = "findAllMenuTree";
        List<Menu> list = super.getByCache(GlobalStatic.qxCacheKey, cacheKey, List.class);
        if (list != null) {
            if (list.size() == 0) {
                return null;
            }
            return list;
        }


        Finder finder = Finder.getSelectFinder(Menu.class)
                .append(" where active=:active")
                .setParam("active", CommonEnum.ACTIVE.未删除.getState())
                .append(" order by sortno desc ");
        List<Menu> listMenu = super.queryForList(finder, Menu.class);
        if (CollectionUtils.isEmpty(listMenu)) {
            list = new ArrayList<>();
            // 加上缓存
            super.putByCache(GlobalStatic.qxCacheKey, cacheKey, list);
            return list;
        }

        list = menuService.menuList2Tree(listMenu);

        // 加上缓存
        super.putByCache(GlobalStatic.qxCacheKey, cacheKey, list);
        return list;
    }


    @Override
    public void wrapVueMenu(List<Menu> listMenu, List<Map<String, Object>> listMap) {
        if (CollectionUtils.isEmpty(listMenu)) {
            return;
        }
        for (Menu menu : listMenu) {
            Map<String, Object> map = new HashMap<>();
            listMap.add(map);
            map.put("path", menu.getPath());
            map.put("redirect", menu.getRedirect());
            map.put("component", menu.getComponent());
            map.put("name", menu.getName());
            map.put("menuType", menu.getMenuType());
            map.put("roleId", menu.getRoleId());
            map.put("menuId", menu.getId());
            map.put("pageurl", menu.getPageurl());

            // meta
            Map<String, Object> meta = new HashMap<>();
            map.put("meta", meta);
            meta.put("title", menu.getTitle());
            meta.put("permission", menu.getPermission());
            meta.put("keepAlive", menu.getKeepAlive());
            //meta.put("target",menu.getTarget());


            List<Menu> listChildren = menu.getChildren();
            if (CollectionUtils.isNotEmpty(listChildren)) {
                // children
                List<Map<String, Object>> children = new ArrayList<>();
                map.put("children", children);
                wrapVueMenu(listChildren, children);
            }

        }

    }

    @Override
    public String updateRoleMenu(RoleMenu roleMenu) throws Exception {

        if (roleMenu == null || StringUtils.isBlank(roleMenu.getRoleId()) || StringUtils.isBlank(roleMenu.getMenuId()) || roleMenu.getCheck() == null) {
            return "数据不完整";
        }

        List<String> menuIds = menuService.findMenuBypid(roleMenu.getMenuId());

        Finder f_delete = Finder.getDeleteFinder(RoleMenu.class).append(" WHERE roleId=:roleId and menuId in (:menuId) ");
        f_delete.setParam("roleId", roleMenu.getRoleId()).setParam("menuId", menuIds);
        super.update(f_delete);

        String cacheKey = "findMenuTreeByRoleId_" + roleMenu.getRoleId();
        super.evictByKey(GlobalStatic.qxCacheKey, cacheKey);

        if (!roleMenu.getCheck()) { //  清理关系
            return null;
        }

        List<RoleMenu> rmList = new ArrayList<>();
        Date now = new Date();
        String userId = SessionUser.getUserId();
        for (String menuId : menuIds) {
            RoleMenu rm = new RoleMenu();
            rm.setId(SecUtils.getUUID());
            rm.setRoleId(roleMenu.getRoleId());
            rm.setMenuId(menuId);
            rm.setUpdateUserId(userId);
            rm.setCreateTime(now);
            rm.setUpdateTime(now);
            rm.setCreateUserId(userId);
            rmList.add(rm);
        }

        if (CollectionUtils.isNotEmpty(rmList)) {
            super.save(rmList);
        }

        return null;
    }

    @Override
    public String updateRoleMenu(String roleId, List<String> menuIds) throws Exception {
        Optional<List<String>> optionalList = Optional.ofNullable(menuIds);
        menuIds = optionalList.orElse(new ArrayList<>());
        if (StringUtils.isBlank(roleId)) {
            throw new RuntimeException("数据不完整！");
        }
        boolean supervisor = roleService.isSupervisor(roleId);
        if (!supervisor) {
            logger.error("该角色中的部门主管才可更新此角色信息!--updateRoleMenu({},{})", roleId, menuIds);
            throw new RuntimeException("该角色中的部门主管才可更新此角色信息!");
        }
        Finder f_delete = Finder.getDeleteFinder(RoleMenu.class).append("where roleId=:roleId")
                .setParam("roleId", roleId);
        super.update(f_delete);
        //String cacheKey = "findMenuByRoleId_" + roleId;
        super.getCache(GlobalStatic.qxCacheKey).clear();

        List<RoleMenu> saveList = new ArrayList<>();
        Date now = new Date();
        String userId = SessionUser.getUserId();
        for (String menuId : menuIds) {
            RoleMenu rm = new RoleMenu();
            rm.setId(SecUtils.getTimeNO());
            rm.setRoleId(roleId);
            rm.setMenuId(menuId);
            rm.setUpdateUserId(userId);
            rm.setCreateTime(now);
            rm.setUpdateTime(now);
            rm.setCreateUserId(userId);
            saveList.add(rm);
        }
        if (CollectionUtils.isNotEmpty(saveList)) {
            super.save(saveList);
        }
        return null;
    }

    @Override
    public String updateUserRoles(String userId, List<String> roleIds) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return null;
        }

        Finder f_select_old = Finder.getSelectFinder(UserRole.class, " roleId ").append(" WHERE userId=:userId ").setParam("userId", userId);
        List<String> listOld = super.queryForList(f_select_old, String.class);
        if (CollectionUtils.isNotEmpty(listOld)) {
            for (String roleId : listOld) {
                super.evictByKey(GlobalStatic.qxCacheKey, "findUserByRoleId_" + roleId);
            }
        }


        Finder f_del = Finder.getDeleteFinder(UserRole.class).append(" WHERE userId=:userId ").setParam("userId", userId);
        super.update(f_del);

        super.evictByKey(GlobalStatic.qxCacheKey, "findRoleByUserId_" + userId);
        super.evictByKey(GlobalStatic.qxCacheKey, "findMenuByUserId_" + userId);


        if (CollectionUtils.isEmpty(roleIds)) {
            return null;
        }

        List<UserRole> list = new ArrayList<>();
        Date now = new Date();

        for (String roleId : roleIds) {
            super.evictByKey(GlobalStatic.qxCacheKey, "findUserByRoleId_" + roleId);
            UserRole ur = new UserRole();
            ur.setId(SecUtils.getUUID());
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            ur.setCreateTime(now);
            ur.setUpdateTime(now);
            ur.setCreateUserId(SessionUser.getUserId());
            ur.setUpdateUserId(SessionUser.getUserId());
            list.add(ur);
        }
        super.save(list);
        return null;
    }


}
