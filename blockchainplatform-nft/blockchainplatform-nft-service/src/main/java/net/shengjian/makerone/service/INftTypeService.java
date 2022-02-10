package net.shengjian.makerone.service;

import net.shengjian.makerone.entity.NftType;
import net.shengjian.makerone.vo.LabelValuePairVO2;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.service.IBaseSpringrainService;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2021-12-21 17:58:16
 */
@RpcServiceAnnotation
public interface INftTypeService extends IBaseSpringrainService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    NftType findNftTypeById(String id) throws Exception;

    /**
     * 获取类型 l-v
     *
     * @return
     * @throws Exception
     */
    List<LabelValuePairVO2> getLabelValuePair() throws Exception;

}
