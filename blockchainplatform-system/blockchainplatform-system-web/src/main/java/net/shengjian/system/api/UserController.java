package net.shengjian.system.api;

import net.shengjian.frame.util.ExcelUtils;
import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.frame.util.property.MessageUtils;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.api.vo.MenuVO;
import net.shengjian.system.api.vo.UserRoleUpdateVO;
import net.shengjian.system.api.vo.UserUpdatePwdVO;
import net.shengjian.system.base.BaseController;
import net.shengjian.system.dto.LVDTO;
import net.shengjian.system.dto.UserDTO;
import net.shengjian.system.entity.*;
import net.shengjian.system.service.IUserRoleMenuService;
import net.shengjian.system.service.IUserRoleOrgService;
import net.shengjian.system.service.IUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户模块
 */
@RestController
@RequestMapping(value = "/api/system/user", method = RequestMethod.POST)
public class UserController extends BaseController {

    @Resource
    private IUserRoleMenuService userRoleMenuService;

    @Resource
    private IUserRoleOrgService userRoleOrgService;

    @Resource
    private IUserService userService;

    @Value("${staticdir}")
    private String path;

    /**
     * 后台用户列表（分页）
     *
     * @param page 分页条件数据
     * @return 分页数据
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ReturnDatas<List<User>> list(@RequestBody Page<User> page) {
        ReturnDatas<List<User>> returnObject = ReturnDatas.getSuccessReturnDatas();

        List<User> datas = null;
        try {
            datas = userService.findUserList(page);
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
     * 查看的Json格式数据
     *
     * @param id 用户id
     */
    @RequestMapping(value = "/look", method = RequestMethod.POST)
    public ReturnDatas<UserDTO> look(String id) throws Exception {
        ReturnDatas<UserDTO> returnObject = ReturnDatas.getSuccessReturnDatas();

        if (StringUtils.isNotBlank(id)) {
            User user = userService.findUserById(id);
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            userDTO.setUserType(user.getUserType() + "");
            userDTO.setOrgList(user.getOrgList());
            userDTO.setRoles(user.getRoles());
            returnObject.setResult(userDTO);
        } else {
            returnObject.setMessage("id为空！");
            returnObject.setStatus(ReturnDatas.ERROR);
        }
        return returnObject;

    }

