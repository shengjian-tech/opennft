package net.shengjian.frame.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

/**
 * JWT工具类,用于生成和验证JWTtoken
 *
 * @author 程相羽
 * @version 2019年3月6日 上午10:33:47
 * @copyright
 * @see JwtUtils
 */
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);


    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String userId, String secret) {
        try {

            if (StringUtils.isBlank(token) || StringUtils.isBlank(userId) || StringUtils.isBlank(secret)) {
                return false;
            }

            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC512(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("userId", userId).build();
            //效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return jwt != null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 获得token中的userId
     *
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        return getClaim(token, "userId").asString();
    }

    /**
     * 获取token中的userType
     *
     * @param token
     * @return
     * @author 程相羽
     * @version 2020年10月28日 下午4:39:01
     */
    public static Integer getUserType(String token) {
        return getClaim(token, "userType").asInt();
    }

    /**
     * 获得token中的信息,无需secret解密.需要提前verify验证一下token是否正确.
     *
     * @param token
     * @return
     */
    public static Claim getClaim(String token, String claimName) {
        try {

            if (StringUtils.isBlank(token)) {
                return null;
            }

            DecodedJWT jwt = JWT.decode(token);
            String userId = jwt.getClaim("userId").asString();

            if (StringUtils.isBlank(userId)) {
                return null;
            }
            boolean verify = verify(token, userId, getSecret(userId));
            if (!verify) {
                return null;
            }

            return jwt.getClaim(claimName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }


    public static Date getExpireDate(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }

        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt();
    }

    /**
     * 根据user对象生成加密字符串,主要是位了兼容第三方使用jwt,所以需要放到util包下,使用map封装参数
     *
     * @param jwtSignMap
     * @return
     */
    public static String sign(Map<String, Object> jwtSignMap) {

        if (jwtSignMap == null) {
            return null;
        }

        try {


            long expires = System.currentTimeMillis() + GlobalStatic.jwtTimeout;
            Date date = new Date(expires);
            String userId = (String) jwtSignMap.get("userId");

            Algorithm algorithm = Algorithm.HMAC512(getSecret(userId));

            String token = JWT.create()
                    .withClaim("userId", userId)
                    .withClaim("account", (String) jwtSignMap.get("account"))
                    .withClaim("userName", (String) jwtSignMap.get("userName"))
                    .withClaim("userType", (Integer) jwtSignMap.get("userType"))
                    .withExpiresAt(date).sign(algorithm);
            return token;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    public static String getSecret(String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        String secret = SecUtils.encoderByMd5With32Bit(userId + GlobalStatic.jwtSecret);
        return secret;
    }

/*
    public static void main(String[] args) {

	    String userId="admin";
        String token=JwtUtils.sign(userId);
        System.out.println(token);
        String uid=JwtUtils.getUserId(token);
        System.out.println(uid);
    }
*/


}
