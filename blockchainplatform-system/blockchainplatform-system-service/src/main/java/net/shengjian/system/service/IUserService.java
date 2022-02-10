package net.shengjian.system.service;

import net.shengjian.frame.util.Page;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.rpc.sessionuser.UserVO;
import net.shengjian.system.dto.LVDTO;
import net.shengjian.system.dto.PhoneLoginDTO;
import net.shengjian.system.entity.User;
import net.shengjian.system.vo.LoginSuccessVO;

import java.util.List;

/**
 * 用户管理查询的基础Servcie
 *
 * @author springrain<Auto generate>
 * @version 2013-07-06 16:03:00
 */
@RpcServiceAnnotation
public interface IUserService extends IBaseSpringrainService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    User findUserById(String id) throws Exception;

    /**
     * 根据userId查找UserVO
     *
     * @param userId
     * @return
     * @throws Exception
     */
    UserVO findUserVOByUserId(String userId) throws Exception;

    /**
     * 根据OpenId查找UserVO
     *
     * @param openId
     * @return
     * @throws Exception
     */
    String findUserIdByOpenId(String openId) throws Exception;

    /**
     * 封装JwtToken字符串
     *
     * @param user
     * @return
     * @throws Exception
     */
    String wrapJwtTokenByUser(User user) throws Exception;


    /**
     * 根据账号密码 验证是否能够登录,userType用于区分用户类型
     *
     * @param account
     * @param password
     * @return
     * @throws Exception
     */
    User findLoginUser(String account, String password, Integer userType) throws Exception;

    /**
     * 查询用户列表
     *
     * @param page
     * @return
     * @throws Exception
     */
    List<User> findUserList(Page<User> page) throws Exception;

    /**
     * 根据用户账号更新密码
     *
     * @param account 用户账号
     * @param oldPwd  旧密码
     * @param newPwd  新密码
     * @throws Exception 旧密码错误异常
     */
    void updatePwd(String account, String oldPwd, String newPwd) throws Exception;

    /**
     * 管理员强制重置密码
     *
     * @param account
     * @param newPwd
     * @throws Exception
     */
    void updatePwd(String account, String newPwd) throws Exception;

    /**
     * 新增用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    User saveUser(User user) throws Exception;

    /**
     * 更新用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    User updateUser(User user) throws Exception;

    /**
     * 删除用户
     *
     * @param id
     * @throws Exception
     */
    void deleteUser(String id) throws Exception;

    /**
     * 批量删除用户
     *
     * @param ids
     */
    void deleteUserBatch(List<String> ids) throws Exception;

    /**
     * 根据用户账号查询用户
     *
     * @param account
     * @return
     */
    User findUserByAccount(String account) throws Exception;

    /**
     * 退出登录
     *
     * @param userId
     * @throws Exception
     */
    void logout(String userId) throws Exception;

    /**
     * 导入
     * 保存用户，数据来源excel
     */
    void saveByImported(String path) throws Exception;

    /**
     * 用户列表，不包含用户角色和部门数据
     *
     * @return
     */
    List<User> findUsers() throws Exception;

    /**
     * 发送短信验证码
     *
     * @param dto
     * @throws Exception
     */
    void sendCode(PhoneLoginDTO dto) throws Exception;

    /**
     * 手机号登录(不存在则注册一个用户,登录账号为手机号,密码为123)
     *
     * @param phoneLoginDTO
     * @return
     * @throws Exception
     */
    User savePhoneLogin(PhoneLoginDTO phoneLoginDTO) throws Exception;

    /**
     * 根据手机号查询用户id
     *
     * @param phone
     * @return
     * @throws Exception
     */
    String findUserIdByPhone(String phone) throws Exception;

    /**
     * 用户类型列表
     *
     * @return
     * @throws Exception
     */
    List<LVDTO> userTypeList() throws Exception;

    /**
     * 权限接口信息
     * @param user
     * @return
     * @throws Exception
     */
    LoginSuccessVO findUserCodeMap(User user) throws Exception;

    /**
     * 获取用户手机号
     * @param userId 用户id
     * @return 手机号
     * @throws Exception  异常
     */
    String getUserPhone(String userId) throws Exception;

    /**
     * 验证用户的手机号验证码
     * @param verificationCode 验证码
     * @param userPhone 用户手机号
     * @throws Exception 验证错误异常
     */
    void verifyPhoneCode(String userPhone,String verificationCode) throws Exception;
}