    /**
     * 保存 操作,返回json格式数据
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ReturnDatas<User> save(@RequestBody User user) {
        ReturnDatas<User> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.SAVE_SUCCESS);
        try {

            String id = user.getId();
            if (StringUtils.isBlank(id)) {
                user.setId(null);
            }
            User save = userService.saveUser(user);

            returnObject.setResult(save);
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.SAVE_ERROR);
        }
        return returnObject;

    }

    /**
     * 用户类型List
     *
     * @return
     */
    @PostMapping("/userTypeList")
    public ReturnDatas<List<LVDTO>> userTypeList() {
        ReturnDatas<List<LVDTO>> returnDatas = new ReturnDatas<>();
        try {
            List<LVDTO> list = userService.userTypeList();
            returnDatas.setResult(list);
            returnDatas.setMessage("查询成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage(e.getMessage());
        }
        return returnDatas;
    }

    /**
     * 修改 操作,返回json格式数据
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ReturnDatas<User> update(@RequestBody User user) {
        ReturnDatas<User> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
        try {

            java.lang.String id = user.getId();
            if (StringUtils.isBlank(id)) {
                return ReturnDatas.getErrorReturnDatas(MessageUtils.UPDATE_NULL_ERROR);
            }
            User update = userService.updateUser(user);

            returnObject.setResult(update);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.UPDATE_ERROR);
        }
        return returnObject;
    }

    /**
     * 更新用户角色关系
     *
     * @param userRoleUpdateVO 用户角色更新参数
     * @return 用户
     */
    @RequestMapping(value = "/updateuserrole", method = RequestMethod.POST)
    public ReturnDatas<String> updateuserrole(@RequestBody UserRoleUpdateVO userRoleUpdateVO)
            throws Exception {
        String userId = userRoleUpdateVO.getUserId();
        List<String> roleIds = userRoleUpdateVO.getRoleIds();
        String str = userRoleMenuService.updateUserRoles(userId, roleIds);
        if (StringUtils.isBlank(str)) {
            return ReturnDatas.getSuccessReturnDatas();
        } else {
            return ReturnDatas.getErrorReturnDatas(str);
        }
    }

    /**
     * 用户获取部门
     *
     * @param userId 用户id
     * @return 用户信息
     * @throws Exception 异常
     */
    @RequestMapping(value = "/findOrgByUserId", method = RequestMethod.POST)
    public ReturnDatas<List<UserOrg>> findOrgByUserId(String userId)
            throws Exception {
        List<UserOrg> orgs = userRoleOrgService.findUserOrgByUserId(userId, null);

        ReturnDatas<List<UserOrg>> returnDatas = ReturnDatas.getSuccessReturnDatas();
        returnDatas.setResult(orgs);
        return returnDatas;
    }

    /**
     * 用户根据角色获取部门
     *
     * @param roleId 角色id
     * @return 角色的部门集合
     * @throws Exception 异常
     */
    @RequestMapping(value = "/findOrgByRoleId", method = RequestMethod.POST)
    public ReturnDatas<List<RoleOrg>> findOrgByRoleId(String roleId)
            throws Exception {
        List<RoleOrg> orgs = userRoleOrgService.findOrgByRoleId(roleId, null);

        ReturnDatas<List<RoleOrg>> returnDatas = ReturnDatas.getSuccessReturnDatas();
        returnDatas.setResult(orgs);
        return returnDatas;
    }

    /**
     * 更新用户部门关系
     *
     * @param userOrg 对象参数
     * @return 更新状态
     * @throws Exception 异常
     */
    @RequestMapping(value = "/updateuserorg", method = RequestMethod.POST)
    public ReturnDatas<String> updateuserorg(@RequestBody UserOrg userOrg) throws Exception {
        String str = userRoleOrgService.updateUserOrg(userOrg);
        if (StringUtils.isBlank(str)) {
            return ReturnDatas.getSuccessReturnDatas();
        } else {
            return ReturnDatas.getErrorReturnDatas(str);
        }
    }

    /**
     * 删除操作
     *
     * @param id 用户id
     * @return 删除状态
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ReturnDatas<User> delete(String id) throws Exception {
        try {
            if (StringUtils.isNotBlank(id)) {
                //userService.deleteById(id, User.class);
                userService.deleteUser(id);
                return new ReturnDatas<>(ReturnDatas.SUCCESS, MessageUtils.DELETE_SUCCESS);
            } else {
                return new ReturnDatas<>(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_ERROR);
            }
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            return new ReturnDatas<>(ReturnDatas.ERROR, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ReturnDatas<>(ReturnDatas.ERROR, MessageUtils.DELETE_ERROR);
        }
    }

    /**
     * 删除多条记录
     *
     * @param ids String数组 [1,2,3]
     * @return 删除状态
     */
    @RequestMapping(value = "/delete/more", method = RequestMethod.POST)
    public ReturnDatas<Object> deleteMore(@RequestBody String[] ids) {

        if (ids == null || ids.length < 1) {
            return new ReturnDatas<>(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_ERROR);
        }
        try {
            List<String> listIds = Arrays.asList(ids);
            userService.deleteUserBatch(listIds);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ReturnDatas<>(ReturnDatas.ERROR, MessageUtils.DELETE_ALL_ERROR);
        }
        return new ReturnDatas<>(ReturnDatas.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);

    }

    /**
     * 获取用户的 权限菜单
     *
     * @return 菜单ids
     * @throws Exception 异常
     */
    @RequestMapping(value = "/getRouters", method = RequestMethod.POST)
    public ReturnDatas<List<String>> menuIds() throws Exception {
        // 获取当前登录人
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }
        ReturnDatas<List<String>> successReturnDatas = ReturnDatas.getSuccessReturnDatas();
        List<Menu> listMenu = userRoleMenuService.findMenuByUserId(userId);

        List<String> listMenuIds = new ArrayList<>();
        listMenuIds.add("default");
        successReturnDatas.setResult(listMenuIds);

        if (CollectionUtils.isEmpty(listMenu)) {
            return successReturnDatas;
        }

        for (Menu menu : listMenu) {
            listMenuIds.add(menu.getId());
        }

        // List<Menu> listMenu =
        // userRoleMenuService.findMenuTreeByUsreId(userId);

        // List<Map<String,Object>> listMap=new ArrayList<>();
        // 包装成Vue使用的树形结构
        // userRoleMenuService.wrapVueMenu(listMenu,listMap);

        // successReturnDatas.setResult(listMap);

        return successReturnDatas;
    }

    /**
     * 获取用户的 权限code
     *
     * @return 菜单ids
     * @throws Exception 异常
     */
    @RequestMapping(value = "/getMenuCodes", method = RequestMethod.POST)
    public ReturnDatas<List<String>> menuCodes(String userId) throws Exception {
        // 获取当前登录人
        if (StringUtils.isBlank(userId)) {
            userId = SessionUser.getUserId();
        }
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }
        ReturnDatas<List<String>> successReturnDatas = ReturnDatas.getSuccessReturnDatas();
        List<Menu> listMenu = userRoleMenuService.findMenuByUserId(userId);

