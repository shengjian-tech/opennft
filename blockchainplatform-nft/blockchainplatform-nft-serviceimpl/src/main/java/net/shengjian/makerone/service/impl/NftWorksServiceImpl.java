package net.shengjian.makerone.service.impl;

import com.baidu.xuper.api.Account;
import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.frame.util.*;
import net.shengjian.makerone.constant.CachePrefixConst;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.constant.NFTExceptionConst;
import net.shengjian.makerone.dto.SearchWorksResultDTO;
import net.shengjian.makerone.dto.UserChainplatInfoDTO;
import net.shengjian.makerone.dto.UserCollectionWorksDTO;
import net.shengjian.makerone.entity.*;
import net.shengjian.makerone.enums.*;
import net.shengjian.makerone.exception.NFTException;
import net.shengjian.makerone.service.*;
import net.shengjian.makerone.strategy.context.ChainAccountStrategyContext;
import net.shengjian.makerone.strategy.context.ChainExecStrategyContext;
import net.shengjian.makerone.utils.PathUtil;
import net.shengjian.makerone.vo.*;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.entity.User;
import net.shengjian.system.service.IUserService;
import net.shengjian.system.service.impl.BaseSpringrainServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;


/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2021-12-21 17:58:08
 */

@Service("nftWorksService")
public class NftWorksServiceImpl extends BaseSpringrainServiceImpl implements INftWorksService {


    @Resource
    private INftUserChainplatService nftUserChainplatService;

    @Resource
    private ChainExecStrategyContext chainExecStrategyContext;

    @Resource
    private INftCollectionService nftCollectionService;

    @Resource
    private INftUserAssetsService nftUserAssetsService;

    @Resource
    private ChainAccountStrategyContext chainAccountStrategyContext;

    @Resource
    private INftOrderService nftOrderService;

    @Resource
    private INftWorksService nftWorksService;

    @Resource
    private INftChainPlatService nftChainPlatService;

    @Resource
    private IUserService userService;

    @Value("${staticdir}")
    private String staticdir;

    @Value("${resource.protocol}")
    private String protocol;
    @Value("${resource.domain}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public String save(IBaseEntity entity) throws Exception {
        NftWorks nftWorks = (NftWorks) entity;
        return super.save(nftWorks).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        NftWorks nftWorks = (NftWorks) entity;
        return super.update(nftWorks);
    }

    @Override
    public NftWorks findNftWorksById(String id) throws Exception {
        return super.findById(id, NftWorks.class);
    }

    @Override
    public Boolean saveDefaultValueNftWorks(NftWorks nftWorks) throws Exception {
        if (nftWorks == null) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        nftWorks.setId(SecUtils.getTimeNO());
        nftWorks.setCreateTime(new Date());
        nftWorks.setAuthorId(SessionUser.getUserId());
        nftWorks.setState(EWorksState.未上架.getCode());
        if(StringUtils.isBlank(nftWorks.getChainPlatCert())) {
            nftWorks.setChainPlatCert("{}");
        }
        super.save(nftWorks);
        return true;
    }

    @Override
    public Boolean updateDefaultValueNftWorks(NftWorks nftWorks) throws Exception {
        if (nftWorks == null || StringUtils.isBlank(nftWorks.getId())) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        nftWorks.setUpdateTime(new Date());
        nftWorks.setAuthorId(SessionUser.getUserId());
        nftWorks.setState(EWorksState.未上架.getCode());
        super.update(nftWorks,true);
        return true;
    }

    @Override
    public NftWorks saveCreateNftWorks(NftWorksVO nftWorksVO) throws Exception {
        if (nftWorksVO == null) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        /*String dataPath = nftWorksVO.getDataPath();
        File file = new File(staticdir + dataPath);
        if((file.length()/1000)>400){
            throw new NFTException("作品大小超过400kb");
        }*/
        NftWorks nftWorks = new NftWorks();
        BeanUtils.copyProperties(nftWorksVO, nftWorks);
        String collectionId = nftWorksVO.getCollectionId();
        Boolean existById = nftCollectionService.isExistById(collectionId);
        if(!existById){
            throw NFTExceptionConst.COLL_NOT_EXIST;
        }
        this.saveDefaultValueNftWorks(nftWorks);

        //用户资产表
        List<NftUserAssets> list= new ArrayList<>();
        for (int i=0;i<nftWorks.getNum();i++){
            NftUserAssets ua = new NftUserAssets();
            ua.setUserId(SessionUser.getUserId());
            ua.setAssetsId(nftWorks.getId());
            ua.setType(EAssetsType.作品.getCode());
            ua.setOrigin(EAssertsOrigin.自己创作.getCode());
            list.add(ua);
            //nftUserAssetsService.saveDefaultNftUserAssets(ua);
        }
        nftUserAssetsService.saveDefaultNftUserAssetsBatch(list);

        //清楚缓存
        super.cleanCache(CachePrefixConst.collectionRanking);
        super.cleanCache(CachePrefixConst.fireCollection);
        super.cleanCache(CachePrefixConst.worksListInCollection);

        //清楚缓存
        super.cleanCache(CachePrefixConst.USER_ASSETS);
        return nftWorks;
    }

