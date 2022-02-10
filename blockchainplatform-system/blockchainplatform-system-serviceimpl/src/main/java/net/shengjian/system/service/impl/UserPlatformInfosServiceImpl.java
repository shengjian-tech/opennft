package net.shengjian.system.service.impl;

import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.frame.util.Finder;
import net.shengjian.system.entity.UserPlatformInfos;
import net.shengjian.system.service.IUserPlatformInfosService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2017-07-31 16:41:34
 */
@Service("userPlatformInfosService")
public class UserPlatformInfosServiceImpl extends BaseSpringrainServiceImpl implements IUserPlatformInfosService {

    @Override
    public String save(IBaseEntity entity) throws Exception {
        UserPlatformInfos userPlatformInfos = (UserPlatformInfos) entity;
        return super.save(userPlatformInfos).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        UserPlatformInfos userPlatformInfos = (UserPlatformInfos) entity;
        return super.update(userPlatformInfos);
    }

    @Override
    public UserPlatformInfos findUserPlatformInfosById(Object id) throws Exception {
        return super.findById(id, UserPlatformInfos.class);
    }


    @Override
    public List<UserPlatformInfos> findUserPlatformsByUserId(String userId) throws Exception {
        UserPlatformInfos infos = new UserPlatformInfos();
        infos.setUserId(userId);
        return super.queryForListByEntity(infos, null);
    }

    @Override
    public UserPlatformInfos findUserPlateformByUserIdAndType(String userId, Integer type) throws Exception {
        Finder finder = Finder.getSelectFinder(UserPlatformInfos.class);
        finder.append(" where userId=:userId and deviceType=:deviceType ");
        finder.setParam("deviceType", type).setParam("userId", userId);
        return queryForObject(finder, UserPlatformInfos.class);

    }

    @Override
    public String findTokenIdByUserIdAndType(String userId, Integer type) throws Exception {
        UserPlatformInfos userPlatformInfos = findUserPlateformByUserIdAndType(userId, type);
        if (userPlatformInfos != null) {
            return userPlatformInfos.getOpenId();
        }
        return null;
    }
}
