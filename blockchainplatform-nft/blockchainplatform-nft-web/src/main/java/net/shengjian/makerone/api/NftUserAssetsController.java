package net.shengjian.makerone.api;

import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.frame.util.property.MessageUtils;
import net.shengjian.makerone.constant.MessageConst;
import net.shengjian.makerone.constant.NFTExceptionConst;
import net.shengjian.makerone.dto.NftUserDetails;
import net.shengjian.makerone.dto.UserCollectionDTO;
import net.shengjian.makerone.dto.UserCollectionWorksDTO;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.exception.NFTException;
import net.shengjian.makerone.service.INftUserAssetsService;
import net.shengjian.makerone.utils.PathUtil;
import net.shengjian.makerone.vo.TransferAssetsVO;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.base.BaseController;
import net.shengjian.system.entity.Attachment;
import net.shengjian.system.service.IAttachmentService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户资产模块
 * @author springrain<Auto generate>
 * @version  2021-12-21 17:58:02
 */
@RestController
@RequestMapping(value="/api/nft/userassets", method = RequestMethod.POST)
public class NftUserAssetsController  extends BaseController {
	@Resource
	private INftUserAssetsService nftUserAssetsService;

	@Resource
	private IAttachmentService attachmentService;

	@Value("${staticdir}")
	private String staticdir;

    @Value("${resource.protocol}")
    private String protocol;

	@Value("${resource.domain}")
	private String domain;

	@Value("${server.servlet.context-path}")
	private String contextPath;

    /**
     * 文件上传返回资源访问链接
     * @param file 文件
     * @return url
     */
    @PostMapping("/upload")
    public ReturnDatas<String> upload(@RequestParam("file") MultipartFile file) {
        ReturnDatas<String> rd = ReturnDatas.getSuccessReturnDatas();
        String userId = SessionUser.getUserId();
        if (StringUtils.isEmpty(userId)) {
            logger.error("用户不存在--upload()");
        }
        String folder = File.separator + "attachmentFile" + File.separator;
        String filePath = staticdir + folder;
        File path = new File(filePath);
        //路径不存在创建文件夹
        if (!path.exists()) {
            path.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        //文件后缀
        String suffix = "无后缀名";
        if (originalFilename.contains(".")) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        //存储数据库中的路径
        String fileUrl = folder + LocalDateTime.now().toString().replace(":", "") + UUID.randomUUID()+suffix;
        //上传的本地路径
        String upLoadPath = staticdir + fileUrl;
        long size = file.getSize();
        try (InputStream is = file.getInputStream();
             OutputStream os = new FileOutputStream(upLoadPath)) {
            IOUtils.copy(is, os);
            Attachment attachment = new Attachment();
            attachment.setOrgId("");
            attachment.setCreateUser(userId);
            attachment.setCreateTime(new Date());
            attachment.setId(SecUtils.getTimeNO());
            attachment.setFileName(originalFilename);
            attachment.setFileURL(PathUtil.pathRightTOLeft(fileUrl));
            attachment.setFileSize(Integer.parseInt(String.valueOf(size)) / 1024);
            attachment.setAttachmentType(0);
            attachment.setSuffix(suffix);
            attachment.setSortno(0);
            attachment.setActive(1);
            String md5Hex = DigestUtils.md5Hex(new FileInputStream(staticdir + attachment.getFileURL()));
            attachment.setMd5Code(md5Hex);
            //保存附件信息
            attachmentService.save(attachment);
            String resourceUrl = protocol + domain + contextPath + attachment.getFileURL();
            rd.setResult(resourceUrl);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            rd.setStatus(ReturnDatas.ERROR);
            rd.setMessage(MessageConst.UNKNOWN_ERR);
        }
        return rd;
    }

    /**
     * 查询用户资产数量详情
     * @return 数量详情数据
     */
    @PostMapping("/findUserDetails")
    public ReturnDatas<NftUserDetails> findUserDetails(){
        ReturnDatas<NftUserDetails> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            NftUserDetails data = nftUserAssetsService.findUserDetails();
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
     * 用戶收藏品列表
     * @return 列表
     */
    @PostMapping("/findUserCollectionWorks")
    public ReturnDatas<List<UserCollectionWorksDTO>> findUserCollectionWorks(@RequestBody Page<UserCollectionWorksDTO> page){
        ReturnDatas<List<UserCollectionWorksDTO>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<UserCollectionWorksDTO> data = nftUserAssetsService.findCollectionWorks(EChainPlat.百度超级链,SessionUser.getUserId(),page);
            rd.setResult(data);
            rd.setMessage(MessageUtils.FIND_SUCCESS);
            rd.setPage(page);
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
     * 用戶创建的作品列表
     * @return 列表
     */
    @PostMapping("/findMyCreateWorks")
    public ReturnDatas<List<UserCollectionWorksDTO>> findMyCreateWorks(){
        ReturnDatas<List<UserCollectionWorksDTO>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<UserCollectionWorksDTO> data = nftUserAssetsService.findUserCreateWorks(SessionUser.getUserId());
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
     * 用戶合集列表
     * @return 列表
     */
    @PostMapping("/findUserCollection")
    public ReturnDatas<List<UserCollectionDTO>> findUserCollection(){
        ReturnDatas<List<UserCollectionDTO>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            List<UserCollectionDTO> data = nftUserAssetsService.findUserCollection(SessionUser.getUserId());
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
     * 逻辑删除
     * @param id 主键
     * @return 是否执行成功
     */
    @PostMapping("/logicDel")
    public ReturnDatas<Boolean> logicDel(String id) {
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            Boolean ok = nftUserAssetsService.updateLogicDel(id);
            if (!ok) {
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
     * 转移资产,转移前须先绑定私钥和安全码
     * @param vo 参数
     * @return true|false
     */
    @PostMapping("/transferAssets")
    public ReturnDatas<Boolean> transferAssets(@RequestBody TransferAssetsVO vo){
        ReturnDatas<Boolean> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            Boolean ok = nftUserAssetsService.updateTransferAssets(EChainPlat.百度超级链,SessionUser.getUserId()
                    ,vo.getToAddress()
                    ,vo.getAssetsId()
                    ,vo.getVerificationCode());
            rd.setResult(ok);
            rd.setMessage(ok?"转移成功":"转移失败");
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
}
