package net.shengjian.system.api;

import net.shengjian.frame.util.CaptchaUtils;
import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.rpc.sessionuser.UserVO;
import net.shengjian.system.api.vo.CaptchaVO;
import net.shengjian.system.base.BaseController;
import net.shengjian.system.dto.PhoneLoginDTO;
import net.shengjian.system.entity.Menu;
import net.shengjian.system.entity.Role;
import net.shengjian.system.entity.User;
import net.shengjian.system.service.IUserRoleMenuService;
import net.shengjian.system.service.IUserService;
import net.shengjian.system.vo.LoginSuccessVO;
import net.shengjian.system.vo.LoginUserVO;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;
import net.shengjian.weixin.sdk.open.SnsApi;
import net.shengjian.weixin.service.IWxMpConfigService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 登录模块
 */
@RestController
@RequestMapping(value = "/api", method = RequestMethod.POST)
public class LoginController extends BaseController {

    private static final String redirect_uri = "";
    @Resource
    private IUserService userService;
    @Resource
    private IWxMpConfigService wxMpConfigService;
    @Resource
    private IUserRoleMenuService userRoleMenuService;
    /**
     * 健康检查
     *
     * @return 数据
     */
    @RequestMapping(value = "/checkHealth", method = RequestMethod.GET)
    public ReturnDatas<String> checkHealth() {
        return ReturnDatas.getSuccessReturnDatas();
    }

    /**
     * 生成验证码
     *
     * @param captchaKey 生成的验证码对应的key,不传会随机生成
     * @return 返回base64验证码图片，和对应的验证码key
     * @throws Exception 缓存异常
     */
    @RequestMapping(value = "/getCaptcha", method = RequestMethod.POST)
    public ReturnDatas<CaptchaVO> getCaptcha(String captchaKey)
            throws Exception {
        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.IMAGE_JPEG);

        ReturnDatas<CaptchaVO> returnDatas = ReturnDatas
                .getSuccessReturnDatas();

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            StringBuilder code = new StringBuilder();
            BufferedImage image = CaptchaUtils.genRandomCodeImage(code);

            if (StringUtils.isNotBlank(captchaKey)) {
                userService.evictByKey(GlobalStatic.springranloginCaptchaKey, captchaKey);

            } else {
                captchaKey = SecUtils.getUUID();
            }
            userService.putByCache(GlobalStatic.springranloginCaptchaKey, captchaKey,
                    code.toString());
            ImageIO.write(image, "JPEG", os);
            String imageBase64 = String.format("data:image/jpeg;base64,%s",
                    SecUtils.encoderByBase64(os.toByteArray()));

