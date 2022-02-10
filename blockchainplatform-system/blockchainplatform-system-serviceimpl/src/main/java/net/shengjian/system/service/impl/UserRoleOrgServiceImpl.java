package net.shengjian.system.service.impl;

import net.shengjian.frame.util.CommonEnum.ACTIVE;
import net.shengjian.frame.util.Finder;
import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.entity.*;
import net.shengjian.system.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service("userRoleOrgService")
public class UserRoleOrgServiceImpl extends BaseSpringrainServiceImpl implements IUserRoleOrgService {
    @Resource
    private IUserRoleMenuService userRoleMenuService;
    @Resource
    private IUserService userService;
    @Resource
    private IOrgService orgService;
    @Resource
    private IRoleService roleService;


    @Override
    public List<String> findUserIdByOrgId(String orgId, Page page) throws Exception {
        if (StringUtils.isBlank(orgId)) {
            return null;
        }

        Finder finder = new Finder("SELECT re.userId FROM ").append(Finder.getTableName(UserOrg.class))
                .append(" re where  re.orgId=:orgId and re.managerType>0 order by re.managerType desc ");
        finder.setParam("orgId", orgId);

        return super.queryForList(finder, String.class, page);
    }

    @Override
    public List<User> findUserByOrgId(String orgId, Page page) throws Exception {
        if (StringUtils.isBlank(orgId)) {
            return null;
        }
        List<String> userIdByOrgId = findUserIdByOrgId(orgId, page);
        return listUserId2ListUser(userIdByOrgId);
    }


    @Override
    public List<User> findAllUserByOrgId(String orgId, Page page) throws Exception {

        if (StringUtils.isBlank(orgId)) {
            return null;
        }

        List<String> userIdByOrgId = findAllUserIdByOrgId(orgId, page);

        return listUserId2ListUser(userIdByOrgId);

    }

    @Override
    public List<String> findAllUserIdByOrgId(String orgId, Page page) throws Exception {

        if (StringUtils.isBlank(orgId)) {
            return null;
        }
        Org orgById = orgService.findOrgById(orgId);
        if (orgById == null) {
            return null;
        }
        String comcode = orgById.getComcode();

        Finder finder = new Finder("SELECT re.userId FROM ").append(Finder.getTableName(UserOrg.class)).append(" re,")
                .append(Finder.getTableName(Org.class))
                .append(" org WHERE org.id=re.orgId and org.comcode like :comcode and re.managerType>0   order by re.userId asc ");
        finder.setParam("comcode", comcode + "%");

        return super.queryForList(finder, String.class, page);

    }

