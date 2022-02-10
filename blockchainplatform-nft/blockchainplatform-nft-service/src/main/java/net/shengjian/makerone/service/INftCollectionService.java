package net.shengjian.makerone.service;

import net.shengjian.frame.util.Page;
import net.shengjian.makerone.entity.NFTMarketDTO;
import net.shengjian.makerone.entity.NftCollection;
import net.shengjian.makerone.vo.*;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.vo.LabelValuePairVO;
import net.shengjian.makerone.vo.NftCollectionVO;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.service.IBaseSpringrainService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2021-12-21 17:57:43
 */
@RpcServiceAnnotation
public interface INftCollectionService extends IBaseSpringrainService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    NftCollection findNftCollectionById(String id) throws Exception;

    /**
     * 保存合集,填充默认值
     *
     * @param nftCollection 保存的数据
     * @return 是否保存成功
     * @throws Exception 保存失败的异常
     */
    Boolean saveDefaultValueCollection(NftCollection nftCollection) throws Exception;

    /**
     * 更新合集,填充默认值
     *
     * @param nftCollection 更新的数据
     * @return 是否更新成功
     * @throws Exception 更新失败的异常
     */
    Boolean updateDefaultValueCollection(NftCollection nftCollection) throws Exception;

    /**
     * 创建合集
     *
     * @param nftCollectionVO 保存的数据
     * @return 保存后的对象
     * @throws Exception 保存失败的异常
     */
    NftCollection saveCreateCollection(NftCollectionVO nftCollectionVO) throws Exception;

    /**
     * 更新合集
     *
     * @param nftCollectionVO 更新的数据
     * @return 更新后的对象
     * @throws Exception 更新失败的异常
     */
    NftCollection updateCreateCollection(NftCollectionVO nftCollectionVO) throws Exception;

    /**
     * 是否存在合集名称
     *
     * @param collectionName 合集名称
     * @return 返回是否
     */
    Boolean isCollectionNameExist(String collectionName) throws Exception;

    /**
     * @return 合集的 label-value
     * @throws Exception 查询失败的异常
     */
    List<LabelValuePairVO> getLabelValuePair(String authorId) throws Exception;

	/**
	 *
	 * @param id
	 * @return 合集的详细信息
	 * @throws Exception
	 */
	CollectionDetailVO nftCollectionDetail(String id) throws Exception;


    /**
     * 更新上架状态
     *
     * @param collectionId 合集id
     * @param isIn         是否上架 0:否,1:是
     * @param inTime       上架时间
     * @param outTime      下架时间
     * @return 是否更新成功
     * @throws Exception 更新时抛出的异常
     */
    Boolean updateIsIn(String collectionId, int isIn, Date inTime, Date outTime) throws Exception;

    /**
     * 更新合集在链平台的上链认证信息
     * @param userId 用户id
     * @param collectionId  合集id
     * @param chainPlatCert 链平台交易的信息
     * @param eChainPlat 链平台枚举
     * @return 是否更新成功
     * @throws Exception 更新时抛出的异常
     */
    Boolean updateChainPlatCert(String userId,String collectionId, EChainPlat eChainPlat, Map<String, Object> chainPlatCert) throws Exception;

    /**
     * 合集上架,
     *
     * @param collectionId 合集id
     * @param inTime 上架时间
     * @param outTime 下架时间
     * @param waitingTime 合集内的作品发布等待时间
     * @return 是否上架成功
     * @throws Exception 上架错误时抛出的异常
     */
    Boolean updateCollectionOnShelves(String collectionId, Date inTime, Date outTime,Date waitingTime) throws Exception;

    /**
     * 合集是否存在
     * @param collectionId 合集id
     * @return 是否
     */
    Boolean isExistById(String collectionId) throws Exception;

    /**
     * 合集是否已上架
     * @param collectionId 合集id
     * @return 是否
     * @throws Exception 查询错误抛出的异常
     */
    Boolean isIn(String collectionId) throws Exception;

    /**
     * 根据作品id查询所在的合集信息
     * @param worksId 作品id
     * @return 合集信息
     * @throws Exception 查询错误抛出的异常
     */
    NftCollection findByWorksId(String worksId) throws Exception;

    /**
     * 根据合集id查询合集版税
     * @param collectionId 合集id
     * @return 版税
     * @throws Exception
     */
    BigDecimal findRoyaltiesById(String collectionId) throws Exception;
    /**
     * 查询用户的合集列表
     * @param userId 用户id
     * @return 列表
     * @throws Exception 异常
     */
    List<NftCollection> findListByUserId(String userId) throws Exception;
    /**
     * 查询用户的合集列表
     * @param userId 用户id
     * @param type 类型
     * @param isIn 是否上架
     * @param isCert 是否平台认证
     * @return 列表
     * @throws Exception 异常
     */
    List<NftCollection> findListByUserId(String userId,Integer type,Integer isIn,Integer isCert) throws Exception;

    /**
     * 市场列表搜索
     * @param searchText 搜索的文本
     * @return
     * @throws Exception
     */
    List<NFTMarketDTO> findNftMarketList(String searchText) throws Exception;

    /**
     * 通过合集名称搜索合集列表
     * @param collectionName 合集名称
     * @return
     * @throws Exception
     */
    List<NFTMarketDTO> findNftMarketListByCollectionName(String collectionName) throws Exception;

    /**
     * 返回热门合集
     * @return
     * @throws Exception
     */

    List<NFTMarketDTO> findFairMarketList(Page<NftDetialWorksVO> page) throws Exception;


    /**
     * 返回交易总数（热度值）、交易总额给合集
     */
    NftCollection saveFireAndPriceToCollection(String workId,BigDecimal price) throws Exception;

    /**
     * 根据作品id返回合集
     */
    NftCollection findCollectionIdByWorkId(String workId) throws Exception;

    /**
     * 计算涨跌幅以及地板价并保存到合集
     */
    List<NftCollection> saveWorksChangesAndLowPrice() throws Exception;


    /**
     * 合集中作品列表
     */
    List<NftDetialWorksVO> findWorksInCollection(String collectionId ,Page<NftDetialWorksVO> page) throws Exception;




}
