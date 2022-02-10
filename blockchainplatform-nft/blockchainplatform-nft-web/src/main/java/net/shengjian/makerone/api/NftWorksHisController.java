package net.shengjian.makerone.api;

import net.shengjian.makerone.service.INftWorksHisService;
import net.shengjian.system.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 作品交易历史模块
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:22
 */
@RestController
@RequestMapping(value="/api/nft/workshis", method = RequestMethod.POST)
public class NftWorksHisController  extends BaseController {
	@Resource
	private INftWorksHisService nftWorksHisService;
}
