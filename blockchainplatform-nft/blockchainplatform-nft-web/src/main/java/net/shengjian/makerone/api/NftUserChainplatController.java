package net.shengjian.makerone.api;

import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.property.MessageUtils;
import net.shengjian.makerone.constant.MessageConst;
import net.shengjian.makerone.dto.UserChainplatInfoDTO;
import net.shengjian.makerone.entity.NftUserChainplat;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.exception.NFTException;
import net.shengjian.makerone.service.INftLoginService;
import net.shengjian.makerone.service.INftUserChainplatService;
import net.shengjian.makerone.vo.BindOpenNetVO;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.base.BaseController;
import net.shengjian.system.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户的链平台账号模块
 *
 * @author springrain<Auto generate>
 * @version 2021-12-21 17:57:36
 */
@RestController
@RequestMapping(value = "/api/nft/userchainplat", method = RequestMethod.POST)
public class NftUserChainplatController extends BaseController {
    @Resource
    private INftUserChainplatService nftUserChainplatService;
    @Resource
    private INftLoginService nftLoginService;

    @Resource
    private IUserService userService;

    /**
     * 创建链平台账户
     *
     * @param chainPaltCodes 链平台的code
     * @return 返回创建成功后的数据
     * @deprecated 离线创建的账号无法在百度超级链开放网络使用,用户完善个人信息时绑定账号
     */
    @PostMapping("/createChainPlatAccount")
    public ReturnDatas<List<NftUserChainplat>> createChainPlatAccount(@RequestBody List<String> chainPaltCodes) {
        ReturnDatas<List<NftUserChainplat>> returnDatas = ReturnDatas.getSuccessReturnDatas();
        try {
            List<NftUserChainplat> data = new ArrayList<>();
            for (String code : chainPaltCodes) {
                NftUserChainplat userChainplat = nftUserChainplatService.saveCreateChainAccount(EChainPlat.codeOf(code));
                if (userChainplat != null) {
                    data.add(userChainplat);
                }
            }
            returnDatas.setResult(data);
            returnDatas.setMessage(MessageConst.CREATE_OK);
        } catch (NFTException nftException) {
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

    /**
     * 用户下载自己的公钥和私钥文件
     * @param verificationCode 手机验证码
     */
    @PostMapping("/userDownPrivateAndPublic")
    public void userDownPrivateAndPublic(String verificationCode,HttpServletResponse response) {
        try {
            String userPhone = userService.getUserPhone(SessionUser.getUserId());
            userService.verifyPhoneCode(userPhone,verificationCode);

            logger.info("用户[{}],下载了自己的私钥,日期:[{}]",SessionUser.getUserId(),new Date());
            String filePath = nftUserChainplatService.userDownPrivateAndPublic(EChainPlat.百度超级链);
            super.downFile(response,new File(filePath),"keys.zip",true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    /**
     * 用户删除自己的公钥和私钥文件
     * @param verificationCode 手机验证码
     * @return 是否删除成功
     */
    @PostMapping("/userDelPrivateAndPublic")
    public ReturnDatas<Boolean> userDelPrivateAndPublic(String verificationCode) {
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            String userPhone = userService.getUserPhone(SessionUser.getUserId());
            userService.verifyPhoneCode(userPhone,verificationCode);

            Boolean flag = nftUserChainplatService.deleteUserPrivateAndPublic(EChainPlat.百度超级链);
            logger.info("用户[{}],删除了自己的私钥,日期:[{}]",SessionUser.getUserId(),new Date());
            rd.setResult(flag);
            rd.setMessage(MessageUtils.DELETE_SUCCESS);
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
     * 用戶查詢在链平台的详细信息
     */
    @PostMapping("/findUserChainplat")
    public ReturnDatas<List<UserChainplatInfoDTO>> findUserChainplat() {
        ReturnDatas<List<UserChainplatInfoDTO>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<UserChainplatInfoDTO> data = nftUserChainplatService.findUserChainplatByUserId(SessionUser.getUserId());
            rd.setResult(data);
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
     * 綁定百度超级链开放网络address
     * @param vo 参数对象
     * @return true|false
     * @deprecated 用戶初始化信息中的address就是百度超级链开放网络的address,此接口已不需要
     */
    @Deprecated
    @PostMapping("/bindXuperChainOpenNet")
    public ReturnDatas<Boolean> bindXuperChainOpenNet(@RequestBody BindOpenNetVO vo) {
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            Boolean ok = nftUserChainplatService.updateBindXuperChainOpenNet(EChainPlat.百度超级链,vo.getVerificationCode(),vo.getOpenNetAddress());
            rd.setResult(ok);
            rd.setMessage(ok?"绑定成功!":"绑定失败!");
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
     * 检查是否綁定百度超级链开放网络address
     * @return true|false
     */
    @PostMapping("/checkBindChainOpenNet")
    public ReturnDatas<Boolean> checkBindChainOpenNet() {
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            Boolean ok = nftUserChainplatService.checkBindChainOpenNet(SessionUser.getUserId(),EChainPlat.百度超级链);
            rd.setResult(ok);
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
     * 綁定百度超级链开放网络 PrivateAndPasswd
     * @param vo 参数对象
     * @return true|false
     */
    @PostMapping("/bindPrivateAndPasswd")
    public ReturnDatas<Boolean> bindPrivateAndPasswd(@RequestBody BindOpenNetVO vo) {
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            Boolean ok = nftUserChainplatService.updateBindPrivateAndPasswd(EChainPlat.百度超级链,vo.getVerificationCode(),vo.getPrivateKey(),vo.getPasswd());
            rd.setResult(ok);
            rd.setMessage(ok?"绑定成功!":"绑定失败!");
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
     * 检查是否綁定百度超级链开放网络 PrivateAndPasswd
     * @return true|false
     */
    @PostMapping("/checkBindPrivateAndPasswd")
    public ReturnDatas<Boolean> checkBindPrivateAndPasswd() {
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            Boolean ok = nftUserChainplatService.havePrivateKeyAndAddressAndPasswd(EChainPlat.百度超级链,SessionUser.getUserId());
            rd.setResult(ok);
            rd.setMessage(ok?"已完善":"未完善");
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
     * 綁定百度超级链开放网络,私钥和密码(作者初始化信息)
     * @param vo 参数对象
     * @return true|false
     */
    @PostMapping("/bindXuperChainOpenNetPrivate")
    public ReturnDatas<Boolean> bindXuperChainOpenNetPrivate(@RequestBody BindOpenNetVO vo) {
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            Boolean ok = nftUserChainplatService.updateBindXuperChainOpenNetPrivate(EChainPlat.百度超级链,vo.getVerificationCode(),vo.getPrivateKey(),vo.getPasswd(),vo.getOpenNetAddress(),vo.getPhone(),vo.getAuthorName());
            rd.setResult(ok);
            rd.setMessage(ok?"绑定成功!":"绑定失败!");
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
     * 检查用户是否綁定百度超级链开放网络,私钥和密码(作者初始化信息)
     * @return true|false
     */
    @PostMapping("/checkBindXuperChainOpenNetPrivate")
    public ReturnDatas<Boolean> checkBindXuperChainOpenNetPrivate() {
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            //Boolean ok = nftUserChainplatService.havePrivateKeyAndAddressAndPasswd(EChainPlat.百度超级链,SessionUser.getUserId());
            Boolean ok = nftUserChainplatService.haveAddress(SessionUser.getUserId(),EChainPlat.百度超级链);
            rd.setResult(ok);
            rd.setMessage(ok?"已完善":"未完善");
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
     * 回显作者绑定的信息
     * @return data
     */
    @PostMapping("/reviewBind")
    public ReturnDatas<BindOpenNetVO> reviewBind(){
        ReturnDatas<BindOpenNetVO> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            //Boolean ok = nftUserChainplatService.havePrivateKeyAndAddressAndPasswd(EChainPlat.百度超级链,SessionUser.getUserId());
            BindOpenNetVO data = nftUserChainplatService.reviewBind(SessionUser.getUserId(),EChainPlat.百度超级链);
            rd.setResult(data);
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
}
