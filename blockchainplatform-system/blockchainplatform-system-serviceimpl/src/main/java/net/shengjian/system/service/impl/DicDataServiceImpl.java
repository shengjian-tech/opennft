package net.shengjian.system.service.impl;

import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.frame.util.CommonEnum.ACTIVE;
import net.shengjian.frame.util.Finder;
import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.frame.util.Page;
import net.shengjian.system.entity.DicData;
import net.shengjian.system.service.IDicDataService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * TODO 在此加入类描述
 *
 * @author jiagou.com<Auto generate>
 * @version 2013-07-31 15:56:45
 */
@Service("dicDataService")
public class DicDataServiceImpl extends BaseSpringrainServiceImpl implements IDicDataService {

    /**
     * 字典顶级父类typeKey
     */
    private static final String ROOT_PID = "root";

    @Override
    public String save(IBaseEntity entity) throws Exception {
        DicData dicData = (DicData) entity;
        return (String) super.save(dicData);
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        DicData dicData = (DicData) entity;
        return super.update(dicData);
    }

    @Override
    public DicData findDicDataById(Object id) throws Exception {
        return super.findById(id, DicData.class);
    }

    @Override
    public DicData findDicDataById(String id, String pathtypekey) throws Exception {
        if (StringUtils.isBlank(id) || StringUtils.isBlank(pathtypekey)) {
            return null;
        }
        Finder finder = Finder.getSelectFinder(DicData.class)
                .append("  where id=:id and  typekey=:typekey   order by name ");
        finder.setParam("typekey", pathtypekey).setParam("id", id);
        DicData dicData = super.queryForObject(finder, DicData.class);


        return dicData;
    }

    @Override
    // @Cacheable(value = GlobalStatic.cacheKey, key =
    // "'findListDicData_'+#pathtypekey")
    public List<DicData> findListDicData(String pathtypekey, Page page, DicData dicData) throws Exception {
        if (StringUtils.isBlank(pathtypekey)) {
            return null;
        }
        Finder finder = Finder.getSelectFinder(DicData.class).append(" WHERE typekey=:typekey ");
        finder.setParam("typekey", pathtypekey);
        return super.queryForList(finder, DicData.class, page);
    }

    @Override
    // @CacheEvict(value = GlobalStatic.cacheKey, key =
    // "'findListDicData_'+#pathtypekey")
    public void deleteDicDataById(String id, String pathtypekey) throws Exception {
        String key = "findListDicData_" + pathtypekey;
        super.evictByKey(GlobalStatic.cacheKey, key);

        if (StringUtils.isBlank(id) || StringUtils.isBlank(pathtypekey)) {
            return;
        }

        Finder finder = Finder.getDeleteFinder(DicData.class).append(" where id=:id and  typekey=:typekey  ");
        finder.setParam("typekey", pathtypekey).setParam("id", id);
        super.update(finder);

    }

    @Override
    // @CacheEvict(value = GlobalStatic.cacheKey, key =
    // "'findListDicData_'+#pathtypekey")
    public void deleteDicDataByIds(List<String> ids, String pathtypekey) throws Exception {

        String key = "findListDicData_" + pathtypekey;
        super.evictByKey(GlobalStatic.cacheKey, key);

        if (CollectionUtils.isEmpty(ids) || StringUtils.isBlank(pathtypekey)) {
            return;
        }

        Finder finder = Finder.getDeleteFinder(DicData.class).append(" where id in(:ids) and  typekey=:typekey  ");
        finder.setParam("typekey", pathtypekey).setParam("ids", ids);
        super.update(finder);

    }

    @Override
    public String findCacheNameById(String id, String pathtypekey) throws Exception {
        List<DicData> findListDicData = findListDicData(pathtypekey, null, null);
        if (CollectionUtils.isEmpty(findListDicData) || StringUtils.isBlank(id)) {
            return null;
        }
        for (DicData dicData : findListDicData) {
            if (dicData.getId().equals(id)) {
                return dicData.getName();
            }
        }

        return null;
    }

    @Override
    public List<DicData> findDisDataByPathtypeKey(String pathtypekey, String sort, Integer active) throws Exception {
        if (StringUtils.isBlank(pathtypekey)) {
            return null;
        }
        Finder finder = Finder.getSelectFinder(DicData.class).append(" WHERE typekey=:typekey and active = :active ");
        finder.setParam("typekey", pathtypekey).setParam("active", active);
        if (StringUtils.isNotBlank(sort)) {
            finder.append(" order by sortno asc");
        }
        return super.queryForList(finder, DicData.class);
    }

    @Override
    public DicData findByCodeAndTypeKey(String code, String typeKey) throws Exception {
        Finder finder = Finder.getSelectFinder(DicData.class)
                .append(" where code=:code and typekey = :typeKey and active=1 ");
        finder.setParam("code", code).setParam("typeKey", typeKey);
        List<DicData> datas = super.queryForList(finder, DicData.class);
        if (!CollectionUtils.isEmpty(datas)) {
            return datas.get(0);
        }
        return null;
    }


