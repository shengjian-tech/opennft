package net.shengjian.frame.service;

import jxl.Cell;
import net.shengjian.frame.dao.IBaseJdbcDao;
import net.shengjian.frame.entity.BaseMapEntity;
import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.frame.util.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.SqlParameter;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * 基础的Service父类,所有的Service都必须继承此类,每个数据库都需要一个实现.</br>
 * 例如
 * demo数据的实现类是org.springrain.springrain.service.BasedemoServiceImpl,demo2数据的实现类是org.springrain.demo2.service.Basedemo2ServiceImpl</br>
 *
 * @author springrain<Auto generate>
 * @version 2013-03-19 11:08:15
 * @see BaseServiceImpl
 */
public abstract class BaseServiceImpl implements IBaseService {
    public Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    public SpringUtils springUtils;


    @Resource
    private CacheManager cacheManager;

    public abstract IBaseJdbcDao getBaseDao();

    @Override
    public SpringUtils getSpringUtils() {
        return springUtils;
    }

    @Override
    public Object getBean(String beanName) throws Exception {
        if (beanName == null)
            return null;
        return getSpringUtils().getBean(beanName);
    }

    @Override
    public Object getBean(Class clazz) throws Exception {
        return getSpringUtils().getBeanByType(clazz);
    }

    @Override
    public Cache getCache(String cacheName) throws Exception {
        if (StringUtils.isBlank(cacheName)) {
            return null;
        }
        return cacheManager.getCache(GlobalStatic.projectKeyPrefix + cacheName);
    }

    @Override
    public <T> T getByCache(String cacheName, String key, Class<T> clazz) throws Exception {
        T t = null;
        try {
            t = getCache(cacheName).get(GlobalStatic.projectKeyPrefix + key, clazz);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //老版本的实体类无法转成新版本,新老版本的属性字段不同
            evictByKey(cacheName, key);
        }

        return t;

    }

    @Override
    public void putByCache(String cacheName, String key, Object value) throws Exception {
        getCache(cacheName).put(GlobalStatic.projectKeyPrefix + key, value);
    }

    @Override
    public void cleanCache(String cacheName) throws Exception {
        getCache(cacheName).clear();
    }

    @Override
    public void evictByKey(String cacheName, String key) throws Exception {
        getCache(cacheName).evict(GlobalStatic.projectKeyPrefix + key);
    }

    @Override
    public <T> T getByCache(String cacheName, String key, Class<T> clazz, Page page) throws Exception {

        if (page == null || page.getPageNo() == null || StringUtils.isBlank(key)) {
            return null;
        }

        String listKey = key + "_" + page.toString();
        T t = getByCache(cacheName, listKey, clazz);

        if (t == null) {
            return t;
        }

        String pageKey = key + GlobalStatic.pageCacheExtKey;
        Integer totalCount = getByCache(cacheName, pageKey, Integer.class);
        if (totalCount != null) {
            page.setTotalCount(totalCount);
        }
        return t;
    }

    @Override
    public void putByCache(String cacheName, String key, Object value, Page page) throws Exception {
        if (page == null || page.getPageNo() == null || StringUtils.isBlank(key)) {
            return;
        }
        String listKey = key + "_" + page.toString();
        putByCache(cacheName, listKey, value);

        if (page != null) {
            putByCache(cacheName, key + GlobalStatic.pageCacheExtKey, page.getTotalCount());
        }
    }

    @Override
    public void evictByKey(String cacheName, String key, Page page) throws Exception {
        evictByKey(cacheName, key);
        evictByKey(cacheName, key + GlobalStatic.pageCacheExtKey);
    }

    @Override
    public <T> List<T> queryForList(Finder finder, Class<T> clazz) throws Exception {
        return getBaseDao().queryForList(finder, clazz);
    }

    @Override
    public List<Map<String, Object>> queryForList(Finder finder) throws Exception {
        return getBaseDao().queryForList(finder);
    }

    @Override
    public Map<String, Object> queryObjectByFunction(Finder finder) throws Exception {
        return getBaseDao().queryObjectByFunction(finder);
    }

    @Override
    public <T> T findById(Object id, Class<T> clazz, String tableExt) throws Exception {
        return getBaseDao().findById(id, clazz, tableExt);
    }

    @Override
    public Map<String, Object> queryObjectByProc(Finder finder) throws Exception {
        return getBaseDao().queryObjectByProc(finder);
    }

    @Override
    public List<Map<String, Object>> queryForListByProc(Finder finder) throws Exception {
        return getBaseDao().queryForListByProc(finder);
    }

    @Override
    public List<Map<String, Object>> queryForListByFunction(Finder finder) throws Exception {
        return getBaseDao().queryForListByFunction(finder);
    }

    @Override
    public <T> T queryForObject(Finder finder, Class<T> clazz) throws Exception {

        return getBaseDao().queryForObject(finder, clazz);
    }

    @Override
    public Map<String, Object> queryForObject(Finder finder) throws Exception {
        return getBaseDao().queryForObject(finder);
    }

