package net.shengjian.makerone.service;

import net.shengjian.makerone.entity.NftWorksHis;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.service.IBaseSpringrainService;

/**
 * TODO 在此加入类描述
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:22
 */
@RpcServiceAnnotation
public interface INftWorksHisService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	NftWorksHis findNftWorksHisById(String id) throws Exception;

    /**
     * 填充默认值并保存数据
     * @param nftWorksHis 保存的数据
     * @return 是否保存成功
     * @throws Exception 异常
     */
	Boolean saveDefaultNftWorkHis(NftWorksHis nftWorksHis) throws Exception;
	
	
	
}