    @Override
    public List<DicData> findTypeListByTypeName(Page<DicData> page, String typeName) throws Exception {
        Finder finder = Finder.getSelectFinder(DicData.class)
                .append(" WHERE pid=:typeName AND active=:active");
        finder.setParam("active", ACTIVE.未删除.getState()).setParam("typeName", typeName);
        return this.queryForList(finder, DicData.class, page);
    }


    @Override
    public List<DicData> findAllRootList(Page<DicData> page) throws Exception {
        Finder finder = Finder.getSelectFinder(DicData.class).append(" WHERE pid=:root ");
        finder.setParam("root", ROOT_PID);//.setParam("active", ACTIVE.未删除.getState());

        // 查询条件
        DicData queryBean = page.getData();
        if (queryBean != null) {
            super.getFinderWhereByQueryBean(finder, queryBean);
			/*if(StringUtils.isNotBlank(queryBean.getName())) {
				finder.append(" AND name like :name");
				finder.setParam("name", "%" + queryBean.getName() + "%");
			}
			
			if(StringUtils.isNotBlank(queryBean.getTypekey())) {
				finder.append(" AND typekey like :typekey");
				finder.setParam("typekey", "%" + queryBean.getTypekey() + "%");
			}
			
			if(queryBean.getStatus() != null) {
				finder.append(" AND status=:status");
				finder.setParam("status", queryBean.getStatus());
			}*/
        }

        page.setOrder("sortno");
        return this.queryForList(finder, DicData.class, page);
    }


    @Override
    public String saveDicDataType(DicData dicData) throws Exception {
        dicData.setActive(ACTIVE.未删除.getState());
        dicData.setId(dicData.getTypekey());
        dicData.setPid(ROOT_PID);
        dicData.setCreateTime(new Date());
        String id = this.save(dicData).toString();
        return id;
    }


    @Override
    public List<DicData> findListByPid(String pid, Page<DicData> page) throws Exception {
        Finder finder = Finder.getSelectFinder(DicData.class).append(" WHERE pid=:pid ");
        finder.setParam("pid", pid);//.setParam("active", ACTIVE.未删除.getState());
        DicData queryBean = page.getData();
        if (queryBean != null) {
            super.getFinderWhereByQueryBean(finder, queryBean);
        }

        return this.queryForList(finder, DicData.class, page);
    }


    @Override
    public List<DicData> findDicDataListByPid(String typekey) throws Exception {
        Finder finder = Finder.getSelectFinder(DicData.class).append(" WHERE pid=:pid AND active=:active");
        finder.setParam("pid", typekey).setParam("active", ACTIVE.未删除.getState());
        return this.queryForList(finder, DicData.class);
    }


    @Override
    public void deleteParentDicDataById(List<String> idList) throws Exception {
        Finder finder = Finder.getSelectFinder(DicData.class).append(" WHERE pid in (:pidList)");
        finder.setParam("pidList", idList);
        List<DicData> subDicDataList = this.queryForList(finder, DicData.class);
        if (CollectionUtils.isNotEmpty(subDicDataList)) {
            throw new Exception("当前字典有子级元素，不能删除");
        }
        this.deleteByIds(idList, DicData.class);

    }

    @Override
    public void saveDic(DicData dicData) throws Exception {
        if (dicData == null) {
            return;
        }
        if (dicData.getCreateTime() == null) {
            dicData.setCreateTime(new Date());
        }
        if (StringUtils.isBlank(dicData.getPid())) {
            dicData.setPid(ROOT_PID);
        }
        super.save(dicData);
    }

    @Override
    public void updateDic(DicData dicData) throws Exception {
        String typekey = dicData.getTypekey();
        String id = dicData.getId();
        List<DicData> dicDataListByPid = this.findDicDataListByPid(id);
        for (DicData data : dicDataListByPid) {
            data.setTypekey(typekey);
            update(data, true);
        }
        update(dicData, true);
    }

    @Override
    public List<DicData> findTree(Page<DicData> page) throws Exception {
        if (page == null || page.getData() == null) {
            logger.error("请求数据不完整!--findList()");
            throw new RuntimeException("请求数据不完整!");
        }
        Finder selectFinder = Finder.getSelectFinder(DicData.class)
                .append(" where 1=1 ");
        DicData data = page.getData();
        getFinderWhereByQueryBean(selectFinder, data);
        List<DicData> dicDataList = super.queryForList(selectFinder, DicData.class);
        return this.listConvertTree(dicDataList);
    }

    @Override
    public List<DicData> listConvertTree(List<DicData> list) throws Exception {
        List<DicData> tree = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return tree;
        }
        Map<String, DicData> map = new HashMap<>();
        for (DicData dicData : list) {
            String id = dicData.getId();
            map.put(id, dicData);
        }
        for (DicData dicData : list) {
            String pid = dicData.getPid();
            DicData parent = map.get(pid);
            if (parent == null) {
                tree.add(dicData);
                continue;
            }
            List<DicData> children = parent.getChildren();
            if (CollectionUtils.isEmpty(children)) {
                children = new ArrayList<>();
                parent.setChildren(children);
            }
            children.add(dicData);
        }
        return tree;
    }

}
