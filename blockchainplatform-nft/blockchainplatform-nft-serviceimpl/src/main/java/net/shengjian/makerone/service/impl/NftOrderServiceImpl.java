package net.shengjian.makerone.service.impl;

import net.shengjian.frame.cache.RedisOperation;
import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.frame.util.*;
import net.shengjian.makerone.constant.CachePrefixConst;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.constant.NFTExceptionConst;
import net.shengjian.makerone.constant.StrategyBeanKeyConst;
import net.shengjian.makerone.dto.ComputeOrderDTO;
import net.shengjian.makerone.dto.UserChainplatInfoDTO;
import net.shengjian.makerone.dto.UserOrderTransHisDTO;
import net.shengjian.makerone.entity.*;
import net.shengjian.makerone.enums.*;
import net.shengjian.makerone.exception.NFTException;
import net.shengjian.makerone.service.*;
import net.shengjian.makerone.strategy.context.ChainAccountStrategyContext;
import net.shengjian.makerone.strategy.context.ChainExecStrategyContext;
import net.shengjian.makerone.utils.NFTImageUtil;
import net.shengjian.makerone.utils.NFTUtils;
import net.shengjian.makerone.utils.PayUtil;
import net.shengjian.makerone.vo.MailVO;
import net.shengjian.makerone.vo.NftRankingsVO;
import net.shengjian.makerone.vo.NftWorksVO;
import net.shengjian.makerone.vo.NftworksBuyDetailVO;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.entity.User;
import net.shengjian.system.service.IUserService;
import net.shengjian.system.service.impl.BaseSpringrainServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * TODO ?????????????????????
 *
 * @author springrain<Auto generate>
 * @version 2021-12-21 17:58:34
 */

@Service("nftOrderService")
public class NftOrderServiceImpl extends BaseSpringrainServiceImpl implements INftOrderService {

    @Resource
    private INftWorksService nftWorksService;

    @Resource
    private INftCollectionService nftCollectionService;

    @Resource
    private INftUserAssetsService nftUserAssetsService;

    @Resource
    private INftWorksHisService nftWorksHisService;

    @Resource
    private ChainExecStrategyContext chainExecStrategyContext;

    @Resource
    private INftUserChainplatService nftUserChainplatService;


    @Resource
    private INftMailService nftMailService;

    @Resource
    private IUserService userService;

    @Resource
    private ChainAccountStrategyContext chainAccountStrategyContext;

    @Resource
    private RedisOperation redisOperation;
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private PayUtil payUtil;

    @Value("${staticdir}")
    private String staticdir;


    @Override
    public String  save(IBaseEntity entity ) throws Exception{
        NftOrder nftOrder=(NftOrder) entity;
        return super.save(nftOrder).toString();
    }


    @Override
    public Integer update(IBaseEntity entity ) throws Exception{
        NftOrder nftOrder=(NftOrder) entity;
        return super.update(nftOrder);
    }

    @Override
    public NftOrder findNftOrderById(String id) throws Exception{
        return super.findById(id,NftOrder.class);
    }

