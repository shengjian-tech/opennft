package net.shengjian.makerone.service;

import net.shengjian.frame.util.Page;
import net.shengjian.makerone.dto.SearchWorksResultDTO;
import net.shengjian.makerone.dto.UserCollectionWorksDTO;
import net.shengjian.makerone.entity.NftOrder;
import net.shengjian.makerone.entity.NftWorks;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.enums.EWorksState;
import net.shengjian.makerone.vo.*;
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
 * @version 2021-12-21 17:58:08
 */
@RpcServiceAnnotation
public interface INftWorksService extends IBaseSpringrainService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    NftWorks findNftWorksById(String id) throws Exception;

    /**
     * 保存作品,填充默认值
     *
     * @param nftWorks 保存的数据
     * @return 是否
     * @throws Exception 保存失败的异常
     */
    Boolean saveDefaultValueNftWorks(NftWorks nftWorks) throws Exception;
    /**
     * 更新作品,填充默认值
     *
     * @param nftWorks 更新的数据
     * @return 是否
     * @throws Exception 更新失败的异常
     */
    Boolean updateDefaultValueNftWorks(NftWorks nftWorks) throws Exception;
    /**
     * 创建作品
     *
     * @param nftWorksVO 创建的数据
     * @return 返回创建成功的数据, null则创建失败
     * @throws Exception 创建失败的异常
     */
    NftWorks saveCreateNftWorks(NftWorksVO nftWorksVO) throws Exception;
    /**
     * 更新作品
     *
     * @param nftWorksVO 更新的数据
     * @return 返回更新成功的数据, null则更新失败
     * @throws Exception 更新失败的异常
     */
    NftWorks updateNftWorks(NftWorksVO nftWorksVO) throws Exception;
    /**
     * 更新作品在链平台的上链认证信息
     *
     * @param userId        用户id
     * @param worksId       作品id
     * @param chainPlatCert 链平台上链认证的数据
     * @return 是否更新成功
     * @throws Exception 更新时抛出的异常
     */
    Boolean updateChainPlatCert(String userId, String worksId, EChainPlat eChainPlat, Map<String, Object> chainPlatCert) throws Exception;

    /**
     * 更新作品状态
     *
     * @param worksId     作品id
     * @param eWorksState 状态 枚举
     * @param inTime      上架时间
     * @param outTime     下架时间
     * @param waitingTime 发布等待期
     * @return 是否更新成功
     * @throws Exception 更新失败抛出的异常
     */
    Boolean updateWorksState(String worksId, EWorksState eWorksState, Date inTime, Date outTime, Date waitingTime,BigDecimal price) throws Exception;

    /**
     * 根据作品id上架信息
     * @param order 订单
     * @param worksId 作品id
     * @return 是否上架成功
     * @throws Exception 上架失败抛出的异常
     */
    Boolean updateWorksIn (NftOrder order, String worksId, Date inTime, Date outTime, Date waitingTime, BigDecimal price) throws Exception;

    /**
     * 根据合集id更新上架后的作品在链平台的上链认证信息
     *
     * @param userId        用户id
     * @param collectionId  合集id
     * @param eChainPlat    链平台 枚举
     * @param chainPlatCert 上链认证后的信息
     * @return 是否更新成功
     */
    Boolean updateWorksChainPlatCertByCollectionId(String userId, String collectionId, EChainPlat eChainPlat, Map<String, Object> chainPlatCert) throws Exception;

    /**
     * 根据合集id上架作品
     *
     * @param collectionId 合集id
     * @param inTime       上架时间
     * @param outTime      下架时间
     * @param waitingTime  发布等待期
     * @return 是否发布成功
     * @throws Exception 发布失败抛出的异常
     */
    List<NftWorks> updateInWorksByCollectionId(String collectionId, Date inTime, Date outTime, Date waitingTime) throws Exception;

    /**
     * 作品状态
     * @param worksId 作品id
     * @return 作品状态 枚举
     * @throws Exception 查询错误的异常
     */
    EWorksState worksState(String worksId) throws Exception;

    /**
     * 查询合集id
     * @param worksId 作品id
     * @return 合集id
     * @throws Exception 查询错误的异常
     */
    String findCollectionId(String worksId) throws Exception;


    /**
     * 作品购买详情页
     * @param id
     * @return
     * @throws Exception
     */
    NftworksBuyDetailVO nftworksBuyDetails(String id) throws Exception;

    /**
     * 作品购买头像下方的信息
     * @param id
     * @return
     * @throws Exception
     */
    WorksInfoVO nftworksInfo(String id) throws Exception;

    /**
     * 作品购买历史信息(历史价格和事件信息)
     * @param id
     * @return
     * @throws Exception
     */
    List<NftWorkHisMapVO> nftworksHisPrice(String id , Page<NftWorkHisMapVO> page) throws Exception;

    /**
     * 作品相似
     * @param id
     * @return
     * @throws Exception
     */
    List<WorksSameVO> nftworkSame(String id , Page<WorksSameVO> page) throws  Exception;



    /**
     * 根据作品id查询作者id
     * @param worksId 作品id
     * @return 作者id
     * @throws Exception 查询错误的异常
     */
    String findAuthorIdByWorksId(String worksId) throws Exception;

    /**
     * 减少作品数量
     * @param worksId 作品
     * @param num 数量
     * @return 是否成功
     * @throws Exception 执行错误抛出的异常
     */
    Boolean updateReduceInQuantity(String worksId, Integer num) throws Exception;

    /**
     * 修改作品购买者,如果卖方是作者,则直接在buyers新增一个购买者,如果不是作者,则 buyer.replace(seller,buyer)
     * @param worksId 作品id
     * @param seller 卖方
     * @param buyer 买方
     * @return 是否更新成功
     * @throws Exception 更新错误抛出的异常
     * @deprecated 数据表中作品购买者字段使用不合理,已启用,查询作品购买者,修改成从订单表中检索
     */
    @Deprecated
    Boolean updateBuyers(String worksId,String seller,String buyer) throws Exception;
    /**
     * 根据条件查询作品记录数量
     * @param authorId 作者id
     * @return 数量
     * @throws Exception 异常
     */
    Integer findUserCreateWorksCount(String authorId) throws Exception;
    /**
     * 根据条件查询作品记录数量
     * @param authorId 作者id
     * @param collectionId 合集id
     * @param type 类型
     * @param eWorksState 作品状态
     * @return 数量
     * @throws Exception 异常
     */
    Integer findUserCreateWorksCount(String authorId,String collectionId,Integer type,EWorksState eWorksState) throws Exception;

    /**
     * 根据id查询作品信息
     * @param id
     * @return
     * @throws Exception
     */
    UserCollectionWorksDTO findUserCollectionWorksDTO(String id) throws Exception;
    /**
     * 根据条件查询作品列表
     * @param authorId 作者id
     * @return 数量
     * @throws Exception 异常
     */
    List<NftWorks> findUserCreateWorks(String authorId) throws Exception;
    /**
     * 根据条件查询作品列表
     * @param authorId 作者id
     * @param collectionId 合集id
     * @param type 类型
     * @param eWorksState 作品状态
     * @return 数量
     * @throws Exception 异常
     */
    List<NftWorks> findUserCreateWorks(String authorId,String collectionId,Integer type,EWorksState eWorksState) throws Exception;

    /**
     * 逻辑删除
     * @param worksId 作品id
     * @return 是否执行成功
     * @throws Exception 异常
     */
    Boolean updateLogicDel(String worksId) throws Exception;

    /**
     * 查询一个作品
     * @param worksId 作品id
     * @return 作品信息
     * @throws Exception 异常
     */
    NftWorksVO findWorksById(String worksId) throws Exception;


    /**
     * 搜索作品
     * @param searchText 搜索的文本
     * @return 结果
     * @throws Exception 异常
     */
    List<SearchWorksResultDTO> searchWorks(String searchText) throws Exception;

    /**
     * 返回作品持有人
     */
    List<String> findWorkBuyers(String worksId ,  Page<String> page) throws Exception;
}
