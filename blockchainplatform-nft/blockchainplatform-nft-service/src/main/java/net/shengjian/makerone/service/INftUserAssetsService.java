package net.shengjian.makerone.service;

import net.shengjian.frame.util.Page;
import net.shengjian.makerone.dto.NftUserDetails;
import net.shengjian.makerone.dto.UserCollectionDTO;
import net.shengjian.makerone.dto.UserCollectionWorksDTO;
import net.shengjian.makerone.entity.NftUserAssets;
import net.shengjian.makerone.enums.EAssertsOrigin;
import net.shengjian.makerone.enums.EAssetsSellState;
import net.shengjian.makerone.enums.EAssetsType;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.service.IBaseSpringrainService;

import java.util.List;

/**
 * TODO 在此加入类描述
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:02
 */
@RpcServiceAnnotation
public interface INftUserAssetsService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	NftUserAssets findNftUserAssetsById(String id) throws Exception;
    /**
     * 根据ID查询用户藏品拥有的数量
     * @param userId 用户id
     * @return 数量
     * @throws Exception 查询错误抛出的异常
     */
    Integer findUserAssetsNum(String userId) throws Exception;
    /**
     * 根据ID查询用户藏品拥有的数量
     * @param userId 用户id
     * @param assetsId 资源id
     * @return 数量
     * @throws Exception 查询错误抛出的异常
     */
    Integer findUserAssetsNum(String userId, String assetsId) throws Exception;
    /**
     * 根据ID查询用户藏品拥有的数量
     * @param userId 用户id
     * @param assetsId 资源id
     * @param eAssetsType 资源类型 枚举
     * @return 数量
     * @throws Exception 查询错误抛出的异常
     */
	Integer findUserAssetsNum(String userId, String assetsId, EAssetsType eAssetsType) throws Exception;

    /**
     * 根据ID查询用户藏品拥有的数量
     * @param userId 用户id
     * @param assetsId 资源id
     * @param eAssetsType 资源类型 枚举
     * @param eAssetsSellState 出售状态 枚举
     * @return 数量
     * @throws Exception 查询错误抛出的异常
     */
    Integer findUserAssetsNum(String userId, String assetsId, EAssetsType eAssetsType,EAssetsSellState eAssetsSellState) throws Exception;

    /**
     * 根据条件查询用户藏品的id列表
     * @param userId 用户id
     * @param eAssetsType 资源类型 枚举
     * @return 数量
     * @throws Exception 查询错误抛出的异常
     */
    List<NftUserAssets> findUserAssets(String userId, EAssetsType eAssetsType) throws Exception;
    /**
     * 根据条件查询用户藏品的id列表
     * @param page page
     * @param userId 用户id
     * @param eAssetsType 资源类型 枚举
     * @return 数量
     * @throws Exception 查询错误抛出的异常
     */
    List<NftUserAssets> findUserAssets(Page page,String userId, EAssetsType eAssetsType) throws Exception;
    /**
     * 根据条件查询用户藏品的id列表
     * @param userId 用户id
     * @param eAssetsType 资源类型 枚举
     * @param assertsOrigin 作品来源
     * @return 数量
     * @throws Exception 查询错误抛出的异常
     */
    List<NftUserAssets> findUserAssets(String userId, EAssetsType eAssetsType, EAssertsOrigin assertsOrigin) throws Exception;
    /**
     * 根据条件查询用户藏品的id列表
     * @param userId 用户id
     * @param assetsId 资源id
     * @param eAssetsType 资源类型 枚举
     * @param eAssetsSellState 出售状态 枚举
     * @param assertsOrigin 作品来源
     * @return 数量
     * @throws Exception 查询错误抛出的异常
     */
    List<NftUserAssets> findUserAssets(Page page,String userId, String assetsId, EAssetsType eAssetsType, EAssetsSellState eAssetsSellState, EAssertsOrigin assertsOrigin) throws Exception;
    /**
     * 新增用户资产,填充默认值
     * @param nftUserAssets 资产信息
     * @return 是否新增成功
     * @throws Exception 新增错误的异常
     */
	Boolean saveDefaultNftUserAssets(NftUserAssets nftUserAssets) throws Exception;

    /**
     * 新增用户资产,填充默认值,批量
     * @param nftUserAssetsList 资产信息
     * @return 是否新增成功
     * @throws Exception 新增错误的异常
     */
    Boolean saveDefaultNftUserAssetsBatch(List<NftUserAssets> nftUserAssetsList) throws Exception;

    /**
     * 更新资产的归属用户(由状态1变为状态2)
     * @param assetsSellState1 状态1
     * @param assetsSellState2 状态2
     * @param assetsId 资产id
     * @param seller 用户id
     * @param buyer 用户id
     * @param num 更新的条数
     * @return 是否更新成功
     * @throws Exception 更新失败抛出的异常
     */
    Integer updateAssetsUser(EAssetsSellState assetsSellState1,EAssetsSellState assetsSellState2,String assetsId,String seller,String buyer,Integer num) throws Exception;

    /**
     * 修改资产出售状态
     * @param userId 用户id
     * @param assetsId 资产id
     * @param eAssetsSellState 资产状态 枚举
     * @param num 更新的条数
     * @return 是否更新成功
     * @throws Exception 更新失败抛出的异常
     */
	Integer updateAssetsSellStateByAssetsId(String userId, String assetsId, EAssetsSellState eAssetsSellState,Integer num) throws Exception;

    /**
     * 查询用户资产数量详情
     * @return 数量详情数据
     * @throws Exception 查询失败抛出的异常
     */
    NftUserDetails findUserDetails() throws Exception;

    /**
     * 查询用户收藏品
     * @param chainPlat 链平台
     * @param userId 用户id
     * @param page 分页对象
     * @return 收藏品列表
     * @throws Exception 异常
     */
    List<UserCollectionWorksDTO> findCollectionWorks(EChainPlat chainPlat,String userId, Page<UserCollectionWorksDTO> page) throws Exception;

    /**
     * 查询用户创建的收藏品
     * @param userId 用户id
     * @return 收藏品列表
     * @throws Exception 异常
     */
    List<UserCollectionWorksDTO> findUserCreateWorks(String userId) throws Exception;

    /**
     * 查询用户合集列表
     * @param userId 用户id
     * @return 列表
     * @throws Exception 异常
     */
    List<UserCollectionDTO> findUserCollection(String userId) throws Exception;

    /**
     * 根据作品id删除资产
     * @param worksId 作品id
     * @throws Exception 异常
     */
    Integer deleteAssertByWorkId(String userId,String worksId) throws Exception;

    /**
     * 逻辑删除
     * @param id 主键
     * @return 是否执行成功
     * @throws Exception 异常
     */
    Boolean updateLogicDel(String id) throws Exception;

    /**
     * 查询资产交易剩余天数
     * @param userId 用戶id
     * @param assetsId 资产
     * @param chainPlat 链平台
     * @return 天数
     * @throws Exception 异常
     */
    Long findUserAssetsLastDay(String userId,String assetsId, EChainPlat chainPlat) throws Exception;

    /**
     * 转移资产
     * @param chainPlat 链平台
     * @param fromUserId 转移人
     * @param toAddress 转移到的地址
     * @param assetsId 转移的资产id
     * @param verificationCode 手机验证码
     * @return true|false 是否转移成功
     * @throws Exception 异常
     */
    Boolean updateTransferAssets(EChainPlat chainPlat,String fromUserId,String toAddress,String assetsId,String verificationCode) throws Exception;
}
