package net.shengjian.system.service.impl;

import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.frame.util.Finder;
import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.dto.RoleDTO;
import net.shengjian.system.entity.*;
import net.shengjian.system.service.IRoleService;
import net.shengjian.system.service.IUserRoleOrgService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 角色
 *
 * @author springrain<Auto generate>
 * @version 2019-07-24 14:20:37
 */

@Service("roleService")
public class RoleServiceImpl extends BaseSpringrainServiceImpl implements IRoleService {

    @Resource
    private IUserRoleOrgService userRoleOrgService;

    @Override
    public String save(IBaseEntity entity) throws Exception {
        Role role = (Role) entity;
        return super.save(entity).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        Role role = (Role) entity;
        //清理缓存
        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, "findRoleById_" + role.getId());
        return super.update(entity);
    }

    @Override
    public RoleDTO findRoleById(String id) throws Exception {

        if (StringUtils.isBlank(id)) {
            return null;
        }
        String key = "findRoleById_" + id;
        RoleDTO dto = super.getByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, RoleDTO.class);
        if (dto != null) {
            return dto;
        }
        Role role = super.findById(id, Role.class);
        Integer roleOrgType = role.getRoleOrgType();
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(role, roleDTO);
        if (roleOrgType == 3) {
            Finder selectFinder = Finder.getSelectFinder(RoleOrg.class, " orgId ")
                    .append(" where roleId=:roleId ")
                    .setParam("roleId", id);
            List<String> orgIds = super.queryForList(selectFinder, String.class);
            if (CollectionUtils.isEmpty(orgIds)) {
                orgIds = new ArrayList<>();
            }
            //角色部门
            roleDTO.setOrgIds(orgIds);
            Finder selectFinder2 = Finder.getSelectFinder(RoleOrg.class)
                    .append(" where roleId=:roleId ")
                    .setParam("roleId", id);
            List<RoleOrg> roleOrgs = super.queryForList(selectFinder2, RoleOrg.class);
            if (CollectionUtils.isEmpty(roleOrgs)) {
                roleOrgs = new ArrayList<>();
            }
            roleDTO.setRoleOrgList(roleOrgs);
        }
        if (role == null) {
            return null;
        }
        // 加上缓存
        super.putByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, dto);
        return roleDTO;
    }

    @Override
    public List<Role> findRoleList(Page<Role> page) throws Exception {
        Finder finder = Finder.getSelectFinder(Role.class)
                .append(" WHERE 1=1 ");
        // 处理查询条件
        Role queryBean = page.getData();
        if (queryBean != null) {
            if (queryBean.getActive() != null) {
                finder.append(" AND active=:active ");
                finder.setParam("active", queryBean.getActive());
            }
            if (StringUtils.isNotBlank(queryBean.getName())) {
                // 按姓名查询
                finder.append(" AND name like :name")
                        .setParam("name", "%" + queryBean.getName() + "%");
            }

            if (StringUtils.isNotBlank(queryBean.getCode())) {
                // 按手机号查询
                finder.append(" AND code like :code")
                        .setParam("code", "%" + queryBean.getCode() + "%");
            }


            if (page.getBeginTime() != null && page.getEndTime() != null) {
                finder.append(" AND createTime>=:beginTime AND createTime<=:endTime ")
                        .setParam("beginTime", page.getBeginTime())
                        .setParam("endTime", DateUtils.addDays(page.getEndTime(), 1));
            }
        }
        Finder finder1 = userRoleOrgService.wrapOrgIdFinderByFinder(finder, "orgId", "createUserId");
        if (finder1 == null) {
            logger.error("当前登录用户不存在!--findRoleList({})", page);
            throw new RuntimeException("当前登录用户不存在!");
        }
        return super.queryForList(finder, Role.class, page);
    }

    @Override
    public Integer findMaxSortNo() throws Exception {
        Finder s_finder = Finder.getSelectFinder(Role.class, " max(sortno) ");
        return this.queryForObject(s_finder, Integer.class);
    }

    @Override
    public void deleteRoleById(String id) throws Exception {
        Finder d_t_role_menu = Finder.getDeleteFinder(RoleMenu.class).append(" where roleId=:roleId").setParam("roleId", id);
        Finder d_t_role_org = Finder.getDeleteFinder(RoleOrg.class).append(" where roleId=:roleId").setParam("roleId", id);
        Finder d_t_user_role = Finder.getDeleteFinder(UserRole.class).append(" where roleId=:roleId").setParam("roleId", id);
        Finder d_t_role = Finder.getDeleteFinder(Role.class).append("where id=:id").setParam("id", id);
        super.update(d_t_role_menu);
        super.update(d_t_role_org);
        super.update(d_t_user_role);
        super.update(d_t_role);
        //super.deleteById(id,Role.class);
        super.cleanCache(GlobalStatic.qxCacheKey);
    }

    @Override
    public void saveRole(RoleDTO role) throws Exception {
        Date now = new Date();
        String id;
        if (StringUtils.isBlank(role.getId())) {
            id = SecUtils.getTimeNO();
            role.setId(id);
        } else {
            id = role.getId();
        }


        //用户部门
        List<Org> orgByUserId = userRoleOrgService.findOrgByUserId(SessionUser.getUserId(), null);
        Org _org = null;
        if (CollectionUtils.isNotEmpty(orgByUserId)) {
            for (Org org : orgByUserId) {
                if (org.getManagerType() == 2) {
                    _org = org;
                    break;
                }
            }
            if (_org == null) {
                throw new RuntimeException("您不是部门主管！不能新增角色");
            }
            role.setOrgId(_org.getId());
        } else {
            throw new RuntimeException("该登录用户无部门！");
        }
        role.setCreateUserId(SessionUser.getUserId());
        role.setCreateTime(now);
        role.setUpdateTime(now);
        Integer maxSortNo = findMaxSortNo();
        role.setSortno(++maxSortNo);
        Role role1 = new Role();
        BeanUtils.copyProperties(role, role1);
        super.save(role1);
        Integer roleOrgType = role.getRoleOrgType();
        //角色自定义部门
        if (roleOrgType == 3) {
            /*List<String> orgIds = role.getOrgIds();
            if(CollectionUtils.isEmpty(orgIds)){
                orgIds = new ArrayList<>();
            }
            //角色部门
            for (String orgId : orgIds) {
                RoleOrg roleOrg = new RoleOrg();
                roleOrg.setId(SecUtils.getTimeNO());
                roleOrg.setRoleId(role.getId());
                roleOrg.setOrgId(orgId);
                roleOrg.setChildren(0);
                roleOrg.setCreateTime(now);
                roleOrg.setUpdateTime(now);
                roleOrg.setCreateUserId(SessionUser.getUserId());
                roleOrg.setUpdateUserId(SessionUser.getUserId());
                super.save(roleOrg);
            }*/
            List<RoleOrg> roleOrgList = role.getRoleOrgList();
            if (CollectionUtils.isEmpty(roleOrgList)) {
                roleOrgList = new ArrayList<>();
            }
            for (RoleOrg roleOrg : roleOrgList) {
                roleOrg.setId(SecUtils.getTimeNO());
                roleOrg.setRoleId(role.getId());
                roleOrg.setChildren(roleOrg.getChildren() == null ? 0 : roleOrg.getChildren());
                roleOrg.setCreateTime(now);
                roleOrg.setUpdateTime(now);
                roleOrg.setCreateUserId(SessionUser.getUserId());
                roleOrg.setUpdateUserId(SessionUser.getUserId());
                super.save(roleOrg);
            }
        } else {
            //角色部门
            RoleOrg roleOrg = new RoleOrg();
            roleOrg.setId(SecUtils.getTimeNO());
            roleOrg.setRoleId(id);
            roleOrg.setOrgId(_org.getId());
            roleOrg.setChildren(0);
            roleOrg.setCreateTime(now);
            roleOrg.setUpdateTime(now);
            roleOrg.setCreateUserId(SessionUser.getUserId());
            roleOrg.setUpdateUserId(SessionUser.getUserId());
            super.save(roleOrg);
        }
        super.cleanCache(GlobalStatic.qxCacheKey);
    }

    @Override
    public List<String> findRoleByUserId(String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return new ArrayList<>();
        }
        Finder finder = Finder.getSelectFinder(UserRole.class, " roleId ")
                .append(" where userId=:userId ")
                .setParam("userId", userId);
        return super.queryForList(finder, String.class);
    }

    @Override
    public void updateRole(RoleDTO dto) throws Exception {
        if (dto == null) {
            logger.error("请求参数不完整--updateRole({})", dto);
            throw new RuntimeException("请求参数不完整!");
        }
        String userId = SessionUser.getUserId();
        String roleId = dto.getId();
        if (!this.isSupervisor(roleId)) {
            logger.error("该角色中的部门主管才可更新此角色信息!--updateRole({})", dto);
            throw new RuntimeException("该角色中的部门主管才可更新此角色信息!");

        }
        dto.setUpdateTime(new Date());
        dto.setUpdateUserId(userId);
        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        super.update(role, true);
        String key = "findRoleById_" + roleId;
        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, key);

        userRoleOrgService.deleteByRoleId(roleId);
        /*List<String> orgIds = dto.getOrgIds();
        if(CollectionUtils.isEmpty(orgIds)){
            orgIds = new ArrayList<>();
        }
        //角色部门
        for (String orgId : orgIds) {
            RoleOrg roleOrg = new RoleOrg();
            roleOrg.setId(SecUtils.getTimeNO());
            roleOrg.setRoleId(roleId);
            roleOrg.setOrgId(orgId);
            roleOrg.setChildren(0);
            roleOrg.setCreateTime(now);
            roleOrg.setUpdateTime(now);
            roleOrg.setCreateUserId(SessionUser.getUserId());
            roleOrg.setUpdateUserId(SessionUser.getUserId());
            super.save(roleOrg);
        }*/
        //角色部门
        Date now = new Date();
        List<RoleOrg> roleOrgList = dto.getRoleOrgList();
        if (CollectionUtils.isEmpty(roleOrgList)) {
            roleOrgList = new ArrayList<>();
        }
        for (RoleOrg roleOrg : roleOrgList) {
            if (StringUtils.isBlank(roleOrg.getOrgId())) {
                continue;
            }
            roleOrg.setId(SecUtils.getTimeNO());
            roleOrg.setRoleId(roleId);
            roleOrg.setChildren(roleOrg.getChildren() == null ? 0 : roleOrg.getChildren());
            roleOrg.setCreateTime(now);
            roleOrg.setUpdateTime(now);
            roleOrg.setCreateUserId(SessionUser.getUserId());
            roleOrg.setUpdateUserId(SessionUser.getUserId());
            super.save(roleOrg);
        }
        super.cleanCache(GlobalStatic.qxCacheKey);
    }

    @Override
    public boolean isSupervisor(String roleId) throws Exception {
        String userId = SessionUser.getUserId();
        //更新角色信息之前的验证
        List<Org> userOrgList = userRoleOrgService.findOrgByUserId(userId, null);
        //角色的部门
        Role roleDB = this.findById(roleId, Role.class);
        String roleOrgId = "";
        if (roleDB != null) {
            roleOrgId = roleDB.getOrgId();
        }
        //标志部门中的主管角色
        boolean supervisor = false;
        for (Org org : userOrgList) {
            //用户在部门中的角色 在部门中的地位-0会员,1员工,2主管
            if (StringUtils.equals(org.getId(), roleOrgId) && org.getManagerType() == 2) {
                return true;
            }
        }
        return supervisor;
    }

    @Override
    public String findRoleByCode(String code) throws Exception {
        Finder finder = Finder.getSelectFinder(Role.class, " id ").append(" where code=:code ")
                .setParam("code", code);
        return super.queryForObject(finder,String.class);
    }
}
