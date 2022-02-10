package net.shengjian.makerone.service.impl;
import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.frame.util.Finder;
import net.shengjian.makerone.constant.CachePrefixConst;
import net.shengjian.makerone.entity.NftChainPlat;
import net.shengjian.makerone.service.INftChainPlatService;
import net.shengjian.system.service.impl.BaseSpringrainServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;


import java.util.List;
/**
 * TODO 在此加入类描述
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:57:22
 */

@Service("nftChainPlatService")
public class NftChainPlatServiceImpl extends BaseSpringrainServiceImpl implements INftChainPlatService {

   
    @Override
	public String  save(IBaseEntity entity ) throws Exception{
	    NftChainPlat nftChainPlat=(NftChainPlat) entity;
        super.cleanCache(CachePrefixConst.CHAIN_PLAT);
	    return super.save(nftChainPlat).toString();
	}


	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
		NftChainPlat nftChainPlat=(NftChainPlat) entity;
		super.cleanCache(CachePrefixConst.CHAIN_PLAT);
		return super.update(nftChainPlat);
    }
	
    @Override
	public NftChainPlat findNftChainPlatById(String id) throws Exception{
		return super.findById(id,NftChainPlat.class);
	}

    @Override
    public NftChainPlat findNftChainPlatByEnglishName(String englishName) throws Exception {
        NftChainPlat nftChainPlat = super.getByCache(CachePrefixConst.CHAIN_PLAT,englishName, NftChainPlat.class);
        if(nftChainPlat!=null){
            return nftChainPlat;
        }

        Finder selectFinder = Finder.getSelectFinder(NftChainPlat.class).append(" WHERE englishName=:englishName ")
                .setParam("englishName",englishName);
        nftChainPlat = super.queryForObject(selectFinder, NftChainPlat.class);

        if(nftChainPlat!=null){
            super.putByCache(CachePrefixConst.CHAIN_PLAT,englishName,nftChainPlat);
        }//else{
            //super.putByCache(CachePrefixConst.CHAIN_PLAT,englishName,new NftChainPlat());
        //}
        return nftChainPlat;
    }

    @Override
    public List<NftChainPlat> findNftChainPlatList() throws Exception {
        List nftChainPlats = super.getByCache(CachePrefixConst.CHAIN_PLAT, "findNftChainPlatList", List.class);
        if(CollectionUtils.isNotEmpty(nftChainPlats)){
            return nftChainPlats;
        }
        Finder selectFinder = Finder.getSelectFinder(NftChainPlat.class);
        nftChainPlats = super.queryForList(selectFinder, NftChainPlat.class);
        if(CollectionUtils.isNotEmpty(nftChainPlats)){
            super.putByCache(CachePrefixConst.CHAIN_PLAT, "findNftChainPlatList", nftChainPlats);
        }
        return nftChainPlats;
    }

}
