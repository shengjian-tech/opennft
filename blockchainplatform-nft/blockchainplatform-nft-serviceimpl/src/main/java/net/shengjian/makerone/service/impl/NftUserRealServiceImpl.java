package net.shengjian.makerone.service.impl;
import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.makerone.entity.NftUserReal;
import net.shengjian.makerone.service.INftUserRealService;
import net.shengjian.system.service.impl.BaseSpringrainServiceImpl;
import org.springframework.stereotype.Service;


/**
 * TODO 在此加入类描述
 * @author springrain<Auto generate>
 * @version  2021-12-22 09:18:06
 */

@Service("nftUserRealService")
public class NftUserRealServiceImpl extends BaseSpringrainServiceImpl implements INftUserRealService {

   
    @Override
	public String  save(IBaseEntity entity ) throws Exception{
	    NftUserReal nftUserReal=(NftUserReal) entity;
	    return super.save(nftUserReal).toString();
	}


	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
		NftUserReal nftUserReal=(NftUserReal) entity;
		return super.update(nftUserReal);
    }
	
    @Override
	public NftUserReal findNftUserRealById(String id) throws Exception{
		return super.findById(id,NftUserReal.class);
	}

}
