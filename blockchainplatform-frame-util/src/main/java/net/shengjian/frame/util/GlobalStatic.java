package net.shengjian.frame.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局的静态变量,用于全局变量的存放
 *
 * @author springrain<Auto generate>
 * @version 2013-03-19 11:08:15
 * @see GlobalStatic
 */
public class GlobalStatic {

    public static final int excelPageSize = 1000;
    public static final String suffix = ".html";
    public static final String excelext = ".xls";
    public static final String exportexcel = "exportexcel";// 是否是导出操作的key
    public static final String dataUpdate = "更新";
    public static final String dataSave = "保存";
    public static final String dataDelete = "删除";
    public static final String SQLCutSeparator = "___";// SQL复合对象查询的分隔符,三个 下划线
    // 对应file.conf的vgroup_mapping.seata_tx_group,seata是拼接vgroup_mapping.+seataTransactionServiceGroup###
    public static final String seataTransactionServiceGroup = "seata_tx_group";
    // 是否开启seata分布式事务,默认是开启的,一旦关闭,整个应用就会关闭
    public static final boolean seataGlobalEnable = true;
    // 记录是否是 分支事务
    public static final ThreadLocal<Boolean> seataBranchTransaction = new ThreadLocal<>();
    // 微信缓存配置
    public static final String wxConfigCacheKey = "wxConfigCacheKey";
    // 支付宝缓存配置
    public static final String aliPayConfigCacheKey = "aliPayConfigCacheKey";
    // page对象的缓存后缀key
    public static final String pageCacheExtKey = "_springrain_page_key";
    // 主业务缓存
    public static final String cacheKey = "springraincache";
    // 权限缓存
    public static final String qxCacheKey = "springrainqxcache";
    // UserVO信息缓存
    public static final String userOrgRoleMenuInfoCacheKey = "springrainuserorgrolemenuinfocache";
    // 页面静态化缓存
    // public static final String staticHtmlCacheKey="statichtmlcache";
    // 登录次数校验缓存
    public static final String springrainloginCacheKey = "springrainlogincache";
    // 登陆验证码识别码
    public static final String springranloginCaptchaKey = "springrainloginCaptchaKey";
    // 缓存用户最后有效的登录sessionId
    public static final String springrainkeeponeCacheKey = "springrainkeeponecache";
    public static final String defaultCharset = "UTF-8";
    public static final String tableSuffix = "_history_";
    public static final String tableSuffixSymbol = "_";
    public static final String frameTableAlias = "frameTableAlias";
    public static final String returnDatas = "returnDatas";
    // 密码连续错误10次,锁定不再进行登录查询,锁定 ERROR_LOGIN_LOCK_MINUTE 分钟
    public static final int ERROR_LOGIN_COUNT = 10;
    // 错误登录后的,锁定分钟数
    public static final int ERROR_LOGIN_LOCK_MINUTE = 30;
    // 加密类型
    public static final String provider = "RSA";
    public static final Map<Integer, String> sexMap = new HashMap<Integer, String>() {{
        put(1, "男");
        put(2, "女");
    }};
    public static String projectKeyPrefix = "springrain_";// ES和redis的固定前缀,用于多个项目使用同一个ES和redis集群
    // seata的全局变量
    // 建议和项目标识符保持一致
    public static final String seataApplicationId = projectKeyPrefix;
    public static boolean showsql = false;//是否显示sql
    public static String rootDir = null;
    public static String webInfoDir = null;
    // 防火墙缓存
    // public static final String springrainfirewallCacheKey = "springrainfriewallcache";
    // 微信缓存
    // public static final String springrainweixinCacheKey="springrainweixincache";
    // cms 缓存
    // public static final String springraincmsCacheKey = "springraincmscache";

    // defaultSiteId 缓存
    // public static final String springraindefaultSiteId = "defaultSiteId";
    public static String staticHtmlDir = null;
    public static String tempRootpath = System.getProperty("user.dir") + "/temp/";
    //GRPC 默认设置
    public static String rpcHost = "127.0.0.1";
    public static Integer rpcPort = 5551;
    // 如果token错误,跳转地址的key
    // public static final String errorTokentoURLKey = "errorspringraintokentourlkey";
    // token错误跳转的页面
    // public static final String errorTokentoURL = "/errorpage/tokenerror";

    // 自定义的登录地址key
    // public static final String customLoginURLKey = "customLoginURLKey";
    public static Boolean isRpcServer = true;
    public static Boolean isRpcClient = true;
    // 默认不启用seata,如果存在service的分布式调用,就修改成true.只有seataGlobalEnable和seataEnable都是true,seata才会启用.
    public static boolean seataEnable = false;
    // 如果启用seata-spring注解@GlobalTransactional方法,和grpcserver的切面存在冲突,会重复提交,grpc就不再负责seata事务管理了.
    public static boolean seataSpringEnable = false;
    // jwt的加密密钥,非常关键,注意保密
    public static String jwtSecret = "dev";

    // 认证
    // public static final String reloginsession="shiro-reloginsession";
    // 认证
    // public static final String authenticationCacheName = "shiro-authenticationCacheName";
    // 授权
    // public static final String authorizationCacheName = "shiro-authorizationCacheName";
    // realm名称
    // public static final String authorizingRealmName = "shiroDbAuthorizingRealmName";

    // 默认验证码参数名称
    //public static final String DEFAULT_CAPTCHA_PARAM = "captchaKey";
    // 前后台传递的tokenKey
    public static String jwtTokenKey = "jwttoken";
    public static String USER_SPECICAL_INFO = "USER_SPECICAL_INFO";

