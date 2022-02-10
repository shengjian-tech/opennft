package net.shengjian.makerone.service.impl;

import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.frame.util.Finder;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.makerone.constant.CachePrefixConst;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.constant.NFTExceptionConst;
import net.shengjian.makerone.dto.UserChainplatInfoDTO;
import net.shengjian.makerone.entity.*;
import net.shengjian.makerone.enums.*;
import net.shengjian.makerone.exception.NFTException;
import net.shengjian.makerone.service.*;
import net.shengjian.makerone.strategy.context.ChainExecStrategyContext;
import net.shengjian.makerone.vo.*;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.entity.User;
import net.shengjian.system.service.impl.BaseSpringrainServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

/**
 * 合集模块实现
 *
 * @author springrain<Auto generate>
 * @version 2021-12-21 17:57:43
 */

@Service("nftCollectionService")
public class NftCollectionServiceImpl extends BaseSpringrainServiceImpl implements INftCollectionService {

    @Resource
    private ChainExecStrategyContext chainExecStrategyContext;

    @Resource
    private INftUserChainplatService nftUserChainplatService;

    @Resource
    private INftWorksService nftWorksService;

    @Resource
    private INftUserAssetsService nftUserAssetsService;

    @Resource
    private INftOrderService nftOrderService;

    @Value("${staticdir}")
    private String staticdir;

    @Override
    public String save(IBaseEntity entity) throws Exception {
        NftCollection nftCollection = (NftCollection) entity;
        return super.save(nftCollection).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        NftCollection nftCollection = (NftCollection) entity;
        return super.update(nftCollection);
    }

    @Override
    public NftCollection findNftCollectionById(String id) throws Exception {
        return super.findById(id, NftCollection.class);
    }

    @Override
    public Boolean saveDefaultValueCollection(NftCollection nftCollection) throws Exception {
        if (nftCollection == null) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        nftCollection.setId(SecUtils.getTimeNO());
        nftCollection.setUserId(SessionUser.getUserId());
        nftCollection.setCreateTime(new Date());
        nftCollection.setIsIn(CommonConst.FALSE);
        nftCollection.setIsCert(CommonConst.FALSE);
        if(StringUtils.isBlank(nftCollection.getChainPlatCert())) {
            nftCollection.setChainPlatCert("{}");
        }
        super.save(nftCollection);
        return true;
    }

    @Override
    public Boolean updateDefaultValueCollection(NftCollection nftCollection) throws Exception {
        if (nftCollection == null) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        nftCollection.setUpdateTime(new Date());
        nftCollection.setIsIn(CommonConst.FALSE);
        nftCollection.setIsCert(CommonConst.FALSE);
        super.update(nftCollection,true);
        return true;
    }

    @Override
    public NftCollection saveCreateCollection(NftCollectionVO nftCollectionVO) throws Exception {
        if (nftCollectionVO == null) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        NftCollection nftCollection = new NftCollection();
        BeanUtils.copyProperties(nftCollectionVO, nftCollection);
        String name = nftCollection.getName();
        //合集名称是否存在
        Boolean collectionNameExist = this.isCollectionNameExist(name);
        if (collectionNameExist) {
            throw NFTExceptionConst.COLLECTION_NAME_EXIST;
        }
        this.saveDefaultValueCollection(nftCollection);

        super.cleanCache(CachePrefixConst.fireCollection);
        super.cleanCache(CachePrefixConst.collectionRanking);
        super.cleanCache(CachePrefixConst.worksListInCollection);

        //用户资产表
        NftUserAssets ua = new NftUserAssets();
        ua.setUserId(SessionUser.getUserId());
        ua.setAssetsId(nftCollection.getId());
        ua.setType(EAssetsType.合集.getCode());
        ua.setOrigin(EAssertsOrigin.自己创作.getCode());
        nftUserAssetsService.saveDefaultNftUserAssets(ua);

        return nftCollection;
    }

