package net.shengjian.system.service;

import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.entity.UserPlatformInfos;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2017-07-31 16:41:34
 * @copyright {@link jiagou.com}
 * @see org.springrain.cms.base.service.UserPlatformInfos
 */
@RpcServiceAnnotation
public interface IUserPlatformInfosService extends IBaseSpringrainService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    UserPlatformInfos findUserPlatformInfosById(Object id) throws Exception;

    /**
     * 通过t_user表小的id找此用户相关的平台Ids
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<UserPlatformInfos> findUserPlatformsByUserId(String userId) throws Exception;

    /**
     * 通过用户ID和用户类型找台对像
     *
     * @param userId
     * @param type
     * @return
     * @throws Exception
     */
    UserPlatformInfos findUserPlateformByUserIdAndType(String userId, Integer type) throws Exception;

    /**
     * 通过用户ID和用户类型找TOKENID
     *
     * @param userId
     * @param type
     * @return
     * @throws Exception
     */
    String findTokenIdByUserIdAndType(String userId, Integer type) throws Exception;
}
