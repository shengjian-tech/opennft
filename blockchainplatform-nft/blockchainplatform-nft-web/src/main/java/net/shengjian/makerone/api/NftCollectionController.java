package net.shengjian.makerone.api;
import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.property.MessageUtils;
import net.shengjian.makerone.constant.MessageConst;
import net.shengjian.makerone.constant.NFTExceptionConst;
import net.shengjian.makerone.entity.NFTMarketDTO;
import net.shengjian.makerone.entity.NftCollection;
import net.shengjian.makerone.exception.NFTException;
import net.shengjian.makerone.service.INftCollectionService;
import net.shengjian.makerone.service.INftOrderService;
import net.shengjian.makerone.vo.*;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.base.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 合集模块
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:57:43
 */
@RestController
@RequestMapping(value="/api/nft/collection", method = RequestMethod.POST)
public class NftCollectionController  extends BaseController {
	@Resource
	private INftCollectionService nftCollectionService;
	@Resource
    private INftOrderService nftOrderService;

    /**
     * 创建合集
     * @param nftCollectionVO 请求参数
     * @return 创建成功后的数据,返回null,则创建失败
     */
	@PostMapping("/create")
	public ReturnDatas<NftCollection> create(@RequestBody NftCollectionVO nftCollectionVO){
        ReturnDatas<NftCollection> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            NftCollection nftCollection = nftCollectionService.saveCreateCollection(nftCollectionVO);
            rd.setResult(nftCollection);
            rd.setMessage(MessageConst.CREATE_OK);
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
     * 更新合集
     * @param nftCollectionVO 请求参数
     * @return 更新成功后的数据,返回null,则更新失败
     */
    @PostMapping("/update")
    public ReturnDatas<NftCollection> update(@RequestBody NftCollectionVO nftCollectionVO){
        ReturnDatas<NftCollection> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            NftCollection nftCollection = nftCollectionService.updateCreateCollection(nftCollectionVO);
            rd.setResult(nftCollection);
            rd.setMessage(MessageUtils.UPDATE_SUCCESS);
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
     * 合集的label-value
     * @return 合集的label-value
     */
    @PostMapping("/labelValuePair")
    public ReturnDatas<List<LabelValuePairVO>> labelValuePair(){
        ReturnDatas<List<LabelValuePairVO>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<LabelValuePairVO> data = nftCollectionService.getLabelValuePair(SessionUser.getUserId());
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
     * 获取合集详情
     * @param nftCollectionId
     * @return
     */

    @PostMapping("/colletionDetail")
    public ReturnDatas<CollectionDetailVO> collectionDetail(String nftCollectionId){
        ReturnDatas<CollectionDetailVO> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            CollectionDetailVO data = nftCollectionService.nftCollectionDetail(nftCollectionId);
            rd.setResult(data);
            rd.setMessage("查询成功");
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
     * 合集上架
     * @param vo 参数对象
     * @return 上架结果 true,false
     */
    @PostMapping("/onShelves")
    public ReturnDatas<Boolean> onShelves(@RequestBody InShelfVO vo){
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            if(vo==null){
                throw NFTExceptionConst.PARAMS_IS_NULL;
            }
            Boolean ok = nftCollectionService.updateCollectionOnShelves(vo.getId(), new Date(), vo.getOutTime(), vo.getWaitingTime());
            if(!ok) {
                throw NFTExceptionConst.OPERATION_FAIL;
            }
            rd.setResult(ok);
            rd.setMessage(MessageConst.OPERATION_OK);
        } catch (RuntimeException nftException) {
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
     * 市场合集列表
     * @param searchText 搜索的文本
     * @return 上架结果 true,false
     */
    @PostMapping("/findNftMarketList")
    public ReturnDatas<List<NFTMarketDTO>> findNftMarketList(String searchText){
        ReturnDatas<List<NFTMarketDTO>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<NFTMarketDTO> data = nftCollectionService.findNftMarketList(searchText);
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
     * 返回热门合集
     * @return
     */

    @PostMapping("/findFairCollectionList")
    public ReturnDatas<List<NFTMarketDTO>> findFairCollectionList(@RequestBody Page<NftDetialWorksVO> page){
        ReturnDatas<List<NFTMarketDTO>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<NFTMarketDTO> data = nftCollectionService.findFairMarketList(page);
            rd.setPage(page);
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
     * 返回合集当中作品列表（合计详情页）
     * @param collectionId
     * @param page
     * @return
     */
    @PostMapping("/findWorksInCollection")
    public ReturnDatas<List<NftDetialWorksVO>> findWorksInCollection(String collectionId,@RequestBody Page<NftDetialWorksVO> page){
        ReturnDatas<List<NftDetialWorksVO>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<NftDetialWorksVO> data = nftCollectionService.findWorksInCollection(collectionId,page);
            rd.setResult(data);
            rd.setPage(page);
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

//    @PostMapping("/test")
//    public ReturnDatas<MailVO> test( String worksId,String mail){
//        ReturnDatas<MailVO> rd = ReturnDatas.getSuccessReturnDatas();
//        try {
//            MailVO data =nftOrderService.sendMailAfterBuy(worksId , mail)  ;
//            rd.setResult(data);
//            rd.setMessage(MessageUtils.FIND_SUCCESS);
//        } catch (RuntimeException nftException) {
//            logger.error(nftException.getMessage(), nftException);
//            rd.setStatus(ReturnDatas.ERROR);
//            rd.setMessage(nftException.getMessage());
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            rd.setStatus(ReturnDatas.ERROR);
//            rd.setMessage(MessageConst.UNKNOWN_ERR);
//        }
//        return rd;
//    }
}