    @Override
    public NftCollection updateCreateCollection(NftCollectionVO nftCollectionVO) throws Exception {
        if (nftCollectionVO == null || StringUtils.isBlank(nftCollectionVO.getId())) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        final String id = nftCollectionVO.getId();
        if(this.isIn(id)){
            throw new NFTException("合集已上架!不可再修改!");
        }
        String name = nftCollectionVO.getName();
        NftCollection byId = super.findById(id, NftCollection.class);
        if(byId==null){
            throw NFTExceptionConst.COLL_NOT_EXIST;
        }
        String userId = SessionUser.getUserId();
        if (!byId.getUserId().equals(userId)) {
            throw NFTExceptionConst.NOT_AUTHOR;
        }
        if (!byId.getName().equals(name)) {
            //合集名称是否存在
            Boolean collectionNameExist = this.isCollectionNameExist(name);
            if (collectionNameExist) {
                throw NFTExceptionConst.COLLECTION_NAME_EXIST;
            }
        }
        BeanUtils.copyProperties(nftCollectionVO, byId);

        this.updateDefaultValueCollection(byId);

        super.cleanCache(CachePrefixConst.fireCollection);
        super.cleanCache(CachePrefixConst.collectionRanking);
        super.cleanCache(CachePrefixConst.worksListInCollection);
        //清除作品购买页（链信息、合集简介....）详情缓存
        super.cleanCache(CachePrefixConst.worksInfo);
        return byId;
    }

    @Override
    public Boolean isCollectionNameExist(String collectionName) throws Exception {
        if (StringUtils.isBlank(collectionName)) {
            return false;
        }
        Finder finder = Finder.getSelectFinder(NftCollection.class, " count(1) ")
                .append(" WHERE name=:collectionName ")
                .setParam("collectionName", collectionName);
        Integer count = super.queryForObject(finder, Integer.class);
        return count != null && count > 0;
    }

    @Override
    public List<LabelValuePairVO> getLabelValuePair(String authorId) throws Exception {
        Finder selectFinder = Finder.getSelectFinder(NftCollection.class, " name as label,id as value")
                .append(" WHERE 1=1 ");
        if(StringUtils.isNotBlank(authorId)){
            selectFinder.append(" AND userId=:authorId ").setParam("authorId",authorId);
        }
        return super.queryForList(selectFinder, LabelValuePairVO.class);
    }

    @Override
    public CollectionDetailVO nftCollectionDetail(String nftCollectionId) throws Exception {

        //拿到缓存
        String cacheKey = nftCollectionId;
        CollectionDetailVO collectionDetailCache = super.getByCache(CachePrefixConst.collectionDetail, cacheKey, CollectionDetailVO.class);
        if(collectionDetailCache != null){
            return collectionDetailCache;
        }
        CollectionDetailVO collectionDetailVO = new CollectionDetailVO();
        //查找合集
        NftCollection nftCollection = findNftCollectionById(nftCollectionId);
        BeanUtils.copyProperties(nftCollection, collectionDetailVO);
        //查找该合集下的作品
        Finder selectFinderWorks = Finder.getSelectFinder(NftWorks.class)
                .append("WHERE collectionId=:nftcollectionId AND state!=:state")
                .setParam("nftcollectionId",nftCollectionId)
                .setParam("state",EWorksState.已删除.getCode());
        //转换成对象
        List<NftWorks> nftWorksList = super.queryForList(selectFinderWorks,NftWorks.class);
        //获取合集的交易总额
        Finder sumPriceFinder = Finder.getSelectFinder(NftCollection.class, "sumPrice")
                .append("WHERE id=:id")
                .setParam("id", nftCollectionId);
        BigDecimal sumprice = super.queryForObject(sumPriceFinder, BigDecimal.class);

        //查找该合集作者名称
        Finder findUser = Finder.getSelectFinder(User.class)
                .append("WHERE id=:userid")
                .setParam("userid",nftCollection.getUserId());
        User user = super.queryForObject(findUser, User.class);
        //设置拥有者姓名和头像
        collectionDetailVO.setUsername(user.getUserName());
        collectionDetailVO.setAvatar(user.getAvatar());
        collectionDetailVO.setCounts(nftWorksList.size());
        collectionDetailVO.setPossessnum(nftCollection.getBuyersnum());
        collectionDetailVO.setTradesum(sumprice);
        collectionDetailVO.setLowPrice(nftCollection.getLowprice());
        //存入缓存
        super.putByCache(CachePrefixConst.collectionDetail,cacheKey,collectionDetailVO);
        return collectionDetailVO;
    }



