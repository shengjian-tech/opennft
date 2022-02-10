package net.shengjian.makerone.api;

import net.shengjian.makerone.service.INftWorksPriceService;
import net.shengjian.system.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 作品价格历史模块
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:29
 */
@RestController
@RequestMapping(value="/api/nft/worksprice", method = RequestMethod.POST)
public class NftWorksPriceController  extends BaseController {
	@Resource
	private INftWorksPriceService nftWorksPriceService;

}