    @Override
    public Integer update(Finder finder) throws Exception {

        return getBaseDao().update(finder);
    }

    @Override
    public void deleteById(Object id, Class clazz) throws Exception {
        getBaseDao().deleteById(id, clazz);
    }

    @Override
    public void deleteByIds(List ids, Class clazz) throws Exception {
        getBaseDao().deleteByIds(ids, clazz);
    }

    @Override
    public void deleteByEntity(IBaseEntity entity) throws Exception {
        EntityInfo entityInfoByEntity = ClassUtils.getEntityInfoByEntity(entity);
        String tableName = entityInfoByEntity.getTableName();
        String tableExt = entityInfoByEntity.getTableSuffix();
        if (StringUtils.isNotBlank(tableExt)) {
            tableName = tableName + tableExt;
        }

        Finder finder = new Finder("DELETE FROM ");
        finder.append(tableName).append(" WHERE 1=1 ");
        getFinderWhereByQueryBean(finder, entity);
        getBaseDao().update(finder);
    }

    @Override
    public <T> List<T> queryForList(Finder finder, Class<T> clazz, Page page) throws Exception {
        return getBaseDao().queryForList(finder, clazz, page);
    }

    @Override
    public List<Map<String, Object>> queryForList(Finder finder, Page page) throws Exception {
        return getBaseDao().queryForList(finder, page);
    }

    @Override
    public <T> T queryForObject(T entity) throws Exception {
        return getBaseDao().queryForObject(entity);

    }

    @Override
    public <T> List<T> queryForListByEntity(T entity, Page page) throws Exception {
        return getBaseDao().queryForListByEntity(entity, page);

    }


    @Override
    public Object save(IBaseEntity entity) throws Exception {
        return getBaseDao().save(entity);
    }

    @Override
    public Object saveMapEntity(BaseMapEntity mapEntity) throws Exception {
        return getBaseDao().saveMapEntity(mapEntity);
    }


    @Override
    public List<Integer> save(List list) throws Exception {
        return getBaseDao().save(list);
    }

    @Override
    public List<Integer> saveMapEntity(List<BaseMapEntity> list) throws Exception {
        return getBaseDao().saveMapEntity(list);
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        return getBaseDao().update(entity);
    }

    @Override
    public Integer updateMapEntity(BaseMapEntity mapEntity) throws Exception {
        return getBaseDao().updateMapEntity(mapEntity);
    }


    @Override
    public Integer update(IBaseEntity entity, boolean onlyupdatenotnull) throws Exception {
        return getBaseDao().update(entity, onlyupdatenotnull);
    }

    @Override
    public List<Integer> update(List list) throws Exception {
        return getBaseDao().update(list);
    }

    @Override
    public List<Integer> updateMapEntity(List<BaseMapEntity> list) throws Exception {
        return getBaseDao().updateMapEntity(list);
    }

    @Override
    public List<Integer> update(List list, boolean onlyupdatenotnull) throws Exception {
        return getBaseDao().update(list, onlyupdatenotnull);
    }

    @Override
    public <T> T findById(Object id, Class<T> clazz) throws Exception {
        return getBaseDao().findById(id, clazz);
    }


    @Override
    public <T> T queryForObjectByProc(Finder finder, Class<T> clazz) throws Exception {
        return getBaseDao().queryForObjectByProc(finder, clazz);
    }

    @Override
    public <T> List<T> queryForListByProc(Finder finder, Class<T> clazz) throws Exception {
        return getBaseDao().queryForListByProc(finder, clazz);
    }

    @Override
    public <T> T queryForObjectByByFunction(Finder finder, Class<T> clazz) throws Exception {
        return getBaseDao().queryForObjectByByFunction(finder, clazz);
    }

    @Override
    public <T> List<T> queryForListByFunction(Finder finder, Class<T> clazz) throws Exception {
        return getBaseDao().queryForListByFunction(finder, clazz);
    }

    @Override
    public Finder getFinderWhereByQueryBean(Finder finder, Object o) throws Exception {
        return getBaseDao().getFinderWhereByQueryBean(finder, o);
    }

    @Override
    public Finder getFinderOrderBy(Finder finder, Page page) throws Exception {
        return getBaseDao().getFinderOrderBy(finder, page);
    }