    @Override
    public List<Org> findOrgByUserId(String userId, Page page) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        List<Org> orgList = new ArrayList<>();
        List<UserOrg> userOrgByUserId = findUserOrgByUserId(userId, page);
        if (CollectionUtils.isEmpty(userOrgByUserId)) {
            return orgList;
        }
        for (UserOrg userOrg : userOrgByUserId) {
            Org org = orgService.findOrgById(userOrg.getOrgId());
            if(org==null){
                continue;
            }
            //用户在部门中的角色
            org.setManagerType(userOrg.getManagerType());
            orgList.add(org);
        }
        return orgList;
        //List<String> orgIdByUserId = findOrgIdByUserId(userId, page);
        //return listOrgId2ListOrg(orgIdByUserId);
    }

    @Override
    public List<UserOrg> findUserOrgByUserId(String userId, Page page) throws Exception {
        Finder finder = new Finder("SELECT re.* FROM  ").append(Finder.getTableName(UserOrg.class)).append(" re ")
                .append("   WHERE re.userId=:userId    order by re.managerType desc   ");
        finder.setParam("userId", userId);

        return super.queryForList(finder, UserOrg.class, page);
    }


    @Override
    public List<String> findOrgIdByUserId(String userId, Page page) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        Finder finder = new Finder("SELECT re.orgId FROM  ").append(Finder.getTableName(UserOrg.class)).append(" re ")
                .append("   WHERE re.userId=:userId    order by re.managerType desc   ");
        finder.setParam("userId", userId);

        return super.queryForList(finder, String.class, page);

    }


    @Override
    public List<String> findManagerOrgIdByUserId(String userId, Page page) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return null;
        }

        Finder finder = new Finder("SELECT re.orgId FROM  ").append(Finder.getTableName(UserOrg.class))
                .append(" re  WHERE re.userId=:userId  and re.managerType=2  order by re.orgId desc   ");
        finder.setParam("userId", userId);

        return super.queryForList(finder, String.class, page);


    }

    @Override
    public List<Org> findManagerOrgByUserId(String userId, Page page) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return null;
        }

        List<String> orgIds = findManagerOrgIdByUserId(userId, page);

        return listOrgId2ListOrg(orgIds);

    }

    @Override
    public String findManagerUserIdByOrgId(String orgId) throws Exception {
        if (StringUtils.isBlank(orgId)) {
            return null;
        }


        Finder finder = new Finder("SELECT re.userId FROM ")
                .append(Finder.getTableName(UserOrg.class))
                .append(" re  WHERE   re.orgId=:orgId and  re.managerType=2   order by re.userId desc   ");
        finder.setParam("orgId", orgId);


        String managerUserId = super.queryForObject(finder, String.class);

        if (StringUtils.isBlank(managerUserId)) {
            managerUserId = "";
        }
        return managerUserId;
    }


    @Override
    public User findManagerUserByOrgId(String orgId) throws Exception {

        if (StringUtils.isBlank(orgId)) {
            return null;
        }
        String managerUserId = findManagerUserIdByOrgId(orgId);

        if (StringUtils.isBlank(managerUserId)) {
            return null;
        }

        return userService.findUserById(managerUserId);

    }


    @Override
    public Integer findAllUserCountByOrgId(String orgId) throws Exception {
        if (StringUtils.isBlank(orgId)) {
            return null;
        }
        Org org = orgService.findOrgById(orgId);

        if (org == null) {
            return null;
        }

        String comcode = org.getComcode();
        Finder finder = new Finder("SELECT count(re.userId) FROM ").append(Finder.getTableName(UserOrg.class))
                .append(" re,").append(Finder.getTableName(Org.class))
                .append(" org WHERE org.id=re.orgId and org.comcode like :comcode and  re.managerType>0 ");
        finder.setParam("comcode", comcode + "%");

        return super.queryForObject(finder, Integer.class);
    }

    @Override
    public List<RoleOrg> findOrgByRoleId(String roleId, Page page) throws Exception {
        if (StringUtils.isBlank(roleId)) {
            return null;
        }
        Finder finder = new Finder("SELECT re.* FROM ").append(Finder.getTableName(RoleOrg.class)).append(" re WHERE re.roleId=:roleId order by re.id desc ");
        finder.setParam("roleId", roleId);
        return super.queryForList(finder, RoleOrg.class, page);
    }


    @Override
    public Finder wrapOrgIdFinderByUserRole(List<RoleOrg> list) throws Exception {

        // 基于 List<UserRole> list 拼装WHERE条件
        Finder f = wrapOrgIdWheresSQLFinder(list);

        // 完善 前面的查询语句,构造完整的Finder 查询语句
        return wrapOrgIdFinder(f);
    }

    @Override
    public Finder wrapOrgIdFinderByFinder(Finder finder, String orgIdColumn, String createUserIdColumn) throws Exception {
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        //获取私有的权限部门 角色ID
        String privateOrgRoleId = SessionUser.getPrivateOrgRoleId();
        //需要拼接的部门权限的Finder对象,如果没有部门权限,为null
        Finder wrapOrgIdFinder = null;

        if (StringUtils.isNotBlank(privateOrgRoleId)) {//如果是私有部门权限的角色ID
            wrapOrgIdFinder = wrapOrgIdFinderByPrivateOrgRoleId(privateOrgRoleId, userId);
        } else {//如果是普通的部门权限
            wrapOrgIdFinder = wrapOrgIdFinderByUserId(userId);
        }

        if (wrapOrgIdFinder != null) {//如果存在部门权限
            finder.append(" AND " + orgIdColumn + " in ( ").appendFinder(wrapOrgIdFinder).append(")");
        } else {//只查询当前登录人的数据
            finder.append(" AND " + createUserIdColumn + "=:createUserId").setParam("createUserId", userId);
        }
        return finder;
    }


    @Override
    public Finder wrapOrgIdFinderByUserId(String userId) throws Exception {

        // 获取用户所有的  List<UserRole> list,包含主管的部门和角色分配的部门
        List<RoleOrg> list = findManagerOrgAndRoleOrgByUserId(userId);

        // 生成 Finder 方法.
        return wrapOrgIdFinderByUserRole(list);
    }

    @Override
    public Finder wrapOrgIdFinderByPrivateOrgRoleId(String roleId, String userId) throws Exception {

        if (StringUtils.isBlank(roleId) || StringUtils.isBlank(userId)) {
            return null;
        }
        Role role = roleService.findRoleById(roleId);
        if (role == null || role.getPrivateOrg() == 0) {//只处理 私有部门 类型的角色.
            return null;
        }

        // 当前用户 如果是主管,所管理的所有部门,如果是私有权限,就严格执行,不处理用户为主管的部门,部门主管可能没有部门权限.
        //  List<RoleOrg> list = wrapManagerRoleOrgByUserId(userId);
        List<RoleOrg> list = new ArrayList<>();
        //角色分配的 私有部门
        List<RoleOrg> findRoleOrgIdByRole = findRoleOrgIdByRole(role, userId, null);
        // 把主管部门
        if (CollectionUtils.isNotEmpty(findRoleOrgIdByRole)) {
            list.addAll(findRoleOrgIdByRole);
        }
        return wrapOrgIdFinderByUserRole(list);
    }

    /**
     * 根据role 对象 查询 Role的关联部门.  roleOrgType 0自己的数据,1所在部门,2所在部门及子部门数据,3.自定义部门数据.部门主管有所管理部门的数据全权限,无论角色是否分配
     * 外围需要单独判断是否启用私有角色,不然很容易造成群贤扩大
     * 这里只处理角色产生的权限,不考虑用户如果是主管派生的下级部门权限,这种情况有业务自己处理
     *
     * @param role
     * @return
     * @throws Exception
     */
    private List<RoleOrg> findRoleOrgIdByRole(Role role, String userId, Page page) throws Exception {

        if (role == null) {
            return null;
        }

        // 角色部门类型 roleOrgType 0自己的数据,1所在部门,2所在部门及子部门数据,3.自定义部门数据
        Integer roleOrgType = role.getRoleOrgType();

        if (roleOrgType == 0) { // 用户自己的数据,不包含部门权限
            return null;
        } else if (roleOrgType == 1 || roleOrgType == 2) { //用户所在的部门

            if (StringUtils.isBlank(userId)) {
                return null;
            }
            //用户所在的部门,这里只处理角色产生的权限,不考虑用户如果是主管派生的下级部门权限,这种情况有业务自己处理
            List<String> orgIdByUserId = findOrgIdByUserId(userId, page);
            if (CollectionUtils.isEmpty(orgIdByUserId)) {
                return null;
            }
            List<RoleOrg> list = new ArrayList<>();
            for (String orgId : orgIdByUserId) {
                RoleOrg re = new RoleOrg();
                // 如果是包含子部门权限
                if (roleOrgType == 2) {
                    re.setChildren(1);
                } else { // 不包含子部门的权限
                    re.setChildren(0);
                }
                re.setOrgId(orgId);
                list.add(re);
            }
            return list;
        } else if (roleOrgType == 3) { // 自定义权限
            return findOrgByRoleId(role.getId(), page);
        }
        return null;
    }


    /**
     * 查询用户根据角色派生和自身主管的所有部门.
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<RoleOrg> findManagerOrgAndRoleOrgByUserId(String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return null;
        }

        // 如果是主管,其管理的部门
        List<RoleOrg> list = wrapManagerRoleOrgByUserId(userId);


        // 查询用户所有的角色
        List<Role> listRole = userRoleMenuService.findRoleByUserId(userId);
        if (CollectionUtils.isEmpty(listRole)) {
            return list;
        }

        // 查询角色被分配到部门权限,不处理私有部门类型的角色
        for (Role role : listRole) {
            if (role.getPrivateOrg() == 1) { // 不处理 私有部门 类型的角色
                continue;
            }
            //角色管理的部门
            List<RoleOrg> findRoleOrgIdByRole = findRoleOrgIdByRole(role, userId, null);
            //添加到List
            if (CollectionUtils.isNotEmpty(findRoleOrgIdByRole)) {
                list.addAll(findRoleOrgIdByRole);
            }
        }


        return list;
    }


    /**
     * 查询用户作为主管时所有的管理部门,封装成 List<UserRole> 格式
     *
     * @param userId
     * @return List 对象,不能是 NULL
     */
    private List<RoleOrg> wrapManagerRoleOrgByUserId(String userId) throws Exception {
        List<RoleOrg> list = new ArrayList<>();

        // 查询用户直接管理的部门
        List<String> managerOrgIdByUserId = findManagerOrgIdByUserId(userId, null);
        if (CollectionUtils.isEmpty(managerOrgIdByUserId)) {
            return list;
        }
        // 构造List<RoleOrg>
        for (String orgId : managerOrgIdByUserId) {
            RoleOrg re = new RoleOrg();
            // 部门主管能管理子部门
            re.setChildren(1);
            re.setOrgId(orgId);
            list.add(re);
        }
        return list;
    }


    /**
     * 基于 List<UserRole> 生成 Finder 对象,并不是完整的语句,只是 WHERE 后面的部门条件语句 类似 and ( 1=2 or ....
     *
     * @param list
     * @return
     * @throws Exception
     */
    private Finder wrapOrgIdWheresSQLFinder(List<RoleOrg> list) throws Exception {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        //去掉重复的对象,对象是使用 id 作为对比字段的,需要把id设置好进行去重
        Set<RoleOrg> set = new HashSet<>();

        // 使用set去重
        // set.addAll(list);
        for (RoleOrg re : list) {
            RoleOrg r = new RoleOrg();
            r.setId(re.getOrgId() + "_" + re.getChildren());
            r.setOrgId(re.getOrgId());
            r.setChildren(re.getChildren());
            set.add(r);
        }


        // 不包含子部门的 部门Id List
        List<String> noChildrenList = new ArrayList<>();

        // 包含子部门的Finder
        Finder hasChildrenFinder = new Finder("  and ( 1=2 ");

        for (RoleOrg re : set) {
            String orgId = re.getOrgId();
            Integer children = re.getChildren();
            if (children == 0) {// 不包含子部门
                noChildrenList.add(orgId);
            } else if (children == 1) {// 包含子部门
                Org org = orgService.findOrgById(orgId);
                if(org==null){
                    continue;
                }
                String comcode = org.getComcode();
                String indexsign = "_system_temp_comcode_" + re.getId();
                hasChildrenFinder.append(" or _system_temp_org.comcode like :").append(indexsign).append(" ");
                hasChildrenFinder.setParam(indexsign, comcode + "%");
            }
        }

        // 处理没有子部门,部门Id or in 到 noChildrenList
        if (CollectionUtils.isNotEmpty(noChildrenList)) {
            // 前面有sql加连接符
            hasChildrenFinder.append(" or ");
            hasChildrenFinder.append(" _system_temp_org.id in (:_system_temp_orglist) ");
            hasChildrenFinder.setParam("_system_temp_orglist", noChildrenList);
        }

        hasChildrenFinder.append(") ");

        return hasChildrenFinder;
    }

    /**
     * 构造完整的finder,基于 wrapOrgIdWheresSQLFinder 产生的 WHERE 条件,完善Finder的查询部分.
     *
     * @param whereFinder WHERE条件的Finder
     * @return
     * @throws Exception
     */
    private Finder wrapOrgIdFinder(Finder whereFinder) throws Exception {
        if (whereFinder == null) {
            return null;
        }
        if (StringUtils.isBlank(whereFinder.getSql())) {
            return null;
        }

        /*
        // 查找人员
        Finder finder = new Finder("SELECT  _system_temp_user_org.userId FROM ");
        finder.append(Finder.getTableName(UserOrg.class)).append(" _system_temp_user_org,")
                .append(Finder.getTableName(Org.class)).append(" _system_temp_org ");
        finder.append(" WHERE _system_temp_user_org.orgId=_system_temp_org.id  ");
        */

        // 查找部门
        Finder finder = new Finder(" SELECT _system_temp_org.id  FROM ").append(Finder.getTableName(Org.class));
        finder.append(" _system_temp_org WHERE 1=1 ");

        // 增加 WHERE 条件
        finder.appendFinder(whereFinder);

        return finder;
    }

    /**
     * 根据 ListUserId 查询封装List<User> 对象
     *
     * @param listUserId
     * @return
     */
    private List<User> listUserId2ListUser(List<String> listUserId) throws Exception {
        if (CollectionUtils.isEmpty(listUserId)) {
            return null;
        }
        List<User> list = new ArrayList<>();
        for (String userId : listUserId) {
            User user = userService.findUserById(userId);
            list.add(user);
        }
        return list;

    }

    /**
     * 根据 ListOrgId 查询封装List<Org> 对象
     *
     * @param listOrgId
     * @return
     * @throws Exception
     */
    private List<Org> listOrgId2ListOrg(List<String> listOrgId) throws Exception {
        if (CollectionUtils.isEmpty(listOrgId)) {
            return null;
        }
        List<Org> list = new ArrayList<>();
        for (String orgId : listOrgId) {
            Org org = orgService.findOrgById(orgId);
            if(org==null){
                continue;
            }
            list.add(org);
        }
        return list;

    }

    @Override
    public String updateUserOrg(UserOrg userOrg) throws Exception {
        //Integer managerType = userOrg.getManagerType();
        List<UserOrg> userOrgList = userOrg.getUserOrgList();
        if (userOrg == null || CollectionUtils.isEmpty(userOrgList)) {
            return "数据不能为空";
        }

        Finder finder = Finder.getDeleteFinder(UserOrg.class).append(" WHERE userId=:userId ");
        finder.setParam("userId", userOrg.getUserId());// and orgId=:orgId .setParam("orgId", userOrg.getOrgId());
        super.update(finder);

        super.save(userOrgList);

        return null;
    }


    @Override
    public String updateRoleOrg(RoleOrg roleOrg) throws Exception {

        if (roleOrg == null || StringUtils.isBlank(roleOrg.getOrgId()) || StringUtils.isBlank(roleOrg.getRoleId()) || roleOrg.getCheck() == null) {
            return "数据不能为空";
        }

        Finder finder = Finder.getDeleteFinder(RoleOrg.class).append(" WHERE roleId=:roleId and orgId=:orgId ");
        finder.setParam("roleId", roleOrg.getRoleId()).setParam("orgId", roleOrg.getOrgId());
        super.update(finder);

        if (roleOrg.getCheck() == false) {// 删除关系
            return null;
        }

        Date now = new Date();
        roleOrg.setId(SecUtils.getUUID());
        roleOrg.setCreateTime(now);
        roleOrg.setUpdateTime(now);
        roleOrg.setCreateUserId(SessionUser.getUserId());
        roleOrg.setUpdateUserId(SessionUser.getUserId());
        super.save(roleOrg);
        return null;
    }

    @Override
    public boolean isUserInOrg(String userId, String orgId) throws Exception {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(orgId)) {
            return false;
        }
        Finder finder = Finder.getSelectFinder(UserOrg.class, " 1 ").append(" WHERE userId=:userId and orgId=:orgId ");
        finder.setParam("userId", userId).setParam("orgId", orgId);
        Integer isUserInOrg = super.queryForObject(finder, Integer.class);

        if (isUserInOrg == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<String> findUserIdListByOrgId(String deptId) throws Exception {
        Finder finder = Finder.getSelectFinder(UserOrg.class, "userId")
                .append(" WHERE orgId IN (SELECT id from ")
                .append(Finder.getTableName(Org.class))
                .append(" WHERE active=:active AND comcode like :deptId) ");
        finder.setParam("deptId", "%" + deptId + "%").setParam("active", ACTIVE.未删除.getState());
        return this.queryForList(finder, String.class);
    }

    @Override
    public void deleteByRoleId(String roleId) throws Exception {
        Finder deleteFinder = Finder.getDeleteFinder(RoleOrg.class)
                .append(" where roleId=:roleId ")
                .setParam("roleId", roleId);
        super.update(deleteFinder);
    }


}
