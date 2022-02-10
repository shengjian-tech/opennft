package net.shengjian.makerone.service;

import net.shengjian.frame.util.Page;
import net.shengjian.makerone.dto.UserOrderTransHisDTO;
import net.shengjian.makerone.entity.NftCollection;
import net.shengjian.makerone.entity.NftOrder;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.enums.EPayPlat;
import net.shengjian.makerone.enums.EPayState;
import net.shengjian.makerone.enums.ETradeType;
import net.shengjian.makerone.vo.MailVO;
import net.shengjian.makerone.vo.NftRankingsVO;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.service.IBaseSpringrainService;

import java.math.BigDecimal;
import java.util.List;

import java.util.Date;
import java.util.Map;

/**
 * 订单service
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:34
 */
@RpcServiceAnnotation
public interface INftOrderService extends IBaseSpringrainService {

    /**
     * 根据ID查找
     * @param id
     * @return
     * @throws Exception
     */
    NftOrder findNftOrderById(String id) throws Exception;

    /**
     * 订单的支付状态
     * @param id 订单id
     * @return 是否
     * @throws Exception 查询错误抛出的异常
     */
    EPayState payState(String id) throws Exception;

    /**
     * 根据作品id生成订单
     * @param worksId 作品id
     * @param sellerId 卖方id 并不只能从作者手中购买
     * @param num 数量
     * @param eTradeType 交易类型
     * @return 创建的订单
     * @throws Exception 创建失败抛出的异常
     */
    NftOrder saveGenerateOrderByWorksId(String worksId,String sellerId, Integer num, ETradeType eTradeType) throws Exception;

    /**
     * 默认保存,填充默认值
     * @param order 保存订单
     * @return 订单
     * @throws Exception 异常
     */
    NftOrder saveDefaultOrder(NftOrder order) throws Exception;
    /**
     * 按作品 ID 查找用户订单
     * @param userId 用户id
     * @param worksId 作品id
     * @return 订单状态
     * @throws Exception
     */
    NftOrder findUserOrderByWorksId(String userId,String worksId) throws Exception;

    /**
     * 查找用户合集排行榜
     * @param
     * @param
     * @return
     * @throws Exception
     */
    List<NftRankingsVO> findCollectionRankings( Page<NftRankingsVO> page) throws Exception;



    /**
     * 处理支付后的操作
     * @param orderId 订单id
     * @param ePayPlat 支付平台枚举
     * @param txTime 交易时间
     * @param ePayState 支付状态
     * @param transactionId 支付平台支付订单号
     * @param attach 订单支付时的附加数据
     * @return 是否处理成功
     * @throws Exception 处理错误抛出的异常
     */
    Boolean updatePayAfter(String orderId, EPayPlat ePayPlat, Date txTime,EPayState ePayState,String transactionId,String attach) throws Exception;

    /**
     * 处理交易作品支付后的操作
     * @param order 订单
     * @return 是否处理成功
     * @throws Exception 处理错误抛出的异常
     */
    Boolean updatePayTradeWorksAfter(NftOrder order) throws Exception;

    /**
     * 处理作品上架支付后的操作
     * @param order 订单
     * @return 是否处理成功
     * @throws Exception 处理错误抛出的异常
     */
    Boolean updatePayWorksInAfter(NftOrder order,String attach) throws Exception;

    /**
     * 更新链平台上链认证的信息
     * @param orderId 订单id
     * @param eChainPlat 链平台
     * @param chainPlatCert 数据
     * @return 是否更新成功
     * @throws Exception 异常
     */
    Boolean updateChainPlatCert(String orderId, EChainPlat eChainPlat, Map<String,Object> chainPlatCert) throws Exception;

    /**
     * 查詢订单数量 买入+卖出
     * @param userId 卖方
     * @return 数量
     * @throws Exception 异常
     */
    Integer findUserOrderCount(String userId) throws Exception;

    /**
     * 查詢订单数量
     * @param fromUser 卖方
     * @param toUser 买方
     * @param assetsId 资产id
     * @param ePayPlat 支付平台
     * @param eTradeType 交易类型
     * @param payState 支付状态
     * @return 数量
     * @throws Exception 异常
     */
    Integer findUserOrderCount(String fromUser,String toUser,String assetsId,EPayPlat ePayPlat,ETradeType eTradeType,EPayState payState) throws Exception;
    /**
     * 查詢订单数量
     * @param toUser 买方
     * @param tradeType 交易类型
     * @param payState 支付状态
     * @return 数量
     * @throws Exception 异常
     */
    Integer findUserOrderCount(String toUser,ETradeType tradeType,EPayState payState) throws Exception;

    /**
     * 查询用户交易历史
     * @param userId 用户id
     * @return 交易历史列表
     * @throws Exception 异常
     */
    List<UserOrderTransHisDTO> findTransHis(String userId) throws Exception;

    /**
     * 查询订单状态
     * @param orderId 订单id
     * @return 状态
     * @throws Exception 异常
     */
    EPayState updateFindOrderState(String orderId) throws Exception;

    /**
     * 根据条件查询订单
     * @param fromUser 卖方
     * @param toUser 买方
     * @param assetsId 资产id
     * @param payState 支付状态
     * @param tradeType 交易类型
     * @return 订单列表
     * @throws Exception 异常
     */
    List<NftOrder> findListBy(String fromUser,String toUser,String assetsId,EPayState payState,ETradeType tradeType) throws Exception;

    /**
     * 根据条件查询订单
     * @param userId 卖方 和 卖方
     * @param assetsId 资产id
     * @param payState 支付状态
     * @param tradeType 交易类型
     * @return 订单列表
     * @throws Exception 异常
     */
    List<NftOrder> findListBy(String userId,String assetsId,EPayState payState,ETradeType tradeType) throws Exception;

    /**
     * 根据条件查询订单
     * @param payState 支付状态
     * @return 订单列表
     * @throws Exception 异常
     */
    List<NftOrder> findListBy(EPayState payState) throws Exception;

    /**
     * 根据条件查询订单
     * @param payState 支付状态
     * @param minute 多少分钟之前
     * @return 订单列表
     * @throws Exception 异常
     */
    List<NftOrder> findListByMinuteBefore(EPayState payState,Integer minute) throws Exception;

    /**
     * 取消订单
     * @param order 订单id
     * @return 是否更新成功
     * @throws Exception 异常
     */
    Boolean updateCancelOrder(NftOrder order) throws Exception;

    /**
     * 取消订单
     * @param orderId 订单Id
     * @return true|false
     * @throws Exception 异常
     */
    Boolean updateCancelOrder(String orderId) throws Exception;

    /**
     * 查询未付款的上架作品订单
     * @param worksId 作品id
     * @param toUser 上架人
     * @return 订单
     * @throws Exception 异常
     */
    NftOrder findNotPayWorksIn(String worksId,String toUser) throws Exception;
    /**
     * 为作品上架生成订单
     * @param worksId 作品id
     * @param outTime 下架时间
     * @param waitingTime 发布等待期
     * @param price 价格
     * @return 订单
     * @throws Exception 异常
     */
    NftOrder saveGenerateOrderWorksIn(String worksId,Date outTime, Date waitingTime, BigDecimal price) throws Exception;

    /**
     * 计算涨跌幅
     * @param num
     * @param nftCollection
     * @throws Exception
     */
    void change(int num , NftCollection nftCollection) throws Exception;

    /**
     * 发送邮件给购买人
     * @param workId
     * @return
     * @throws Exception
     */
    MailVO sendMailAfterBuy(String workId,String mail) throws Exception;
}
