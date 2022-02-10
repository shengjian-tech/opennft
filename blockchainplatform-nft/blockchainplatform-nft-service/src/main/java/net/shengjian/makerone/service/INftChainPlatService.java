package net.shengjian.makerone.service;

import net.shengjian.makerone.entity.NftChainPlat;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.service.IBaseSpringrainService;

import java.util.List;

/**
 * TODO 在此加入类描述
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:57:22
 */
@RpcServiceAnnotation
public interface INftChainPlatService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	NftChainPlat findNftChainPlatById(String id) throws Exception;

    /**
     * 通过英文名称查询
     * @param englishName 英文名称
     * @return 链平台数据
     * @throws Exception
     */
	NftChainPlat findNftChainPlatByEnglishName(String englishName) throws Exception;

    /**
     * 链平台账户列表
     * @return
     * @throws Exception
     */
	List<NftChainPlat> findNftChainPlatList() throws Exception;
	
	
	
}
