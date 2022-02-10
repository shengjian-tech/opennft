package net.shengjian.makerone.service.impl;

import com.baidu.xuper.api.Account;
import net.shengjian.frame.cache.RedisOperation;
import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.frame.util.Finder;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.makerone.constant.CachePrefixConst;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.constant.NFTExceptionConst;
import net.shengjian.makerone.dto.NftUserDetails;
import net.shengjian.makerone.dto.UserChainplatInfoDTO;
import net.shengjian.makerone.dto.UserCollectionDTO;
import net.shengjian.makerone.dto.UserCollectionWorksDTO;
import net.shengjian.makerone.entity.*;
import net.shengjian.makerone.enums.*;
import net.shengjian.makerone.exception.NFTException;
import net.shengjian.makerone.service.*;
import net.shengjian.makerone.strategy.context.ChainExecStrategyContext;
import net.shengjian.makerone.utils.PathUtil;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.entity.User;
import net.shengjian.system.service.IUserService;
import net.shengjian.system.service.impl.BaseSpringrainServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO 在此加入类描述
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:02
 */

@Service("nftUserAssetsService")
public class NftUserAssetsServiceImpl extends BaseSpringrainServiceImpl implements INftUserAssetsService {

    @Resource
    private INftWorksService nftWorksService;

    @Resource
    private INftUserChainplatService nftUserChainplatService;

    @Resource
    private INftOrderService nftOrderService;

    @Resource
    private INftCollectionService nftCollectionService;

    @Resource
    private ChainExecStrategyContext chainExecStrategyContext;

    @Resource
    private INftUserAssetsService nftUserAssetsService;

    @Resource
    private INftWorksHisService nftWorksHisService;

    @Resource
    private IUserService userService;

    @Resource
    private RedisOperation redisOperation;

    @Value("${staticdir}")
    private String staticdir;

