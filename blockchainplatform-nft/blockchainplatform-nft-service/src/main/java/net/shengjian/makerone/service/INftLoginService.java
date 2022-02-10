package net.shengjian.makerone.service;

import net.shengjian.makerone.dto.UserInitDTO;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.vo.UserInitVO;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.service.IBaseSpringrainService;
import net.shengjian.system.vo.LoginSuccessVO;

import java.util.List;
import java.util.Map;

/**
 * @descriptions: nft登录模块
 * @author: YSK
 * @date: 2021/12/30 16:51
 * @version: 1.0
 */
@RpcServiceAnnotation
public interface INftLoginService extends IBaseSpringrainService {

    /**
     * 通过微信code获取微信用户信息
     * @param code code
     * @return openId
     * @throws Exception 异常
     */
    Map<String,String> getWxUserInfo(String code) throws Exception;
    /**
     * 微信登录
     * @param openId openId
     * @param unionId unionId
     * @return 登录成功后的信息
     */
    LoginSuccessVO saveWechat(String openId,String unionId) throws Exception;


    /**
     * 当前用户是否有基本信息
     * @return true|false
     * @throws Exception 异常
     */
    Boolean haveCurrentUserInfo(String userId) throws Exception;

    /**
     * 用户在那些链上创建过账户
     * @param userId 用户id
     * @return 链平台列表
     * @throws Exception 异常
     */
    List<String> userChainPlatAccount(String userId) throws Exception;

    /**
     * 用户初始化基本信息
     * @param vo 参数对象
     * @param chainPlat 链平台
     * @return 链信息
     * @throws Exception 异常
     */
    List<UserInitDTO> updateUserInfoInit(UserInitVO vo, EChainPlat chainPlat) throws Exception;
}
