package net.shengjian.rpc.sessionuser;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 当前登录用户信息,可以在bean中调用获取当前登录用户信息,例如 SessionUser.getUserId()获取当前登录人的userId
 *
 * @author springrain<9 iuorg @ gmail.com>
 * @version 2013-03-19 11:08:15
 */
public class SessionUser {
    private static final Logger logger = LoggerFactory.getLogger(SessionUser.class);
    // 存放sessionUser对象
    public static ThreadLocal<UserVO> sessionUserLocal = new ThreadLocal<>();

    private SessionUser() {
        throw new IllegalAccessError("工具类不能实例化");
    }


    public static UserVO getUserVO() {
        return sessionUserLocal.get();
    }


    public static String getUserId() {
        try {
            UserVO userVO = getUserVO();
            if (userVO != null) {
                return userVO.getUserId();
            }

            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static Integer getUserType() {
        try {
            UserVO userVO = getUserVO();
            if (userVO != null) {
                return userVO.getUserType();
            }

            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static String getAccount() {
        try {
            UserVO userVO = getUserVO();
            if (userVO != null) {
                return userVO.getAccount();
            }

            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }

    public static String getUserName() {
        try {
            UserVO userVO = getUserVO();
            if (userVO != null) {
                return userVO.getUserName();
            }

            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static String getEmail() {
        try {
            UserVO userVO = getUserVO();
            if (userVO != null) {
                return userVO.getEmail();
            }

            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static String getPrivateOrgRoleId() {
        try {
            UserVO userVO = getUserVO();
            if (userVO != null) {
                return userVO.getPrivateOrgRoleId();
            }

            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

}
