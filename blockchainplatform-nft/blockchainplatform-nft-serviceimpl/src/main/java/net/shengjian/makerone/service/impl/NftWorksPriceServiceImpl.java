package net.shengjian.makerone.service.impl;

import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.makerone.entity.NftWorksPrice;
import net.shengjian.makerone.service.INftWorksPriceService;
import net.shengjian.system.service.impl.BaseSpringrainServiceImpl;
import org.springframework.stereotype.Service;

/**
 * TODO 在此加入类描述
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:29
 */

@Service("nftWorksPriceService")
public class NftWorksPriceServiceImpl extends BaseSpringrainServiceImpl implements INftWorksPriceService {

   
    @Override
	public String  save(IBaseEntity entity ) throws Exception{
	    NftWorksPrice nftWorksPrice=(NftWorksPrice) entity;
	    return super.save(nftWorksPrice).toString();
	}


	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
		NftWorksPrice nftWorksPrice=(NftWorksPrice) entity;
		return super.update(nftWorksPrice);
    }
	
    @Override
	public NftWorksPrice findNftWorksPriceById(String id) throws Exception{
		return super.findById(id,NftWorksPrice.class);
	}

}