    @Override
    public <T> String saveImportExcelFile(File excelFile, Class<T> clazz,
                                          boolean istest) throws Exception {
        StringBuilder message = new StringBuilder();
        List<Cell[]> excel = ExcelUtils.getExcle(excelFile);
        if (CollectionUtils.isEmpty(excel)) {
            return "Excel文件没有sheet!";
        }
        Map<Integer, String> map = new HashMap<Integer, String>();
        Cell[] title = excel.get(0);
        //title = excel.get(0);
        if (title == null || title.length < 1) {
            return "表头没有数据!";
        }
        List<String> listTitle = new ArrayList<>();
        // 封装字段
        for (int i = 0; i < title.length; i++) {
            map.put(i, title[i].getContents());
            listTitle.add(title[i].getContents());
        }

        for (int j = 2; j < excel.size(); j++) {
            Cell[] cells = excel.get(j);
            T rt = clazz.newInstance();
            IBaseEntity r = (IBaseEntity) rt;
            for (int m = 0; m < cells.length; m++) {
                Object o = r;
                Cell cell = cells[m];
                String name = map.get(m);
                try {
                    if (name.contains(".")) {
                        String[] strs = name.split("\\.");
                        // 获取最后的属性名称
                        name = strs[strs.length - 1];
                        // 循环获取实体对象
                        for (int i = 0; i < strs.length - 1; i++) {
                            o = ClassUtils.getPropertieValue(strs[i], o);
                        }
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    String s = "第" + (m + 1) + "列列名有错误," + name + " 类型错误!";
                    if (istest) {
                        message.append(s).append("</br>");
                    } else {
                        throw new Exception(s);
                    }
                }
                String value = cell.getContents().trim();
                // 处理特殊字符，如果以`开头则去掉
                if (value.startsWith("`")) {
                    value = value.substring(1, value.length());
                }
                Class className = ClassUtils.getReturnType(name, o.getClass());

                if (className == String.class) {
                    try {
                        ClassUtils.setPropertieValue(name, o, value);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        String s = "第" + (j + 1) + "行,第" + (m + 1) + "列:" + name + " 类型错误!";
                        if (istest) {
                            message.append(s).append("</br>");
                        } else {
                            throw new Exception(s);
                        }
                    }
                } else if (className == Date.class) {
                    try {
                        value = value.replace("/", "-");
                        Date d = DateUtils.convertString2Date(value);

                        ClassUtils.setPropertieValue(name, o, d);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        String s = "第" + (j + 1) + "行,第" + (m + 1) + "列:" + name + " 类型错误!";
                        if (istest) {
                            message.append(s).append("</br>");
                        } else {
                            throw new Exception(s);
                        }
                    }

                } else if (className == Double.class) {
                    try {
                        Double db = null;
                        if (StringUtils.isNotBlank(value)) {
                            db = Double.valueOf(value);
                        }
                        ClassUtils.setPropertieValue(name, o, db);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        String s = "第" + (j + 1) + "行,第" + (m + 1) + "列:" + name + " 类型错误!";
                        if (istest) {
                            message.append(s).append("</br>");
                        } else {
                            throw new Exception(s);
                        }
                    }
                } else if (className == Float.class) {
                    try {
                        Float f = null;
                        if (StringUtils.isNotBlank(value)) {
                            f = Float.valueOf(value);
                        }
                        ClassUtils.setPropertieValue(name, o, f);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        String s = "第" + (j + 1) + "行,第" + (m + 1) + "列:" + name + " 类型错误!";
                        if (istest) {
                            message.append(s).append("</br>");
                        } else {
                            throw new Exception(s);
                        }
                    }

                } else if (className == Integer.class) {
                    try {
                        Integer _i = null;

                        if (StringUtils.isNotBlank(value)) {
                            _i = Integer.valueOf(value);
                        }

                        ClassUtils.setPropertieValue(name, o, _i);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        String s = "第" + (j + 1) + "行,第" + (m + 1) + "列:" + name + " 类型错误!";
                        if (istest) {
                            message.append(s).append("</br>");
                        } else {
                            throw new Exception(s);
                        }
                    }
                } else if (className == BigDecimal.class) {
                    try {
                        BigDecimal bd = null;
                        if (StringUtils.isNotBlank(value)) {
                            bd = new BigDecimal(value);
                        }
                        ClassUtils.setPropertieValue(name, o, bd);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        String s = "第" + (j + 1) + "行,第" + (m + 1) + "列:" + name + " 类型错误!";
                        if (istest) {
                            message.append(s).append("</br>");
                        } else {
                            throw new Exception(s);
                        }
                    }
                }

            }
            try {
                String s = saveFromExcel(r, (j + 1), istest, listTitle);
                if (istest && StringUtils.isNotBlank(s)) {
                    message.append(s).append("</br>");
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new Exception("第" + (j + 1) + "行,保存失败");
            }
        }
        if (excelFile.exists() && (istest == false)) {
            //excelFile.delete();
        }
        if (StringUtils.isBlank(message.toString())) {
            return null;
        }
        if (excelFile.exists() && istest && StringUtils.isNotBlank(message.toString())) {
            //excelFile.delete();
        }

        return message.toString();

    }

    @Override
    public <T> String saveImportExcelFile(File excelFile, Class<T> clazz)
            throws Exception {
        String message = saveImportExcelFile(excelFile, clazz, true);
        if (StringUtils.isNotBlank(message)) {
            return message;
        }
        return saveImportExcelFile(excelFile, clazz, false);

    }

    @Override
    public String saveFromExcel(IBaseEntity entity, int index, boolean istest, List<String> listTitle) throws Exception {
        if (istest) {
            return null;
        }
        return save(entity).toString();
    }

    @Override
    public Object executeCallBack(CallableStatementCreator callableStatementCreator, List<SqlParameter> parameter)
            throws Exception {
        return getBaseDao().executeCallBack(callableStatementCreator, parameter);
    }

}
