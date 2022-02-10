package net.shengjian.makerone.api;

import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.property.MessageUtils;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.system.base.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 链平台模块
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:57:22
 */
@RestController
@RequestMapping(value="/api/nft/chainplat", method = RequestMethod.POST)
public class NftChainPlatController  extends BaseController {
    /**
     * 链平台类型列表
     * @return kv列表
     */
    @PostMapping("/chainPlatTypeList")
    public ReturnDatas<Map<String,String>> chainPlatTypeList() {
        ReturnDatas<Map<String,String>> returnDatas = ReturnDatas.getSuccessReturnDatas();
        try {
            returnDatas.setResult(EChainPlat.KVPair.KVPair());
            returnDatas.setMessage(MessageUtils.FIND_SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage(MessageUtils.FIND_ERROR);
        }
        return returnDatas;
    }
}