    @Override
    public Boolean updateIsIn(String collectionId, int isIn, Date inTime, Date outTime) throws Exception {
        if (StringUtils.isBlank(collectionId)) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        NftCollection nftCollection = new NftCollection();
        if (isIn == 1) {
            if (inTime == null || outTime == null) {
                throw NFTExceptionConst.DATE_IS_NULL;
            }
            nftCollection.setInTime(inTime);
            nftCollection.setOutTime(outTime);
        }
        nftCollection.setId(collectionId);
        nftCollection.setIsIn(isIn);
        nftCollection.setUpdateTime(new Date());
        Integer update = super.update(nftCollection, true);
        return update > 0;
    }

    @Override
    public Boolean updateChainPlatCert(String userId,String collectionId, EChainPlat eChainPlat, Map<String, Object> chainPlatCert) throws Exception {
        chainPlatCert.remove("gasUsed");
        StringBuffer JSON_SET = new StringBuffer();
        JSON_SET.append("JSON_SET(");
        JSON_SET.append(" chainPlatCert,");
        JSON_SET.append("'$.").append(eChainPlat.getCode()).append("'");//k
        JSON_SET.append(",");//k
        JSON_SET.append("'").append(JsonUtils.writeValueAsString(chainPlatCert)).append("'");//v
        JSON_SET.append(") ");

        Finder updateFinder = Finder.getUpdateFinder(NftCollection.class)
                .append("updateTime=:updateTime,chainPlatCert="+JSON_SET)
                .append(" WHERE id=:collectionId AND userId=:userId")
                .setParam("updateTime",new Date())
                .setParam("collectionId",collectionId)
                .setParam("userId",userId);
        updateFinder.setEscapeSql(false);

        Integer update = super.update(updateFinder);
        return update > 0;
    }

