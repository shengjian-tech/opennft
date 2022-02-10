package net.shengjian.system.api.miniapp;

import com.google.common.collect.Maps;
import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.api.miniapp.vo.GetPhoneVO;
import net.shengjian.system.api.miniapp.vo.MiniAppVO;
import net.shengjian.system.base.BaseController;
import net.shengjian.system.entity.User;
import net.shengjian.system.service.IUserService;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxCryptUtils;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMiniappConfig;
import net.shengjian.weixin.sdk.miniapp.MiniappAuthApi;
import net.shengjian.weixin.sdk.miniapp.MiniappQrcode;
import net.shengjian.weixin.sdk.miniapp.MiniappQrcodeApi;
import net.shengjian.weixin.service.IWxMiniappConfigService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * 小程序模块
 */
@RestController
@RequestMapping(value = "/api/system/auth", method = RequestMethod.POST)
public class AuthController extends BaseController {

    @Resource
    private IWxMiniappConfigService wxMiniappConfigService;
    @Resource
    private IUserService userService;

    /**
     * 微信小程序登录
     *
     * @param miniAppVO 对象参数
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ReturnDatas<ConcurrentMap<String, Serializable>> login(@RequestBody MiniAppVO miniAppVO) throws Exception {
        ReturnDatas<ConcurrentMap<String, Serializable>> returnObject = ReturnDatas.getSuccessReturnDatas();
        String appId = miniAppVO.getAppId();
        String jsCode = miniAppVO.getJsCode();
        IWxMiniappConfig config = wxMiniappConfigService.findWxMiniappConfigById(appId);

        if (config == null) {
            return ReturnDatas.getErrorReturnDatas("appId不存在");
        }

        ApiResult apiResult = MiniappAuthApi.code2Session(config, jsCode);

        if (apiResult.isSucceed() == false) {
            return ReturnDatas.getErrorReturnDatas("数据错误");
        }

        String openId = apiResult.getOpenId();

        if (StringUtils.isBlank(openId)) {
            return ReturnDatas.getErrorReturnDatas("数据错误");
        }
        String userId = userService.findUserIdByOpenId(openId);
        User user = null;
        if (StringUtils.isNotBlank(userId)) {
            user = userService.findUserById(userId);
        } else {
            user = new User();
            String id = SecUtils.getTimeNO();
            user.setId(id);
            user.setOpenId(openId);
            user.setUnionID(apiResult.getUnionid());
            user.setAccount(id);
            user.setPassword(SecUtils.encoderByMd5With32Bit(id + openId));
            user.setSex(GlobalStatic.sexMap.get(miniAppVO.getGender()));
            // 小程序端 店长注册
            user.setUserType(2);

            user.setActive(1);
            user.setUserName(miniAppVO.getNickName());
            user.setAvatar(miniAppVO.getAvatarUrl());

            user.setNickName(miniAppVO.getNickName());

            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());

            user.setUpdateUserId("0");
            user.setCreateUserId("0");

            user.setBak1(miniAppVO.getCountry() + "," + miniAppVO.getProvince() + "," + miniAppVO.getCity());

            userService.save(user);
        }

        ConcurrentMap<String, Serializable> resutltMap = Maps.newConcurrentMap();

        String jwtToken = userService.wrapJwtTokenByUser(user);
        resutltMap.put(GlobalStatic.jwtTokenKey, jwtToken);

        resutltMap.put(GlobalStatic.USER_SPECICAL_INFO, user);
        returnObject.setResult(resutltMap);

        userService.putByCache(GlobalStatic.wxConfigCacheKey, "sessionKey_" + user.getId(),
                apiResult.getSessionKey());
        return returnObject;
    }

    /**
     * 注册二维码
     *
     * @param mcode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getRegQrcode", method = RequestMethod.POST)
    public ReturnDatas<String> getRegQrcode(@RequestBody String mcode) throws Exception {

        ReturnDatas<String> returnObject = ReturnDatas.getSuccessReturnDatas();

        String appId = "123";
        IWxMiniappConfig config = wxMiniappConfigService.findWxMiniappConfigById(appId);

        MiniappQrcode miniappQrcode = new MiniappQrcode();
        miniappQrcode.setPage("");
        miniappQrcode.setScene(mcode);

        ApiResult apiResult = MiniappQrcodeApi.getUnlimited(config, miniappQrcode);
        if (apiResult != null && apiResult.isSucceed()) {
            File file = apiResult.getFile();
            file.getName();
            returnObject.setResult("/upload/miniappqrcode/" + file.getName());
            return returnObject;
        } else {
            returnObject.setStatus("error");
            returnObject.setMessage("二维码生成失败");
            return returnObject;
        }

    }

    /**
     * 获取手机号
     *
     * @param getPhoneParam 对象参数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getPhone", method = RequestMethod.POST)
    public ReturnDatas<String> getPhone(@RequestBody GetPhoneVO getPhoneParam) throws Exception {
        String encryptedData = getPhoneParam.getEncryptedData();
        String iv = getPhoneParam.getIv();
        String userId = SessionUser.getUserId();
        String sessionKey = userService.getByCache(GlobalStatic.wxConfigCacheKey,
                "sessionKey_" + userId, String.class);
        if (StringUtils.isBlank(sessionKey)) {
            return ReturnDatas.getErrorReturnDatas("数据错误");
        }
        String json = WxCryptUtils.decrypt(sessionKey, encryptedData, iv);
        Map<?, ?> m = JsonUtils.readValue(json, Map.class);
        String phone = MapUtils.getString(m, "phoneNumber");
        if (StringUtils.isBlank(phone)) {
            return ReturnDatas.getErrorReturnDatas("数据错误");
        }
        User user = new User();
        user.setId(userId);
        user.setMobile(phone);
        userService.update(user, true);
        ReturnDatas<String> returnDatas = ReturnDatas.getSuccessReturnDatas();
        returnDatas.setResult(phone);
        return returnDatas;
    }

}