    // 同一IP防火墙阀值
    // public static final Integer FRIEWALL_LOCK_CHECK_COUNT = -1;
    // 同一IP阀值时间,单位是 秒
    // public static final Integer FRIEWALL_LOCK_CHECK_SECOND = 60;
    // 锁定秒数
    // public static final Integer FRIEWALL_LOCKED_SECOND = 10 * 60;
    // jwt token的超时时间
    public static long jwtTimeout = 10 * 60 * 60 * 1000;
    // rsa私钥文件,非常关键,注意保密.生产环境一定要配置到 yaml里.
    public static String rsaPrivateKeyPem = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMXQq9T6Zp2RfLoiP1FuU9sVwBGS2n5V0hv1K1Qt6fOl9IrY/MyH7iJLctWpNsB4xP0H6C9MR5eVyn/u87wJq6YkBD0gPeAOl12k5NhNcwXyww/naQCJonMeKTuJ196wc1G/Y3wh8zhmWEvLJ2WsiYnrWgRw/paATjFZxGqld0FRAgMBAAECgYAOGUxV9q7fpiHspko7BJEHeWpiFkRqYrWB9pxq+IbHLdnbF5IVBc8sNJJvtgrH9xObMTgUPuyPWka/jYljqpDIO4vVyMCbGy44r+NVUzWkDURwwpQmM3WY/Y6fltjLps/DJxpcFdwE6ym9Gn5mAr3qK0EfyzM9MnGjLlThdufKTQJBAOdItW6P12dLTcDPbvWCMW9UYE8fprJUw/qfuSJrymGh8t0XeqjxRlPlvQNpy5GcUGHRajv5BlIWz3utvusHf6sCQQDa9FiVUtgNVBIv0yOZp8JSoLj3N0bmRs8HDx53Z47SRn5ZowcbEF8uat7I3TKRiA6rY0bUO2p9v9DMDc2qQTbzAkEA5Pj0thqt1FOXDJXQcjqMmn1cQ7lAHTkSNONANDWmzPW6LOx7wRns2E6Py/tHUEcCxXS/ydQGT+rJStZ+NszbtQJANIGMDKMFNI0In3defiaArxfjw0T6N5abZvH1OGUZFsvY2N4DgjX23Ua7jiedTND305nHDnYBH460H3rf6wggiQJBAK8mgXyzGNtDwEYoQDdHeIy47PUPXgEeIrKiHPIPnYTuF7n9aCVnWHbHBsDXTur9/I3Qkt0qbPJQGAbdScFJ0/0=";
    // rsa公钥文件
    public static String rsaPublicKeyPem = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDF0KvU+madkXy6Ij9RblPbFcARktp+VdIb9StULenzpfSK2PzMh+4iS3LVqTbAeMT9B+gvTEeXlcp/7vO8CaumJAQ9ID3gDpddpOTYTXMF8sMP52kAiaJzHik7idfesHNRv2N8IfM4ZlhLyydlrImJ61oEcP6WgE4xWcRqpXdBUQIDAQAB";

    // 不拦截的URL,用于多项目设置excludePathPatterns
    public static List<String> excludePathPatterns = new ArrayList<>();

    // 用户登录之后,默认能够访问的URL
    public static List<String> userDefaultUrl = new ArrayList<>();


    static {
        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
        path = path.replace("\\", "/");

        if (path.startsWith("file:/")) {
            path = path.substring(6);
        }

        int _info = path.indexOf("/WEB-INF/classes");
        if (_info > 0) {
            path = path.substring(0, _info);
        }
        if (!path.startsWith("/") && path.indexOf(":") == -1) {
            path = File.separatorChar + path;
        }

        rootDir = path;
        webInfoDir = rootDir + "/WEB-INF";
        tempRootpath = rootDir + "/temp/";
        staticHtmlDir = rootDir + "/statichtml/";


        // 设置默认不拦截的URL
        excludePathPatterns.add("/api/system/login");
        excludePathPatterns.add("/api/system/auth/login");
        //excludePathPatterns.add("/api/system/logout");

        excludePathPatterns.add("/api/work/login");
        excludePathPatterns.add("/api/user/login");

        excludePathPatterns.add("/api/sendCode");
        excludePathPatterns.add("/api/getCaptcha");
        excludePathPatterns.add("/api/checkHealth");
        excludePathPatterns.add("/api/error/*");
        excludePathPatterns.add("/error");

        // 微信支付
        excludePathPatterns.add("/api/mp/pay/getWxpayfaceAuthinfo");
        excludePathPatterns.add("/api/mp/pay/facePayOrder");
        excludePathPatterns.add("/api/mp/pay/facePayOrderQuery");

        excludePathPatterns.add("/f/xcx/autologin/*/login");
        excludePathPatterns.add("/mp/mpautologin/*/oauth2");
        excludePathPatterns.add("/mp/mpautologin/*/callback");

        // 默认能够访问的URL
        userDefaultUrl.add("/api/system/user/menu");
        userDefaultUrl.add("/api/system/user/info");
        userDefaultUrl.add("/api/system/user/getRouters");
        userDefaultUrl.add("/api/system/logout");


        //userDefaultUrl.add("/upload/**");//上传的目录


    }


    private GlobalStatic() {
        throw new IllegalAccessError("工具类不能实例化");
    }

}
