package net.shengjian.system.api;

import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.property.MessageUtils;
import net.shengjian.system.api.vo.MenuVO;
import net.shengjian.system.base.BaseController;
import net.shengjian.system.entity.Menu;
import net.shengjian.system.service.IMenuService;
import net.shengjian.system.service.IUserRoleMenuService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 菜单模块
 *
 * @author springrain<Auto generate>
 * @version 2019-07-26 17:52:10
 */
@RestController
@RequestMapping(value = "/api/system/menu", method = RequestMethod.POST)
public class MenuController extends BaseController {
    @Resource
    private IMenuService menuService;

    @Resource
    private IUserRoleMenuService userRoleMenuServiceImpl;

    /**
     * 所有菜单数据，分页查询，page.data中可封装查询条件
     *
     * @param page 参数对象
     * @return 分页数据
     * @throws Exception 异常
     */
    @PostMapping("/all/list/json")
    public ReturnDatas<List<MenuVO>> allListJson(@RequestBody Page<Menu> page) {
        ReturnDatas<List<MenuVO>> returnObject = ReturnDatas.getSuccessReturnDatas();
        try {
            List<Menu> datas = menuService.findAllMenuListByQueryBean(page.getData(), null);
            List<MenuVO> menuVOList = MenuVO.menuTreeConvertMenuVOTree(datas);
            returnObject.setPage(page);
            returnObject.setResult(menuVOList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(e.getMessage());
        }

        return returnObject;
    }

    /**
     * 查询所有的菜单树，
     *
     * @return 全部数据，不分页
     * @throws Exception 异常
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ReturnDatas<List<MenuVO>> lists() throws Exception {
        ReturnDatas<List<MenuVO>> returnObject = ReturnDatas.getSuccessReturnDatas();
        // ==构造分页请求
        // Page page = newPage(request);
        // ==执行分页查询
        List<Menu> datas = userRoleMenuServiceImpl.findAllMenuTree();//Menu_Tree
        List<MenuVO> menuVOList = MenuVO.menuTreeConvertMenuVOTree(datas);//MenuVO_Tree
        returnObject.setResult(menuVOList);
        return returnObject;
    }

    /**
     * 查看菜单
     *
     * @param id 菜单id
     */
    @RequestMapping(value = "/look", method = RequestMethod.POST)
    public ReturnDatas<MenuVO> look(String id) throws Exception {
        ReturnDatas<MenuVO> returnObject = ReturnDatas.getSuccessReturnDatas();

        if (StringUtils.isNotBlank(id)) {
            Menu menu = menuService.findMenuById(id);
            MenuVO menuVO = new MenuVO(menu);
            returnObject.setResult(menuVO);
        } else {
            returnObject.setStatus(ReturnDatas.ERROR);
        }
        return returnObject;

    }

    /**
     * 保存菜单
     *
     * @param menuVO 菜单对象
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ReturnDatas<Menu> save(@RequestBody MenuVO menuVO) {
        ReturnDatas<Menu> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.SAVE_SUCCESS);
        try {
            Menu menu = menuVO.menuVoConvertMenu();
            String id = menu.getId();
            if (StringUtils.isBlank(id)) {
                menu.setId(null);
            }
            menu.setUpdateTime(new Date());
            menu.setCreateTime(new Date());
            menu.setActive(1);
            menuService.saveMenu(menu);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.SAVE_ERROR);
        }
        return returnObject;

    }

    /**
     * 批量保存菜单
     *
     * @param menuVOList 参数对象
     * @return 返回保存失败的数据
     */
    @PostMapping("/batchSave")
    public ReturnDatas<List<MenuVO>> batchSave(@RequestBody List<MenuVO> menuVOList) {
        if (CollectionUtils.isNotEmpty(menuVOList)) {
            List<Menu> menuList = MenuVO.menvVOListConvertMentList(menuVOList);
            try {
                menuService.saveBatch(menuList);
            } catch (Exception e) {
                e.printStackTrace();
                List<MenuVO> errMenuVOList = MenuVO.menuConvertMenuVO(menuList);
                return new ReturnDatas<>(ReturnDatas.ERROR, e.getMessage(), errMenuVOList);
            }
            return new ReturnDatas<>(ReturnDatas.SUCCESS, MessageUtils.SAVE_SUCCESS);
        }
        return ReturnDatas.getSuccessReturnDatas();
    }

    /**
     * 修改菜单
     *
     * @param menuVO 修改的对象
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ReturnDatas<Menu> update(@RequestBody MenuVO menuVO) {
        ReturnDatas<Menu> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
        try {
            Menu menu = menuVO.menuVoConvertMenu();
            String id = menu.getId();
            if (StringUtils.isBlank(id)) {
                return ReturnDatas.getErrorReturnDatas(MessageUtils.UPDATE_NULL_ERROR);
            }

            menuService.updateMenu(menu);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.UPDATE_ERROR);
        }
        return returnObject;

    }

    /**
     * 删除操作
     *
     * @param id 删除的菜单id
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ReturnDatas<Menu> delete(String id) throws Exception {
        // 执行删除
        try {
            if (StringUtils.isNotBlank(id)) {
                menuService.deleteMenuById(id);
                return new ReturnDatas<>(ReturnDatas.SUCCESS, MessageUtils.DELETE_SUCCESS);
            } else {
                return new ReturnDatas<>(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ReturnDatas<>(ReturnDatas.ERROR, MessageUtils.DELETE_ERROR);
    }

}
