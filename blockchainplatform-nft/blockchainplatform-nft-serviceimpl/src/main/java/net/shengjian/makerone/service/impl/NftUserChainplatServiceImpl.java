package net.shengjian.makerone.service.impl;

import com.baidu.xuper.api.Account;
import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.frame.util.FileUtils;
import net.shengjian.frame.util.Finder;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.makerone.constant.CachePrefixConst;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.constant.NFTExceptionConst;
import net.shengjian.makerone.dto.UserChainplatInfoDTO;
import net.shengjian.makerone.entity.NftChainPlat;
import net.shengjian.makerone.entity.NftUserChainplat;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.exception.NFTException;
import net.shengjian.makerone.service.INftUserChainplatService;
import net.shengjian.makerone.strategy.context.ChainAccountStrategyContext;
import net.shengjian.makerone.strategy.context.ChainExecStrategyContext;
import net.shengjian.makerone.utils.PathUtil;
import net.shengjian.makerone.vo.BindOpenNetVO;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.entity.User;
import net.shengjian.system.service.IUserService;
import net.shengjian.system.service.impl.BaseSpringrainServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * TODO 在此加入类描述
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:57:36
 */

@Service("nftUserChainplatService")
public class NftUserChainplatServiceImpl extends BaseSpringrainServiceImpl implements INftUserChainplatService {

    @Resource
    private ChainAccountStrategyContext accountStrategyContext;

    @Resource
    private ChainExecStrategyContext chainExecStrategyContext;

    @Resource
    private IUserService userService;

    @Resource
    private INftUserChainplatService nftUserChainplatService;

    @Value("${staticdir}")
    private String staticdir;

