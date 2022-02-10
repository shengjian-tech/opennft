package net.shengjian.makerone.api;

import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.property.MessageUtils;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.constant.MessageConst;
import net.shengjian.makerone.constant.NFTExceptionConst;
import net.shengjian.makerone.dto.SearchWorksResultDTO;
import net.shengjian.makerone.entity.NftWorks;
import net.shengjian.makerone.exception.NFTException;
import net.shengjian.makerone.service.INftWorksService;
import net.shengjian.makerone.vo.*;
import net.shengjian.makerone.vo.NftWorksVO;
import net.shengjian.system.base.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作品模块
 *
 * @author springrain<Auto generate>
 * @version 2021-12-21 17:58:08
 */
@RestController
@RequestMapping(value = "/api/nft/works", method = RequestMethod.POST)
public class NftWorksController extends BaseController {
    @Resource
    private INftWorksService nftWorksService;

    /**
     * 查询一个作品
     * @param worksId 作品id
     * @return 响应创建成功后的数据,null则创建失败
     */
    @PostMapping("/findWorks")
    public ReturnDatas<NftWorksVO> findWorks(String worksId) {
        ReturnDatas<NftWorksVO> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            NftWorksVO data = nftWorksService.findWorksById(worksId);
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
     * 搜索作品
     * @param searchText 检索的文本
     */
    @PostMapping("/searchWorks")
    public ReturnDatas<List<SearchWorksResultDTO>> searchWorks(String searchText) {
        ReturnDatas<List<SearchWorksResultDTO>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<SearchWorksResultDTO> data = nftWorksService.searchWorks(searchText);
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
     * 创建作品
     * @param nftWorksVO 请求数据
     * @return 响应创建成功后的数据,null则创建失败
     */
    @PostMapping("/createWorks")
    public ReturnDatas<NftWorks> createWorks(@RequestBody NftWorksVO nftWorksVO) {
        ReturnDatas<NftWorks> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            NftWorks data = nftWorksService.saveCreateNftWorks(nftWorksVO);
            rd.setResult(data);
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
     * 更新作品
     * @param nftWorksVO 请求数据
     * @return 响应更新成功后的数据
     */
    @PostMapping("/updateWorks")
    public ReturnDatas<NftWorks> updateWorks(@RequestBody NftWorksVO nftWorksVO) {
        ReturnDatas<NftWorks> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            NftWorks data = nftWorksService.updateNftWorks(nftWorksVO);
            rd.setResult(data);
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
     * 返回作品购买详情页
     * @param worksId
     * @return
     */
    @PostMapping("/buyWorks")
    public ReturnDatas<NftworksBuyDetailVO> buyWorks(String worksId) {
        ReturnDatas<NftworksBuyDetailVO> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            NftworksBuyDetailVO data = nftWorksService.nftworksBuyDetails(worksId);
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
     * 合集详情头像下方合集简介、链信息、标签信息等
     * @param worksId
     * @return
     */

    @PostMapping("/buyWorksInfo")
    public ReturnDatas<WorksInfoVO> buyWorksInfo(String worksId) {
        ReturnDatas<WorksInfoVO> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            WorksInfoVO data = nftWorksService.nftworksInfo(worksId);
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
     * 返回作品购买历史信息
     * @param worksId
     * @return
     */

    @PostMapping("/buyWorksHis")
    public ReturnDatas<List<NftWorkHisMapVO>> buyWorksHis(String worksId,@RequestBody Page<NftWorkHisMapVO> page) {
        ReturnDatas<List<NftWorkHisMapVO>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<NftWorkHisMapVO> data = nftWorksService.nftworksHisPrice(worksId,page);
            rd.setPage(page);
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
     * 返回作品相似信息
     * @param worksId
     * @return
     */
    @PostMapping("/buyWorksSame")
    public ReturnDatas<List<WorksSameVO>> buyWorksSame(String worksId ,@RequestBody Page<WorksSameVO> page) {
        ReturnDatas<List<WorksSameVO>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<WorksSameVO> data = nftWorksService.nftworkSame(worksId,page);
            rd.setPage(page);
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
     * 上架作品
     * @param vo 参数对象
     * @return 响应上架结果数据,true,false
     */
    /*@PostMapping("/updateWorksIn")
    public ReturnDatas<Boolean> updateWorksIn(@RequestBody InShelfVO vo){
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            if(vo==null){
                throw NFTExceptionConst.PARAMS_IS_NULL;
            }
            Boolean ok = nftWorksService.updateWorksIn(vo.getId(), new Date(), vo.getOutTime(), vo.getWaitingTime(), vo.getPrice());
            if(!ok) {
                throw NFTExceptionConst.OPERATION_FAIL;
            }
            rd.setResult(ok);
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
     * 逻辑删除
     * @param worksId 作品id
     * @return 是否执行成功
     */
    @PostMapping("/logicDel")
    public ReturnDatas<Boolean> loginDel(String worksId){
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            Boolean ok = nftWorksService.updateLogicDel(worksId);
            if(!ok) {
                throw NFTExceptionConst.OPERATION_FAIL;
            }
            rd.setResult(ok);
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
     * 返回作品持有人信息
     * @param worksId
     * @return
     */
    @PostMapping("/findWorkBuyers")
    public ReturnDatas<List<String>> findWorkBuyers(String worksId ,@RequestBody  Page<String> page) {
        ReturnDatas<List<String>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<String> data = nftWorksService.findWorkBuyers(worksId , page);
            rd.setPage(page);
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
     * 获取商品转移冷却期 天
     * @return data
     */
    @PostMapping("/getCoolingPeriod")
    public ReturnDatas<String> getCoolingPeriod(){
        ReturnDatas<String> rd = ReturnDatas.getSuccessReturnDatas();
        Integer day=Integer.parseInt(CommonConst.TRADING_COOLING_PERIOD)/60/60/24;
        rd.setResult(day.toString());
        return rd;
    }
}
