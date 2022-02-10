package net.shengjian.makerone.api;

import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.property.MessageUtils;
import net.shengjian.makerone.constant.MessageConst;
import net.shengjian.makerone.dto.UserInitDTO;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.exception.NFTException;
import net.shengjian.makerone.service.INftLoginService;
import net.shengjian.makerone.vo.UserInitVO;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.base.BaseController;
import net.shengjian.system.entity.User;
import net.shengjian.system.service.IUserService;
import net.shengjian.system.vo.LoginSuccessVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @descriptions: 登录模块
 * @author: YSK
 * @date: 2021/12/30 16:38
 * @version: 1.0
 */
@Controller
@RequestMapping(value = "/api/nft/login", method = RequestMethod.POST)
public class NftLoginController extends BaseController {

    @Resource
    private INftLoginService nftLoginService;

    @Resource
    private IUserService userService;

    //@Value("${resource.wechatLoginRedirect}")
    //private String wechatLoginRedirect;
    /**
     * 微信登录
     * @param code code
     */
    @GetMapping("/wechat")
    @ResponseBody
    public  ReturnDatas<LoginSuccessVO> wechat(String code) {
        ReturnDatas<LoginSuccessVO> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            //String openId = request.getParameter("openId");
            Map<String, String> wxUserInfo = nftLoginService.getWxUserInfo(code);
            String openId = wxUserInfo.get("openId");
            String unionId = wxUserInfo.get("unionId");
            LoginSuccessVO data = nftLoginService.saveWechat(openId,unionId);
            rd.setResult(data);
            rd.setMessage("登录成功!");
        } catch (RuntimeException nftException) {
            logger.error(nftException.getMessage(), nftException);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(nftException.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(MessageConst.UNKNOWN_ERR);
        }
        //return redirect+wechatLoginRedirect.replace("{jwttoken}",rd.getResult().getJwttoken());
        //return redirect+"http://192.168.0.103:9999/front_nft_mobile/nft_mobile_home?jwttoken="+rd.getResult().getJwttoken();
        return rd;
    }

    /**
     * 查询接口权限
     * @return kv列表
     */
    @ResponseBody
    @PostMapping("/findUserPermissions")
    public ReturnDatas<LoginSuccessVO>  findUserCodeMap() {
        ReturnDatas<LoginSuccessVO> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            User byId = userService.findById(SessionUser.getUserId(), User.class);
            LoginSuccessVO userCodeMap = userService.findUserCodeMap(byId);
            rd.setResult(userCodeMap);
            rd.setMessage(MessageUtils.FIND_SUCCESS);
        } catch (NFTException nftException) {
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
    /**
     * 当前用户是否已完善用户信息
     * @return true|false
     */
    @ResponseBody
    @PostMapping("/haveCurrentUserInfo")
    public ReturnDatas<Boolean> haveCurrentUserInfo(){
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            Boolean flag = nftLoginService.haveCurrentUserInfo(SessionUser.getUserId());
            rd.setResult(flag);
            rd.setMessage(MessageUtils.FIND_SUCCESS);
        } catch (NFTException nftException) {
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

    /**
     * 用户在那些链上创建过账户
     * @return 用户链平台
     */
    @ResponseBody
    @PostMapping("/userChainPlatAccount")
    public ReturnDatas<List<String>> userChainPlatAccount(){
        ReturnDatas<List<String>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<String> list = nftLoginService.userChainPlatAccount(SessionUser.getUserId());
            rd.setResult(list);
            rd.setMessage(MessageUtils.FIND_SUCCESS);
        } catch (NFTException nftException) {
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

    /**
     * 用戶初始化基本信息
     * @return 用户链平台
     */
    @ResponseBody
    @PostMapping("/userInfoInit")
    public ReturnDatas<List<UserInitDTO>> userInfoInit(@RequestBody UserInitVO vo){
        ReturnDatas<List<UserInitDTO>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<UserInitDTO> userInitDTOS = nftLoginService.updateUserInfoInit(vo, EChainPlat.百度超级链);
            rd.setResult(userInitDTOS);
            rd.setMessage(MessageConst.OPERATION_OK);
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

    /**
     * 回显用户绑定的手机号
     * @return 手机号
     */
    @ResponseBody
    @PostMapping("/getUserPhone")
    public ReturnDatas<String> getUserPhone() {
        ReturnDatas<String> returnDatas = ReturnDatas.getSuccessReturnDatas();
        try {
            String data = userService.getUserPhone(SessionUser.getUserId());
            returnDatas.setResult(data);
            returnDatas.setMessage(MessageUtils.FIND_SUCCESS);
        } catch (RuntimeException nftException) {
            logger.error(nftException.getMessage(), nftException);
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage(nftException.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage(MessageConst.UNKNOWN_ERR);
        }
        return returnDatas;
    }
}