    @Override
    public NftWorks updateNftWorks(NftWorksVO nftWorksVO) throws Exception {
        if (nftWorksVO == null) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        EWorksState eWorksState = this.worksState(nftWorksVO.getId());
        //只能更新未上架的
        if(!eWorksState.getCode().equals(EWorksState.未上架.getCode())){
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        String userId = SessionUser.getUserId();
        String authorIdByWorksId = this.findAuthorIdByWorksId(nftWorksVO.getId());
        if(!authorIdByWorksId.equals(userId)){
            throw NFTExceptionConst.NOT_AUTHOR;
        }
        NftWorks nftWorks = new NftWorks();
        BeanUtils.copyProperties(nftWorksVO, nftWorks);
        String collectionId = nftWorksVO.getCollectionId();
        Boolean existById = nftCollectionService.isExistById(collectionId);
        if(!existById){
            throw NFTExceptionConst.COLL_NOT_EXIST;
        }
        this.updateDefaultValueNftWorks(nftWorks);

        String assetsId = nftWorks.getId();
        Integer userAssetsNum = nftUserAssetsService.findUserAssetsNum(userId, assetsId);
        Integer deleteNum = nftUserAssetsService.deleteAssertByWorkId(userId, assetsId);
        if(!deleteNum.equals(userAssetsNum)){
            throw NFTExceptionConst.OPERATION_FAIL;
        }

        //用户资产表
        List<NftUserAssets> list= new ArrayList<>();
        for (int i=0;i<nftWorks.getNum();i++){
            NftUserAssets ua = new NftUserAssets();
            ua.setUserId(userId);
            ua.setAssetsId(nftWorks.getId());
            ua.setType(EAssetsType.作品.getCode());
            ua.setOrigin(EAssertsOrigin.自己创作.getCode());
            list.add(ua);
            //nftUserAssetsService.saveDefaultNftUserAssets(ua);
        }
        nftUserAssetsService.saveDefaultNftUserAssetsBatch(list);

        //清楚缓存
        super.cleanCache(CachePrefixConst.collectionRanking);
        super.cleanCache(CachePrefixConst.fireCollection);
        super.cleanCache(CachePrefixConst.worksListInCollection);
        //清除作品购买页（价格、作品名称....）详情缓存
        super.cleanCache(CachePrefixConst.worksInfoDetail);
        //清楚缓存
        super.cleanCache(CachePrefixConst.USER_ASSETS);
        return nftWorks;
    }

    @Override
    public Boolean updateChainPlatCert(String userId, String worksId, EChainPlat eChainPlat, Map<String, Object> chainPlatCert) throws Exception {
        chainPlatCert.remove("gasUsed");
        StringBuffer JSON_SET = new StringBuffer();
        JSON_SET.append("JSON_SET(");
        JSON_SET.append(" chainPlatCert,");
        JSON_SET.append("'$.").append(eChainPlat.getCode()).append("'");//k
        JSON_SET.append(",");//k
        JSON_SET.append("'").append(JsonUtils.writeValueAsString(chainPlatCert)).append("'");//v
        JSON_SET.append(") ");
        Finder updateFinder = Finder.getUpdateFinder(NftWorks.class)
                .append("updateTime=:updateTime,chainPlatCert=" + JSON_SET)
                .append(" WHERE authorId=:userId AND id=:worksId ")
                .setParam("updateTime", new Date())
                .setParam("userId", userId)
                .setParam("worksId", worksId);
        updateFinder.setEscapeSql(false);
        Integer update = super.update(updateFinder);
        return update > 0;
    }

    @Override
    public Boolean updateWorksState(String worksId, EWorksState eWorksState, Date inTime, Date outTime, Date waitingTime,BigDecimal price) throws Exception {
        if (StringUtils.isBlank(worksId)) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        NftWorks nftWorks = new NftWorks();
        if (EWorksState.售卖中.getCode().equals(eWorksState.getCode())) {
            if (inTime == null || outTime == null ) {
                throw NFTExceptionConst.DATE_IS_NULL;
            }
            nftWorks.setInTime(inTime);
            nftWorks.setOutTime(outTime);
            nftWorks.setWaitingTime(waitingTime);
            if(waitingTime == null){
                nftWorks.setWaitingTime(DateUtils.addDay(CommonConst.DEFAULT_WAITING_TIME,inTime));
            }
        }
        nftWorks.setId(worksId);
        nftWorks.setState(eWorksState.getCode());
        nftWorks.setPrice(price);
        nftWorks.setUpdateTime(new Date());
        Integer update = super.update(nftWorks, true);
        //清除相似作品缓存
        super.cleanCache(CachePrefixConst.worksSame);
        return update > 0;
    }


    @Override
    public Boolean updateWorksChainPlatCertByCollectionId(String userId, String collectionId, EChainPlat eChainPlat, Map<String, Object> chainPlatCert) throws Exception {
        StringBuffer JSON_SET = new StringBuffer();
        JSON_SET.append("JSON_SET(");
        JSON_SET.append(" chainPlatCert,");
        JSON_SET.append("'$.").append(eChainPlat.getCode()).append("'");//k
        JSON_SET.append(",");//k
        JSON_SET.append("'").append(JsonUtils.writeValueAsString(chainPlatCert)).append("'");//v
        JSON_SET.append(") ");

        Finder updateFinder = Finder.getUpdateFinder(NftWorks.class)
                .append("updateTime=:updateTime,chainPlatCert=" + JSON_SET)
                .append(" WHERE authorId=:userId AND collectionId=:collectionId ")
                .setParam("updateTime", new Date())
                .setParam("userId", userId)
                .setParam("collectionId", collectionId);
        updateFinder.setEscapeSql(false);
        Integer update = super.update(updateFinder);
        return update > 0;
    }

    @Override
    public List<NftWorks> updateInWorksByCollectionId(String collectionId, Date inTime, Date outTime, Date waitingTime) throws Exception {
        if (StringUtils.isBlank(collectionId) || inTime == null || outTime == null) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        if (waitingTime == null) {
            waitingTime = DateUtils.addDay(CommonConst.DEFAULT_WAITING_TIME,inTime);
        }
        Boolean batchWorksFlag = this.updateBatchWorksStateByCollectionId(collectionId, inTime, outTime, waitingTime);
        if (!batchWorksFlag) {
            return new ArrayList<>();
        }
        Finder selectFinder = Finder.getSelectFinder(NftWorks.class)
                .append(" WHERE collectionId=:collectionId AND state=:state ")
                .setParam("collectionId", collectionId)
                .setParam("state", EWorksState.售卖中.getCode());
        List<NftWorks> nftWorks = super.queryForList(selectFinder, NftWorks.class);
        if (CollectionUtils.isEmpty(nftWorks)) {
            return new ArrayList<>();
        }
        String userId = SessionUser.getUserId();
        for (NftWorks nftWork : nftWorks) {
            Integer num = nftUserAssetsService.updateAssetsSellStateByAssetsId(userId, nftWork.getId(), EAssetsSellState.售卖中, nftWork.getNum());
            if (!num.equals(nftWork.getNum())){
                throw NFTExceptionConst.OPERATION_FAIL;
            }
        }
        return nftWorks;
    }

    @Override
    public EWorksState worksState(String worksId) throws Exception {
        if(StringUtils.isBlank(worksId)){
            throw NFTExceptionConst.WORKS_NOT_EXIST;
        }
        Finder selectFinder = Finder.getSelectFinder(NftWorks.class, " state ")
                .append(" WHERE id=:worksId ")
                .setParam("worksId",worksId);
        Integer integer = super.queryForObject(selectFinder, Integer.class);
        if(integer==null){
            throw NFTExceptionConst.WORKS_NOT_EXIST;
        }
        return EWorksState.codeOf(integer);
    }

    /**
     * 根据合集id批量更新未上架的作品状态
     *
     * @param collectionId 合集id
     * @param inTime       上架时间
     * @param outTime      下架时间
     * @param waitingTime  发布等待期
     * @return 是否更新成功
     * @throws Exception 更新失败抛出异常
     */
    private Boolean updateBatchWorksStateByCollectionId(String collectionId, Date inTime, Date outTime, Date waitingTime) throws Exception {
        Finder updateFinder = Finder.getUpdateFinder(NftWorks.class)
                .append(" state=:state1,inTime=:inTime,outTime=:outTime,waitingTime=:waitingTime,updateTime=:updateTime ")
                .append(" WHERE collectionId=:collectionId AND state=:state2 ")
                .setParam("state1", EWorksState.售卖中.getCode())
                .setParam("state2", EWorksState.未上架.getCode())
                .setParam("inTime", inTime)
                .setParam("outTime", outTime)
                .setParam("waitingTime", waitingTime)
                .setParam("updateTime", new Date())
                .setParam("collectionId", collectionId);
        Integer update = super.update(updateFinder);
        return update > 0;
    }

    @Override
    public Boolean updateWorksIn(NftOrder order,String worksId,Date inTime,Date outTime,Date waitingTime,BigDecimal price) throws Exception {
        EWorksState eWorksState = this.worksState(worksId);
        //只能上架,未上架的作品
        if (eWorksState != null && !eWorksState.getCode().equals(EWorksState.未上架.getCode())) {
            throw NFTExceptionConst.OPERATION_REPEAT;
        }
        /*String collectionId = this.findCollectionId(worksId);
        Boolean ok = nftCollectionService.isIn(collectionId);
        if(!ok){
            throw new NFTException("该作品所在的合集未上架!请上架作品合集!");
        }*/

        Boolean updateIsIn = this.updateWorksState(worksId, EWorksState.售卖中, inTime, outTime,waitingTime,price);
        if(!updateIsIn){
            return false;
        }
        final String userId = order.getToUser();
        NftWorks byId = super.findById(worksId, NftWorks.class);
        if (!byId.getAuthorId().equals(userId)) {
            //当前用户不是作品作者
            throw NFTExceptionConst.NOT_AUTHOR;
        }
        Integer dbNum = nftUserAssetsService.updateAssetsSellStateByAssetsId(userId, worksId, EAssetsSellState.售卖中,  byId.getNum());
        if (!dbNum.equals(byId.getNum())){
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        Boolean ok = nftUserChainplatService.haveAddress(userId, EChainPlat.百度超级链);
        if(!ok){
            throw NFTExceptionConst.NOT_ADDRESS;
        }
        UserChainplatInfoDTO infoDTO = nftUserChainplatService.findUserChainPlatInfo(userId, EChainPlat.百度超级链);
        //执行合约的信息
        String dataPath = byId.getDataPath();
        new File(staticdir +CommonConst.NFT_ARTWORK_PATH).mkdirs();
        String fileName = CommonConst.NFT_ARTWORK_PATH+worksId+dataPath.substring(dataPath.lastIndexOf("."));
        //String base64Str = NFTImageUtil.imageToBase64Str(staticdir+dataPath);
        try (FileInputStream fis = new FileInputStream(staticdir + dataPath);
             FileOutputStream fos = new FileOutputStream(staticdir + fileName)){
            IOUtils.copy(fis,fos);
        }
        //图片链接
        String link = protocol+domain+contextPath+fileName;
        String md5Hex = DigestUtils.md5Hex(new FileInputStream(staticdir + fileName));

        NftWorksVO worksById = nftWorksService.findWorksById(worksId);
        Map<String,String> _data= new HashMap<>();
        _data.put("hash",md5Hex);
        //_data.put("data",base64Str);//Token 数据,Base64
        _data.put("link",link);//图片链接
        _data.put("name",worksById.getName());
        _data.put("address",infoDTO.getAddress());
        _data.put("EVMAddress",infoDTO.getEVMAddress());
        User userById = userService.findUserById(userId);
        _data.put("authorName",userById.getUserName());

        Map<String,Object> args = new HashMap<>();
        args.put("_id",byId.getId());//TokenID
        args.put("_initialSupply",byId.getNum());//Token数量
        args.put("_data",SecUtils.encoderByBase64(JsonUtils.writeValueAsString(_data)));
        args.put("tokenTime",CommonConst.TRADING_COOLING_PERIOD);//作品交易冷却期

        //预执行,查询gas消耗
        Map<String, String> preMap = chainExecStrategyContext.rootQuery(EChainPlat.百度超级链
                , CommonConst.getNftContractName()
                , "addNewToken"
                , args);
        String gasUsed = preMap.get("gasUsed");
        //执行合约前向用户address转笔gas
        chainAccountStrategyContext.sendUserGas(EChainPlat.百度超级链, staticdir + infoDTO.getAddressPath(),new BigInteger(gasUsed));

        Map<String, String> map = chainExecStrategyContext.exec(EChainPlat.百度超级链
                ,staticdir+infoDTO.getPrivatePath()
                , infoDTO.getPasswd()
                ,CommonConst.getNftContractName()
                ,"addNewToken"
                ,args);

        map.put("hash",md5Hex);
        Map<String, Object> param = new HashMap<>(map);
        try {
            if(StringUtils.isBlank(infoDTO.getPasswd()) || StringUtils.isBlank(infoDTO.getPasswd())){
                //铸币成功,为作者授权
                Map<String,Object> auth = new HashMap<>();
                NftChainPlat db = nftChainPlatService.findNftChainPlatByEnglishName(EChainPlat.百度超级链.getCode());
                String addressPath = db.getAddressPath();
                String rootAddress;
                if(StringUtils.isNotBlank(addressPath) && addressPath.contains(CommonConst.CLASS_PATH)){
                    addressPath = addressPath.replace(CommonConst.CLASS_PATH,"");
                    rootAddress = PathUtil.readPath(new ClassPathResource(addressPath).getInputStream());
                }else{
                    rootAddress = PathUtil.readPath(staticdir+addressPath);
                }
                if(StringUtils.isBlank(rootAddress)){
                    throw NFTExceptionConst.NOT_ADDRESS;
                }
                auth.put("from", Account.xchainAKToEVMAddress(rootAddress));
                auth.put("id", byId.getId());//TokenID
                auth.put("to", Account.xchainAKToEVMAddress(infoDTO.getAddress()));
                chainExecStrategyContext.rootExec(EChainPlat.百度超级链
                        , CommonConst.getNftContractName()
                        ,"approveForOne"
                        ,auth);
            }
            this.updateChainPlatCert(userId,worksId, EChainPlat.百度超级链, param);
            nftOrderService.updateChainPlatCert(order.getId(), EChainPlat.百度超级链, param);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return true;
    }
    @Override
    public NftworksBuyDetailVO nftworksBuyDetails(String worksId) throws Exception {

        String cacheKey = worksId;
        //获取缓存
        NftworksBuyDetailVO worksBuyDetailCache = super.getByCache(CachePrefixConst.worksInfoDetail, cacheKey, NftworksBuyDetailVO.class);
        if(worksBuyDetailCache != null){
            String userId = SessionUser.getUserId();
            worksBuyDetailCache.setUserId(userId);
            return worksBuyDetailCache;
        }
        String userId = SessionUser.getUserId();
        NftworksBuyDetailVO nftworksBuyDetailVO = new NftworksBuyDetailVO();
        NftWorks nftWorks = findNftWorksById(worksId);
        //获取当前作者的id
        String authorId = nftWorks.getAuthorId();
        //获取状态
        Integer state = nftWorks.getState();
        //获取合集名称
        String collectionId = nftWorks.getCollectionId();
        NftCollection nftCollection = nftCollectionService.findNftCollectionById(collectionId);
        String collectionName = nftCollection.getName();
        //获取编号信息
        String worksCode = nftWorks.getId();
        //获取当前价格
        BigDecimal price = nftWorks.getPrice();
        //作品剩余数量
        Integer remainingNum = nftUserAssetsService.findUserAssetsNum(authorId, worksId, EAssetsType.作品, EAssetsSellState.售卖中);
        nftworksBuyDetailVO.setRemainingNum(remainingNum);
        //填补返回vo对象
        nftworksBuyDetailVO.setWorksName(nftWorks.getName());
        nftworksBuyDetailVO.setUserId(userId);
        nftworksBuyDetailVO.setAuthorId(authorId);
        nftworksBuyDetailVO.setState(state);
        nftworksBuyDetailVO.setCollectionName(collectionName);
        nftworksBuyDetailVO.setPrice(price);
        nftworksBuyDetailVO.setLogoPath(nftWorks.getDataPath());
        nftworksBuyDetailVO.setWorksNum(worksCode);
        nftworksBuyDetailVO.setTotalNumber(nftWorks.getNum());//发行总数
        //存入缓存
        super.putByCache(CachePrefixConst.worksInfoDetail,cacheKey,nftworksBuyDetailVO);
        return nftworksBuyDetailVO;
    }

    @Override
    public WorksInfoVO nftworksInfo(String worksId) throws Exception {
        String cacheKey = worksId;
        //获取缓存
        WorksInfoVO worksInfoCache = super.getByCache(CachePrefixConst.worksInfo, cacheKey, WorksInfoVO.class);
        if(worksInfoCache != null){
            return worksInfoCache;
        }

        WorksInfoVO worksInfoVO = new WorksInfoVO();
        NftWorks nftWorks = findNftWorksById(worksId);
        Finder username = Finder.getSelectFinder(User.class,"userName").append("WHERE id =:id").setParam("id", nftWorks.getAuthorId());
        String name = super.queryForObject(username, String.class);
        //合集简介
        String collectionId = nftWorks.getCollectionId();
        NftCollection nftCollection = nftCollectionService.findNftCollectionById(collectionId);
        String collectionDetails = nftCollection.getDetails();
        //获取链上信息
        String chainPlatCert = nftWorks.getChainPlatCert();
        //填补返回对象
        worksInfoVO.setChainInfo(chainPlatCert);
        worksInfoVO.setAuthor(name);
        worksInfoVO.setDetail(nftWorks.getDetails());
        worksInfoVO.setCollectionDetail(collectionDetails);
        //存入缓存
        super.putByCache(CachePrefixConst.worksInfo,cacheKey,worksInfoVO);
        return worksInfoVO;
    }

    @Override
    public List<NftWorkHisMapVO> nftworksHisPrice(String worksId ,Page<NftWorkHisMapVO> page) throws Exception {
        Finder worksInfo = Finder.getSelectFinder(NftOrder.class).append("WHERE assetsId =:id").setParam("id", worksId);
        List<NftOrder> nftWorksHis = super.queryForList(worksInfo, NftOrder.class,page);
        List<NftWorkHisMapVO> nftWorkHisMapVOS = new ArrayList<>();
        for(NftOrder nftOrder:nftWorksHis){
            NftWorkHisMapVO nftWorkHisMapVO = new NftWorkHisMapVO();
            nftWorkHisMapVO.setDate(nftOrder.getTxTime());
            nftWorkHisMapVO.setPrice(nftOrder.getPrice());
            nftWorkHisMapVOS.add(nftWorkHisMapVO);
        }
        return nftWorkHisMapVOS;
    }

    @Override
    public List<WorksSameVO> nftworkSame(String worksId,Page<WorksSameVO> page) throws Exception {
        page.setPageNo(1);
        page.setPageSize(4);
        page.setSort("desc");
        page.setOrder("price");
        //获取缓存
        String cacheKey = "nftworkSame"+"_"+worksId+"_"+page.getPageSize();
        List<WorksSameVO> worksSameVOs = super.getByCache(CachePrefixConst.worksSame, cacheKey, List.class,page);
        if(!CollectionUtils.isEmpty(worksSameVOs)){
            return worksSameVOs;
        }
        NftCollection nftCollection1 = nftCollectionService.findCollectionIdByWorkId(worksId);
        //获取合集的名字
        Finder collectionInfo = Finder.getSelectFinder(NftWorks.class).append("WHERE collectionId=:id")
                .setParam("id", nftCollection1.getId())
                .append("state=:state")
                .setParam("state",EWorksState.售卖中);
        List<NftWorks> nftWorks = super.queryForList(collectionInfo, NftWorks.class,page);
        String collectionName = nftCollection1.getName();
        List<WorksSameVO> nftWorksArrayList = new ArrayList<>();
        for(NftWorks nftWorks1:nftWorks){
            WorksSameVO worksSameVO = new WorksSameVO();
            worksSameVO.setPath(nftWorks1.getDataPath());
            worksSameVO.setPrice(nftWorks1.getPrice());
            worksSameVO.setWorkcode(nftWorks1.getId());
            worksSameVO.setCollectionName(collectionName);
            nftWorksArrayList.add(worksSameVO);
        }
        super.putByCache(CachePrefixConst.worksSame, cacheKey,nftWorksArrayList,page);
        return nftWorksArrayList;
    }

    @Override
    public String findCollectionId(String worksId) throws Exception {
        Finder selectFinder = Finder.getSelectFinder(NftWorks.class, " collectionId ")
                .append(" WHERE id=:worksId ")
                .setParam("worksId",worksId);
        String collectionId = super.queryForObject(selectFinder, String.class);
        if(StringUtils.isBlank(collectionId)){
            throw NFTExceptionConst.WORKS_NOT_EXIST;
        }
        return collectionId;
    }

    @Override
    public String findAuthorIdByWorksId(String worksId) throws Exception {
        if(StringUtils.isBlank(worksId)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftWorks.class, " authorId ")
                .append(" WHERE id=:worksId ")
                .setParam("worksId",worksId);
        String authorId = super.queryForObject(selectFinder, String.class);
        if (StringUtils.isBlank(authorId)){
            throw NFTExceptionConst.WORKS_NOT_EXIST;
        }
        return authorId;
    }

    @Override
    public Boolean updateReduceInQuantity(String worksId, Integer num) throws Exception {
        if(StringUtils.isBlank(worksId) || num==null){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder updateFinder = Finder.getUpdateFinder(NftWorks.class)
                .append(" num=num-:num WHERE id=:worksId AND num>=:num ")
                .setParam("worksId", worksId)
                .setParam("num", num);
        Integer update = super.update(updateFinder);
        return update>0;
    }

    @Override
    public Boolean updateBuyers(String worksId, String seller, String buyer) throws Exception {
        if (StringUtils.isBlank(worksId) || StringUtils.isBlank(seller) || StringUtils.isBlank(buyer)) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        NftWorks byId = super.findById(worksId, NftWorks.class);
        if(byId==null){
            throw NFTExceptionConst.WORKS_NOT_EXIST;
        }
        NftWorks updateDb = new NftWorks();
        updateDb.setId(byId.getId());
        updateDb.setUpdateTime(new Date());

        String authorId = byId.getAuthorId();
        String buyers = byId.getBuyers();
        //首件必须从作者方卖出
        if (StringUtils.isBlank(buyers) && !authorId.equals(seller)){
            return false;
        }
        if(StringUtils.isBlank(buyers)){
            buyers = "";
        }
        Set<String> buyerSet = new HashSet<>(Arrays.asList(buyers.split(",")));
        //不是作者,作品拥有者中必须包含卖方
        if(!authorId.equals(seller) && !buyerSet.contains(seller)){
            return false;
        }
        buyerSet.add(buyer);//新增买方

        Integer num = nftUserAssetsService.findUserAssetsNum(seller, worksId, EAssetsType.作品);
        //卖出者完全卖出
        if (num <= 0) {
            buyerSet.remove(seller);//移除卖方
        }

        buyers = String.join(",", buyerSet);
        updateDb.setBuyers(buyers);
        Integer update = super.update(updateDb,true);
        return update>0;
    }

    @Override
    public Integer findUserCreateWorksCount(String authorId) throws Exception {
        return this.findUserCreateWorksCount(authorId,null,null,null);
    }

    @Override
    public Integer findUserCreateWorksCount(String authorId, String collectionId, Integer type, EWorksState eWorksState) throws Exception {
        Finder selectFinder = Finder.getSelectFinder(NftWorks.class, " count(1) ");
        selectFinder.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(authorId)) {
            selectFinder.append(" AND  authorId=:authorId ").setParam("authorId", authorId);
        }
        if (StringUtils.isNotBlank(collectionId)) {
            selectFinder.append(" AND  collectionId=:collectionId ").setParam("collectionId", collectionId);
        }
        if (type != null) {
            selectFinder.append(" AND  type=:type ").setParam("type", type);
        }
        if (eWorksState != null) {
            selectFinder.append(" AND  state=:state ").setParam("state", eWorksState.getCode());
        }
        Integer count = super.queryForObject(selectFinder, Integer.class);
        return count==null?0:count;
    }

    @Override
    public UserCollectionWorksDTO findUserCollectionWorksDTO(String id) throws Exception {
        if(StringUtils.isBlank(id)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftWorks.class, "id AS worksId,dataPath,name,price ")
                .append(" WHERE id=:id ").setParam("id",id);
        return super.queryForObject(selectFinder,UserCollectionWorksDTO.class);
    }

    @Override
    public List<NftWorks> findUserCreateWorks(String authorId) throws Exception {
        return this.findUserCreateWorks(authorId,null,null,null);
    }

    @Override
    public List<NftWorks> findUserCreateWorks(String authorId, String collectionId, Integer type, EWorksState eWorksState) throws Exception {
        Finder selectFinder = Finder.getSelectFinder(NftWorks.class);
        selectFinder.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(authorId)) {
            selectFinder.append(" AND  authorId=:authorId ").setParam("authorId", authorId);
        }
        if (StringUtils.isNotBlank(collectionId)) {
            selectFinder.append(" AND  collectionId=:collectionId ").setParam("collectionId", collectionId);
        }
        if (type != null) {
            selectFinder.append(" AND  type=:type ").setParam("type", type);
        }
        if (eWorksState != null) {
            selectFinder.append(" AND  state=:state ").setParam("state", eWorksState.getCode());
        }
        List<NftWorks> data = super.queryForList(selectFinder, NftWorks.class);
        return data==null?new ArrayList<>():data;
    }

    @Override
    public Boolean updateLogicDel(String worksId) throws Exception {
        if (StringUtils.isBlank(worksId)) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder updateFinder = Finder.getUpdateFinder(NftWorks.class, " state=:state,updateTime=:updateTime ")
                .append(" WHERE authorId=:authorId AND id=:worksId AND state in (:state1,:state2) ")
                .setParam("authorId",SessionUser.getUserId())
                .setParam("state", EWorksState.已删除.getCode())
                .setParam("state1", EWorksState.未上架.getCode())
                .setParam("state2", EWorksState.已下架.getCode())
                .setParam("updateTime", new Date())
                .setParam("worksId", worksId);
        Integer update = super.update(updateFinder);
        return update > 0;
    }

    @Override
    public NftWorksVO findWorksById(String worksId) throws Exception {
        Finder selectFinder = Finder.getSelectFinder(NftWorks.class)
                .append(" where id=:worksId ")
                .setParam("worksId",worksId);
        return super.queryForObject(selectFinder,NftWorksVO.class);
    }

    @Override
    public List<SearchWorksResultDTO> searchWorks(String searchText) throws Exception {
        Finder selectFinder = Finder.getSelectFinder(NftWorks.class)
                .append(" where state=:state AND name like :name ")
                .setParam("state",EWorksState.售卖中.getCode())
                .setParam("name","%"+searchText+"%");
        List<SearchWorksResultDTO> searchWorksResultDTOS = super.queryForList(selectFinder, SearchWorksResultDTO.class);
        if(CollectionUtils.isEmpty(searchWorksResultDTOS)){
            return new ArrayList<>();
        }
        return searchWorksResultDTOS;
    }

    @Override
    public List<String> findWorkBuyers(String worksId , Page<String> page) throws Exception {
        if (page == null) {
            page.setPageNo(1);
            page.setPageSize(9999);
            page.setSort("desc");
            page.setOrder("createTime");
        }
        //从缓存中拿数据
        String cacheKey = worksId+"_"+page.getPageSize();
        List<String> buyersCache = super.getByCache(CachePrefixConst.buyersForWorks, cacheKey, List.class, page);
        if(!CollectionUtils.isEmpty(buyersCache)){
            return buyersCache;
        }
        List<String> nameList = new ArrayList<>();
        Finder worksFinder = Finder.getSelectFinder(NftOrder.class, "toUser")
                .append("WHERE assetsId=:worksId")
                .append(" AND payState=:state")
                .append(" AND tradeType=:tradeType")
                .setParam("state",EPayState.已支付.getCode())
                .setParam("tradeType",ETradeType.交易作品.getCode())
                .setParam("worksId", worksId);
        List<String> toUserList = super.queryForList(worksFinder, String.class, page);
        if(CollectionUtils.isEmpty(toUserList)){
            super.putByCache(CachePrefixConst.buyersForWorks, cacheKey,nameList,page);
            return nameList;
        }
        for(String s:toUserList){
            Finder username = Finder.getSelectFinder(NftUserChainplat.class, "address").append("WHERE userId=:id").setParam("id", s);
            String name = super.queryForObject(username, String.class);
            nameList.add(name);
        }

        //存入缓存
        super.putByCache(CachePrefixConst.buyersForWorks, cacheKey,nameList,page);
        return nameList;
    }
}
