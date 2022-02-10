package net.shengjian.system.api.mp;


import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.system.base.BaseController;
import net.shengjian.system.service.IUserService;
import net.shengjian.weixin.service.IWxMpConfigService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 微信模块
 */
@RestController
@RequestMapping(value = "/api/mp/auth", method = RequestMethod.POST)
public class AuthController extends BaseController {

    @Resource
    private IWxMpConfigService wxMpConfigService;

    @Resource
    private IUserService userService;


    /**
     * 微信登录接口
     *
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ReturnDatas<?> login(@RequestBody Map<?, ?> map) throws Exception {
        ReturnDatas<?> returnObject = ReturnDatas.getSuccessReturnDatas();

        return returnObject;
    }
}