    @Override
    public Boolean updateCollectionOnShelves(String collectionId,Date inTime, Date outTime,Date waitingTime) throws Exception {
        if(this.isIn(collectionId)){
            throw NFTExceptionConst.OPERATION_REPEAT;
        }
        Boolean updateIsIn = this.updateIsIn(collectionId, CommonConst.TRUE, inTime, outTime);
        if(!updateIsIn){
            return false;
        }
        NftCollection byId = super.findById(collectionId, NftCollection.class);
        String userId = SessionUser.getUserId();
        if (!byId.getUserId().equals(userId)) {
            //当前用户不是合集作者
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        UserChainplatInfoDTO infoDTO = nftUserChainplatService.findUserChainPlatInfo(userId, EChainPlat.百度超级链);
        //上架合集内的作品,合集内有无作品都不影响合集的上架
        List<NftWorks> nftWorks = nftWorksService.updateInWorksByCollectionId(collectionId, inTime, outTime, waitingTime);

        //TODO 执行合约的信息
        Map<String,Object> args = new HashMap<>();
        args.put("data1",JsonUtils.writeValueAsString(byId).getBytes(StandardCharsets.UTF_8));
        args.put("data2",JsonUtils.writeValueAsString(nftWorks).getBytes(StandardCharsets.UTF_8));

        args.put("key","num".getBytes(StandardCharsets.UTF_8));
        Map<String, String> map = chainExecStrategyContext.exec(EChainPlat.百度超级链
                ,staticdir+infoDTO.getPrivatePath()
                ,infoDTO.getPasswd()
                ,"counter"
                ,"increase"
                ,args);
        Map<String, Object> param = new HashMap<>(map);

        Boolean updateChainPlatCertByIn = this.updateChainPlatCert(userId,collectionId, EChainPlat.百度超级链, param);
        if(!updateChainPlatCertByIn){
            return false;
        }
        nftWorksService.updateWorksChainPlatCertByCollectionId(SessionUser.getUserId(), collectionId, EChainPlat.百度超级链, param);
        return true;
    }

    @Override
    public Boolean isExistById(String collectionId) throws Exception {
        if(StringUtils.isBlank(collectionId)){
            return false;
        }
        Finder selectFinder = Finder.getSelectFinder(NftCollection.class, " count(1) ")
                .append(" WHERE id=:collectionId ")
                .setParam("collectionId", collectionId);
        Integer integer = super.queryForObject(selectFinder, Integer.class);
        return integer > 0;
    }

    @Override
    public Boolean isIn(String collectionId) throws Exception {
        if(StringUtils.isBlank(collectionId)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftCollection.class, " count(1) ")
                .append(" WHERE id=:collectionId AND isIn=:isIn ")
                .setParam("collectionId",collectionId)
                .setParam("isIn",CommonConst.TRUE);
        Integer integer = super.queryForObject(selectFinder, Integer.class);
        return integer>0;
    }

    @Override
    public NftCollection findByWorksId(String worksId) throws Exception {
        String collectionId = nftWorksService.findCollectionId(worksId);
        NftCollection byId = super.findById(collectionId, NftCollection.class);
        if (byId==null){
            throw NFTExceptionConst.COLL_NOT_EXIST;
        }
        return byId;
    }

    @Override
    public BigDecimal findRoyaltiesById(String collectionId) throws Exception {
        if (StringUtils.isBlank(collectionId)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftCollection.class, " royalties ")
                .append(" WHERE id=:collectionId ")
                .setParam("collectionId", collectionId);
        BigDecimal royalties = super.queryForObject(selectFinder, BigDecimal.class);
        if (royalties==null){
            throw NFTExceptionConst.COLL_NOT_EXIST;
        }
        return royalties;
    }

    @Override
    public List<NftCollection> findListByUserId(String userId) throws Exception {
        return this.findListByUserId(userId,null,null,null);
    }

    @Override
    public List<NftCollection> findListByUserId(String userId,Integer type,Integer isIn,Integer isCert) throws Exception {
        Finder selectFinder = Finder.getSelectFinder(NftCollection.class)
                .append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(userId)) {
            selectFinder.append(" AND userId=:userId ").setParam("userId", userId);
        }
        if (type != null) {
            selectFinder.append(" AND type=:type ").setParam("type", type);
        }
        if (isIn != null) {
            selectFinder.append(" AND isIn=:isIn ").setParam("isIn", isIn);
        }
        if (isCert != null) {
            selectFinder.append(" AND isCert=:isCert ").setParam("isCert", isCert);
        }
        List<NftCollection> d = super.queryForList(selectFinder, NftCollection.class);
        return d==null?new ArrayList<>():d;
    }

    @Override
    public List<NFTMarketDTO> findNftMarketList(String searchText) throws Exception {
        //合集名称
        return this.findNftMarketListByCollectionName(searchText);
    }

    @Override
    public List<NFTMarketDTO> findNftMarketListByCollectionName(String collectionName) throws Exception {
        if(StringUtils.isBlank(collectionName)){
            collectionName="";
        }
        Finder selectFinder = Finder.getSelectFinder(NftCollection.class, " a.id AS collectionId,a.logoPath,a.coverPath,a.name,b.userName AS authorName,a.details ")
                .append(" AS a ")
                .append(" JOIN ").append(Finder.getTableName(User.class)).append(" AS b ON a.userId=b.id ")
                .append(" WHERE a.name like :collectionName ")
                .setParam("collectionName","%"+collectionName+"%");
        List<NFTMarketDTO> data = super.queryForList(selectFinder, NFTMarketDTO.class);
        String userId = SessionUser.getUserId();
        for(NFTMarketDTO dto:data){
            dto.setUserId(userId);
            Finder collection = Finder.getSelectFinder(NftCollection.class).append("WHERE id=:id").setParam("id", dto.getCollectionId());
            NftCollection nftCollection = super.queryForObject(collection, NftCollection.class);
            dto.setAuthorId(nftCollection.getUserId());
            dto.setState(nftCollection.getIsIn());
        }
        if(CollectionUtils.isEmpty(data)){
            return new ArrayList<>();
        }
        return data;
    }

