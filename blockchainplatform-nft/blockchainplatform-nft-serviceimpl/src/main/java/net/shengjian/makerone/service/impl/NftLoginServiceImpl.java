package net.shengjian.makerone.service.impl;

import com.baidu.xuper.api.Account;
import net.shengjian.frame.util.Finder;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.makerone.constant.CachePrefixConst;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.constant.NFTExceptionConst;
import net.shengjian.makerone.dto.UserInitDTO;
import net.shengjian.makerone.entity.NftUserChainplat;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.exception.NFTException;
import net.shengjian.makerone.service.INftLoginService;
import net.shengjian.makerone.service.INftUserChainplatService;
import net.shengjian.makerone.utils.PathUtil;
import net.shengjian.makerone.vo.UserInitVO;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.entity.User;
import net.shengjian.system.service.IUserRoleMenuService;
import net.shengjian.system.service.IUserService;
import net.shengjian.system.service.impl.BaseSpringrainServiceImpl;
import net.shengjian.system.vo.LoginSuccessVO;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;
import net.shengjian.weixin.sdk.open.SnsApi;
import net.shengjian.weixin.sdk.open.WxUserInfo;
import net.shengjian.weixin.service.IWxMpConfigService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * @descriptions:
 * @author: YSK
 * @date: 2021/12/30 16:53
 * @version: 1.0
 */
@Service("nftLoginService")
public class NftLoginServiceImpl extends BaseSpringrainServiceImpl implements INftLoginService  {

    @Resource
    private IUserService userService;

    @Resource
    private IUserRoleMenuService userRoleMenuService;

    @Resource
    private INftUserChainplatService nftUserChainplatService;

    @Resource
    private IWxMpConfigService wxMpConfigService;

    @Value("${staticdir}")
    private String staticdir;

    @Override
    public Map<String,String> getWxUserInfo(String code) throws Exception {
        if(StringUtils.isBlank(code)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Map<String,String> map = new HashMap<>();
        IWxMpConfig wxmpconfig = wxMpConfigService.findWxMpConfigById(CommonConst.SITE);
        WxUserInfo wu = SnsApi.getWxUserInfo(wxmpconfig, code);
        if(wu==null){
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        map.put("openId",wu.getOpenid());
        map.put("unionId",wu.getUnionid());
        return map;
    }

    @Override
    public LoginSuccessVO saveWechat(String openId,String unionId) throws Exception {
        if(StringUtils.isBlank(openId)){
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        String userIdByOpenId = userService.findUserIdByOpenId(openId);
        User user;
        //用户首次登录
        if(StringUtils.isBlank(userIdByOpenId)){
            user = new User();
            user.setId(SecUtils.getTimeNO());
            user.setUpdateUserId(CommonConst.ROOT_ID);
            user.setCreateUserId(CommonConst.ROOT_ID);
            user.setUserName(CommonConst.WECHAT_NAME);
            user.setAccount(CommonConst.UNKNOWN);
            user.setPassword(CommonConst.UNKNOWN);
            user.setSex(CommonConst.UNKNOWN);
            user.setMobile(CommonConst.UNKNOWN);
            user.setEmail(CommonConst.UNKNOWN);
            user.setOpenId(openId);
            user.setUnionID(unionId);
            //user.setAvatar(CommonConst.UNKNOWN);
            user.setUserType(1);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setActive(CommonConst.TRUE);
            super.save(user);
            //新增用户角色
            userRoleMenuService.updateUserRoles(user.getId(), Collections.singletonList(CommonConst.NFT_ROLE_ID));
        }else{
            user = userService.findById(userIdByOpenId, User.class);
        }
        if(user==null){
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        return userService.findUserCodeMap(user);
    }

    @Override
    public Boolean haveCurrentUserInfo(String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(User.class, " count(1) ")
                .append(" WHERE id=:userId AND mobile!=:unknown ")
                .setParam("userId", userId)
                .setParam("unknown", CommonConst.UNKNOWN);
        Integer count = super.queryForObject(selectFinder, Integer.class);
        if (count > 0) {
            return nftUserChainplatService.haveAddress(userId, EChainPlat.百度超级链);
        }
        return false;
    }

    @Override
    public List<String> userChainPlatAccount(String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftUserChainplat.class, " chainPlatName ")
                .append(" WHERE userId=:userId ")
                .setParam("userId",userId);
        List<String> list = super.queryForList(selectFinder, String.class);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<UserInitDTO> updateUserInfoInit(UserInitVO vo,EChainPlat chainPlat) throws Exception {
        if(vo==null){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        final String bindAddress = vo.getBindAddress();
        if(StringUtils.isBlank(bindAddress)|| bindAddress.length()!=33){
            throw new NFTException("绑定的address不正确!");
        }
        String userIdByAddress = nftUserChainplatService.findUserIdByAddress(chainPlat, bindAddress);
        if(StringUtils.isNotBlank(userIdByAddress)){
            throw new NFTException("该地址已在本平台绑定过!");
        }
        final String userId = SessionUser.getUserId();
        Boolean init = this.haveCurrentUserInfo(userId);
        if(init!=null && init){
            throw new NFTException("用户信息已经初始化过了!,请勿重复操作!");
        }

        vo.setId(userId);
        String mobile = vo.getMobile();
        String verificationCode = vo.getVerificationCode();
        //验证手机验证码
        userService.verifyPhoneCode(mobile,verificationCode);

        User db = new User();
        BeanUtils.copyProperties(vo,db);
        db.setUpdateUserId(userId);
        db.setUpdateTime(new Date());

        db.setPassword(SecUtils.encoderByMd5With32Bit(vo.getPassword()));
        userService.update(db,true);

        //创建链平台账户
        //NftUserChainplat nftUserChainplat = nftUserChainplatService.saveCreateChainAccount(EChainPlat.百度超级链);
        //文件路径
        String folder = staticdir + File.separator + "userkeys" + File.separator + SessionUser.getUserId() + File.separator + "xuperchain" + File.separator;
        new File(folder).mkdirs();
        String addressPath = folder + "address";
        try(FileOutputStream os = new FileOutputStream(addressPath)) {
            os.write(bindAddress.getBytes());
        }
        NftUserChainplat dbUserChain = new NftUserChainplat();
        dbUserChain.setChainPlatName(chainPlat.getCode());
        dbUserChain.setEVMAddress(Account.xchainAKToEVMAddress(bindAddress));
        dbUserChain.setAddress(bindAddress);
        dbUserChain.setAddressPath(PathUtil.pathRightTOLeft(addressPath.replace(staticdir,"")));
        Boolean ok = nftUserChainplatService.saveDefaultValueNftUserChainPlat(dbUserChain);
        if(!ok){
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        List<UserInitDTO> data = new ArrayList<>();
        UserInitDTO dto = new UserInitDTO();
        BeanUtils.copyProperties(dbUserChain,dto);
        data.add(dto);

        String key = userId+"_"+chainPlat.getCode();
        super.evictByKey(CachePrefixConst.USER_CHAIN_INFO,key);
        return data;
    }
}
