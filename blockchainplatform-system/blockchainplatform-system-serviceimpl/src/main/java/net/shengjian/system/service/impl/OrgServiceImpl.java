package net.shengjian.system.service.impl;

import net.shengjian.frame.util.Finder;
import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.entity.DicData;
import net.shengjian.system.entity.Org;
import net.shengjian.system.entity.User;
import net.shengjian.system.entity.UserOrg;
import net.shengjian.system.service.IDicDataService;
import net.shengjian.system.service.IOrgService;
import net.shengjian.system.service.IUserRoleOrgService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 *
 */
@Service("orgService")
public class OrgServiceImpl extends BaseSpringrainServiceImpl implements IOrgService {

    @Resource
    private IDicDataService dicDataService;

    @Resource
    private IUserRoleOrgService userRoleOrgService;

    @Override
    public Org findOrgById(String id) throws Exception {

        if (StringUtils.isBlank(id)) {
            return null;
        }
        String key = "findOrgById_" + id;
        Org org = super.getByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, Org.class);
        if (org != null) {
            return org;
        }
        org = super.findById(id, Org.class);
        if (org == null) {
            return null;
        }
        // 加上缓存
        super.putByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, org);
        return org;
    }


    @Override
    public String saveOrg(Org entity) throws Exception {

        //String id= SecUtils.getUUID();
        String id = null;
        if (StringUtils.isNotBlank(entity.getId())) {
            id = entity.getId();
        } else {
            id = SecUtils.getTimeNO();
            //id = tableindexService.updateNewId(Org.class);
        }
        entity.setId(id);

        String comcode = findOrgNewComcode(id, entity.getPid());

        entity.setComcode(comcode);
        if (StringUtils.isBlank(entity.getPid())) {
            entity.setPid("");
        }

        if (entity.getActive() == null) {
            entity.setActive(1);
        }

        if (entity.getSortno() == null) {
            entity.setSortno(1);
        }

        Date now = new Date();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        entity.setUpdateUserId(SessionUser.getUserId());
        entity.setCreateUserId(SessionUser.getUserId());
        if (entity.getOrgType() == null) {
            entity.setOrgType(100);
        }
        if (entity.getPid() == null) {
            entity.setPid("");
        }

        if (entity.getSortno() == null) {
            entity.setSortno(100);
        }


        super.save(entity);
        //部门归属用户
        String userId = SessionUser.getUserId();
        UserOrg userOrg = new UserOrg();
        userOrg.setId(SecUtils.getTimeNO());
        userOrg.setUserId(userId);
        userOrg.setOrgId(entity.getId());
        userOrg.setCreateUserId(userId);
        userOrg.setCreateTime(new Date());
        userOrg.setUpdateTime(new Date());
        userOrg.setManagerType(2);
        userRoleOrgService.save(userOrg);

        // updateOrgManager(id,entity.getManagerRoleId());

        return id;
    }

    @Override
    public Integer updateOrg(Org entity) throws Exception {
        String pid = entity.getPid();
        entity.setComcode(null);

        String id = entity.getId();
        if (StringUtils.isEmpty(id)) {
            return null;
        }


        Org old_org = findOrgById(id);
        if (old_org == null) {
            return null;
        }

        if (id.equals(pid)) {
            pid = old_org.getPid();
            entity.setPid(pid);
        }
        String old_c = old_org.getComcode();

        String new_c = findOrgNewComcode(id, pid);

        if (new_c.equalsIgnoreCase(old_c)) {// 编码没有改变
            Integer update = super.update(entity, true);
            super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, "findOrgById_" + id);
            return update;
        }
        // 编码改变
        entity.setComcode(new_c);
        Integer update = super.update(entity, true);
        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, "findOrgById_" + id);

        // 级联更新
        Finder f_s_list = Finder.getSelectFinder(Org.class, "id,comcode")
                .append(" WHERE comcode like :comcode and id<>:id ").setParam("comcode", old_c + "%")
                .setParam("id", id);
        List<Org> list = super.queryForList(f_s_list, Org.class);
        if (CollectionUtils.isEmpty(list)) {
            return update;
        }

        for (Org org : list) {
            String _id = org.getId();
            String _c = findOrgNewComcode(_id, id);
            org.setComcode(_c);
            org.setPid(id);
            //清理缓存
            super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, "findOrgById_" + _id);
        }

        super.update(list, true);

        return update;
    }

    /**
     * 更新部门的主管Id
     *
     * @param orgId
     * @param managerId
     * @return
     * @throws Exception
     */

    @SuppressWarnings("unused")
    private void updateOrgManager(String orgId, String managerId) throws Exception {
        /*
         * 此方法不在用。 部门没有主管，只要主管角色相关联
         */
        if (StringUtils.isBlank(orgId) || StringUtils.isBlank(managerId)) {
            return;
        }

        Finder finder = Finder.getDeleteFinder(UserOrg.class).append(" WHERE orgId=:orgId and manager=1 ");
        finder.setParam("orgId", orgId);
        super.update(finder);
        UserOrg userOrg = new UserOrg();
        userOrg.setOrgId(orgId);
        userOrg.setUserId(managerId);

        super.save(userOrg);
    }

    @Override
    public Org findOrgById(Object id) throws Exception {

        Org org = super.findById(id, Org.class);
        if (org == null) {
            return null;
        }

        Finder f = new Finder("SELECT u.id id,u.name name FROM  ").append(Finder.getTableName(User.class)).append(" u,")
                .append(Finder.getTableName(UserOrg.class)).append(" re ");
        f.append(" WHERE re.userId=u.id and re.managerType=11 and re.orgId=:orgId order by u.id asc ");
        f.setParam("orgId", org.getId());

        Page page = new Page(1);
        page.setPageSize(1);
        page.setSelectpagecount(false);

        List<User> list = super.queryForList(f, User.class, page);

        if (CollectionUtils.isEmpty(list)) {
            return org;
        }

        // User u=list.get(0);

        return org;
    }


    @Override
    public List<Org> findTreeOrg() throws Exception {

        return findTreeByPid("");
    }


    @Override
    public String deleteOrgById(String orgId) throws Exception {
        if (StringUtils.isEmpty(orgId)) {
            return null;
        }
        Org org = findOrgById(orgId);
        if (org == null) {
            return null;
        }

        Finder f_update = Finder.getUpdateFinder(Org.class, " active=:active ").append(" WHERE comcode like :comcode ")
                .setParam("active", 0).setParam("comcode", org.getComcode() + "%");
        super.update(f_update);
        super.cleanCache(GlobalStatic.userOrgRoleMenuInfoCacheKey);
        return null;
    }

    @Override
    public List<Org> findTreeByPid(String pid) throws Exception {
       /* Finder finder = Finder.getSelectFinder(Org.class).append(" WHERE active=:active ").setParam("active", 1);

        if (StringUtils.isNotBlank(pid)) {//不是根目录
            Org porg = findOrgById(pid);
            if (porg == null) {
                return null;
            }
            finder.append(" and comcode like :comcode  ").setParam("comcode", porg.getComcode() + "%");
            //  .setParam("pid", pid);
        }
        *//*Finder finder1 = userRoleOrgService.wrapOrgIdFinderByFinder(finder, "id", "createUserId");
        if(finder1==null){
            logger.error("当前登录用户不存在!--findTreeByPid({})",pid);
            throw new RuntimeException("当前登录用户不存在!");
        }
*//*

        finder.append(" order by sortno asc ");*/

        List<Org> list = new ArrayList<>();
        Finder finder = userRoleOrgService.wrapOrgIdFinderByUserId(SessionUser.getUserId());

        if (finder == null) {
            return list;
        }

        List<String> orgIds = super.queryForList(finder, String.class);
        //List<RoleOrg> managerOrgAndRoleOrgByUserId = userRoleOrgService.findManagerOrgAndRoleOrgByUserId(SessionUser.getUserId());
        //Set<String> collect = managerOrgAndRoleOrgByUserId.stream().map(RoleOrg::getOrgId).collect(Collectors.toSet());
        for (String orgId : orgIds) {
            Org orgById = findOrgById(orgId);
            if(orgById==null){
                continue;
            }
            list.add(orgById);
        }
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        orgTypeName(list);

        return listOrg2Tree(list);
    }


    /**
     * 把 部门列表 转换成树形结构
     *
     * @param orgList
     * @return
     */
    @Override
    public List<Org> listOrg2Tree(List<Org> orgList) {
        if (CollectionUtils.isEmpty(orgList)) {
            return null;
        }
        // 先把数据放到map里,方便取值,对象和orgList里的是同一个
        Map<String, Org> map = new HashMap<>();
        for (Org org : orgList) {
            map.put(org.getId(), org);
        }

        // 循环遍历orgList
        List<Org> list = new ArrayList<>();
        for (Org org : orgList) {
            String pid = org.getPid();
            Org parent = map.get(pid);
            // 没有父节点
            if (parent == null) {
                list.add(org);
                continue;
            }

            //如果有父节点
            List<Org> children = parent.getChildren();
            if (children == null) {
                children = new ArrayList<>();
                parent.setChildren(children);
            }
            children.add(org);
        }

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list;

    }

    @Override
    public void orgTypeName(List<Org> orgList) throws Exception {
        if (CollectionUtils.isEmpty(orgList)) {
            return;
        }
        List<DicData> bumenleixing = dicDataService.findListDicData("bumenleixing", null, null);
        if (CollectionUtils.isEmpty(bumenleixing)) {
            return;
        }
        for (Org data : orgList) {
            Integer orgType = data.getOrgType();
            for (DicData dicData : bumenleixing) {
                String val = dicData.getVal();
                if (StringUtils.isNotBlank(val) && orgType == Integer.parseInt(val)) {
                    data.setOrgTypeName(dicData.getName());
                }
            }
        }
    }

    @Override
    public List<Org> findOrgList(Org org, Page<Org> page) throws Exception {
        List<Org> list = new ArrayList<>();
        Finder finder = userRoleOrgService.wrapOrgIdFinderByUserId(SessionUser.getUserId());
        if (finder == null) {
            return list;
        }
        if (org!=null) {
            super.getFinderWhereByQueryBean(finder,org);
        }
        List<String> orgIds = super.queryForList(finder, String.class, page);
        if(CollectionUtils.isEmpty(orgIds)){
            return list;
        }
        for (String orgId : orgIds) {
            Org orgById = findOrgById(orgId);
            if(orgById==null){
                continue;
            }
            list.add(orgById);
        }
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list;
    }

    @Override
    public String findOrgNewComcode(String id, String pid) throws Exception {

        if (StringUtils.isEmpty(id)) {
            return null;
        }

        String comcode = null;
        Finder f_p_c = Finder.getSelectFinder(Org.class, "comcode").append(" WHERE id=:pid ").setParam("pid", pid);
        String p_c = super.queryForObject(f_p_c, String.class);
        // 如果没有上级部门
        if (StringUtils.isEmpty(p_c)) {
            p_c = ",";
        }

        comcode = p_c + id + ",";

        return comcode;
    }


    /*@SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> findOrgTreeVoList() throws Exception {
        Finder finder = Finder.getSelectFinder(Org.class).append(" WHERE active=:active");
        finder.setParam("active", 1);
        List<Org> orgList = this.queryForList(finder, Org.class);
        if (CollectionUtils.isNotEmpty(orgList)) {
            Map<String, Object> idOrgMap = new HashMap<String, Object>();
            Map<String, Object> orgMap = new HashMap<String, Object>();
            // 按层级排序  让循环从父到子循环
            orgList = orgList.stream().sorted(Comparator.comparingInt(t -> {
                return StringUtils.split(t.getComcode(), ";").length;
            })).collect(Collectors.toList());
            for (Org org : orgList) {
                String pid = org.getPid();

                Map<String, Object> orgInfo = new HashMap<String, Object>();
                orgInfo.put("id", org.getId());
                orgInfo.put("label", org.getName());
                idOrgMap.put(org.getId(), orgInfo);

                if (StringUtils.isBlank(org.getPid())) {
                    // 顶级部门
                    orgMap.put(org.getId(), orgInfo);
                } else {
                    // 子级部门
                    Map<String, Object> parentOrg = (Map<String, Object>) idOrgMap.get(pid);
                    List<Map<String, Object>> children = null;
                    if (parentOrg.containsKey("children")) {
                        children = (List<Map<String, Object>>) parentOrg.get("children");
                    } else {
                        children = new ArrayList<Map<String, Object>>();
                    }
                    children.add(orgInfo);
                    parentOrg.put("children", children);
                }
            }
            List<Map<String, Object>> orgInfoList = new ArrayList<Map<String, Object>>();
            for (String orgIdKey : orgMap.keySet()) {
                orgInfoList.add((Map<String, Object>) orgMap.get(orgIdKey));
            }
            return orgInfoList;
        }
        return null;
    }*/
}
