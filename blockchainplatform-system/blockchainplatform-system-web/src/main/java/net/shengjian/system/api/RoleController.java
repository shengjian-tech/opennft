package net.shengjian.system.api;

import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.property.MessageUtils;
import net.shengjian.system.api.vo.MenuVO;
import net.shengjian.system.api.vo.RoleUpdateMenuVO;
import net.shengjian.system.base.BaseController;
import net.shengjian.system.dto.RoleDTO;
import net.shengjian.system.entity.Menu;
import net.shengjian.system.entity.Role;
import net.shengjian.system.entity.RoleMenu;
import net.shengjian.system.entity.RoleOrg;
import net.shengjian.system.service.IRoleService;
import net.shengjian.system.service.IUserRoleMenuService;
import net.shengjian.system.service.IUserRoleOrgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 角色模块
 *
 * @author springrain<Auto generate>
 * @version 2019-07-27 16:10:16
 */
@RestController
@RequestMapping(value = "/api/system/role", method = RequestMethod.POST)
public class RoleController extends BaseController {

    @Resource
    private IRoleService roleService;

    @Resource
    private IUserRoleMenuService userRoleMenuService;

    @Resource
    private IUserRoleOrgService userRoleOrgService;

    /**
     * 角色列表数据，分页数据,包含（是否有效：否）
     *
     * @param page 分页对象 page.pageIndex 第几页
     * @return 分页数据，
     */
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public ReturnDatas<List<Role>> lists(@RequestBody Page<Role> page) {
        ReturnDatas<List<Role>> returnObject = ReturnDatas.getSuccessReturnDatas();


        List<Role> datas = null;
        try {
            datas = roleService.findRoleList(page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage("查询失败");
        }

        returnObject.setResult(datas);
        returnObject.setPage(page);
        return returnObject;
    }

    /**
     * 查询所有角色列表
     * 不包含（是否有效：是）
     *
     * @return 不分页数据
     * @throws Exception 异常
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ReturnDatas<List<Role>> list() throws Exception {
        ReturnDatas<List<Role>> returnObject = ReturnDatas.getSuccessReturnDatas();
        // ==构造分页请求
        Role role = new Role();
        role.setActive(1);
        Page<Role> page = new Page<>();
        page.setData(role);
        List<Role> roleList = roleService.findRoleList(page);
        returnObject.setResult(roleList);
        return returnObject;
    }

    /**
     * 查看的角色信息
     *
     * @param id 角色id
     * @return 角色信息
     * @throws Exception 异常
     */
    @RequestMapping(value = "/look", method = RequestMethod.POST)
    public ReturnDatas<RoleDTO> look(String id) throws Exception {
        ReturnDatas<RoleDTO> returnObject = ReturnDatas.getSuccessReturnDatas();

        if (StringUtils.isNotBlank(id)) {
            RoleDTO role = roleService.findRoleById(id);
            returnObject.setResult(role);
        } else {
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage("id不能为空!");
        }
        return returnObject;
    }

    /**
     * 保存新增角色操作
     *
     * @param dto 角色信息
     * @return 保存状态
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ReturnDatas<RoleDTO> save(@RequestBody RoleDTO dto) {
        ReturnDatas<RoleDTO> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.SAVE_SUCCESS);
        try {

            roleService.saveRole(dto);
            returnObject.setResult(dto);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(e.getMessage());
        }
        return returnObject;

    }

    /**
     * 根据角色id获取角色菜单
     *
     * @param roleId 角色id
     * @return 角色菜单
     * @throws Exception 异常
     */
    @RequestMapping(value = "/getMenusByRoleId", method = RequestMethod.POST)
    public ReturnDatas<List<MenuVO>> findMenuByRoleId(String roleId) throws Exception {
        ReturnDatas<List<MenuVO>> returnDatas = ReturnDatas.getSuccessReturnDatas();
        List<Menu> menus = userRoleMenuService.findMenuByRoleId(roleId);
        List<MenuVO> menuVOList = MenuVO.menuTreeConvertMenuVOTree(menus);
        /*ConcurrentMap<String, List<Map<String, Object>>> resutltMap = Maps.newConcurrentMap();
        List<Map<String, Object>> listMap = new ArrayList<>();
        userRoleMenuService.wrapVueMenu(menus, listMap);
        resutltMap.put("menus", listMap);*/
        returnDatas.setResult(menuVOList);
        return returnDatas;
    }

    /**
     * 修改更新角色
     *
     * @param dto 角色信息
     * @return 更新状态
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ReturnDatas<RoleDTO> update(@RequestBody RoleDTO dto) {
        ReturnDatas<RoleDTO> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
        try {

            String id = dto.getId();
            if (StringUtils.isBlank(id)) {
                return ReturnDatas.getErrorReturnDatas(MessageUtils.UPDATE_NULL_ERROR);
            }
            roleService.updateRole(dto);
            returnObject.setResult(dto);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(e.getMessage());
        }
        return returnObject;

    }

    /**
     * 删除操作
     *
     * @param id 角色id
     * @return 删除状态
     * @throws Exception 异常
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ReturnDatas<Role> delete(String id) throws Exception {
        if (StringUtils.isNotBlank(id)) {
            roleService.deleteRoleById(id);
            return new ReturnDatas<>(ReturnDatas.SUCCESS, MessageUtils.DELETE_SUCCESS);
        } else {
            return new ReturnDatas<>(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_ERROR);
        }
    }

    /**
     * 更新 角色 菜单
     *
     * @param roleMenu 角色菜单
     * @return 更新状态
     */
    @RequestMapping(value = "/updaterolemenu", method = RequestMethod.POST)
    public ReturnDatas<String> updaterolemenu(@RequestBody RoleMenu roleMenu) throws Exception {
        String str = userRoleMenuService.updateRoleMenu(roleMenu);
        if (StringUtils.isBlank(str)) {
            return ReturnDatas.getSuccessReturnDatas();
        } else {
            return ReturnDatas.getErrorReturnDatas(str);
        }
    }

    /**
     * 更新 角色 菜单，传入菜单集合
     *
     * @param roleUpdateMenuVO 参数对象
     * @return 更新状态
     * @throws Exception sql异常
     */
    @PostMapping("/updateRoleMenus")
    public ReturnDatas<String> updateRoleMens(@RequestBody RoleUpdateMenuVO roleUpdateMenuVO) {
        try {
            userRoleMenuService.updateRoleMenu(roleUpdateMenuVO.getRoleId(), roleUpdateMenuVO.getMenuIds());
            return new ReturnDatas<>(ReturnDatas.SUCCESS, MessageUtils.SAVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnDatas(ReturnDatas.ERROR, e.getMessage());
        }
    }

    /**
     * 更新 角色 部门
     *
     * @param roleOrg 角色部门
     * @return 更新状态
     */
    @RequestMapping(value = "/updateRoleOrg", method = RequestMethod.POST)
    public ReturnDatas<String> updateRoleOrg(@RequestBody RoleOrg roleOrg) throws Exception {
        String str = userRoleOrgService.updateRoleOrg(roleOrg);
        if (StringUtils.isBlank(str)) {
            return ReturnDatas.getSuccessReturnDatas();
        } else {
            return ReturnDatas.getErrorReturnDatas(str);
        }
    }

    /**
     * 删除多条记录
     *
     * @param ids 角色ids
     * @return 删除状态
     */
    @RequestMapping(value = "/delete/more", method = RequestMethod.POST)
    public ReturnDatas<Object> deleteMore(@RequestBody String[] ids) {

        if (ids == null || ids.length < 1) {
            return new ReturnDatas<>(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_ERROR);
        }
        try {
            List<String> listIds = Arrays.asList(ids);
            roleService.deleteByIds(listIds, Role.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ReturnDatas<>(ReturnDatas.ERROR, MessageUtils.DELETE_ALL_ERROR);
        }
        return new ReturnDatas<>(ReturnDatas.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);

    }

}