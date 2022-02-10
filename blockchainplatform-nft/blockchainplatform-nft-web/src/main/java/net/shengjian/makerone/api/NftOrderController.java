package net.shengjian.makerone.api;

import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.property.MessageUtils;
import net.shengjian.makerone.constant.MessageConst;
import net.shengjian.makerone.dto.PayRequestParamDTO;
import net.shengjian.makerone.dto.UserOrderTransHisDTO;
import net.shengjian.makerone.entity.NftOrder;
import net.shengjian.makerone.enums.EPayState;
import net.shengjian.makerone.enums.ETradeType;
import net.shengjian.makerone.exception.NFTException;
import net.shengjian.makerone.service.INftOrderService;
import net.shengjian.makerone.utils.PayUtil;
import net.shengjian.makerone.vo.GenerateOrderVO;
import net.shengjian.makerone.vo.InShelfVO;
import net.shengjian.makerone.vo.NftRankingsVO;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.base.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单模块
 *
 * @author springrain<Auto generate>
 * @version 2021-12-21 17:58:34
 */
@RestController
@RequestMapping(value = "/api/nft/order", method = RequestMethod.POST)
public class NftOrderController extends BaseController {
    @Resource
    private INftOrderService nftOrderService;

    @Resource
    private PayUtil payUtil;

    /**
     * 根据作品id生成订单
     * @param vo 参数对象
     * @return 返回生成的订单信息
     */
    @PostMapping("/generateOrderByWorksId")
    public ReturnDatas<NftOrder> generateOrderByWorksId(@RequestBody GenerateOrderVO vo) {
        ReturnDatas<NftOrder> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            NftOrder order = nftOrderService.saveGenerateOrderByWorksId(vo.getWorksId()
                    ,vo.getSellerId()
                    , vo.getNum()
                    , ETradeType.codeOf(vo.getTradeType()));
            rd.setResult(order);
            rd.setMessage(MessageConst.OPERATION_OK);
        } catch (NFTException nftException) {
            logger.error(nftException.getMessage(), nftException);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(nftException.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(MessageConst.UNKNOWN_ERR);
        }
        return rd;
    }
    /**
     * 作品上架生成订单
     * @param vo 参数对象
     * @return 返回生成的订单信息
     */
    @PostMapping("/generateOrderWorksIn")
    public ReturnDatas<NftOrder> saveGenerateOrderWorksIn(@RequestBody InShelfVO vo) {
        ReturnDatas<NftOrder> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            NftOrder order = nftOrderService.saveGenerateOrderWorksIn(vo.getId(), vo.getOutTime(), vo.getWaitingTime(), vo.getPrice());
            rd.setResult(order);
            rd.setMessage(MessageConst.OPERATION_OK);
        } catch (NFTException nftException) {
            logger.error(nftException.getMessage(), nftException);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(nftException.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(MessageConst.UNKNOWN_ERR);
        }
        return rd;
    }

    /**
     * 调起微信收银台的js请求参数
     * @param prepayId 预支付id
     * @return 源数据和签名后的数据
     */
    @PostMapping("/getPayRequestParam")
    public ReturnDatas<PayRequestParamDTO> getPayRequestParamByPrePayId(String prepayId) {
        ReturnDatas<PayRequestParamDTO> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            PayRequestParamDTO data = payUtil.getPayRequestParam(prepayId);
            rd.setResult(data);
            rd.setMessage(MessageConst.OPERATION_OK);
        } catch (NFTException nftException) {
            logger.error(nftException.getMessage(), nftException);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(nftException.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(MessageConst.UNKNOWN_ERR);
        }
        return rd;
    }

    /**
     * 模拟支付成功后请求
     * @param orderId 订单id
     * @return
     */
    /*@PostMapping("/payAfter")
    public ReturnDatas<String> payAfter(String orderId) {
        ReturnDatas<String> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            nftOrderService.updatePayAfter(orderId, EPayPlat.微信支付,new Date(),EPayState.已支付,"1234567");
            rd.setMessage(MessageConst.OPERATION_OK);
        } catch (NFTException nftException) {
            logger.error(nftException.getMessage(), nftException);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(nftException.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(MessageConst.UNKNOWN_ERR);
        }
        return rd;
    }*/
    /**
     * 返回排行榜数据
     * @param page 分页
     * @return
     */

    @PostMapping("/rankingFindAll")
    public ReturnDatas<List<NftRankingsVO>> rankingFindAll(@RequestBody Page<NftRankingsVO> page) {
        ReturnDatas<List<NftRankingsVO>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<NftRankingsVO> datas = nftOrderService.findCollectionRankings(page);
            rd.setPage(page);
            rd.setResult(datas);
        } catch (NFTException nftException) {
            logger.error(nftException.getMessage(), nftException);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(nftException.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(MessageConst.UNKNOWN_ERR);
        }
        return rd;
    }

    /**
     * 检查订单状态
     * @param orderId 订单id
     * @return
     */
    @PostMapping("/checkOrderState")
    public ReturnDatas<Boolean> checkOrderState(String orderId){
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            EPayState state = nftOrderService.updateFindOrderState(orderId);
            rd.setResult(EPayState.已支付.getCode().equals(state.getCode()));
            rd.setMessage(MessageUtils.FIND_SUCCESS);
        } catch (NFTException nftException) {
            logger.error(nftException.getMessage(), nftException);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(nftException.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(MessageConst.UNKNOWN_ERR);
        }
        return rd;
    };

    /**
     * 用户订单列表,交易历史
     * @return 返回生成的订单信息
     */
    @PostMapping("/findTransHis")
    public ReturnDatas< List<UserOrderTransHisDTO>> findTransHis() {
        ReturnDatas< List<UserOrderTransHisDTO>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<UserOrderTransHisDTO> data = nftOrderService.findTransHis(SessionUser.getUserId());
            rd.setResult(data);
            rd.setMessage(MessageUtils.FIND_SUCCESS);
        } catch (NFTException nftException) {
            logger.error(nftException.getMessage(), nftException);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(nftException.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(MessageConst.UNKNOWN_ERR);
        }
        return rd;
    }

    /**
     * 检查用户是否有未支付的订单
     * @return true | false
     */
    @PostMapping("/checkUnpaidOrders")
    public ReturnDatas<Boolean> checkUnpaidOrders(){
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            Integer orderCount = nftOrderService.findUserOrderCount(SessionUser.getUserId(),ETradeType.交易作品, EPayState.未支付);
            rd.setResult(orderCount>0);
        } catch (NFTException nftException) {
            logger.error(nftException.getMessage(), nftException);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(nftException.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(MessageConst.UNKNOWN_ERR);
        }
        return rd;
    }

    /**
     * 取消订单
     * @return true | false
     */
    @PostMapping("/cancelOrder")
    public ReturnDatas<Boolean> cancelOrder(String orderId){
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            Boolean flag = nftOrderService.updateCancelOrder(orderId);
            rd.setResult(flag);
            rd.setMessage(MessageConst.OPERATION_OK);
        } catch (NFTException nftException) {
            logger.error(nftException.getMessage(), nftException);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(nftException.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(MessageConst.UNKNOWN_ERR);
        }
        return rd;
    }
}
