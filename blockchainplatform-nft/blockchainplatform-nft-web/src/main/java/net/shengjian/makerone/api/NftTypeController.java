package net.shengjian.makerone.api;

import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.property.MessageUtils;
import net.shengjian.makerone.constant.MessageConst;
import net.shengjian.makerone.service.INftTypeService;
import net.shengjian.makerone.vo.LabelValuePairVO2;
import net.shengjian.system.base.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 类型模块,合集-作品
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:16
 */
@RestController
@RequestMapping(value="/api/nft/type", method = RequestMethod.POST)
public class NftTypeController  extends BaseController {
	@Resource
	private INftTypeService nftTypeService;

    /**
     * 类型列表
     * @return kv列表
     */
	@PostMapping("/list")
	public ReturnDatas<List<LabelValuePairVO2>> typeList(){
        ReturnDatas<List<LabelValuePairVO2>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<LabelValuePairVO2> data = nftTypeService.getLabelValuePair();
            rd.setResult(data);
            rd.setMessage(MessageUtils.FIND_SUCCESS);
        } catch (RuntimeException nftException) {
            logger.error(nftException.getMessage(), nftException);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(nftException.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(MessageConst.UNKNOWN_ERR);
        }
        return rd;
    }
}