    @Override
    public List<NFTMarketDTO> findFairMarketList(Page<NftDetialWorksVO> page) throws Exception {
        page.setPageNo(1);
        page.setPageSize(4);
        page.setSort("desc");
        page.setOrder("fire");
        String cacheKey = "fireCollectionKey"+"_"+page.getPageSize();
        //获取缓存
        List<NFTMarketDTO> fireMarketList = super.getByCache(CachePrefixConst.fireCollection,cacheKey, List.class,page);
        if(!CollectionUtils.isEmpty(fireMarketList)){
            String userId = SessionUser.getUserId();
            for(NFTMarketDTO nftMarketDTO:fireMarketList){
                nftMarketDTO.setUserId(userId);
            }
            return fireMarketList;
        }
        List<NFTMarketDTO> nftMarketDTOS = new ArrayList<>();
        //查询所有的合集
        Finder collections = Finder.getSelectFinder(NftCollection.class, "*");
        List<NftCollection> nftCollections = super.queryForList(collections, NftCollection.class,page);
        if(CollectionUtils.isEmpty(nftCollections)){
            return new ArrayList<>();
        }
//        //拿4个热门合集
        for (int i = 0; i < nftCollections.size(); i++) {
            NFTMarketDTO nftMarketDTO = new NFTMarketDTO();
            String userId = SessionUser.getUserId();
            nftMarketDTO.setUserId(userId);
            NftCollection nftCollection = nftCollections.get(i);
            nftMarketDTO.setAuthorId(nftCollection.getUserId());
            nftMarketDTO.setState(nftCollection.getIsIn());
            BeanUtils.copyProperties(nftCollection, nftMarketDTO);
            Finder user = Finder.getSelectFinder(User.class, "userName").append("WHERE id=:userId").setParam("userId", nftCollection.getUserId());
            String userName = super.queryForObject(user, String.class);
            nftMarketDTO.setAuthorName(userName);
            nftMarketDTO.setCollectionId(nftCollection.getId());
            nftMarketDTOS.add(nftMarketDTO);
        }
        //添加缓存
        super.putByCache(CachePrefixConst.fireCollection,cacheKey,nftMarketDTOS,page);
        return nftMarketDTOS;

    }

    @Override
    public NftCollection saveFireAndPriceToCollection(String workId, BigDecimal price) throws Exception {
        NftCollection nftCollection = findCollectionIdByWorkId(workId);
        if(nftCollection.getFire() == null){
            nftCollection.setFire(0);
        }
        if(nftCollection.getSumprice()==null){
            nftCollection.setSumprice(new BigDecimal(0));
        }
        if(nftCollection.getBuyersnum()==null){
            nftCollection.setBuyersnum(0);
        }
        //在原有的交易金额上加上此次交易额
        nftCollection.setSumprice(nftCollection.getSumprice().add(price));
        //在原来的交易总次数上加1，计算热度
        nftCollection.setFire(nftCollection.getFire()+1);
        //在原来的拥有者个数上加1，计算当前拥有者
        nftCollection.setBuyersnum(nftCollection.getBuyersnum()+1);
        super.update(nftCollection,true);
        return nftCollection;
    }


    @Override
    public NftCollection findCollectionIdByWorkId(String workId) throws Exception {
        NftWorks nftWorks = nftWorksService.findNftWorksById(workId);
        NftCollection nftCollection = findNftCollectionById(nftWorks.getCollectionId());
        return nftCollection;
    }