    @Override
	public String  save(IBaseEntity entity ) throws Exception{
	    NftUserAssets nftUserAssets=(NftUserAssets) entity;
	    return super.save(nftUserAssets).toString();
	}


	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
		NftUserAssets nftUserAssets=(NftUserAssets) entity;
		return super.update(nftUserAssets);
    }
	
    @Override
	public NftUserAssets findNftUserAssetsById(String id) throws Exception{
		return super.findById(id,NftUserAssets.class);
	}

    @Override
    public Integer findUserAssetsNum(String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        return this.findUserAssetsNum(userId,null,null,null);
    }

    @Override
    public Integer findUserAssetsNum(String userId, String assetsId) throws Exception {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(assetsId) ) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        return this.findUserAssetsNum(userId,assetsId,null,null);
    }

    @Override
    public Integer findUserAssetsNum(String userId, String assetsId, EAssetsType eAssetsType) throws Exception {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(assetsId) || eAssetsType == null) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        return this.findUserAssetsNum(userId,assetsId,eAssetsType,null);
    }

    @Override
    public Integer findUserAssetsNum(String userId, String assetsId, EAssetsType eAssetsType, EAssetsSellState eAssetsSellState) throws Exception {

        Finder selectFinder = Finder.getSelectFinder(NftUserAssets.class, " count(1) ")
                .append(" WHERE userId=:userId ")
                .setParam("userId", userId);
        if (StringUtils.isNotBlank(assetsId)){
            selectFinder.append(" AND assetsId=:assetsId ").setParam("assetsId", assetsId);
        }
        if (eAssetsType != null){
            selectFinder.append(" AND type=:type ").setParam("type", eAssetsType.getCode());
        }
        if (eAssetsSellState != null){
            selectFinder.append(" AND sellState=:sellState ").setParam("sellState", eAssetsSellState.getCode());
        }
        Integer num = super.queryForObject(selectFinder, Integer.class);
        return num==null?0:num;
    }

    @Override
    public List<NftUserAssets> findUserAssets(String userId, EAssetsType eAssetsType) throws Exception {
        return this.findUserAssets(userId,eAssetsType,null);
    }

    @Override
    public List<NftUserAssets> findUserAssets(Page page, String userId, EAssetsType eAssetsType) throws Exception {
        return this.findUserAssets(page,userId,null,eAssetsType,null,null);
    }

    @Override
    public List<NftUserAssets> findUserAssets(String userId, EAssetsType eAssetsType, EAssertsOrigin assertsOrigin) throws Exception {
        return this.findUserAssets(null,userId,null,eAssetsType,null,assertsOrigin);
    }

    @Override
    public List<NftUserAssets> findUserAssets(Page page,String userId, String assetsId, EAssetsType eAssetsType, EAssetsSellState eAssetsSellState, EAssertsOrigin assertsOrigin) throws Exception {
        Finder selectFinder = Finder.getSelectFinder(NftUserAssets.class)
                .append(" WHERE 1=1 ");

        if (StringUtils.isNotBlank(userId)){
            selectFinder.append(" AND userId=:userId ").setParam("userId", userId);
        }
        if (StringUtils.isNotBlank(assetsId)){
            selectFinder.append(" AND assetsId=:assetsId ").setParam("assetsId", assetsId);
        }
        if (eAssetsType != null){
            selectFinder.append(" AND type=:type ").setParam("type", eAssetsType.getCode());
        }
        if (eAssetsSellState != null){
            selectFinder.append(" AND sellState=:sellState ").setParam("sellState", eAssetsSellState.getCode());
        }
        if (assertsOrigin != null){
            selectFinder.append(" AND origin=:origin ").setParam("origin", assertsOrigin.getCode());
        }
        List<NftUserAssets> list;
        if(page!=null){
            list = super.queryForList(selectFinder, NftUserAssets.class,page);
        }else{
            list = super.queryForList(selectFinder, NftUserAssets.class);
        }
        return list==null?new ArrayList<>():list;
    }

    @Override
    public Boolean saveDefaultNftUserAssets(NftUserAssets nftUserAssets) throws Exception {
        if (nftUserAssets==null){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        nftUserAssets.setId(SecUtils.getTimeNO());
        nftUserAssets.setSellState(EAssetsSellState.不出售.getCode());
        nftUserAssets.setCreateTime(new Date());
        super.save(nftUserAssets);
        return true;
    }

    @Override
    public Boolean saveDefaultNftUserAssetsBatch(List<NftUserAssets> nftUserAssetsList) throws Exception {
        if (CollectionUtils.isEmpty(nftUserAssetsList)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        for (NftUserAssets item : nftUserAssetsList) {
            item.setId(SecUtils.getTimeNO());
            item.setSellState(EAssetsSellState.不出售.getCode());
            item.setCreateTime(new Date());
        }
        super.save(nftUserAssetsList);
        return true;
    }

    @Override
    public Integer updateAssetsUser(EAssetsSellState assetsSellState1,EAssetsSellState assetsSellState2,String assetsId, String seller,String buyer,Integer num) throws Exception {
        if (assetsSellState2==null || StringUtils.isBlank(assetsId) || StringUtils.isBlank(seller) || StringUtils.isBlank(buyer) || num == null) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        if (num==0) {
            return 0;
        }

        String authorId = nftWorksService.findAuthorIdByWorksId(assetsId);
        EAssertsOrigin eAssertsOrigin = EAssertsOrigin.购买其他作者;
        if(buyer.equals(authorId)){
            eAssertsOrigin=EAssertsOrigin.自己创作;
        }

        Finder updateFinder = Finder.getUpdateFinder(NftUserAssets.class)
                .append(" userId=:buyer,updateTime=:updateTime,origin=:origin,sellState=:sellState2 ")
                .append(" WHERE userId=:seller AND assetsId=:assetsId ")
                .setParam("updateTime",new Date())
                .setParam("buyer",buyer)
                .setParam("seller",seller)
                .setParam("origin", eAssertsOrigin.getCode())
                .setParam("assetsId",assetsId)
                .setParam("sellState2",assetsSellState2.getCode());
        if(assetsSellState1!=null){
            updateFinder.append(" AND sellState=:sellState1 ").setParam("sellState1",assetsSellState1.getCode());
        }else {
            updateFinder.append(" AND sellState!=:sellState1 ").setParam("sellState1",EAssetsSellState.已生成订单.getCode());
        }
        updateFinder.append(" limit " + num);
        updateFinder.setEscapeSql(false);
        return super.update(updateFinder);
    }

    @Override
    public Integer updateAssetsSellStateByAssetsId(String userId, String assetsId, EAssetsSellState eAssetsSellState, Integer num) throws Exception {
        if(StringUtils.isBlank(userId)||StringUtils.isBlank(assetsId)||eAssetsSellState==null||num==null){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        if (num==0){
            return 0;
        }
        Finder updateFinder = Finder.getUpdateFinder(NftUserAssets.class)
                .append(" sellState=:sellState,updateTime=:updateTime ")
                .append(" WHERE userId=:userId AND assetsId=:assetsId AND sellState!=:sellState ")
                .setParam("sellState",eAssetsSellState.getCode())
                .setParam("updateTime",new Date())
                .setParam("userId",userId)
                .setParam("assetsId",assetsId);
        //如果要生成订单,须要当前状态是售卖中
        if(eAssetsSellState.getCode().equals(EAssetsSellState.已生成订单.getCode())){
            updateFinder.append(" AND sellState=:sellState2")
                    .setParam("sellState2",EAssetsSellState.售卖中.getCode());
        }
        updateFinder.append(" limit "+num);
        updateFinder.setEscapeSql(false);
        return super.update(updateFinder);
    }

    @Override
    public NftUserDetails findUserDetails() throws Exception {
        final String userId = SessionUser.getUserId();
        User byId = super.findById(userId, User.class);
        if(byId==null){
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        String userName = byId.getUserName();
        String avatar = byId.getAvatar();
        UserChainplatInfoDTO ucp = nftUserChainplatService.findUserChainPlatInfo(userId, EChainPlat.百度超级链);
        if(ucp==null){
            throw NFTExceptionConst.USER_NOT_CHAIN_PLAT_ACCOUNT;
        }
        String address=PathUtil.readPath(staticdir+ucp.getAddressPath());
        Integer assetsCount = this.findUserAssetsNum(userId,null, EAssetsType.作品,null);

        List<NftUserAssets> userAssets = this.findUserAssets(userId, EAssetsType.作品);
        Set<String> assetsIdList = userAssets.stream().map(NftUserAssets::getAssetsId).collect(Collectors.toSet());
        Integer count=0;
        Map<String,Object> args = new HashMap<>();
        args.put("account",ucp.getEVMAddress());
        for (String assetsId : assetsIdList) {
            args.put("id",assetsId);
            Map<String, String> map = chainExecStrategyContext.rootQuery(EChainPlat.百度超级链
                    , CommonConst.getNftContractName()
                    ,"balanceOf"
                    ,args);
            if(map==null){
                continue;
            }
            List<Map> bodyStr = JsonUtils.readValueList(map.get("bodyStr"), Map.class);
            if(CollectionUtils.isEmpty(bodyStr)){
                continue;
            }
            Object o = bodyStr.get(0).get("0");
            if(o==null){
                continue;
            }
            int balance = Integer.parseInt(o.toString());
            count+=balance;
        }
        if(!assetsCount.equals(count)){
            logger.error("资产数量余额异常!");
        }



        Integer createCount = nftWorksService.findUserCreateWorksCount(userId);
        Integer collectionCount = this.findUserAssetsNum(userId, null, EAssetsType.合集,null);
        Integer orderCount = nftOrderService.findUserOrderCount(userId);

        NftUserDetails d = new NftUserDetails();
        d.setUserId(userId);
        d.setUserName(userName);
        d.setAvatarPath(avatar);
        d.setAddress(address);
        d.setEVMAddress(ucp.getEVMAddress());
        d.setPhone(byId.getMobile());
        d.setJoinDate(byId.getCreateTime());
        d.setAssetsCount(assetsCount);
        d.setCreateCount(createCount);
        d.setCollectionCount(collectionCount);
        d.setTransCount(orderCount);
        d.setLikeCount(0);
        return d;
    }

    @Override
    public List<UserCollectionWorksDTO> findCollectionWorks(EChainPlat chainPlat,String userId, Page<UserCollectionWorksDTO> page) throws Exception {
        if(StringUtils.isBlank(userId)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        if(page==null){
            throw new NFTException("请传入分页信息!");
        }
        List byCache = super.getByCache(CachePrefixConst.USER_ASSETS, userId, List.class, page);
        if(CollectionUtils.isNotEmpty(byCache)){
            return byCache;
        }
        List<UserCollectionWorksDTO> dtoList = new ArrayList<>();
        List<NftUserAssets> userAssets = this.findUserAssets(page,userId, EAssetsType.作品);

        Map<String,Long> inquiredTokenId = new HashMap<>();
        for (NftUserAssets assets : userAssets) {
            UserCollectionWorksDTO dto = nftWorksService.findUserCollectionWorksDTO(assets.getAssetsId());
            if (dto==null){
                continue;
            }
            dtoList.add(dto);
            final String tokenId = assets.getAssetsId();
            dto.setId(tokenId);
            //查询计算合约内交易的剩余天数
            if(!inquiredTokenId.containsKey(tokenId)){
                inquiredTokenId.put(tokenId,findUserAssetsLastDay(userId,tokenId,chainPlat));
            }
            Long day = inquiredTokenId.get(tokenId);
            dto.setDay(day);
        }
        if(CollectionUtils.isNotEmpty(dtoList)){
            super.putByCache(CachePrefixConst.USER_ASSETS,userId,dtoList,page);
        }
        return dtoList;
    }

    @Override
    public List<UserCollectionWorksDTO> findUserCreateWorks(String userId) throws Exception {
        if(StringUtils.isBlank(userId)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        List<UserCollectionWorksDTO> dtoList = new ArrayList<>();
        List<NftWorks> userCreateWorks = nftWorksService.findUserCreateWorks(userId);
        for (NftWorks works : userCreateWorks) {
            UserCollectionWorksDTO dto = new UserCollectionWorksDTO();
            BeanUtils.copyProperties(works,dto);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<UserCollectionDTO> findUserCollection(String userId) throws Exception {
        if(StringUtils.isBlank(userId)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        List<UserCollectionDTO> dtoList = new ArrayList<>();
        List<NftCollection> list = nftCollectionService.findListByUserId(userId);
        for (NftCollection c : list) {
            UserCollectionDTO dto = new UserCollectionDTO();
            BeanUtils.copyProperties(c,dto);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public Integer deleteAssertByWorkId(String userId,String worksId) throws Exception {
        if(StringUtils.isBlank(worksId)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder deleteFinder = Finder.getDeleteFinder(NftUserAssets.class)
                .append(" WHERE assetsId=:assetsId AND userId=:userId AND sellState=:sellState")
                .setParam("assetsId", worksId)
                .setParam("userId", userId)
                .setParam("sellState",EAssetsSellState.不出售.getCode());
        return super.update(deleteFinder);
    }

    @Override
    public Boolean updateLogicDel(String id) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder updateFinder = Finder.getUpdateFinder(NftUserAssets.class, " sellState=:sellState,updateTime=:updateTime ")
                .append(" WHERE userId=:userId AND id=:id AND sellState=:sellState1 ")
                .setParam("userId",SessionUser.getUserId())
                .setParam("sellState", EAssetsSellState.已删除.getCode())
                .setParam("updateTime", new Date())
                .setParam("id", id)
                .setParam("sellState1", EAssetsSellState.不出售.getCode());
        Integer update = super.update(updateFinder);
        return update > 0;
    }

    @Override
    public Long findUserAssetsLastDay(String userId,String assetsId, EChainPlat chainPlat) throws Exception {
        if(StringUtils.isBlank(assetsId) || chainPlat==null){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        UserChainplatInfoDTO ucpi = nftUserChainplatService.findUserChainPlatInfo(userId, chainPlat);
        if(ucpi==null){
            return null;
        }
        Map<String,Object> args = new HashMap<>();
        args.put("_id",assetsId);
        Map<String, String> getTokenExpireTime = chainExecStrategyContext.query(chainPlat
                , staticdir+ucpi.getPrivatePath()
                , ucpi.getPasswd()
                , CommonConst.getNftContractName()
                , "getTokenExpireTime"
                , args);
        if (getTokenExpireTime == null) {
            return null;
        }
        try {
            String bodyStr = getTokenExpireTime.get("bodyStr");///[{"0":"1642569076"}]
            final List<Map> maps = JsonUtils.readValueList(bodyStr, Map.class);
            String time = maps.get(0).get("0").toString();
            long timestamp = Long.parseLong(time);//交易日期时间戳
            if(timestamp<=0){
                return 0L;
            }
            Instant instant = Instant.ofEpochMilli(timestamp);
            LocalDateTime d1 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

            //合约时间戳
            Instant instant2 = Instant.ofEpochMilli(CommonConst.CONTRACT_NOW_TIMESTAMP);
            LocalDateTime d2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault());

            Duration between = Duration.between(d1, d2);
            long rs = (Integer.parseInt(CommonConst.TRADING_COOLING_PERIOD)/60/60/24)- between.toDays();
            return rs<0?0:rs;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public Boolean updateTransferAssets(EChainPlat chainPlat,String fromUserId, String toAddress, String assetsId, String verificationCode) throws Exception {
        if(StringUtils.isBlank(fromUserId) || StringUtils.isBlank(toAddress) ||StringUtils.isBlank(assetsId)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Boolean ok = nftUserChainplatService.havePrivateKeyAndAddressAndPasswd(chainPlat, fromUserId);
        if(!ok){
            throw NFTExceptionConst.USER_NOT_CHAIN_PLAT_ACCOUNT;
        }
        String toUserId = nftUserChainplatService.findUserIdByAddress(chainPlat,toAddress);
        if(StringUtils.isBlank(toUserId)){
            throw new NFTException("转向的地址该本平台未注册账户,请先在本平台完善转向的地址!");
        }
        Integer assetsNum = 1;

        //作者转移作品
        String authorIdByWorksId = nftWorksService.findAuthorIdByWorksId(assetsId);
        if(fromUserId.equals(authorIdByWorksId)){
            //库存锁
            final Long atomicLong = redisOperation.getAtomicLong(CachePrefixConst.LOCK_PREFIX+assetsId, Long.parseLong((-assetsNum)+""));
            if(atomicLong<0){
                redisOperation.getAtomicLong(CachePrefixConst.LOCK_PREFIX+assetsId,Long.parseLong(assetsNum+""));
                throw NFTExceptionConst.WORKS_NUM_INSUFFICIENT;
            }
        }
        String userPhone = userService.getUserPhone(fromUserId);
        userService.verifyPhoneCode(userPhone,verificationCode);

        Integer num = nftUserAssetsService.updateAssetsUser(null,EAssetsSellState.不出售, assetsId, fromUserId, toUserId, assetsNum);
        if(!num.equals(assetsNum)){
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        //新增订单历史
        NftWorks nftWorksById = nftWorksService.findNftWorksById(assetsId);
        NftOrder order = new NftOrder();
        order.setChainPlatCert("{}");
        order.setId(SecUtils.getTimeNO());
        order.setGas(new BigDecimal("0"));
        order.setFromUser(fromUserId);
        order.setToUser(toUserId);
        order.setAssetsId(assetsId);
        order.setNum(assetsNum);
        order.setPrice(nftWorksById.getPrice());
        order.setTotal(new BigDecimal("0"));
        order.setCommission(new BigDecimal("0"));
        order.setTaxes(new BigDecimal("0"));
        order.setSellerAmount(new BigDecimal("0"));
        order.setTradeTotal(new BigDecimal("0"));
        order.setTradeType(ETradeType.转移作品.getCode());
        order.setPayState(EPayState.已支付.getCode());
        order.setCreateTime(new Date());
        nftOrderService.save(order);

        //新增作品交易历史
        NftWorksHis db = new NftWorksHis();
        db.setPrice(order.getTotal());
        db.setNum(assetsNum);
        db.setWorksId(assetsId);
        db.setFromUser(fromUserId);
        db.setToUser(toUserId);
        db.setTradeTime(new Date());
        db.setType(EWorksHisType.转移.getCode());
        nftWorksHisService.saveDefaultNftWorkHis(db);

        UserChainplatInfoDTO uci = nftUserChainplatService.findUserChainPlatInfo(fromUserId, chainPlat);
        Map<String,Object> args = new HashMap<>();
        args.put("id",assetsId);//TokenID
        args.put("from",uci.getEVMAddress());//
        args.put("to",Account.xchainAKToEVMAddress(toAddress));//用户的address转换的数据
        args.put("amount",assetsNum.toString());//转移的数量
        args.put("data","");//附加数据
        chainExecStrategyContext.exec(chainPlat
                , staticdir + uci.getPrivatePath()
                , uci.getPasswd()
                , CommonConst.getNftContractName()
                , "safeTransferFrom"
                , args);
        super.cleanCache(CachePrefixConst.USER_ASSETS);
        return true;
    }
}
