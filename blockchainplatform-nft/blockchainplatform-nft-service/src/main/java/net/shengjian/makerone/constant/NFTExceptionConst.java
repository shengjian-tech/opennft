package net.shengjian.makerone.constant;

import net.shengjian.makerone.exception.NFTException;

/**
 * @descriptions: 异常常量
 * @author: YSK
 * @date: 2021/12/24 9:33
 * @version: 1.0
 */
public class NFTExceptionConst {
    public static final Exception PARAMS_IS_NULL = new NFTException("参数为空!");
    public static final Exception COLLECTION_NAME_EXIST = new NFTException("合集名称已存在!");
    public static final Exception WORKS_NOT_EXIST = new NFTException("作品不存在!");
    public static final Exception COLL_NOT_EXIST = new NFTException("合集不存在!");
    public static final Exception USER_NOT_CHAIN_PLAT_ACCOUNT = new NFTException("用户没有该链平台账户!");
    public static final Exception DATE_IS_NULL = new NFTException("日期为空!");
    public static final Exception OPERATION_FAIL = new NFTException("操作失败!");
    public static final Exception OPERATION_REPEAT = new NFTException("请勿重复操作!");

    public static final Exception ORDER_NOT_EXIST = new NFTException("订单不存在!");
    public static final Exception WORKS_NUM_INSUFFICIENT = new NFTException("作品数量不足!");
    public static final Exception NOT_AUTHOR = new NFTException("不是作者");
    public static final Exception MOBILE_NULL = new NFTException("手机号为空");
    public static final Exception VERIFY_CODE_NULL = new NFTException("验证码为空");
    public static final Exception VERIFY_CODE_ERR = new NFTException("验证码错误");
    public static final Exception ACCOUNT_EXIST = new NFTException("账号已存在");
    public static final Exception ORDER_ERR = new NFTException("订单异常");
    public static final Exception NOT_ADDRESS = new NFTException("用户没有完善百度超级链开放网络地址(address)");
}