    @Override
    public EPayState payState(String id) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftOrder.class, " payState ")
                .append(" WHERE id=:id ")
                .setParam("id", id);
        Integer integer = super.queryForObject(selectFinder, Integer.class);
        if (integer == null) {
            throw NFTExceptionConst.ORDER_NOT_EXIST;
        }
        return EPayState.codeOf(integer);
    }

    @Override
    public NftOrder saveGenerateOrderByWorksId(String worksId,String sellerId, Integer num, ETradeType eTradeType) throws Exception {
        if (StringUtils.isBlank(worksId) || num == null || num <= 0 || eTradeType == null) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        //?????????
        final boolean lock = redisOperation.lock(CachePrefixConst.LOCK_PREFIX+SessionUser.getUserId(), 1000 * 5);
        if(!lock){
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        //??????????????????????????????????????????????????????
        NftOrder db = this.findUserOrderByWorksId(SessionUser.getUserId(), worksId);
        if (db != null) {
            return db;
        }
        Integer orderCount = this.findUserOrderCount(SessionUser.getUserId(),ETradeType.????????????, EPayState.?????????);
        if(orderCount>0){
            throw new NFTException("??????????????????????????????!");
        }
        NftWorks byId = nftWorksService.findById(worksId, NftWorks.class);
        if (byId == null) {
            throw NFTExceptionConst.WORKS_NOT_EXIST;
        }
        //?????? sellerId
        //??????
        String toUserId = SessionUser.getUserId();
        //?????????
        if(CommonConst.isPromotionPeriod()){
            Integer userAssetsNumByWorksId = nftUserAssetsService.findUserAssetsNum(toUserId, worksId);
            if(userAssetsNumByWorksId>=1){
                throw new NFTException("????????????!??????????????????????????????!");
            }
        }

        UserChainplatInfoDTO ui = nftUserChainplatService.findUserChainPlatInfo(toUserId, EChainPlat.???????????????);
        if(ui==null){
            throw NFTExceptionConst.USER_NOT_CHAIN_PLAT_ACCOUNT;
        }
        //????????????
        Integer userAssetsNum = nftUserAssetsService.findUserAssetsNum(sellerId, worksId, EAssetsType.??????, EAssetsSellState.?????????);
        if (num > userAssetsNum) {
            throw NFTExceptionConst.WORKS_NUM_INSUFFICIENT;
        }
        //????????????????????????key
        String lockKey = CachePrefixConst.LOCK_PREFIX+worksId;
        Boolean hasKey = redisTemplate.hasKey(lockKey);
        if(hasKey==null || !hasKey){
            redisOperation.getAtomicLong(lockKey,Long.parseLong(userAssetsNum+""));
        }

        //?????????
        final Long atomicLong = redisOperation.getAtomicLong(lockKey, Long.parseLong((-num)+""));
        if(atomicLong<0){
            redisOperation.getAtomicLong(lockKey,Long.parseLong(num+""));
            throw NFTExceptionConst.WORKS_NUM_INSUFFICIENT;
        }

        if((userAssetsNum-num) <= 0){//??????????????????
            Boolean ok = nftWorksService.updateWorksState(worksId,EWorksState.?????????,null,null,null,null);
            if(!ok){
                throw NFTExceptionConst.OPERATION_FAIL;
            }
        }
        //??????????????????
        Integer dbNum = nftUserAssetsService.updateAssetsSellStateByAssetsId(sellerId, worksId, EAssetsSellState.???????????????, num);
        if (!dbNum.equals(num)){
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        //??????gas?????????
        String fromEvmAddress = nftUserChainplatService.findEVMAddress(sellerId, EChainPlat.???????????????);
        String toEvmAddress = nftUserChainplatService.findEVMAddress(toUserId, EChainPlat.???????????????);
        Map<String,Object> args = new HashMap<>();
        args.put("from",fromEvmAddress);//?????????address???????????????
        args.put("to",toEvmAddress);//?????????address???????????????
        args.put("id",worksId);//???????????????
        args.put("amount",num);//???????????????
        args.put("data",JsonUtils.writeValueAsString(byId));//????????????
        UserChainplatInfoDTO infoDTO = nftUserChainplatService.findUserChainPlatInfo(sellerId, EChainPlat.???????????????);

        Map<String, String> preMap = chainExecStrategyContext.query(EChainPlat.???????????????,staticdir + infoDTO.getPrivatePath(),infoDTO.getPasswd(),CommonConst.getNftContractName(), "safeTransferFrom", args);
        String gasUsed = preMap.get("gasUsed");

        BigDecimal royalties = nftCollectionService.findRoyaltiesById(byId.getCollectionId());
        //??????
        BigDecimal price = byId.getPrice();
        //??????????????????
        INFTComputeOrderService bean = SpringUtils.getContext().getBean(EComputeStrategy.DEFAULT.getCode(StrategyBeanKeyConst.NFT_COMPUTE_SERVICE_IMPL), INFTComputeOrderService.class);
        ComputeOrderDTO dto = bean.computeOrder(price, num, royalties,new BigDecimal(gasUsed));
        if(dto == null){
            throw NFTExceptionConst.OPERATION_FAIL;
        }

        NftOrder order = new NftOrder();
        BeanUtils.copyProperties(dto,order);
        order.setId(SecUtils.getTimeNO());
        order.setFromUser(sellerId);
        order.setGas(dto.getGasUsed());
        order.setToUser(toUserId);
        order.setAssetsId(worksId);
        order.setNum(num);
        order.setPrice(price);
        order.setTradeType(eTradeType.getCode());

        User userById = userService.findUserById(toUserId);
        final String amount = dto.getTradeTotal()
                .multiply(CommonConst.HUNDRED)
                .setScale(0,RoundingMode.UP).toString();
        String prepayId = payUtil.placeAnOrderByJsApi(amount
                , byId.getName()
                , userById.getOpenId()
                , order.getId());
        order.setPrepayId(prepayId);

        this.saveDefaultOrder(order);

        super.cleanCache(CachePrefixConst.worksInfoDetail);
        super.cleanCache(CachePrefixConst.worksInfo);

        redisOperation.unlock(CachePrefixConst.LOCK_PREFIX+SessionUser.getUserId());
        return order;
    }

    @Override
    public NftOrder saveDefaultOrder(NftOrder order) throws Exception {
        if(StringUtils.isBlank(order.getId())){
            order.setId(SecUtils.getTimeNO());
        }
        order.setPayState(EPayState.?????????.getCode());
        order.setCreateTime(new Date());
        if(StringUtils.isBlank(order.getChainPlatCert())){
            order.setChainPlatCert("{}");
        }
        super.save(order);
        return order;
    }

    @Override
    public NftOrder findUserOrderByWorksId(String userId, String worksId) throws Exception {
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(worksId)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftOrder.class)
                .append(" WHERE toUser=:userId AND assetsId=:worksId AND payState=:payState")
                .setParam("userId",userId)
                .setParam("worksId",worksId)
                .setParam("payState",EPayState.?????????.getCode());
        return super.queryForObject(selectFinder, NftOrder.class);
    }

    @Override
    public Boolean updatePayAfter(String orderId, EPayPlat ePayPlat, Date txTime, EPayState ePayState,String transactionId,String attach) throws Exception {
        if (StringUtils.isBlank(orderId) || ePayPlat==null || txTime == null || ePayState==null) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        NftOrder byId = super.findById(orderId, NftOrder.class);
        if(byId==null){
            throw NFTExceptionConst.ORDER_NOT_EXIST;
        }
        //?????????????????????,??????????????????
        if (byId.getPayState().equals(EPayState.?????????.getCode())) {
            return true;
        }
        byId.setPayState(ePayState.getCode());
        byId.setPayPlat(ePayPlat.getCode());
        byId.setTxTime(txTime);
        byId.setTransactionId(transactionId);

        boolean flag = false;
        if(ETradeType.????????????.getCode().equals(byId.getTradeType())){
            flag = this.updatePayTradeWorksAfter(byId);
        }else if(ETradeType.????????????.getCode().equals(byId.getTradeType())){
            flag = this.updatePayWorksInAfter(byId,attach);
        }

        //????????????
        super.cleanCache(CachePrefixConst.USER_ASSETS);
        super.cleanCache(CachePrefixConst.worksInfoDetail);
        super.cleanCache(CachePrefixConst.worksInfo);
        return flag;
    }

    @Override
    public Boolean updatePayTradeWorksAfter(NftOrder order) throws Exception {
        order.setUpdateTime(new Date());

        Integer update = super.update(order, true);
        //???????????????????????????,??????????????????
        if (order.getPayState().equals(EPayState.?????????.getCode())) {
            Integer num = nftUserAssetsService.updateAssetsSellStateByAssetsId(order.getFromUser(), order.getAssetsId(), EAssetsSellState.?????????, order.getNum());
            if(!num.equals(order.getNum())){
                throw NFTExceptionConst.OPERATION_FAIL;
            }
            return update>0;
        }
        Integer payState = order.getTradeType();
        String assetsId = order.getAssetsId();
        final String fromUser = order.getFromUser();
        final String toUser = order.getToUser();
        BigDecimal total = order.getTotal();
        Integer num = order.getNum();

        if(payState.equals(ETradeType.????????????.getCode())){
            //1.??????????????????,(????????????????????? ??????),????????????????????????????????????
            Integer dbNum = nftUserAssetsService.updateAssetsUser(EAssetsSellState.???????????????,EAssetsSellState.?????????,assetsId, fromUser, toUser, num);
            if(!dbNum.equals(num)){
                throw NFTExceptionConst.OPERATION_FAIL;
            }
            /*//2.????????????????????????
            Boolean ok = nftWorksService.updateBuyers(assetsId,fromUser,toUser);
            if(!ok){
                throw NFTExceptionConst.OPERATION_FAIL;
            }*/
        }

        //TODO 3. ??????????????????,
        //TODO 4. ?????????????????????

        //?????????????????????
        String fromEvmAddress = nftUserChainplatService.findEVMAddress(fromUser, EChainPlat.???????????????);
        String toEvmAddress = nftUserChainplatService.findEVMAddress(toUser, EChainPlat.???????????????);

        Map<String,Object> args = new HashMap<>();
        args.put("from",fromEvmAddress);//?????????address???????????????
        args.put("to",toEvmAddress);//?????????address???????????????
        args.put("id",assetsId);//???????????????
        args.put("amount",order.getNum());//???????????????
        args.put("data",JsonUtils.writeValueAsString(order));//????????????
        UserChainplatInfoDTO infoDTO = nftUserChainplatService.findUserChainPlatInfo(fromUser, EChainPlat.???????????????);

        Map<String, String> preMap = chainExecStrategyContext.query(EChainPlat.???????????????,staticdir + infoDTO.getPrivatePath(),infoDTO.getPasswd(),CommonConst.getNftContractName(), "safeTransferFrom", args);
        String gasUsed = preMap.get("gasUsed");
        //????????????????????????address??????gas
        chainAccountStrategyContext.sendUserGas(EChainPlat.???????????????, staticdir + infoDTO.getAddressPath(),new BigInteger(gasUsed));

        Map<String, String> map = chainExecStrategyContext.exec(EChainPlat.???????????????
                ,staticdir+infoDTO.getPrivatePath()
                ,infoDTO.getPasswd()
                ,CommonConst.getNftContractName()
                ,"safeTransferFrom"
                ,args);
        try {
            Map<String, Object> param = new HashMap<>(map);
            Boolean updateChainPlatCertByIn = this.updateChainPlatCert(order.getId(), EChainPlat.???????????????, param);
            if(!updateChainPlatCertByIn){
                throw NFTExceptionConst.OPERATION_FAIL;
            }

            //5.????????????????????????
            NftWorksHis db = new NftWorksHis();
            db.setPrice(total);
            db.setNum(num);
            db.setWorksId(assetsId);
            db.setFromUser(fromUser);
            db.setToUser(toUser);
            db.setTradeTime(order.getTxTime());

            Map<String,String> stringMap = new HashMap<>();
            stringMap.put(EChainPlat.???????????????.getCode(),JsonUtils.writeValueAsString(map));
            db.setChainPlatCert(JsonUtils.writeValueAsString(stringMap));

            //String authorId = nftWorksService.findAuthorIdByWorksId(byId.getAssetsId());//??????????????????????:??????
            //db.setType(byId.getFromUser().equals(authorId) ? EWorksHisType.??????.getCode() : EWorksHisType.??????.getCode());
            nftWorksHisService.saveDefaultNftWorkHis(db);

            //????????????
            nftCollectionService.saveFireAndPriceToCollection(assetsId, order.getTradeTotal());
            nftCollectionService.saveWorksChangesAndLowPrice();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        try {
            //??????????????????
            User userById = userService.findUserById(toUser);
            this.sendMailAfterBuy(assetsId,userById.getEmail());
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }

        //????????????
        super.cleanCache(CachePrefixConst.collectionRanking);
        super.cleanCache(CachePrefixConst.fireCollection);
        super.cleanCache(CachePrefixConst.worksListInCollection);
        super.cleanCache(CachePrefixConst.USER_ASSETS);
        return true;
    }

    @Override
    public Boolean updatePayWorksInAfter(NftOrder order, String attach) throws Exception {
        if(StringUtils.isBlank(attach)){
            throw new RuntimeException("??????????????????!");
        }
        order.setUpdateTime(new Date());
        super.update(order, true);
        //???????????????????????????,??????????????????
        if (order.getPayState().equals(EPayState.?????????.getCode())) {
            return false;
        }
        String[] split = attach.split("&");
        if(split.length<4){
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        /* ?????????????????????
        String attach=worksId
                +"&"+DateUtils.convertDate2String(DateUtils.DATETIME_FORMAT,waitingTime)
                +"&"+DateUtils.convertDate2String(DateUtils.DATETIME_FORMAT,outTime)
                +"&"+price.toString();*/
        String worksId=split[0];
        String outTimeStr =split[1];
        String waitingTimeStr =split[2];
        String price=split[3];

        Date outTime = DateUtils.convertString2Date(outTimeStr);
        Date waitingTime = DateUtils.convertString2Date(waitingTimeStr);
        Boolean flag = nftWorksService.updateWorksIn(order, worksId, new Date(), outTime, waitingTime, new BigDecimal(price));
        //????????????
        nftCollectionService.saveWorksChangesAndLowPrice();

        //?????????
        NftWorksVO worksById = nftWorksService.findWorksById(worksId);
        redisOperation.getAtomicLong(CachePrefixConst.LOCK_PREFIX+worksId, Long.parseLong(worksById.getNum().toString()));

        //????????????
        super.cleanCache(CachePrefixConst.collectionRanking);
        //????????????????????????
        super.cleanCache(CachePrefixConst.fireCollection);
        //?????????????????????????????????
        super.cleanCache(CachePrefixConst.worksListInCollection);
        //?????????????????????????????????
        super.cleanCache(CachePrefixConst.buyersForWorks);
        //???????????????????????????
        super.cleanCache(CachePrefixConst.collectionDetail);
        return flag;
    }

    @Override
    public Boolean updateChainPlatCert(String orderId, EChainPlat eChainPlat, Map<String,Object> chainPlatCert) throws Exception {
        chainPlatCert.remove("gasUsed");
        StringBuffer JSON_SET = new StringBuffer();
        JSON_SET.append("JSON_SET(");
        JSON_SET.append(" chainPlatCert,");
        JSON_SET.append("'$.").append(eChainPlat.getCode()).append("'");//k
        JSON_SET.append(",");//k
        JSON_SET.append("'").append(JsonUtils.writeValueAsString(chainPlatCert)).append("'");//v
        JSON_SET.append(") ");
        //Object gasUsed = chainPlatCert.get("gasUsed");
        Finder updateFinder = Finder.getUpdateFinder(NftOrder.class)//gas=gas+:gas
                .append(" updateTime=:updateTime,chainPlatCert=" + JSON_SET)
                .append(" WHERE id=:orderId ")
                //.setParam("gas",Integer.parseInt(gasUsed.toString()))
                .setParam("updateTime", new Date())
                .setParam("orderId", orderId);
        updateFinder.setEscapeSql(false);
        Integer update = super.update(updateFinder);
        return update > 0;
    }

    @Override
    public Integer findUserOrderCount(String userId) throws Exception {
        Integer sendCount = this.findUserOrderCount(userId, null, null, null, null, null);
        Integer buyCount = this.findUserOrderCount(null, userId, null, null, null, null);
        return buyCount + sendCount;
    }

    @Override
    public Integer findUserOrderCount(String fromUser, String toUser, String assetsId, EPayPlat ePayPlat, ETradeType eTradeType,EPayState payState) throws Exception {
        Finder selectFinder = Finder.getSelectFinder(NftOrder.class, " count(1) ");
        selectFinder.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(fromUser)) {
            selectFinder.append(" AND fromUser=:fromUser ").setParam("fromUser", fromUser);
        }
        if (StringUtils.isNotBlank(toUser)) {
            selectFinder.append(" AND toUser=:toUser ").setParam("toUser", toUser);
        }
        if (StringUtils.isNotBlank(assetsId)) {
            selectFinder.append(" AND assetsId=:assetsId ").setParam("assetsId", assetsId);
        }
        if (ePayPlat != null) {
            selectFinder.append(" AND payPlat=:payPlat ").setParam("payPlat", ePayPlat.getCode());
        }
        if (eTradeType != null) {
            selectFinder.append(" AND tradeType=:tradeType ").setParam("tradeType", eTradeType.getCode());
        }
        if (payState != null) {
            selectFinder.append(" AND payState=:payState ").setParam("payState", payState.getCode());
        }
        Integer count = super.queryForObject(selectFinder, Integer.class);
        return count==null?0:count;
    }

    @Override
    public Integer findUserOrderCount(String toUser,ETradeType tradeType, EPayState payState) throws Exception {
        return this.findUserOrderCount(null,toUser,null,null,tradeType,payState);
    }

    @Override
    public List<UserOrderTransHisDTO> findTransHis(String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftOrder.class," a.id,a.gas,b.address AS fromUserName,c.address AS toUserName,d.NAME AS assetsName,a.num,a.tradeTotal,a.payState,a.createTime,a.prepayId,d.dataPath ")
                .append(" AS a ")
                .append(" JOIN ").append(Finder.getTableName(NftUserChainplat.class)).append(" AS b ON a.fromUser = b.userId ")
                .append(" JOIN ").append(Finder.getTableName(NftUserChainplat.class)).append(" AS c ON a.toUser = c.userId ")
                .append(" JOIN ").append(Finder.getTableName(NftWorks.class)).append(" AS d ON a.assetsId = d.id ")
                .append(" WHERE a.fromUser=:userId OR a.toUser=:userId ")
                .append(" ORDER BY a.createTime DESC ")
                .setParam("userId", userId);
        List<UserOrderTransHisDTO> data = super.queryForList(selectFinder, UserOrderTransHisDTO.class);
        if (CollectionUtils.isEmpty(data)) {
            return new ArrayList<>();
        }
        return data;
    }

    @Override
    public EPayState updateFindOrderState(String orderId) throws Exception {
        if(StringUtils.isBlank(orderId)){
            return EPayState.codeOf(0);
        }
        Finder selectFinder = Finder.getSelectFinder(NftOrder.class, " payState ")
                .append(" WHERE id=:orderId ")
                .setParam("orderId",orderId);
        Integer state = super.queryForObject(selectFinder, Integer.class);
        if(EPayState.?????????.getCode().equals(state)){
            return EPayState.codeOf(state);
        }
        try {
            Map<String, String> dataMap = payUtil.finOrder(orderId);
            String tradeState = dataMap.get("trade_state");//????????????
            if("NOTPAY".equals(tradeState)){
                return EPayState.?????????;
            }
            String transactionId = dataMap.get("transaction_id");//?????????????????????
            String successTime = dataMap.get("success_time");//?????????????????? yyyyMMddHHmmss
            String attach = dataMap.get("attach");//???????????????

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
            Date txTime = df.parse(successTime);
            if("SUCCESS".equals(tradeState)) {
                Boolean ok = this.updatePayAfter(orderId, EPayPlat.????????????, txTime, EPayState.?????????, transactionId, attach);
                if(ok){
                    return EPayState.?????????;
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            return EPayState.?????????;
        }
        return EPayState.?????????;
    }

    @Override
    public List<NftOrder> findListBy(String fromUser, String toUser, String assetsId, EPayState payState, ETradeType tradeType) throws Exception {
        Finder selectFinder = Finder.getSelectFinder(NftOrder.class);
        selectFinder.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(fromUser)) {
            selectFinder.append(" AND fromUser=:fromUser ").setParam("fromUser", fromUser);
        }
        if (StringUtils.isNotBlank(toUser)) {
            selectFinder.append(" AND toUser=:toUser ").setParam("toUser", toUser);
        }
        if (StringUtils.isNotBlank(assetsId)) {
            selectFinder.append(" AND assetsId=:assetsId ").setParam("assetsId", assetsId);
        }
        if (tradeType != null) {
            selectFinder.append(" AND tradeType=:tradeType ").setParam("tradeType", tradeType.getCode());
        }
        if (payState != null) {
            selectFinder.append(" AND payState=:payState ").setParam("payState", payState.getCode());
        }
        List<NftOrder> nftOrders = queryForList(selectFinder, NftOrder.class);
        if(CollectionUtils.isEmpty(nftOrders)){
            return new ArrayList<>();
        }
        return nftOrders;
    }

    @Override
    public List<NftOrder> findListBy(String userId, String assetsId, EPayState payState, ETradeType tradeType) throws Exception {
        if(StringUtils.isBlank(userId)){
            return new ArrayList<>();
        }
        List<NftOrder> listBy = this.findListBy(userId, null, assetsId, payState, tradeType);
        List<NftOrder> listBy2 = this.findListBy(null,userId, assetsId, payState, tradeType);
        listBy.addAll(listBy2);
        return listBy;
    }

    @Override
    public List<NftOrder> findListBy(EPayState payState) throws Exception {
        return this.findListBy(null,null,null,payState,null);
    }

    @Override
    public List<NftOrder> findListByMinuteBefore(EPayState payState,Integer minute) throws Exception {
        if(payState==null || minute==null || minute<=0){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftOrder.class)
                .append(" WHERE payState=:payState AND timestampdiff(MINUTE,createTime,NOW())>=:minute ")
                .setParam("minute",minute)
                .setParam("payState", payState.getCode());
        List<NftOrder> nftOrders = queryForList(selectFinder, NftOrder.class);
        if(CollectionUtils.isEmpty(nftOrders)){
            return new ArrayList<>();
        }
        return nftOrders;
    }

    @Override
    public Boolean updateCancelOrder(NftOrder order) throws Exception {
        if(order==null){
            throw NFTExceptionConst.ORDER_NOT_EXIST;
        }
        if(EPayState.?????????.getCode().equals(order.getPayState())){
            return true;
        }
        if(EPayState.?????????.getCode().equals(order.getPayState())){
            throw new NFTException("????????????????????????!????????????!");
        }
        Finder updateFinder = Finder.getUpdateFinder(NftOrder.class, " payState=:payState2,updateTime=:updateTime ")
                .append(" WHERE id=:orderId AND payState=:payState1 ")
                .setParam("orderId", order.getId())
                .setParam("payState1", EPayState.?????????.getCode())
                .setParam("payState2", EPayState.?????????.getCode())
                .setParam("updateTime",new Date());
        Integer update = super.update(updateFinder);
        if(update>0){
            if (ETradeType.????????????.getCode().equals(order.getTradeType())) {
                super.cleanCache(CachePrefixConst.worksInfo);
                super.cleanCache(CachePrefixConst.worksInfoDetail);
                //?????????
                final Long atomicLong = redisOperation.getAtomicLong(CachePrefixConst.LOCK_PREFIX+order.getAssetsId(), Long.parseLong(order.getNum() + ""));
                System.out.println(atomicLong);

                Integer num = nftUserAssetsService.updateAssetsSellStateByAssetsId(order.getFromUser(), order.getAssetsId(), EAssetsSellState.?????????, order.getNum());
                if(!num.equals(order.getNum())){
                    throw NFTExceptionConst.OPERATION_FAIL;
                }
                return true;
            }else if (ETradeType.????????????.getCode().equals(order.getTradeType())){
                //?????????
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean updateCancelOrder(String orderId) throws Exception {
        if(StringUtils.isBlank(orderId)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        NftOrder byId = this.findById(orderId, NftOrder.class);
        if (byId==null) {
            throw NFTExceptionConst.ORDER_NOT_EXIST;
        }
        Boolean ok = this.updateCancelOrder(byId);
        if(!ok){
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        return true;
    }

    @Override
    public NftOrder findNotPayWorksIn(String worksId,String toUser) throws Exception {
        if(StringUtils.isBlank(worksId)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftOrder.class)
                .append(" WHERE toUser=:toUser AND tradeType=:tradeType AND assetsId=:worksId AND payState=:payState ")
                .setParam("toUser",toUser)
                .setParam("tradeType",ETradeType.????????????.getCode())
                .setParam("worksId",worksId)
                .setParam("payState",EPayState.?????????.getCode());
        return super.queryForObject(selectFinder,NftOrder.class);
    }

    @Override
    public NftOrder saveGenerateOrderWorksIn(String worksId, Date outTime, Date waitingTime, BigDecimal price) throws Exception {
        final String userId = SessionUser.getUserId();
        EWorksState eWorksState = nftWorksService.worksState(worksId);
        //????????????,??????????????????
        if (eWorksState != null && !eWorksState.getCode().equals(EWorksState.?????????.getCode())) {
            throw NFTExceptionConst.OPERATION_REPEAT;
        }
        //???????????????????????????
        NftOrder notPayWorksIn = this.findNotPayWorksIn(worksId,userId);
        if(notPayWorksIn!=null){
            return notPayWorksIn;
        }
        Integer orderCount = this.findUserOrderCount(userId,ETradeType.????????????, EPayState.?????????);
        if(orderCount>0){
            throw new NFTException("??????????????????????????????!");
        }
        NftWorks byId = nftWorksService.findById(worksId, NftWorks.class);
        if (byId == null) {
            throw NFTExceptionConst.WORKS_NOT_EXIST;
        }
        if (!byId.getAuthorId().equals(userId)) {
            //??????????????????????????????
            throw NFTExceptionConst.NOT_AUTHOR;
        }
        if(EWorksState.?????????.getCode().equals(byId.getState())){
            throw NFTExceptionConst.OPERATION_REPEAT;
        }
        /*Boolean ok = nftUserChainplatService.havePrivateKeyAndAddressAndPasswd(EChainPlat.???????????????, userId);
        if(!ok){
            throw new NFTException("????????????????????????,??????,??????");
        }*/
        Boolean ok = nftUserChainplatService.haveAddress(userId, EChainPlat.???????????????);
        if(!ok){
            throw NFTExceptionConst.NOT_ADDRESS;
        }
        BigDecimal money;
        if(CommonConst.isPromotionPeriod()){
            //?????????
            money=new BigDecimal("0.01");
        }else{
            //??????gas???,??????????????????
            String dataPath = byId.getDataPath();
            String base64Str = NFTImageUtil.imageToBase64Str(staticdir+dataPath);
            Map<String,Object> args = new HashMap<>();
            args.put("_id",byId.getId());//TokenID
            args.put("_initialSupply",byId.getNum());//Token??????
            args.put("_data",base64Str);//Token ??????,Base64
            args.put("tokenTime",CommonConst.TRADING_COOLING_PERIOD);//?????????????????????
            try {
                UserChainplatInfoDTO ui = nftUserChainplatService.findUserChainPlatInfo(userId, EChainPlat.???????????????);
                if(ui==null){
                    throw NFTExceptionConst.USER_NOT_CHAIN_PLAT_ACCOUNT;
                }
                //?????????,??????gas??????
                Map<String, String> preMap = chainExecStrategyContext.query(EChainPlat.???????????????
                        ,staticdir + ui.getPrivatePath()
                        , ui.getPasswd()
                        , CommonConst.getNftContractName()
                        , "addNewToken"
                        , args);
                String gasUsed = preMap.get("gasUsed");
                money = NFTUtils.gasToRmb(new BigDecimal(gasUsed));
            }catch (Exception e){
                throw NFTException.NFTExceptionERC1155(e);
            }
        }
        NftOrder order = new NftOrder();
        order.setId(SecUtils.getTimeNO());
        order.setAssetsId(worksId);
        order.setNum(byId.getNum());
        order.setFromUser(CommonConst.MAKER_ONE);
        order.setToUser(userId);
        order.setTradeTotal(money);
        order.setPayPlat(EPayPlat.????????????.getCode());
        order.setTradeType(ETradeType.????????????.getCode());
        BigDecimal zero = new BigDecimal(0);
        order.setPrice(zero);
        order.setTotal(zero);
        //???
        final String rmb = money.multiply(CommonConst.HUNDRED).setScale(0, RoundingMode.UP).toString();

        String attach=worksId
                +"&"+DateUtils.convertDate2String(DateUtils.DATETIME_FORMAT,outTime)
                +"&"+DateUtils.convertDate2String(DateUtils.DATETIME_FORMAT,waitingTime)
                +"&"+price.toString();

        final String weChatPayQRCode = payUtil.getWeChatPayQRCode(byId.getName(), order.getId(), rmb,attach);
        order.setCodeUrl(weChatPayQRCode);
        order.setGas(money);

        this.saveDefaultOrder(order);
        return order;
    }


    @Override
    public List<NftRankingsVO> findCollectionRankings(Page<NftRankingsVO> page) throws Exception {
        page.setPageNo(1);
        page.setPageSize(10);
        page.setSort("desc");
        page.setOrder("sumPrice");

        //????????????????????????
        String cacheKey = "collectionRankings"+"_"+page.getPageSize();
        List<NftRankingsVO> findCollectionRankingList = super.getByCache(CachePrefixConst.collectionRanking, cacheKey, List.class,page);
        if(!CollectionUtils.isEmpty(findCollectionRankingList)){
            return findCollectionRankingList;
        }
        //?????????????????????
        Finder collections = Finder.getSelectFinder(NftCollection.class, "*");
        List<NftCollection> nftCollections = super.queryForList(collections, NftCollection.class,page);
        List<NftRankingsVO> nftRankingsVOS = new ArrayList<>();
        for (NftCollection nftCollection:nftCollections){
            NftRankingsVO nftRankingsVO = new NftRankingsVO();
            Finder selectFinderWorks = Finder.getSelectFinder(NftWorks.class).append("WHERE collectionId=:nftcollectionId").setParam("nftcollectionId", nftCollection.getId());
            List<NftWorks> nftWorksList = super.queryForList(selectFinderWorks, NftWorks.class);
            if(CollectionUtils.isEmpty(nftWorksList)){
                nftRankingsVO.setCollectionName(nftCollection.getName());
                nftRankingsVO.setBuyerNum(0);
                nftRankingsVO.setWorksNum(nftWorksList.size());
                nftRankingsVO.setSumPrice(new BigDecimal(0));
                nftRankingsVO.setLowPrice(new BigDecimal(0));
                nftRankingsVO.setWeekChange(0);
                nftRankingsVO.setDayChange(0);
                nftRankingsVO.setLogoPath(null);
                nftRankingsVOS.add(nftRankingsVO);
                continue;
            }
            //?????????????????????
            Integer buyersnum = nftCollection.getBuyersnum();
            if(buyersnum ==null){
                buyersnum=0;
            }
            //??????????????????
            BigDecimal sumprice = nftCollection.getSumprice();
            if(sumprice == null){
                sumprice = new BigDecimal(0);
            }
            nftRankingsVO.setLowPrice(nftCollection.getLowprice());
            nftRankingsVO.setLogoPath(nftCollection.getLogoPath());
            nftRankingsVO.setCollectionName(nftCollection.getName());
            nftRankingsVO.setBuyerNum(buyersnum);
            nftRankingsVO.setWorksNum(nftWorksList.size());
            nftRankingsVO.setSumPrice(sumprice);
            nftRankingsVO.setWeekChange(nftCollection.getWeekchange());
            nftRankingsVO.setDayChange(nftCollection.getDaychange());
            nftRankingsVOS.add(nftRankingsVO);
        }
        //???????????????
        super.putByCache(CachePrefixConst.collectionRanking,cacheKey,nftRankingsVOS,page);
        return nftRankingsVOS;
    }

    /**
     * ???????????????

     */
    @Override
    public void change(int num ,NftCollection  nftCollection) throws Exception {
        ArrayList<Double> avgList = new ArrayList<>();
        double weeknum = 0;
        Finder workFinder = Finder.getSelectFinder(NftWorks.class, "*").append("WHERE collectionId=:collectionId").setParam("collectionId", nftCollection.getId());
        List<NftWorks> nftWorks = super.queryForList(workFinder, NftWorks.class);
        for(NftWorks nftWork:nftWorks){
            Finder findWorks = new Finder();
            if(num==7){
                findWorks = Finder.getSelectFinder(NftWorksHis.class).append("WHERE worksId=:worksid AND DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= DATE(tradeTime) ORDER BY tradeTime DESC").setParam("worksid",nftWork.getId());
            }
            if(num==1){
                findWorks = Finder.getSelectFinder(NftWorksHis.class).append("WHERE worksId=:worksid AND DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= DATE(tradeTime) ORDER BY tradeTime DESC").setParam("worksid",nftWork.getId());
            }
            List<NftWorksHis> nftFindWorks = super.queryForList(findWorks, NftWorksHis.class);
            LinkedList<BigDecimal> bigDecimalList = new LinkedList<>();
            for (NftWorksHis nftWorksHis1 : nftFindWorks) {
                bigDecimalList.add(nftWorksHis1.getPrice().divide(new BigDecimal(nftWorksHis1.getNum()),4,ROUND_HALF_DOWN));
            }
            if (bigDecimalList.size() == 0) {
                avgList.add(new Double(0));
            }
            if (bigDecimalList.size() >= 1) {
                BigDecimal first = bigDecimalList.getFirst();
                BigDecimal last = bigDecimalList.getLast();
                BigDecimal avg = (first.subtract(last)).divide(last, 4, ROUND_HALF_DOWN);
                avgList.add(avg.doubleValue());
            }

        }
        if (avgList.size() != 0) {
            for (int i = 0; i < avgList.size(); i++) {
                weeknum = weeknum+avgList.get(i);
            }
            if(num==7){
                nftCollection.setWeekchange(weeknum/avgList.size());
            }else {
                nftCollection.setDaychange(weeknum/avgList.size());
            }
        }
        super.update(nftCollection,true);

    }

    @Override
    public MailVO sendMailAfterBuy(String workId, String mail) throws Exception {
        NftworksBuyDetailVO nftWorks = nftWorksService.nftworksBuyDetails(workId);
        String hash = DigestUtils.md5Hex(staticdir + nftWorks.getLogoPath());
        MailVO mailVO = new MailVO();
        mailVO.setSubject("????????????NFT????????????"+"\n");
        mailVO.setTo(mail);
        mailVO.setPath(staticdir + nftWorks.getLogoPath());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("???????????????????????????????????????NFT?????????????????????").append("???????????????????????????").append("\n")
                .append("??????????????????[").append(nftWorks.getWorksName()).append("]").append("\n")
                .append("??????????????????[").append(nftWorks.getWorksNum()).append("]").append("\n")
                .append("???????????????HASH[").append(hash).append("]").append("\n");
        mailVO.setText(stringBuilder.toString());
        MailVO result = nftMailService.sendMail(mailVO);
        return result;
    }


}


