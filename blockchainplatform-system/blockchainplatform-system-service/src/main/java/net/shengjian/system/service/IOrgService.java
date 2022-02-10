package net.shengjian.system.service;

import net.shengjian.frame.util.Page;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.entity.Org;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2013-07-06 16:02:58
 */
@RpcServiceAnnotation
public interface IOrgService extends IBaseSpringrainService {


    /**
     * 根据部门Id 查找部门信息,有缓存
     *
     * @param id
     * @return
     * @throws Exception
     */
    Org findOrgById(String id) throws Exception;


    /**
     * 保存
     *
     * @param entity
     * @return
     * @throws Exception
     */
    String saveOrg(Org entity) throws Exception;

    /**
     * 更新
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Integer updateOrg(Org entity) throws Exception;

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    Org findOrgById(Object id) throws Exception;

    /**
     * 查找Org的树形结构
     *
     * @return
     * @throws Exception
     */
    List<Org> findTreeOrg() throws Exception;

    /**
     * 根据父类Id 查找Org的树形结构,根为 null
     *
     * @return
     * @throws Exception
     */
    List<Org> findTreeByPid(String pid) throws Exception;

    /**
     * 删除部门Id以及下面的子部门
     *
     * @param orgId
     * @return
     * @throws Exception
     */
    String deleteOrgById(String orgId) throws Exception;

    /**
     * 根据id和pid生成部门的Comcode
     *
     * @param id
     * @param pid
     * @return
     * @throws Exception
     */
    String findOrgNewComcode(String id, String pid) throws Exception;

    /**
     * 把 部门列表 转换成树形结构(从impl中抽出接口)
     *
     * @param orgList
     * @return
     */
    List<Org> listOrg2Tree(List<Org> orgList);

    /**
     * 封装部门类型名字，用于前端展示的属性
     *
     * @param orgList
     */
    void orgTypeName(List<Org> orgList) throws Exception;

    /**
     * 当前用户所管理的部门数据
     *
     * @param org
     * @param page
     * @return
     */
    List<Org> findOrgList(Org org, Page<Org> page) throws Exception;
    /**
     * 查询部门树形结构
     *
     * @return
     * @throws Exception
     * @author 程相羽
     * @version 2020年10月29日 下午2:17:34
     *//*
    List<Map<String, Object>> findOrgTreeVoList() throws Exception;
*/

}
