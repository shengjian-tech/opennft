package net.shengjian.system.service;

import net.shengjian.frame.util.Page;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.dto.RoleDTO;
import net.shengjian.system.entity.Role;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2019-07-24 14:20:37
 */
@RpcServiceAnnotation
public interface IRoleService extends IBaseSpringrainService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    RoleDTO findRoleById(String id) throws Exception;


    /**
     * 查角色列表
     *
     * @param page
     * @return
     * @throws Exception
     */
    List<Role> findRoleList(Page<Role> page) throws Exception;

    /**
     * 角色序号
     *
     * @return
     * @throws Exception
     */
    Integer findMaxSortNo() throws Exception;

    /**
     * 删除角色前，与角色关联的关系表数据，一并清楚
     * t_role_menu
     * t_role_org
     * t_user_role
     *
     * @param id 角色id
     * @throws Exception
     */
    void deleteRoleById(String id) throws Exception;

    /**
     * 新增角色
     *
     * @param role
     * @throws Exception
     */
    void saveRole(RoleDTO role) throws Exception;

    /**
     * 根据用户Id,查询角色IdList
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<String> findRoleByUserId(String userId) throws Exception;

    /**
     * 更新角色
     *
     * @param dto
     * @throws Exception
     */
    void updateRole(RoleDTO dto) throws Exception;

    /**
     * 判断当前登录用户是否是该角色的部门管理者
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    boolean isSupervisor(String roleId) throws Exception;

    /**
     *根据code查询角色id
     * @param code
     * @return
     * @throws Exception
     */
    String findRoleByCode(String code) throws Exception;
}
