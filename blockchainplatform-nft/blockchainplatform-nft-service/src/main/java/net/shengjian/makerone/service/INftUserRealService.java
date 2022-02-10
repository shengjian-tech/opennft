package net.shengjian.makerone.service;

import net.shengjian.makerone.entity.NftUserReal;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.service.IBaseSpringrainService;

/**
 * TODO 在此加入类描述
 * @author springrain<Auto generate>
 * @version  2021-12-22 09:18:06
 */
@RpcServiceAnnotation
public interface INftUserRealService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	NftUserReal findNftUserRealById(String id) throws Exception;
	
	
	
}
