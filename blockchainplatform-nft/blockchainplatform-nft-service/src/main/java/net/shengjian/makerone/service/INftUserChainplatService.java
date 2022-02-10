package net.shengjian.makerone.service;

import net.shengjian.makerone.dto.UserChainplatInfoDTO;
import net.shengjian.makerone.entity.NftUserChainplat;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.vo.BindOpenNetVO;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.service.IBaseSpringrainService;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2021-12-21 17:57:36
 */
@RpcServiceAnnotation
public interface INftUserChainplatService extends IBaseSpringrainService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    NftUserChainplat findNftUserChainplatById(String id) throws Exception;

    /**
     * 新增用户链平台账号
     *
     * @param nftUserChainplat
     * @return
     * @throws Exception
     */
    Boolean saveDefaultValueNftUserChainPlat(NftUserChainplat nftUserChainplat) throws Exception;

    /**
     * 用户创建链平台账户
     *
     * @param eChainPlat 平台类型
     * @return 是否创建成功
     * @throws Exception
     * @deprecated 离线创建的账号无法在百度超级链开放网络使用,用户完善个人信息时绑定账号
     */
    @Deprecated
    NftUserChainplat saveCreateChainAccount(EChainPlat eChainPlat) throws Exception;

    /**
     * 查询用户链接链平台信息
     *
     * @param userId     用户id
     * @param eChainPlat 链平台枚举
     * @return 用户链接链平台信息
     * @throws Exception 查询错误的异常
     */
    UserChainplatInfoDTO findUserChainPlatInfo(String userId, EChainPlat eChainPlat) throws Exception;

    /**
     * 用户是否有address
     *
     * @param userId     用户id
     * @param eChainPlat 链平台枚举
     * @return 用户链接链平台信息
     * @throws Exception 查询错误的异常
     */
    Boolean haveAddress(String userId, EChainPlat eChainPlat) throws Exception;

    /**
     * 用户下载自己的公钥和私钥文件
     * @return 压缩包路径
     * @throws Exception 异常
     */
    String userDownPrivateAndPublic(EChainPlat chainPlat) throws Exception;

    /**
     * 用户删除自己的公钥和私钥文件
     * @return 是否删除成功
     */
    Boolean deleteUserPrivateAndPublic(EChainPlat chainPlat) throws Exception;

    /**
     * 查询用户的EVM Address (address转换后的)
     * @param userId
     * @return
     * @throws Exception
     */
    String findEVMAddress(String userId,EChainPlat eChainPlat) throws Exception;

    /**
     * 查找用戶链平台账号列表
     * @param userId 用戶id
     * @return 用户链平台列表
     * @throws Exception 异常
     */
    List<UserChainplatInfoDTO> findUserChainplatByUserId(String userId) throws Exception;

    /**
     * 绑定链平台开放网络的address
     * @param eChainPlat 链平台
     * @param verificationCode 验证码
     * @param openNetAddress 链平台开放网络的address
     * @return 是否綁定成功
     * @throws Exception 异常
     */
    @Deprecated
    Boolean updateBindXuperChainOpenNet(EChainPlat eChainPlat,String verificationCode, String openNetAddress) throws Exception;

    /**
     * 检查用户是否绑定了链平台的开放网络
     * @param userId 用户id
     * @param eChainPlat 链平台
     * @return true|false
     */
    Boolean checkBindChainOpenNet(String userId,EChainPlat eChainPlat) throws Exception;

    /**
     * 綁定百度超级链开放网络,私钥和密码
     * @param chainPlat 链平台
     * @param verificationCode 验证码
     * @param privateKey 私钥
     * @param passwd 密码
     * @param address address
     * @param phone 手机号
     * @param authorName 作者名称
     * @return 是否绑定成功
     * @throws Exception 异常
     */
    Boolean updateBindXuperChainOpenNetPrivate(EChainPlat chainPlat, String verificationCode, String privateKey, String passwd,String address,String phone,String authorName) throws Exception;

    /**
     * 有私钥和地址
     * @param chainPlat 链平台
     * @param userId 用户id
     * @return true|false
     * @throws Exception 异常
     */
    Boolean havePrivateKeyAndAddressAndPasswd(EChainPlat chainPlat, String userId) throws Exception;

    /**
     * 回显作者绑定的信息
     * @param userId 用户id
     * @param chainPlat 链平台
     * @return 用户信息
     * @throws Exception 异常
     */
    BindOpenNetVO reviewBind(String userId, EChainPlat chainPlat) throws Exception;

    /**
     * 绑定用户开放网络的私钥和密码
     * @param chainPlat 链平台
     * @param verificationCode 验证码
     * @param privateKey 私钥
     * @param passwd 密码
     * @return true|false
     * @throws Exception 异常
     */
    Boolean updateBindPrivateAndPasswd(EChainPlat chainPlat, String verificationCode, String privateKey, String passwd) throws Exception;

    /**
     * 更具address查詢用户id
     * @param chainPlat 链平台
     * @param address 开放网络address
     * @return userId
     * @throws Exception 异常
     */
    String findUserIdByAddress(EChainPlat chainPlat, String address) throws Exception;
}
