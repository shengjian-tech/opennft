package net.shengjian.system.service.impl;

import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.frame.util.*;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.rpc.sessionuser.UserVO;
import net.shengjian.sms.service.ISmsToolService;
import net.shengjian.sms.util.SmsTypeEnum;
import net.shengjian.system.dto.LVDTO;
import net.shengjian.system.dto.PhoneLoginDTO;
import net.shengjian.system.entity.*;
import net.shengjian.system.service.*;
import net.shengjian.system.vo.LoginSuccessVO;
import net.shengjian.system.vo.LoginUserVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2013-07-06 16:03:00
 */
@Service("userService")
public class UserServiceImpl extends BaseSpringrainServiceImpl implements IUserService {

    @Resource
    private IUserRoleOrgService userRoleOrgService;

    @Resource
    private IUserRoleMenuService userRoleMenuService;

    @Resource
    private ISmsToolService smsToolService;

    @Resource
    private IDicDataService dicDataService;

    @Resource
    private IRoleService roleService;


    @Override
    public String save(IBaseEntity entity) throws Exception {
        return super.save(entity).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        User user = (User) entity;
        //清理缓存
        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, "findUserById_" + user.getId());
        return super.update(entity);
    }

    @Override
    public Integer update(IBaseEntity entity, boolean onlyupdatenotnull) throws Exception {
        User user = (User) entity;
        //清理缓存
        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, "findUserById_" + user.getId());
        return super.update(entity, onlyupdatenotnull);
    }


    @Override
    public User findUserById(String id) throws Exception {

        if (StringUtils.isBlank(id)) {
            return null;
        }
        String key = "findUserById_" + id;
        User user = super.getByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, User.class);
        if (user != null) {
            return user;
        }
        user = super.findById(id, User.class);
        if (user == null) {
            return null;
        }
        List<Org> orgByUserId = userRoleOrgService.findOrgByUserId(id, null);
        user.setOrgList(orgByUserId);
        List<Role> roles = userRoleMenuService.findRoleByUserId(id);
        user.setRoles(roles);
        // 加上缓存
        super.putByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, user);
        return user;
    }


    @Override
    public UserVO findUserVOByUserId(String userId) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return null;
        }
        User user = findUserById(userId);
        if (user == null) {
            return null;
        }

        UserVO userVO = new UserVO();
        userVO.setUserId(user.getId());
        userVO.setAccount(user.getAccount());
        userVO.setEmail(user.getEmail());
        userVO.setUserName(user.getUserName());
        userVO.setUserType(user.getUserType());
        userVO.setActive(user.getActive());


        return userVO;
    }

    @Override
    public String findUserIdByOpenId(String openId) throws Exception {

        if (StringUtils.isBlank(openId)) {
            return null;
        }
        Finder finder = Finder.getSelectFinder(User.class, " id ").append(" WHERE openId=:openId ").setParam("openId", openId);
        return super.queryForObject(finder, String.class);
    }

    @Override
    public String wrapJwtTokenByUser(User user) throws Exception {
        Map<String, Object> jwtSignMap = new HashMap<>();
        jwtSignMap.put("userId", user.getId());
        jwtSignMap.put("account", user.getAccount());
        jwtSignMap.put("userName", user.getUserName());
        jwtSignMap.put("userType", user.getUserType());

        String jwtToken = JwtUtils.sign(jwtSignMap);
        // RSA 私钥加密
        jwtToken = SecUtils.encoderByRSAPrivateKey(jwtToken);
        return jwtToken;
    }


    @Override
    public User findLoginUser(String account, String password, Integer userType) throws Exception {
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return null;
        }
        // Finder finder = new Finder("SELECT * FROM t_user WHERE account=:account ");
        Finder finder = Finder.getSelectFinder(User.class).append(" WHERE  account=:account  and password=:password  and active=1 order by createTime desc ");
        finder.setParam("account", account).setParam("password", password);
        List<User> users = super.queryForList(finder, User.class, new Page(1, 1));
        if(CollectionUtils.isNotEmpty(users)){
            return users.get(0);
        }
        return null;
    }


    @Override
    public List<User> findUserList(Page<User> page) throws Exception {
        Finder finder = Finder.getSelectFinder(User.class)
                .append(" WHERE 1=1 ");
        //	.append(" WHERE active=:active");
        //finder.setParam("active", CommonEnum.ACTIVE.未删除.getState());

        // 处理查询条件
        User queryBean = page.getData();
        if (queryBean != null) {
            String deptId = queryBean.getDeptId();
            if (StringUtils.isNotBlank(deptId)) {
                // 按部门查询
                List<String> userIdList = userRoleOrgService.findUserIdListByOrgId(deptId);
                if (CollectionUtils.isEmpty(userIdList)) {
                    return null;
                }
                finder.append(" AND id in (:userIdList)").setParam("userIdList", userIdList);
            }
            if (queryBean.getActive() != null) {
                finder.append(" AND active=:active ")
                        .setParam("active", queryBean.getActive());
            }
            if (StringUtils.isNotBlank(queryBean.getUserName())) {
                // 按姓名查询
                finder.append(" AND userName like :userName")
                        .setParam("userName", "%" + queryBean.getUserName() + "%");
            }

            if (StringUtils.isNotBlank(queryBean.getMobile())) {
                // 按手机号查询
                finder.append(" AND mobile like :mobile")
                        .setParam("mobile", "%" + queryBean.getMobile() + "%");
            }

            if (queryBean.getStatus() != null) {
                // 按状态查询
                finder.append(" AND status=:status")
                        .setParam("status", queryBean.getStatus());
            }

            if (page.getBeginTime() != null) {
                finder.append(" AND createTime>=:beginTime ")
                        .setParam("beginTime", page.getBeginTime());
            }
            if(page.getEndTime() != null){
                finder.append(" AND createTime<=:endTime ")
                        .setParam("endTime", DateUtils.addDays(page.getEndTime(), 1));
            }
        }
        //封装用户的部门和角色信息
        List<User> users = this.queryForList(finder, User.class, page);
        if (CollectionUtils.isEmpty(users)) {
            return new ArrayList<>();
        }
        for (User user : users) {
            String id = user.getId();
            List<Org> orgByUserId = userRoleOrgService.findOrgByUserId(id, null);
            user.setOrgList(orgByUserId);
            List<Role> roles = userRoleMenuService.findRoleByUserId(id);
            user.setRoles(roles);
        }
        return users;
    }

    @Override
    public void updatePwd(String account, String oldPwd, String newPwd) throws Exception {
        User userByAccount = findUserByAccount(account);
        if (userByAccount == null) {
            throw new RuntimeException("用户不存在");
        }
        String password = userByAccount.getPassword();
        oldPwd = SecUtils.encoderByMd5With32Bit(oldPwd);
        oldPwd = Optional.ofNullable(oldPwd).orElse("");
        if (!oldPwd.equals(password)) {
            throw new RuntimeException("旧密码不正确！");
        }
        newPwd = SecUtils.encoderByMd5With32Bit(newPwd);
        userByAccount.setPassword(newPwd);
        update(userByAccount, true);
    }

    @Override
    public void updatePwd(String account, String newPwd) throws Exception {
        User userByAccount = findUserByAccount(account);
        if (userByAccount == null) {
            throw new RuntimeException("用户不存在");
        }
        newPwd = SecUtils.encoderByMd5With32Bit(newPwd);
        userByAccount.setPassword(newPwd);
        update(userByAccount, true);
    }

    @Override
    public User saveUser(User user) throws Exception {
        String id;
        if (StringUtils.isBlank(user.getId())) {
            id = SecUtils.getTimeNO();
            //用户保存时账号唯一性判断
            String account = user.getAccount();
            User userByAccount = findUserByAccount(account);
            if (userByAccount != null) {
                throw new RuntimeException("用户账号[" + account + "]已存在！");
            }
        } else {
            id = user.getId();
        }
        user.setId(id);
        String password = user.getPassword();
        if (StringUtils.isBlank(password)) {
            password = SecUtils.encoderByMd5With32Bit("123");
        } else {
            password = SecUtils.encoderByMd5With32Bit(password);
        }
        user.setPassword(password);
        if (user.getCreateTime() == null) {
            user.setCreateTime(new Date());
        }
        if (user.getUserType() == null) {
            user.setUserType(1);
        }
        if (StringUtils.isBlank(user.getAvatar())) {
            user.setAvatar("/avatar/default.jpg");//用户默认头像
        }
        user.setUpdateTime(new Date());
        user.setCreateUserId(SessionUser.getUserId());
        user.setUpdateUserId(SessionUser.getUserId());
        super.save(user);

        List<Org> orgList = user.getOrgList();
        if (CollectionUtils.isEmpty(orgList)) {
            logger.error("没有选择用户部门！--updateUser({})",user);
            Org org = new Org();
            org.setId("defaultOrgId");
            orgList = new ArrayList<>();
            orgList.add(org);
        }
        UserOrg saveUserOrg = new UserOrg();
        List<UserOrg> userOrgList = new ArrayList<>();
        Date now = new Date();
        for (Org org : orgList) {
            UserOrg userOrg = new UserOrg();
            userOrg.setId(SecUtils.getUUID());
            userOrg.setCreateTime(now);
            userOrg.setUpdateTime(now);
            userOrg.setCreateUserId(SessionUser.getUserId());
            userOrg.setUpdateUserId(SessionUser.getUserId());

            Integer managerType = org.getManagerType();
            userOrg.setUserId(id);
            userOrg.setOrgId(org.getId());
            userOrg.setManagerType(managerType==null?1:managerType);
            userOrgList.add(userOrg);
        }
        saveUserOrg.setUserId(id);
        saveUserOrg.setUserOrgList(userOrgList);
        userRoleOrgService.updateUserOrg(saveUserOrg);

        List<Role> roles = user.getRoles();
        List<String> rolesString;
        if (CollectionUtils.isEmpty(roles)) {
            //没有选择角色,使用默认角色
            rolesString = Arrays.asList("defaultRoleId");
        } else {
            rolesString = roles.stream().map(Role::getId).collect(Collectors.toList());
        }
        userRoleMenuService.updateUserRoles(user.getId(), rolesString);

        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, "findUserById_" + user.getId());
        return user;
    }

    @Override
    public User updateUser(User user) throws Exception {
        String id;
        if (StringUtils.isBlank(user.getId())) {
            id = SecUtils.getTimeNO();
            //用户保存时账号唯一性判断
            String account = user.getAccount();
            User userByAccount = findUserByAccount(account);
            if (userByAccount != null) {
                throw new RuntimeException("用户账号已存在！");
            }
        } else {
            id = user.getId();
        }
        user.setId(id);
        super.update(user, true);

        List<Org> orgList = user.getOrgList();
        if (CollectionUtils.isEmpty(orgList)) {
            logger.error("没有选择用户部门！--updateUser({})",user);
            Org org = new Org();
            org.setId("defaultOrgId");
            orgList = new ArrayList<>();
            orgList.add(org);
        }
        UserOrg saveUserOrg = new UserOrg();
        List<UserOrg> userOrgList = new ArrayList<>();
        Date now = new Date();
        for (Org org : orgList) {
            UserOrg userOrg = new UserOrg();
            userOrg.setId(SecUtils.getUUID());
            userOrg.setCreateTime(now);
            userOrg.setUpdateTime(now);
            userOrg.setCreateUserId(SessionUser.getUserId());
            userOrg.setUpdateUserId(SessionUser.getUserId());

            Integer managerType = org.getManagerType();
            userOrg.setUserId(id);
            userOrg.setOrgId(org.getId());
            userOrg.setManagerType(managerType==null?1:managerType);
            userOrgList.add(userOrg);
        }
        saveUserOrg.setUserId(id);
        saveUserOrg.setUserOrgList(userOrgList);
        userRoleOrgService.updateUserOrg(saveUserOrg);

        List<Role> roles = user.getRoles();
        List<String> rolesString;
        if (CollectionUtils.isEmpty(roles)) {
            //没有选择角色,使用默认角色
            rolesString = Arrays.asList("defaultRoleId");
        } else {
            rolesString = roles.stream().map(Role::getId).collect(Collectors.toList());
        }
        userRoleMenuService.updateUserRoles(user.getId(), rolesString);

        super.cleanCache(GlobalStatic.userOrgRoleMenuInfoCacheKey);
        super.cleanCache(GlobalStatic.qxCacheKey);
        return user;
    }

    @Override
    public void deleteUser(String id) throws Exception {
        if (StringUtils.isNotBlank(id)) {
            String userId = SessionUser.getUserId();
            if (StringUtils.equals(userId, id)) {
                throw new RuntimeException("不能删除自己！");
            }

            //先清关系表，再清理主表
            Finder d_user_org = Finder.getDeleteFinder(UserOrg.class).append(" where userId=:userId").setParam("userId", id);
            Finder d_user_role = Finder.getDeleteFinder(UserRole.class).append(" where userId=:userId").setParam("userId", id);
            super.update(d_user_org);
            super.update(d_user_role);

            Finder d_user = Finder.getDeleteFinder(User.class).append(" where id=:id").setParam("id", id);
            super.update(d_user);
        }
    }

    @Override
    public void deleteUserBatch(List<String> ids) throws Exception {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        for (String id : ids) {
            deleteUser(id);
        }
    }

    @Override
    public User findUserByAccount(String account) throws Exception {
        Finder s_finder = Finder.getSelectFinder(User.class).append(" where account=:account").append(" and active=:active")
                .setParam("account", account)
                .setParam("active", CommonEnum.ACTIVE.未删除.getState());
        User user = super.queryForObject(s_finder, User.class);
        return user;
    }

    @Override
    public void logout(String userId) throws Exception {
        /*String roleCacheKey = "findRoleByUserId_" + userId;
        String menuCacheKey = "findMenuByUserId_" + userId;
        String userCacheKey = "findUserById_" + userId;
        super.evictByKey(GlobalStatic.qxCacheKey, userCacheKey);
        super.evictByKey(GlobalStatic.qxCacheKey, menuCacheKey);
        super.evictByKey(GlobalStatic.qxCacheKey, roleCacheKey);
        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, userCacheKey);
        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, menuCacheKey);
        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, roleCacheKey);
        List<Role> roleByUserId = userRoleMenuService.findRoleByUserId(userId);
        if(org.apache.commons.collections4.CollectionUtils.isNotEmpty(roleByUserId)){
            String roleId = roleByUserId.get(0).getId();
            String menuRoleCacheKey = "findMenuByRoleId_"+ roleId;
            super.evictByKey(GlobalStatic.qxCacheKey, menuRoleCacheKey);
            super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, menuRoleCacheKey);
        }*/
    }

    @Override
    public void saveByImported(String path) throws Exception {
        if (StringUtils.isBlank(path)) {
            return;
        }
        List<Row> excel = ExcelUtils.getExcel(path);
        Iterator<Row> iterator = excel.iterator();
        iterator.next();//过滤掉表格头
        while (iterator.hasNext()) {
            Row row = iterator.next();

            User user = new User();
            String userName = row.getCell(0).toString();
            if (StringUtils.isBlank(userName)) {
                break;
            }
            user.setUserName(userName);

            //角色
            String roleName = row.getCell(1).toString();
            Role role = new Role();
            role.setName(roleName);
            Role DBrole = super.queryForObject(role);
            if (DBrole == null) {
                //用户角色不存在！附一个默认角色
                DBrole = new Role();
                DBrole.setId("defaultRoleId");
            }
            List<Role> roles = Arrays.asList(DBrole);
            user.setRoles(roles);

            //部门
            String orgName = row.getCell(2).toString();
            Org org = new Org();
            org.setName(orgName);
            Org DBorg = super.queryForObject(org);
            if (DBorg == null) {
                //用户部门不存在!附一个默认部门
                DBorg = new Org();
                DBorg.setId("defaultOrgId");
            }
            DBorg.setManagerType(1);
            List<Org> orgList = Arrays.asList(DBorg);
            user.setOrgList(orgList);

            user.setAccount(row.getCell(3).toString());
            user.setMobile(row.getCell(4).toString());
            user.setEmail(row.getCell(5).toString());
            int active = "有效".equals(row.getCell(6).toString()) ? 1 : 0;
            user.setActive(active);
            Cell cell = row.getCell(7);
            Date createTime = net.shengjian.frame.util.DateUtils.convertString2Date(net.shengjian.frame.util.DateUtils.DATETIME_FORMAT, cell.toString());
            user.setCreateTime(createTime);

            this.saveUser(user);
        }

    }

    @Override
    public List<User> findUsers() throws Exception {
        Finder selectFinder = Finder.getSelectFinder(User.class).append(" where active=:active ").setParam("active", CommonEnum.ACTIVE.未删除.getState());
        return super.queryForList(selectFinder, User.class,new Page(1,20));
    }

    @Override
    public void sendCode(PhoneLoginDTO dto) throws Exception {
        if (dto == null) {
            logger.error("请求参数不完整!--sendCode({})", dto);
            throw new RuntimeException("请求参数不完整!");
        }
        String phone = dto.getPhone();
        if (StringUtils.isBlank(phone) || phone.length() != 11) {
            logger.error("手机号不正确--sendVerification({})", dto);
            throw new RuntimeException("手机号不正确!");
        }
        //发送验证码前验证图形验证码
        String captchaKey = dto.getCaptchaKey();
        String code = super.getByCache(GlobalStatic.springranloginCaptchaKey, captchaKey, String.class);
        super.evictByKey(GlobalStatic.springranloginCaptchaKey, captchaKey);
        if (StringUtils.isBlank(code) || !code.equalsIgnoreCase(dto.getCode())) {
            logger.error("验证码错误!--sendVerification({})", dto);
            throw new RuntimeException("验证码错误!");
        }
        Map<String, String> templateParam = new HashMap<>();
        String randomCode = this.getRandomCode(6);
        //生成随机数
        System.err.println(randomCode);
        templateParam.put("code", randomCode);
        smsToolService.send(phone, SmsTypeEnum.验证码, templateParam);
        //发送成功,设置缓存
        super.putByCache(GlobalStatic.springranloginCaptchaKey, phone, randomCode);
    }

    @Override
    public User savePhoneLogin(PhoneLoginDTO phoneLoginDTO) throws Exception {
        if (phoneLoginDTO == null) {
            logger.error("请求数据不完整!--saveEnterpriseInfo({})", phoneLoginDTO);
            throw new RuntimeException("请求数据不完整!");
        }
        String phone = phoneLoginDTO.getPhone();
        String dtoCode = phoneLoginDTO.getCode();
        if (StringUtils.isBlank(phone)) {
            logger.error("请填写手机号!--saveEnterpriseInfo({})", phoneLoginDTO);
            throw new RuntimeException("请填写手机号!");
        }
        if (StringUtils.isBlank(dtoCode)) {
            logger.error("验证码错误!--saveEnterpriseInfo({})", phoneLoginDTO);
            throw new RuntimeException("验证码错误!");
        }
        //保存信息前验证手机验证码
        String code = super.getByCache(GlobalStatic.springranloginCaptchaKey, phone, String.class);
        super.evictByKey(GlobalStatic.springranloginCaptchaKey, phone);
        if (!dtoCode.equals(code)) {
            logger.error("验证码错误!--saveEnterpriseInfo({})", phoneLoginDTO);
            throw new RuntimeException("验证码错误!");
        }
        //验证码正确,数据库是否有数据
        //保存信息前验证手机验证码
        String userId = this.findUserIdByPhone(phone);
        User user = new User();
        if (StringUtils.isBlank(userId)) {//用户不存在数据库中,将用户保存
            userId = SecUtils.getTimeNO();
            user.setId(userId);
            user.setPassword(SecUtils.encoderByMd5With32Bit("123"));
            user.setUserName("手机号登录用户");
            user.setCreateTime(new Date());
            user.setUserType(1);
            user.setAvatar("/avatar/default.jpg");//用户默认头像
            user.setUpdateTime(new Date());
            user.setAccount(phone);
            user.setMobile(phone);
            user.setActive(1);
            user.setBak1("");
            this.save(user);
            //手机用户默认角色
            String id = roleService.findRoleByCode("defaultRoleId");
            if (StringUtils.isNotBlank(id)) {
                userRoleMenuService.updateUserRoles(user.getId(),Arrays.asList(id));
            }
            //手机用户默认部门
            Org org = userRoleOrgService.findById("defaultOrgId", Org.class);
            if (org!=null){
                Date now = new Date();
                UserOrg userOrg = new UserOrg();
                userOrg.setId(SecUtils.getUUID());
                userOrg.setUserId(userId);
                userOrg.setOrgId(org.getId());
                userOrg.setCreateTime(now);
                userOrg.setUpdateTime(now);
                userOrg.setCreateUserId(userId);
                userOrg.setUpdateUserId(userId);

                Integer managerType = org.getManagerType();
                userOrg.setManagerType(managerType==null?1:managerType);
                userRoleOrgService.save(userOrg);
            }
        } else {
            user = this.findUserById(userId);
        }
        return user;
    }

    @Override
    public String findUserIdByPhone(String phone) throws Exception {
        Finder selectFinder = Finder.getSelectFinder(User.class, " id ")
                .append(" where active=1 AND mobile=:mobile order by createTime desc ")
                .setParam("mobile", phone);
        List<String> strings = super.queryForList(selectFinder, String.class, new Page(1, 1));
        if (CollectionUtils.isEmpty(strings)) {
            return null;
        }
        return strings.get(0);
    }

    @Override
    public List<LVDTO> userTypeList() throws Exception {
        Finder finder = Finder.getSelectFinder(DicData.class, " name as label,val as value ")
                .append(" where active=1 AND val is not null AND typekey=:typekey ")
                .setParam("typekey", "userType");
        return dicDataService.queryForList(finder, LVDTO.class);
    }

    @Override
    public LoginSuccessVO findUserCodeMap(User user) throws Exception {
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        List<Role> roleByUserId = userRoleMenuService.findRoleByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(roleByUserId)) {
            roleByUserId = roleByUserId.stream().sorted(Comparator.comparing(Role::getSortno)).collect(Collectors.toList());
            Role role = roleByUserId.get(0);
            Optional<String> optional = Optional.ofNullable(role.getCode());
            String roleCode = optional.orElse("").toUpperCase();
            String roleName = role.getName();
            LoginUserVO loginUserVO = new LoginUserVO();
            BeanUtils.copyProperties(user, loginUserVO);
            loginUserVO.setRoleName(roleName).setRoleCode(roleCode);
            List<Role> collect = roleByUserId.stream()
                    .filter(item -> StringUtils.isNotBlank(item.getIndexPage()))
                    .sorted(Comparator.comparing(Role::getSortno)).collect(Collectors.toList());
            if(CollectionUtils.isNotEmpty(collect)){
                loginUserVO.setIndexPage(collect.get(0).getIndexPage());
            }
            List<Menu> listMenu = userRoleMenuService.findMenuByUserId(loginUserVO.getId());
            List<String> listMenuCodes = new ArrayList<>();
            listMenuCodes.add("DEFAULT");
            for (Menu menu : listMenu) {
                Optional<String> optionalPageUrl = Optional.ofNullable(menu.getPageurl());
                String code = optionalPageUrl.orElse("");
                listMenuCodes.add(code);
            }
            loginUserVO.setCodes(listMenuCodes);

            Map<String, String> codeMap = new HashMap<>();
            loginUserVO.setCodeMap(codeMap);
            for (Menu menu : listMenu) {
                if (StringUtils.isNotBlank(menu.getCode())) {
                    codeMap.put(menu.getCode(), menu.getPageurl());
                }
            }
            loginSuccessVO.setUser(loginUserVO);
            String jwtToken = this.wrapJwtTokenByUser(user);
            loginSuccessVO.setJwttoken(jwtToken);
        }
        return loginSuccessVO;
    }
    //6为随机数
    private String getRandomCode(int size) {
        String randomCode = "";
        for (int i : generateRandomNumber(0, 9, size)) {
            randomCode += i;
        }
        return randomCode;
    }

    private static int[] generateRandomNumber(int begin, int end, int size) {
        if (begin > end) {
            int temp = begin;
            begin = end;
            end = temp;
        }
        if (end - begin < size) {
            throw new RuntimeException("Size is larger than range between begin and end!");
        } else {
            int[] seed = new int[end - begin];
            {
                int i = begin;
                while (i < end) {
                    seed[i - begin] = i++;
                }
            }
            int[] ranArr = new int[size];
            Random ran = new Random();
            for (int i = 0; i < size; ++i) {
                int j = ran.nextInt(seed.length - i);
                ranArr[i] = seed[j];
                seed[j] = seed[seed.length - 1 - i];
            }
            return ranArr;
        }
    }

    @Override
    public String getUserPhone(String userId) throws Exception {
        if(StringUtils.isBlank(userId)){
            throw new RuntimeException("参数为空");
        }
        User userById = this.findUserById(userId);
        if(userById==null || StringUtils.isBlank(userById.getMobile()) || "未知".equals(userById.getMobile())){
            throw new RuntimeException("手机号为空");
        }
        return userById.getMobile();
    }
    @Override
    public void verifyPhoneCode(String userPhone, String verificationCode) throws Exception {
        if(StringUtils.isBlank(userPhone)){
            throw new RuntimeException("手机号为空!");
        }
        if(StringUtils.isBlank(verificationCode)){
            throw new RuntimeException("验证码为空!");
        }
        //验证手机验证码
        String code = this.getByCache(GlobalStatic.springranloginCaptchaKey, userPhone, String.class);
        super.evictByKey(GlobalStatic.springranloginCaptchaKey, userPhone);
        if(!verificationCode.equalsIgnoreCase(code)){
            throw new RuntimeException("验证码错误!");
        }
    }
}