    @Override
	public String  save(IBaseEntity entity ) throws Exception{
	    NftUserChainplat nftUserChainplat=(NftUserChainplat) entity;
	    return super.save(nftUserChainplat).toString();
	}


	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
		NftUserChainplat nftUserChainplat=(NftUserChainplat) entity;
		return super.update(nftUserChainplat);
    }

    @Override
	public NftUserChainplat findNftUserChainplatById(String id) throws Exception{
		return super.findById(id,NftUserChainplat.class);
	}

    @Override
    public Boolean saveDefaultValueNftUserChainPlat(NftUserChainplat nftUserChainplat) throws Exception {
        if (nftUserChainplat==null){
            return false;
        }
        nftUserChainplat.setId(SecUtils.getTimeNO());
        nftUserChainplat.setCreateTime(new Date());
        nftUserChainplat.setUserId(SessionUser.getUserId());
        super.save(nftUserChainplat);
        return true;
    }

    @Override
    public NftUserChainplat saveCreateChainAccount(EChainPlat eChainPlat) throws Exception {
        if (eChainPlat==null){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftUserChainplat.class)
                .append(" WHERE chainPlatName=:chainPlatName AND userId=:userId AND LENGTH(privatePath) > 0 ")
                .setParam("chainPlatName",eChainPlat.getCode())
                .setParam("userId",SessionUser.getUserId());
        NftUserChainplat nftUserChainplat = super.queryForObject(selectFinder, NftUserChainplat.class);
        if (nftUserChainplat!=null){
            //已创建过该链平台账户,直接返回
            return nftUserChainplat;
        }
        Map<String, String> chainAccount = accountStrategyContext.createChainAccount(eChainPlat);
        if (MapUtils.isEmpty(chainAccount)){
            return null;
        }
        NftUserChainplat db = new NftUserChainplat();
        db.setEVMAddress(chainAccount.get("EVMAddress"));
        db.setAddressPath(PathUtil.pathRightTOLeft(chainAccount.get("addressPath")));
        db.setPublicPath(PathUtil.pathRightTOLeft(chainAccount.get("publicPath")));
        db.setPrivatePath(PathUtil.pathRightTOLeft(chainAccount.get("privatePath")));
        db.setAddress(chainAccount.get("address"));
        //db.setMnemonic(chainAccount.get("mnemonic"));不保存助记词
        db.setChainPlatName(eChainPlat.getCode());
        Boolean aBoolean = this.saveDefaultValueNftUserChainPlat(db);
        if(!aBoolean){
            return null;
        }
        return db;
    }

    @Override
    public UserChainplatInfoDTO findUserChainPlatInfo(String userId, EChainPlat eChainPlat) throws Exception {
        if (StringUtils.isBlank(userId) || eChainPlat == null) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        String key = userId+"_"+eChainPlat.getCode();
        UserChainplatInfoDTO byCache = super.getByCache(CachePrefixConst.USER_CHAIN_INFO, key, UserChainplatInfoDTO.class);
        if(byCache!=null){
            return byCache;
        }

        Finder selectFinder = Finder.getSelectFinder(NftUserChainplat.class)
                .append(" WHERE userId=:userId AND chainPlatName=:chainPlatName ")
                .setParam("userId", userId)
                .setParam("chainPlatName", eChainPlat.getCode());
        UserChainplatInfoDTO dto = super.queryForObject(selectFinder, UserChainplatInfoDTO.class);
        if (dto == null) {
            return null;
        }

        super.putByCache(CachePrefixConst.USER_CHAIN_INFO, key,dto);
        return dto;
    }

    @Override
    public Boolean haveAddress(String userId, EChainPlat eChainPlat) throws Exception {
        if (StringUtils.isBlank(userId) || eChainPlat == null) {
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftUserChainplat.class, " count(1) ")
                .append(" WHERE userId=:userId AND chainPlatName=:chainPlatName AND LENGTH(address) > 0 ")
                .setParam("userId", userId)
                .setParam("chainPlatName", eChainPlat.getCode());
        Integer integer= super.queryForObject(selectFinder, Integer.class);
        return integer>0;
    }

    @Override
    public String userDownPrivateAndPublic(EChainPlat chainPlat) throws Exception {
        final String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftUserChainplat.class)
                .append(" WHERE userId=:userId AND chainPlatName=:chainPlatName ")
                .setParam("userId", userId)
                .setParam("chainPlatName", chainPlat.getCode());
        NftUserChainplat db = super.queryForObject(selectFinder, NftUserChainplat.class);
        if (db == null) {
            throw NFTExceptionConst.USER_NOT_CHAIN_PLAT_ACCOUNT;
        }
        List<File> fileList = new ArrayList<>();
        File privateFile = new File(staticdir + db.getPrivatePath());
        File pubFile = new File(staticdir + db.getPublicPath());
        File addressFile = new File(staticdir + db.getAddressPath());
        fileList.add(privateFile);
        fileList.add(pubFile);
        fileList.add(addressFile);
        String temp = staticdir + "keys.zip";
        FileUtils.toZip(fileList, temp);
        return temp;
    }

    @Override
    public Boolean deleteUserPrivateAndPublic(EChainPlat chainPlat) throws Exception {
        final String userId = SessionUser.getUserId();
        if(StringUtils.isBlank(userId)){
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftUserChainplat.class)
                .append(" WHERE userId=:userId AND chainPlatName=:chainPlatName ")
                .setParam("userId",userId)
                .setParam("chainPlatName",chainPlat.getCode());
        NftUserChainplat db = super.queryForObject(selectFinder, NftUserChainplat.class);
        final String privatePath = db.getPrivatePath();
        final String publicPath = db.getPublicPath();
        db.setPrivatePath("");
        db.setPublicPath("");
        db.setUpdateTime(new Date());
        super.update(db,true);

        new File(staticdir+privatePath).deleteOnExit();
        new File(staticdir+publicPath).deleteOnExit();
        return true;
    }

    @Override
    public String findEVMAddress(String userId,EChainPlat eChainPlat) throws Exception {
        if(StringUtils.isBlank(userId)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftUserChainplat.class, " EVMAddress ")
                .append(" WHERE userId=:userId AND chainPlatName=:chainPlatName ")
                .setParam("userId",userId)
                .setParam("chainPlatName", eChainPlat.getCode());
        String EVMAddress = super.queryForObject(selectFinder, String.class);
        if (StringUtils.isBlank(EVMAddress)){
            throw NFTExceptionConst.USER_NOT_CHAIN_PLAT_ACCOUNT;
        }
        return EVMAddress;
    }

    @Override
    public List<UserChainplatInfoDTO> findUserChainplatByUserId(String userId) throws Exception {
        if(StringUtils.isBlank(userId)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftUserChainplat.class, "b.name as chainPlatName,a.addressPath,a.privatePath,a.EVMAddress,a.mnemonic,a.prevTime,a.createTime ")
                .append(" AS a ")
                .append(" JOIN ").append(Finder.getTableName(NftChainPlat.class)).append(" AS b ON a.chainPlatName = b.englishName ")
                .append(" WHERE a.userId=:userId ")
                .setParam("userId", userId);
        List<UserChainplatInfoDTO> dto = super.queryForList(selectFinder, UserChainplatInfoDTO.class);
        if (CollectionUtils.isEmpty(dto)) {
            return new ArrayList<>();
        }
        for (UserChainplatInfoDTO item : dto) {
            String addressPath = item.getAddressPath();
            item.setAddress(PathUtil.readPath(staticdir+addressPath));
        }
        return dto;
    }

    @Deprecated
    @Override
    public Boolean updateBindXuperChainOpenNet(EChainPlat eChainPlat,String verificationCode, String openNetAddress) throws Exception {
        if(StringUtils.isBlank(openNetAddress)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        String userId = SessionUser.getUserId();
        Boolean ok = this.checkBindChainOpenNet(userId, eChainPlat);
        if(ok){
            throw new NFTException("已绑定过,请勿重复操作!");
        }
        UserChainplatInfoDTO uci = this.findUserChainPlatInfo(userId, eChainPlat);
        if(uci==null){
            throw NFTExceptionConst.USER_NOT_CHAIN_PLAT_ACCOUNT;
        }
        if (openNetAddress.equals(uci.getAddress())) {
            throw new NFTException("被绑定的address不能是当前用户系统内的address!");
        }

        String userPhone = userService.getUserPhone(userId);
        userService.verifyPhoneCode(userPhone,verificationCode);

        Finder updateFinder = Finder.getUpdateFinder(NftUserChainplat.class, " bindAddress=:bindAddress ")
                .append(" WHERE userId=:userId AND chainPlatName=:chainPlatName ")
                .setParam("userId", userId)
                .setParam("bindAddress",openNetAddress)
                .setParam("chainPlatName", eChainPlat.getCode());
        Integer update = super.update(updateFinder);
        if(update<=0){
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        String evmAddress = Account.xchainAKToEVMAddress(openNetAddress);
        Map<String,Object> args = new HashMap<>();
        args.put("operator",evmAddress);
        args.put("approved","true");

        //发送gas
        Map<String, String> preMap = chainExecStrategyContext.query(eChainPlat, staticdir + uci.getPrivatePath(), uci.getPasswd(),CommonConst.getNftContractName(),"setApprovalForAll", args);
        final String gasUsed = preMap.get("gasUsed");
        accountStrategyContext.sendUserGas(eChainPlat,staticdir+uci.getAddressPath(),new BigInteger(gasUsed));

        try {
            chainExecStrategyContext.exec(eChainPlat
                                        , staticdir+uci.getPrivatePath()
                                        , CommonConst.getNftContractName()
                                        , "setApprovalForAll", args);
        }catch (Exception e){
            throw NFTException.NFTExceptionERC1155(e);
        }

        String key = userId+"_"+eChainPlat.getCode();
        super.evictByKey(CachePrefixConst.USER_CHAIN_INFO,key);
        return true;
    }

    @Override
    public Boolean checkBindChainOpenNet(String userId,EChainPlat eChainPlat) throws Exception {
        if(StringUtils.isBlank(userId)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder finder = Finder.getSelectFinder(NftUserChainplat.class, " count(1) ")
                .append(" WHERE userId=:userId AND chainPlatName=:chainPlatName AND LENGTH(bindAddress)>0 ")
                .setParam("userId", userId)
                .setParam("chainPlatName", eChainPlat.getCode());
        Integer integer = super.queryForObject(finder, Integer.class);
        return integer>0;
    }

    @Override
    public Boolean updateBindXuperChainOpenNetPrivate(EChainPlat chainPlat, String verificationCode, String privateKey, String passwd,String address,String phone,String authorName) throws Exception {
        if(chainPlat==null || StringUtils.isBlank(verificationCode) || StringUtils.isBlank(authorName)){//|| StringUtils.isBlank(privateKey) || StringUtils.isBlank(passwd)
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        if(StringUtils.isBlank(address)|| address.length()!=33){
            throw new NFTException("绑定的address不正确!");
        }
        final String userId = SessionUser.getUserId();
        //Boolean init = nftUserChainplatService.havePrivateKeyAndAddressAndPasswd(chainPlat,userId);
        Boolean init = nftUserChainplatService.haveAddress(userId, chainPlat);
        if(init!=null && init){
            throw new NFTException("用户信息已经初始化过了!,请勿重复操作!");
        }
        //验证手机验证码
        userService.verifyPhoneCode(phone,verificationCode);

        User userById = userService.findUserById(userId);
        userById.setMobile(phone);
        userById.setUpdateUserId(userId);
        userById.setUpdateTime(new Date());
        userById.setUserName(authorName);
        userService.update(userById,true);
        //文件路径
        String folder = staticdir + File.separator + "userkeys" + File.separator + SessionUser.getUserId() + File.separator + "xuperchain" + File.separator;
        new File(folder).mkdirs();
        String addressPath = folder + "address";
        try (FileOutputStream os = new FileOutputStream(addressPath);){
            os.write(address.getBytes());

        }
        NftUserChainplat dbUserChain = new NftUserChainplat();
        if(StringUtils.isNotBlank(privateKey)){
            String privatePath = folder + "private.key";
            FileOutputStream os1 = new FileOutputStream(privatePath);
            os1.write(privateKey.getBytes());
            dbUserChain.setPrivatePath(PathUtil.pathRightTOLeft(privatePath.replace(staticdir,"")));
        }
        if(StringUtils.isNotBlank(passwd)){
            dbUserChain.setPasswd(SecUtils.encoderByRSAPrivateKey(passwd));
        }

        dbUserChain.setChainPlatName(chainPlat.getCode());
        dbUserChain.setEVMAddress(Account.xchainAKToEVMAddress(address));
        dbUserChain.setAddress(address);
        dbUserChain.setAddressPath(PathUtil.pathRightTOLeft(addressPath.replace(staticdir,"")));
        Boolean ok = nftUserChainplatService.saveDefaultValueNftUserChainPlat(dbUserChain);
        if(!ok){
            throw NFTExceptionConst.OPERATION_FAIL;
        }

        String key = userId+"_"+chainPlat.getCode();
        super.evictByKey(CachePrefixConst.USER_CHAIN_INFO,key);
        return true;
    }

    @Override
    public Boolean havePrivateKeyAndAddressAndPasswd(EChainPlat chainPlat, String userId) throws Exception {
        if(chainPlat==null || StringUtils.isBlank(userId)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftUserChainplat.class, " count(1) ")
                .append(" WHERE chainPlatName=:chainPlatName AND userId=:userId AND LENGTH(privatePath) > 0 AND LENGTH(address) > 0 AND LENGTH(passwd) > 0 ")
                .setParam("chainPlatName",chainPlat.getCode())
                .setParam("userId",userId);
        Integer count = super.queryForObject(selectFinder, Integer.class);
        return count > 0;
    }

    @Override
    public BindOpenNetVO reviewBind(String userId, EChainPlat chainPlat) throws Exception {
        if(StringUtils.isBlank(userId) || chainPlat==null){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftUserChainplat.class, " a.address AS openNetAddress,b.userName AS authorName,b.mobile AS phone ")
                .append(" AS a JOIN ").append(Finder.getTableName(User.class)).append(" AS b ON a.userId=b.id ")
                .append(" WHERE a.userId=:userId AND a.chainPlatName=:chainPlatName ")
                .setParam("userId",userId)
                .setParam("chainPlatName",chainPlat.getCode());
        return super.queryForObject(selectFinder,BindOpenNetVO.class);
    }

    @Override
    public Boolean updateBindPrivateAndPasswd(EChainPlat chainPlat, String verificationCode, String privateKey, String passwd) throws Exception {
        if(chainPlat==null || StringUtils.isBlank(verificationCode) || StringUtils.isBlank(privateKey) || StringUtils.isBlank(passwd)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        final String userId = SessionUser.getUserId();
        Boolean init = nftUserChainplatService.havePrivateKeyAndAddressAndPasswd(chainPlat,userId);
        if(init!=null && init){
            throw NFTExceptionConst.OPERATION_REPEAT;
        }
        //验证手机验证码
        String userPhone = userService.getUserPhone(userId);
        userService.verifyPhoneCode(userPhone,verificationCode);
        //文件路径
        String folder = staticdir + File.separator + "userkeys" + File.separator + userId + File.separator + "xuperchain" + File.separator;
        new File(folder).mkdirs();
        String privatePath = folder + "private.key";
        try (FileOutputStream os1 = new FileOutputStream(privatePath)){os1.write(privateKey.getBytes());}

        UserChainplatInfoDTO userChainPlatInfo = nftUserChainplatService.findUserChainPlatInfo(userId, chainPlat);
        if(userChainPlatInfo==null){
            throw NFTExceptionConst.USER_NOT_CHAIN_PLAT_ACCOUNT;
        }
        NftUserChainplat dbUserChain = new NftUserChainplat();
        dbUserChain.setId(userChainPlatInfo.getId());
        dbUserChain.setPrivatePath(PathUtil.pathRightTOLeft(privatePath.replace(staticdir,"")));
        dbUserChain.setPasswd(SecUtils.encoderByRSAPrivateKey(passwd));
        dbUserChain.setUpdateTime(new Date());

        Integer update = nftUserChainplatService.update(dbUserChain, true);

        String key = userId+"_"+chainPlat.getCode();
        super.evictByKey(CachePrefixConst.USER_CHAIN_INFO,key);
        return update>0;
    }

    @Override
    public String findUserIdByAddress(EChainPlat chainPlat, String address) throws Exception {
        if(chainPlat==null || StringUtils.isBlank(address)){
            throw NFTExceptionConst.PARAMS_IS_NULL;
        }
        Finder selectFinder = Finder.getSelectFinder(NftUserChainplat.class, " userId ")
                .append(" WHERE chainPlatName=:chainPlatName AND address=:address ")
                .setParam("chainPlatName",chainPlat.getCode())
                .setParam("address",address);
        return super.queryForObject(selectFinder, String.class);
    }

}