        List<String> listMenuCodes = new ArrayList<>();
        listMenuCodes.add("DEFAULT");
        successReturnDatas.setResult(listMenuCodes);

        if (CollectionUtils.isEmpty(listMenu)) {
            return successReturnDatas;
        }

        for (Menu menu : listMenu) {
            Optional<String> optional = Optional.ofNullable(menu.getPageurl());
            //String code = optional.orElse("").toUpperCase().replace("/","_");
            String code = optional.orElse("");
            listMenuCodes.add(code);
        }

        // List<Menu> listMenu =
        // userRoleMenuService.findMenuTreeByUsreId(userId);

        // List<Map<String,Object>> listMap=new ArrayList<>();
        // 包装成Vue使用的树形结构
        // userRoleMenuService.wrapVueMenu(listMenu,listMap);

        // successReturnDatas.setResult(listMap);

        return successReturnDatas;
    }

    /**
     * 获取用户的角色，（缺省参数，获取当前登录用户的角色）
     *
     * @return 角色集合
     * @throws Exception 异常
     */
    @RequestMapping(value = "/getRolesByUserId", method = RequestMethod.POST)
    public ReturnDatas<List<Role>> getRolesByUserId(String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            // 获取当前登录人
            userId = SessionUser.getUserId();
        }
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }
        ReturnDatas<List<Role>> successReturnDatas = ReturnDatas.getSuccessReturnDatas();
        List<Role> roles = userRoleMenuService.findRoleByUserId(userId);

        successReturnDatas.setResult(roles);

        return successReturnDatas;
    }

    /**
     * 获取用户的 信息
     *
     * @return 用户信息
     * @throws Exception 异常
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public ReturnDatas<User> info() throws Exception {
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }

        User user = userService.findUserById(userId);
        ReturnDatas<User> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setResult(user);
        return returnObject;
    }

    /**
     * 获取用户的 路由权限菜单
     *
     * @return 显示用户菜单
     * @throws Exception 缓存异常
     */
    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    public ReturnDatas<List<MenuVO>> getRouters() throws Exception {
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }
        List<Menu> listMenu = userRoleMenuService.findMenuByUserId(userId);
        List<MenuVO> menuVOList = MenuVO.menuConvertMenuVO(listMenu);
        menuVOList = menuVOList.stream().filter(m -> m.getType() != 0).collect(Collectors.toList());
        ReturnDatas<List<MenuVO>> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setResult(menuVOList);
        return returnObject;
    }

    /**
     * 根据用户账号更新用户密码,
     * 管理员强制重置密码（无需旧密码）
     *
     * @param userUpdatePwdVO 对象参数
     * @return 更新结果
     */
    @PostMapping("/updatePwd")
    public ReturnDatas<String> updatePwd(@RequestBody UserUpdatePwdVO userUpdatePwdVO) {
        String account = userUpdatePwdVO.getAccount();
        if (StringUtils.isBlank(account)) {
            return ReturnDatas.getErrorReturnDatas("用户账号不能为空");
        }
        String newPwd = userUpdatePwdVO.getNewPwd();
        if (StringUtils.isBlank(newPwd)) {
            return ReturnDatas.getErrorReturnDatas("新密码不能为空！");
        }
        try {
            userService.updatePwd(account, newPwd);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnDatas<>(ReturnDatas.ERROR, e.getMessage());
        }
        return new ReturnDatas<>(ReturnDatas.SUCCESS, "密码修改成功！");
    }

    /**
     * 修改个人密码，
     * 默认登录权限
     *
     * @param userUpdatePwdVO
     * @return
     */
    @PostMapping("/updateSelfPassword")
    public ReturnDatas<String> updateSelfPassword(@RequestBody UserUpdatePwdVO userUpdatePwdVO) {
        String account = SessionUser.getAccount();
        if (account == null) {
            throw new RuntimeException("用户不存在！");
        }
        userUpdatePwdVO.setAccount(account);

        String oldPwd = userUpdatePwdVO.getOldPwd();
        String newPwd = userUpdatePwdVO.getNewPwd();
        if (StringUtils.isBlank(oldPwd) || StringUtils.isBlank(newPwd)) {
            return ReturnDatas.getErrorReturnDatas("旧密码或新密码不能为空！");
        }
        try {
            userService.updatePwd(account, oldPwd, newPwd);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnDatas<>(ReturnDatas.ERROR, e.getMessage());
        }
        return new ReturnDatas<>(ReturnDatas.SUCCESS, "密码修改成功！");
    }

    /**
     * 用户导出
     *
     * @return
     */
    @GetMapping("/export")
    public void export(@RequestBody(required = false) Page<User> page, HttpServletResponse response) {
        try {
            if(page==null){
                page = new Page<>();
                page.setPageNo(1);
                page.setPageSize(60000);
            }
            List<User> userList = userService.findUserList(page);
            List<String[]> title = new ArrayList<>();
            String[] userName = new String[2];
            userName[0] = "userName";
            userName[1] = "用户名称";

            String[] roles = new String[2];
            roles[0] = "roles.name";
            roles[1] = "角色名称";

            String[] orgList = new String[2];
            orgList[0] = "orgList.name";
            orgList[1] = "部门名称";

            String[] account = new String[2];
            account[0] = "account";
            account[1] = "登录账号";

            String[] mobile = new String[2];
            mobile[0] = "mobile";
            mobile[1] = "手机";

            String[] email = new String[2];
            email[0] = "email";
            email[1] = "邮箱";

            String[] active = new String[2];
            active[0] = "active";
            active[1] = "是否有效";

            String[] createTime = new String[2];
            createTime[0] = "createTime";
            createTime[1] = "创建时间";

            title.add(userName);
            title.add(roles);
            title.add(orgList);
            title.add(account);
            title.add(mobile);
            title.add(email);
            title.add(active);
            title.add(createTime);

            File file = ExcelUtils.dataToExcel(userList, title, path + "/temp/");
            downFile(response, file, "用户信息.xlsx", true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 下载用户信息导入模板
     *
     * @param response
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        try {
            File file = new File(path + "/template/用户信息导入模板.xlsx");
            downFile(response, file, "用户信息导入模板.xlsx", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户导入，（excel）
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("/import")
    public ReturnDatas<String> userImport(@RequestParam(value = "file") MultipartFile multipartFile) {

        String originalFilename = multipartFile.getOriginalFilename();
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        String fileName = "/upload/" + SecUtils.getTimeNO() + fileSuffix;
        String filePath = path + fileName;
        File file = new File(path + "/upload/");
        if (!file.exists()) {
            file.mkdirs();
        }
        try (InputStream inputStream = multipartFile.getInputStream();
             FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            int copy = IOUtils.copy(inputStream, fileOutputStream);
            userService.saveByImported(filePath);
            return new ReturnDatas<>(ReturnDatas.SUCCESS, "数据导入成功！");
        } catch (IOException e) {
            e.printStackTrace();
            return new ReturnDatas<>(ReturnDatas.ERROR, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnDatas<>(ReturnDatas.ERROR, e.getMessage());
        }
    }

    /**
     * 个人信息修改（不能修改 角色 ，部门）
     *
     * @param user
     * @return
     */
    @PostMapping("/updateSelf")
    public ReturnDatas<User> updateSelf(@RequestBody User user) throws Exception {
        ReturnDatas<User> returnDatas = ReturnDatas.getSuccessReturnDatas();
        String userId = SessionUser.getUserId();
        User DBUser = null;
        if (StringUtils.isNotBlank(userId)) {
            user.setId(userId);
            DBUser = userService.findUserById(userId);
        }

        if (DBUser != null) {
            //用户修改个人信息时，修改部门和角色无效
            List<Org> orgList = DBUser.getOrgList();
            List<Role> roles = DBUser.getRoles();
            user.setOrgList(orgList);
            user.setRoles(roles);
            userService.updateUser(user);
            returnDatas.setStatus(ReturnDatas.SUCCESS);
            returnDatas.setMessage(MessageUtils.UPDATE_SUCCESS);
        } else {
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage("用户不存在！");
        }
        returnDatas.setResult(user);
        return returnDatas;
    }

    /**
     * 头像修改
     *
     * @param avatar
     * @return
     */
    @PostMapping("/updateAvatar")
    public ReturnDatas<String> avatar(MultipartFile avatar) {
        ReturnDatas<String> returnDatas = ReturnDatas.getSuccessReturnDatas();
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage("用户不存在！");
            return returnDatas;
        }
        String originalFilename = avatar.getOriginalFilename();
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        List<String> list = Arrays.asList(".jpg", ".gif", ".png");
        if (list.contains(fileSuffix)) {
            String fileName = "/avatar/" + SecUtils.getTimeNO() + fileSuffix;
            ;
            String filePath = path + fileName;
            File file = new File(path + "/avatar/");
            if (!file.exists()) {
                file.mkdirs();
            }
            try (InputStream inputStream = avatar.getInputStream();
                 OutputStream outputStream = new FileOutputStream(filePath)) {
                IOUtils.copy(inputStream, outputStream);
                User user = new User();
                user.setId(userId);
                user.setAvatar(fileName);
                userService.update(user, true);
                returnDatas.setMessage(MessageUtils.UPDATE_SUCCESS);
            } catch (IOException e) {
                returnDatas.setStatus(ReturnDatas.ERROR);
                returnDatas.setMessage(e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage("文件类型不正确！");
        }
        return returnDatas;
    }
}
