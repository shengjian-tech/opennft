package net.shengjian.system.service;

import net.shengjian.frame.util.Page;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.entity.DicData;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author jiagou.com<Auto generate>
 * @version 2013-07-31 15:56:45
 * @copyright {@link springrain}
 * @see org.springrain.springrain.service.DicData
 */
@RpcServiceAnnotation
public interface IDicDataService extends IBaseSpringrainService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    DicData findDicDataById(Object id) throws Exception;

    /**
     * 根据pathKey和Id查找对象
     *
     * @param id
     * @param pathtypekey
     * @return
     * @throws Exception
     */
    DicData findDicDataById(String id, String pathtypekey) throws Exception;

    /**
     * 根据pathKey查询所有字典
     *
     * @param pathtypekey
     * @return
     * @throws Exception
     */
    List<DicData> findListDicData(String pathtypekey, Page page, DicData dicData) throws Exception;

    /**
     * 根据Id和pathKey删除字典
     *
     * @param id
     * @param pathtypekey
     * @throws Exception
     */
    void deleteDicDataById(String id, String pathtypekey) throws Exception;

    /**
     * 根据Ids和pathKey删除字典
     *
     * @param ids
     * @param pathtypekey
     * @throws Exception
     */
    void deleteDicDataByIds(List<String> ids, String pathtypekey) throws Exception;


    /**
     * 从缓存中查询name
     *
     * @param id
     * @param pathtypekey
     * @return
     * @throws Exception
     */
    String findCacheNameById(String id, String pathtypekey) throws Exception;

    /**
     * 根据pathtypekey查询字典列表
     *
     * @param pathtypekey
     * @param sort        排序
     * @return
     * @throws Exception
     */
    List<DicData> findDisDataByPathtypeKey(String pathtypekey, String sort, Integer active) throws Exception;

    /**
     * 根据Code,typeKey获取字典
     *
     * @param code
     * @param typeKey
     * @return
     * @throws Exception
     */
    DicData findByCodeAndTypeKey(String code, String typeKey) throws Exception;

    /**
     * 根据typeName查询字典子类
     *
     * @param page
     * @param typeName
     * @return
     * @throws Exception
     */
    List<DicData> findTypeListByTypeName(Page<DicData> page, String typeName) throws Exception;

    /**
     * 分页查询所有字典类型
     *
     * @param page
     * @return
     * @throws Exception
     */
    List<DicData> findAllRootList(Page<DicData> page) throws Exception;

    /**
     * 保存新的字典类型
     *
     * @param dicData
     * @return
     * @throws Exception
     */
    String saveDicDataType(DicData dicData) throws Exception;

    /**
     * 根据pid分页查询字典信息
     *
     * @param pid
     * @param page
     * @return
     * @throws Exception
     */
    List<DicData> findListByPid(String pid, Page<DicData> page) throws Exception;

    /**
     * 根据pid查询所有子类
     *
     * @param typekey
     * @return
     * @throws Exception
     */
    List<DicData> findDicDataListByPid(String typekey) throws Exception;

    /**
     * 根据id删除父级字典
     *
     * @param idList
     * @throws Exception
     */
    void deleteParentDicDataById(List<String> idList) throws Exception;


    void saveDic(DicData dicData) throws Exception;

    void updateDic(DicData dicData) throws Exception;

    /**
     * 查询字典列表所有数据
     *
     * @param page
     * @return
     * @throws Exception
     */
    List<DicData> findTree(Page<DicData> page) throws Exception;

    /**
     * list装tree
     *
     * @param list
     * @return
     * @throws Exception
     */
    List<DicData> listConvertTree(List<DicData> list) throws Exception;
}
