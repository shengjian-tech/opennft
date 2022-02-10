package net.shengjian.makerone.api;
import net.shengjian.makerone.service.INftUserRealService;
import net.shengjian.system.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 实名认证模块
 * @author springrain<Auto generate>
 * @version  2021-12-22 09:18:06
 */
@RestController
@RequestMapping(value="/api/government/nftuserreal", method = RequestMethod.POST)
public class NftUserRealController  extends BaseController {
	@Resource
	private INftUserRealService nftUserRealService;
}
