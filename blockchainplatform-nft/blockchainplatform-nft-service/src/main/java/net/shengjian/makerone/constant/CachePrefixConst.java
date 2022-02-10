package net.shengjian.makerone.constant;

import net.shengjian.frame.util.GlobalStatic;

/**
 * @descriptions: 缓存常量key前缀
 * @author: YSK
 * @date: 2021/12/21 16:39
 * @version: 1.0
 */
public class CachePrefixConst {

    /**
     * nft缓存
     */
    public static final String NFT_CACHE_PREFIX = "nft_cache_prefix_";
    /**
     * 排行榜缓存
     */
    public static final String collectionRanking = "collectionRanking";

    /**
     * 热门合集缓存
     */
    public static final String fireCollection = "fireCollection";
    /**
     * 合集详情页作品列表缓存
     */
    public static final String worksListInCollection = "worksListInCollection";
    /**
     * 作品购买详情页作品相似缓存
     */
    public static final String worksSame = "worksSame";

    /**
     * 作品购买者缓存
     */
    public static final String buyersForWorks = "buyersForWorks";

    /**
     * 作品购买者缓存
     */
    public static final String collectionDetail = "collectionDetail";
    /**
     * 作品购买详情缓存（头像下一部分）
     */
    public static final String worksInfo = "worksInfo";

    /**
     * 作品购买详情缓存（购买按钮旁边）
     */
    public static final String worksInfoDetail = "worksInfoDetail";

    /**
     * 链平台信息缓存
     */
    public static final String CHAIN_PLAT = NFT_CACHE_PREFIX+"chain_plat";
    /**
     * 用户资产
     */
    public static final String USER_ASSETS = NFT_CACHE_PREFIX+"user_assets";
    /**
     * 用户链信息
     */
    public static final String USER_CHAIN_INFO = NFT_CACHE_PREFIX+"user_chain_info";
    /**
     * 锁key前缀
     */
    public static final String LOCK_PREFIX = "nft_lock_";
}
