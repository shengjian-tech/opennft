package net.shengjian.system.api;

import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.property.MessageUtils;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.base.BaseController;
import net.shengjian.system.entity.Notify;
import net.shengjian.system.service.INotifyService;
import net.shengjian.system.vo.NotifyVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 消息通知
 * @author springrain<Auto generate>
 * @version  2021-09-14 13:36:14
 */
@RestController
@RequestMapping(value="/api/system/notify", method = RequestMethod.POST)
public class NotifyController  extends BaseController {
	@Resource
	private INotifyService notifyService;

	/**
	 * 通知消息列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public ReturnDatas<List<NotifyVO>> message() {
		ReturnDatas<List<NotifyVO>> returnObject = ReturnDatas.getSuccessReturnDatas();
        try {
            List<NotifyVO> datas = notifyService.message();
            returnObject.setResult(datas);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return returnObject;
	}

	/**
	 * 消息状态更新
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatemassagestatus", method = RequestMethod.GET)
	public ReturnDatas<NotifyVO> updatemassagestatus(String id) {
		ReturnDatas<NotifyVO> returnObject = ReturnDatas.getSuccessReturnDatas();
		try {
			NotifyVO datas = notifyService.updatemessagestatus(id);
			returnObject.setResult(datas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnObject;
	}
}
