package net.shengjian.makerone.service;

import net.shengjian.makerone.entity.NftWorksPrice;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.service.IBaseSpringrainService;

/**
 * TODO 在此加入类描述
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:29
 */
@RpcServiceAnnotation
public interface INftWorksPriceService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	NftWorksPrice findNftWorksPriceById(String id) throws Exception;
	
	
	
}
