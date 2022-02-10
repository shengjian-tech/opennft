package net.shengjian.makerone.service.impl;

import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.frame.util.Finder;
import net.shengjian.makerone.entity.NftType;
import net.shengjian.makerone.service.INftTypeService;
import net.shengjian.makerone.vo.LabelValuePairVO2;
import net.shengjian.system.service.impl.BaseSpringrainServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2021-12-21 17:58:16
 */

@Service("nftTypeService")
public class NftTypeServiceImpl extends BaseSpringrainServiceImpl implements INftTypeService {


    @Override
    public String save(IBaseEntity entity) throws Exception {
        NftType nftType = (NftType) entity;
        return super.save(nftType).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        NftType nftType = (NftType) entity;
        return super.update(nftType);
    }

    @Override
    public NftType findNftTypeById(String id) throws Exception {
        return super.findById(id, NftType.class);
    }

    @Override
    public List<LabelValuePairVO2> getLabelValuePair() throws Exception {
        Finder selectFinder = Finder.getSelectFinder(NftType.class," name as label,value ");
        return super.queryForList(selectFinder, LabelValuePairVO2.class);
    }

}
