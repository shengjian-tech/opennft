package net.shengjian.makerone.constant;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 公共常量
 *
 * @author yanshikun
 * @date 2021/12/21
 */
@Component
public class CommonConst implements EnvironmentAware {
    private Environment environment;
    @Override
    public void setEnvironment(Environment environment) {
        this.environment=environment;
    }
    @PostConstruct
    public void init(){
        //从yml中获取=~
        //~手续费比例
        HANDLING_FEE=environment.getProperty("const.handlingFee",BigDecimal.class,HANDLING_FEE);
        //~是否推广期
        PROMOTION_PERIOD=environment.getProperty("const.promotionPeriod",Boolean.class,PROMOTION_PERIOD);
        //~nft合约名
        NFT_CONTRACT_NAME=environment.getProperty("const.nftContractName",String.class,NFT_CONTRACT_NAME);
    }
    /**
     * 是否推广期
     */
    private static Boolean PROMOTION_PERIOD = true;
    public static Boolean isPromotionPeriod(){
        return PROMOTION_PERIOD;
    }

    /**
     * 全局交易手续费 支付订单费用的百分比
     */
    private static BigDecimal HANDLING_FEE = new BigDecimal("0");
    public static BigDecimal getHandlingFee(){
        return HANDLING_FEE;
    }
    /**
     * erc合约名
     */
    private static String NFT_CONTRACT_NAME = "TestErc";
    public static String getNftContractName(){
        return NFT_CONTRACT_NAME;
    }

    /**
     * 微信配置表中的主键(站点)
     */
    public static final String SITE = "1";
    /**
     * 否
     */
    public static final int FALSE = 0;
    /**
     * 是
     */
    public static final int TRUE = 1;
    /**
     * 默认用户头像
     */
    public static final String DEFAULT_AVATAR = "/avatar/default.jpg";
    /**
     * 未知的
     */
    public static final String UNKNOWN = "未知";
    /**
     * 百
     */
    public static final BigDecimal HUNDRED = new BigDecimal("100");
    /**
     * ROOT id
     */
    public static final String ROOT_ID = "u_10001";
    /**
     * classpath: 路径前缀
     */
    public static final String CLASS_PATH = "classpath:";

    /**
     * 默认发布等待期 ,发布日期 1天后
     */
    public static final int DEFAULT_WAITING_TIME = 1;
    /**
     * 默认交易Gas消耗转换RMB的比例
     * gas:rbm
     * 10000:1  (元)
     */
    public static final BigDecimal GAS_RATIO =new BigDecimal("10000");
    /**
     * 微信用户首次登录的用户名
     */
    public static final String WECHAT_NAME = "微信用户";
    /**
     * nft用戶角色 id
     */
    public static final String NFT_ROLE_ID="nft_role";
    /**
     * 金额计算的保留小数位
     *
     */
    public static final Integer DECIMAL_PLACES = 2;
    /**
     * 交易冷却期 30天
     * TRADING_COOLING_PERIOD 秒
     * 秒*分*时*天
     */
    public static final String TRADING_COOLING_PERIOD = String.valueOf(60*60*24*30);
    /**
     * 合约内的当前时间戳
     */
    public static Long CONTRACT_NOW_TIMESTAMP = new Date().getTime();

    /**
     * 百度区块链浏览器地址
     */
    public static final String BAIDU_BROWSER_ADDRESS = "https://xuper.baidu.com/n/console#/xuperos/explorer";

    /**
     * 系统名称
     */
    public static final String MAKER_ONE = "MakerOne平台";

    /**
     * 取消订单定时任务锁
     */
    public static final String CANCEL_ORDER_TIMED_TASK_LOCK = "cancel_order_timed_task_lock";
    /**
     * 更新合约内时间定时任务锁
     */
    public static final String UPDATE_CONTRACT_EXPIRE_LOCK = "update_contract_expire_lock";

    /**
     * 路径
     */
    public static final String NFT_ARTWORK_PATH = "/artwork/";
}