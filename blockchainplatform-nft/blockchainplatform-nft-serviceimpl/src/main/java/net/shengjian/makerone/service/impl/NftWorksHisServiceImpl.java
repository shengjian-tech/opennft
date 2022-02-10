package net.shengjian.makerone.service.impl;

import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.makerone.constant.NFTExceptionConst;
import net.shengjian.makerone.entity.NftWorksHis;
import net.shengjian.makerone.service.INftWorksHisService;
import net.shengjian.system.service.impl.BaseSpringrainServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * TODO 在此加入类描述
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:22
 */

@Service("nftWorksHisService")
public class NftWorksHisServiceImpl extends BaseSpringrainServiceImpl implements INftWorksHisService {

   
    @Override
	public String  save(IBaseEntity entity ) throws Exception{
	    NftWorksHis nftWorksHis=(NftWorksHis) entity;
	    return super.save(nftWorksHis).toString();
	}


	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
		NftWorksHis nftWorksHis=(NftWorksHis) entity;
		return super.update(nftWorksHis);
    }
	
    @Override
	public NftWorksHis findNftWorksHisById(String id) throws Exception{
		return super.findById(id,NftWorksHis.class);
	}

    @Override
    public Boolean saveDefaultNftWorkHis(NftWorksHis nftWorksHis) throws Exception {
        if (nftWorksHis==null){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        nftWorksHis.setId(SecUtils.getTimeNO());
        nftWorksHis.setCreateTime(new Date());
        if(StringUtils.isBlank(nftWorksHis.getChainPlatCert())){
            nftWorksHis.setChainPlatCert("{}");
        }
        super.save(nftWorksHis);
        return true;
    }

}