            returnDatas.setResult(new CaptchaVO(captchaKey, imageBase64));

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }

        return returnDatas;
    }
    /**
     * 退出后台登录用户
     *
     * @return 退出成功
     */
    @RequestMapping(value = "/system/logout", method = RequestMethod.POST)
    public ReturnDatas<String> systemLogout() {

        // 获取当前登录人
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }
        try {
            userService.logout(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnDatas<>(ReturnDatas.ERROR, e.getMessage());
        }
        return ReturnDatas.getSuccessReturnDatas();
    }

    /**
     * 获取登陆二维码
     *
     * @param appId 公众号id
     * @return 数据
     */
    @RequestMapping(value = "/system/qrcode", method = RequestMethod.POST)
    public ReturnDatas<String> qrcode(@RequestBody String appId) {
        ReturnDatas<String> returnObject = ReturnDatas.getSuccessReturnDatas();
        final IWxMpConfig config = wxMpConfigService.findWxMpConfigById(appId);
        String url = SnsApi.getQrConnectURL(config, redirect_uri);
        returnObject.setResult(url);
        return returnObject;
    }

    /**
     * QR码
     *
     * @param map 请求实例
     * @return 。
     */
    @RequestMapping(value = "/system/qrcodeback", method = RequestMethod.POST)
    public ReturnDatas<?> qrcodeback(@RequestBody Map<String, Object> map) {
        ReturnDatas<?> returnObject = ReturnDatas.getSuccessReturnDatas();

        return returnObject;
    }

    /**
     * 处理后台用户登录
     *
     * @param userVO 请求的参数
     * @return 登陆成功后生成的全局参数中的token
     * @throws Exception 缓存异常
     */
    @RequestMapping(value = "/system/login", method = RequestMethod.POST)
    public ReturnDatas<LoginSuccessVO> systemLoginPost(@RequestBody UserVO userVO) throws Exception {

        if (StringUtils.isBlank(userVO.getAccount())) {
            return ReturnDatas.getErrorReturnDatas("用户不能为空");
        }

        if (StringUtils.isBlank(userVO.getPassword())) {
            return ReturnDatas.getErrorReturnDatas("密码不能为空");
        }

        // 处理密码错误缓存
        String errorLogincountKey = userVO.getAccount() + "_errorlogincount";
        Integer errorLogincount = userService.getByCache(GlobalStatic.springrainloginCacheKey,
                errorLogincountKey, Integer.class);

        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        if (errorLogincount != null && errorLogincount > 2) {
            ReturnDatas<LoginSuccessVO> returnDatas = ReturnDatas.getErrorReturnDatas();
            if (StringUtils.isBlank(userVO.getCaptchaKey())) {
                loginSuccessVO.setShowCaptcha(true);
                returnDatas.setResult(loginSuccessVO);
                returnDatas.setMessage("验证码不能为空");
                return returnDatas;
            }

            String userCaptcha = userService.getByCache(GlobalStatic.springranloginCaptchaKey, userVO.getCaptchaKey(), String.class);
            userService.evictByKey(GlobalStatic.springranloginCaptchaKey, userVO.getCaptchaKey());
            // Cache captchaCache = userService.getCache(GlobalStatic.springranloginCaptchaKey);
            // ValueWrapper captchaKey = captchaCache.get(GlobalStatic.projectKeyPrefix + userVO.getCaptchaKey());
            // assert captchaKey != null;
            if (!StringUtils.equalsIgnoreCase(userCaptcha, userVO.getCaptcha())) {
                loginSuccessVO.setShowCaptcha(true);
                returnDatas.setResult(loginSuccessVO);
                returnDatas.setMessage("验证码错误");
                return returnDatas;
            }
        }

        if (errorLogincount != null && errorLogincount >= GlobalStatic.ERROR_LOGIN_COUNT) {// 密码连续错误10次以上

            String errorMessage = "密码连续错误超过" + GlobalStatic.ERROR_LOGIN_COUNT + "次,账号被锁定,请"
                    + GlobalStatic.ERROR_LOGIN_LOCK_MINUTE + "分钟之后再尝试登录!";
            Long endDateLong = userService.getByCache(GlobalStatic.springrainloginCacheKey,
                    userVO.getAccount() + "_endDateLong", Long.class);
            long now = System.currentTimeMillis() / 1000;// 秒

            if (endDateLong == null) {
                endDateLong = now + GlobalStatic.ERROR_LOGIN_LOCK_MINUTE * 60;// 秒
                userService.putByCache(GlobalStatic.springrainloginCacheKey,
                        userVO.getAccount() + "_endDateLong", endDateLong);
                return ReturnDatas.getErrorReturnDatas(errorMessage);
            } else if (now > endDateLong) {// 过了失效时间
                userService.evictByKey(GlobalStatic.springrainloginCacheKey, errorLogincountKey);
                userService.evictByKey(GlobalStatic.springrainloginCacheKey,
                        userVO.getAccount() + "_endDateLong");

            } else {
                return ReturnDatas.getErrorReturnDatas(errorMessage);
            }
        }

        User user = userService.findLoginUser(userVO.getAccount(),
                SecUtils.encoderByMd5With32Bit(userVO.getPassword()), userVO.getUserType());

        if (user == null) {// 登录失败
            if (errorLogincount == null) {
                errorLogincount = 0;
            }
            errorLogincount = errorLogincount + 1;
            userService.putByCache(GlobalStatic.springrainloginCacheKey, errorLogincountKey,
                    errorLogincount);
            ReturnDatas<LoginSuccessVO> returnDatas = ReturnDatas.getErrorReturnDatas();
            if (errorLogincount > 2) {
                loginSuccessVO.setShowCaptcha(true);
            } else {
                loginSuccessVO.setShowCaptcha(false);
            }
            returnDatas.setResult(loginSuccessVO);
            returnDatas.setMessage("账号或密码错误");
            return returnDatas;
        }
        try {
            loginSuccessVO = userService.findUserCodeMap(user);
        }catch (Exception e){
            e.printStackTrace();
        }

        //resutltMap.put("menus",menuVOList);

        ReturnDatas<LoginSuccessVO> returnDatas = ReturnDatas.getSuccessReturnDatas();
        returnDatas.setResult(loginSuccessVO);

        // 登录成功,清空错误次数
        userService.evictByKey(GlobalStatic.springrainloginCacheKey, errorLogincountKey);
        userService.evictByKey(GlobalStatic.springrainloginCacheKey,
                userVO.getAccount() + "_endDateLong");

        return returnDatas;
    }

    /**
     * 处理前台用户登录
     *
     * @param userVO 请求的参数
     * @return 登陆成功后生成的全局参数中的token
     * @throws Exception 缓存异常
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ReturnDatas<?> userLoginPost(@RequestBody UserVO userVO) throws Exception {
        return systemLoginPost(userVO);
    }

    /**
     * 员工登录地址
     *
     * @param userVO 请求的参数
     * @return 登陆成功后生成的全局参数中的token
     * @throws Exception 缓存异常
     */
    @RequestMapping(value = "/work/login", method = RequestMethod.POST)
    public ReturnDatas<?> workLoginPost(@RequestBody UserVO userVO) throws Exception {
        return systemLoginPost(userVO);
    }

    /**
     * 发送短信验证码
     *
     * @param dto
     * @return
     */
    @PostMapping("/sendCode")
    public ReturnDatas<String> sendCode(@RequestBody PhoneLoginDTO dto) {
        ReturnDatas<String> returnDatas = ReturnDatas.getSuccessReturnDatas();
        try {
            userService.sendCode(dto);
            returnDatas.setMessage("发送成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage(e.getMessage());
        }
        return returnDatas;
    }

    /**
     * 手机号登录,返回jwttoken
     *
     * @return
     */
    @PostMapping("/phoneLogin")
    public ReturnDatas<LoginSuccessVO> phoneLogin(@RequestBody PhoneLoginDTO phoneLoginDTO) {
        ReturnDatas<LoginSuccessVO> returnDatas = ReturnDatas.getSuccessReturnDatas();
        try {
            User user = userService.savePhoneLogin(phoneLoginDTO);
            LoginSuccessVO loginSuccessVO = userService.findUserCodeMap(user);
            returnDatas.setResult(loginSuccessVO);
            returnDatas.setMessage("登录成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage(e.getMessage());
        }
        return returnDatas;
    }


    /**
     * 查看所有缓存key的值
     * @return
     */
    /*
    @PostMapping("/cacheValue")
    public ReturnDatas<Cache> cacheValue(String keyName) throws Exception {
        if(StringUtils.isBlank(keyName)){
            throw new RuntimeException("key的值为空!");
        }
        Cache value = userService.getCache(keyName);
        return new ReturnDatas(ReturnDatas.SUCCESS,"",value);
    }
     */

    /**
     * 清除所有缓存
     *
     * @return
     */
    @PostMapping("/clearCache")
    public ReturnDatas<String> clearCache(String keyName) throws Exception {
        userService.getCache(keyName).clear();
        return ReturnDatas.getSuccessReturnDatas();
    }
}
