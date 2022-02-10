package net.shengjian.test;

import net.shengjian.SpringrainApplication;
import net.shengjian.frame.util.Finder;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.system.entity.Menu;
import net.shengjian.system.entity.Org;
import net.shengjian.system.entity.RoleMenu;
import net.shengjian.system.service.IMenuService;
import net.shengjian.system.service.IOrgService;
import net.shengjian.system.service.IUserRoleMenuService;
import net.shengjian.system.service.IUserRoleOrgService;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringrainApplication.class)
public class SpringTests {
    String userId = "u_10001";
    @Resource
    private IUserRoleMenuService userRoleMenuService;
    @Resource
    private IUserRoleOrgService userRoleOrgService;
    @Resource
    private IOrgService orgService;
    @Resource
    private IMenuService menuService;

    @Test
    public void userMenuTest() throws Exception {

        List<Menu> menuAndLeafByUserId = userRoleMenuService.findMenuTreeByUsreId(userId);
        List<Map<String, Object>> listMap = new ArrayList<>();
        userRoleMenuService.wrapVueMenu(menuAndLeafByUserId, listMap);
        System.out.println(JsonUtils.writeValueAsString(listMap));
    }

    @Test
    public void publicUserOrgTest() throws Exception {
        Finder finder = userRoleOrgService.wrapOrgIdFinderByUserId("23a2c0c52ed142938c159c9b9004fa35");
        System.out.println(finder.getSql());
    }

    @Test
    public void privateUserOrgTest() throws Exception {
        Finder finder = userRoleOrgService.wrapOrgIdFinderByPrivateOrgRoleId("e8a4ad9944894908b43ded631094dcbb", "23a2c0c52ed142938c159c9b9004fa35");
        System.out.println(finder.getSql());
    }

    @Test
    public void orgTreeTest() throws Exception {
        List<Org> treeByPid = orgService.findTreeByPid("");
        System.out.printf(treeByPid.size() + "");

    }

    @Test
    public void deleteOrgTest() throws Exception {
        String o_10003 = orgService.deleteOrgById("o_10003");
        //   System.out.printf(o_10003);

    }

    @Test
    public void deleteMenuTests() throws Exception {
        menuService.deleteMenuById("f4d7a1bf7ddf43dc9016e1465cd3d9d8");
    }

    @Test
    public void updateRoleMenuTests() throws Exception {
        List<Menu> menuList = userRoleMenuService.findMenuByRoleId("e8a4ad9944894908b43ded631094dcbb");
        System.out.println(menuList.size());
        RoleMenu rm = new RoleMenu();
        rm.setRoleId("e8a4ad9944894908b43ded631094dcbb");
        rm.setMenuId("9bccbc28b32e41438c5ac73a5e61ed58");
        rm.setCheck(false);
        userRoleMenuService.updateRoleMenu(rm);
        menuList = userRoleMenuService.findMenuByRoleId("e8a4ad9944894908b43ded631094dcbb");
        System.out.println(menuList.size());
    }

    @Test
    public void updateMenuTests() throws Exception {
        Finder f = Finder.getSelectFinder(Menu.class).append(" where pid=:pid ").setParam("pid", "");

        List<Menu> list = menuService.queryForList(f, Menu.class);
        for (Menu menu : list) {
            menuService.updateMenu(menu);
        }


    }

    @Test
    public void updateTreeComcode() throws Exception {
        updateComcodeBypid("", ",");

    }


    private void updateComcodeBypid(String pid, String pcomcode) throws Exception {

        Finder f = Finder.getSelectFinder(Menu.class, "id").append(" where pid=:pid order by id asc ").setParam("pid", pid);
        List<String> list = menuService.queryForList(f, String.class);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }


        for (String id : list) {
            String comcode = pcomcode + id + ",";
            Finder f_update = new Finder("UPDATE ").append(Finder.getTableName(Menu.class)).append(" SET comcode=:comcode ").append(" WHERE id=:id ").setParam("comcode", comcode).setParam("id", id);
            menuService.update(f_update);
            updateComcodeBypid(id, comcode);
        }


    }


}