    @Override
    public List<NftCollection> saveWorksChangesAndLowPrice() throws Exception {
        Finder nftCollectionsFinder = Finder.getSelectFinder(NftCollection.class, "*");
        List<NftCollection> nftCollections = super.queryForList(nftCollectionsFinder, NftCollection.class);
        for(NftCollection nftCollection:nftCollections){
            //算出合集地板价
            Finder price1 = Finder.getSelectFinder(NftWorks.class, "min(price)")
                    .append("WHERE collectionId=:id AND state=:state")
                    .setParam("id",nftCollection.getId())
                    .setParam("state", EWorksState.售卖中.getCode());
            BigDecimal bigDecimal1 = super.queryForObject(price1, BigDecimal.class);
            Finder price2 = Finder.getSelectFinder(NftWorks.class, "price")
                    .append("WHERE price =:price")
                    .append(" AND collectionId=:id")
                    .setParam("id",nftCollection.getId())
                    .setParam("price",bigDecimal1);
            List<BigDecimal> lowprice = super.queryForList(price2, BigDecimal.class);
            BigDecimal priceDecimal = new BigDecimal(0);
            if(CollectionUtils.isEmpty(lowprice)){
                priceDecimal = new BigDecimal(0);
            }else {
                priceDecimal = lowprice.get(0);
            }
            //算出合集的涨跌幅
            nftOrderService.change(1,nftCollection);
            nftOrderService.change(7,nftCollection);
            nftCollection.setLowprice(priceDecimal);
            super.update(nftCollection,true);
        }
        return nftCollections;
    }

    @Override
    public List<NftDetialWorksVO> findWorksInCollection(String collectionId ,Page<NftDetialWorksVO> page) throws Exception {
        if (page == null) {
            page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(99999);
        }
        //从缓存里获取数据
        String cacheKey = "findWorksInCollectionById"+collectionId+"_"+page.getPageSize();
        List<NftDetialWorksVO> nftDeailWorksList = super.getByCache(CachePrefixConst.worksListInCollection, cacheKey, List.class,page);
        if(!CollectionUtils.isEmpty(nftDeailWorksList)){
            String userId = SessionUser.getUserId();
            for(NftDetialWorksVO nftDetialWorksVO:nftDeailWorksList){
                nftDetialWorksVO.setUserId(userId);
            }
            return nftDeailWorksList;
        }
        //如果缓存里没有数据,进入数据库开始查询
        String userId = SessionUser.getUserId();
        //查找该合集下的作品
        Finder selectFinderWorks = Finder.getSelectFinder(NftWorks.class)
                .append("WHERE collectionId=:nftcollectionId AND state!=:state")
                .setParam("nftcollectionId",collectionId)
                .setParam("state",EWorksState.已删除.getCode());
        //转换成对象
        List<NftWorks> nftWorksList = super.queryForList(selectFinderWorks,NftWorks.class,page);
        //创建返回的作品Vo对象
        List<NftDetialWorksVO> nftDetialWorksVOS = new ArrayList<>();
        if(!CollectionUtils.isEmpty(nftWorksList)){
            //将合集的作品存入返回list
            for(int j=0; j<nftWorksList.size();j++){
                NftDetialWorksVO nftDetialWorksVO = new NftDetialWorksVO();
                BeanUtils.copyProperties(nftWorksList.get(j), nftDetialWorksVO);
                //设置作品展示信息中的合集名称
                Finder findNftCollectionName = Finder.getSelectFinder(NftCollection.class,"name")
                        .append("WHERE id=:worksid")
                        .setParam("worksid",nftWorksList.get(j).getCollectionId());
                String s = super.queryForObject(findNftCollectionName, String.class);
                nftDetialWorksVO.setCollectionName(s);
                nftDetialWorksVO.setUserId(userId);
                nftDetialWorksVO.setAuthorId(nftWorksList.get(j).getAuthorId());
                nftDetialWorksVO.setState(nftWorksList.get(j).getState());
                //将作品添加到返回作品列表
                nftDetialWorksVOS.add(nftDetialWorksVO);
            }
        }
        //存入缓存
        super.putByCache(CachePrefixConst.worksListInCollection,cacheKey,nftDetialWorksVOS,page);
        return nftDetialWorksVOS;
    }



}
