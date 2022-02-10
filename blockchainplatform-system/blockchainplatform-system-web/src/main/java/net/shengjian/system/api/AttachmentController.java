package net.shengjian.system.api;

import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.frame.util.property.MessageUtils;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.base.BaseController;
import net.shengjian.system.entity.Attachment;
import net.shengjian.system.service.IAttachmentService;
import net.shengjian.system.service.IUserRoleOrgService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 附件模块
 *
 * @author springrain<Auto generate>
 * @version 2021-06-09 11:55:02
 */
@RestController
@RequestMapping(value = "/attachment", method = RequestMethod.POST)
public class AttachmentController extends BaseController {
    @Resource
    private IAttachmentService attachmentService;

    @Resource
    private IUserRoleOrgService userRoleOrgService;

    @Value("${staticdir}")
    private String staticdir;

    /**
     * 列表数据
     *
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ReturnDatas<Attachment> list(@RequestBody Page<Attachment> page)
            throws Exception {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
        // ==构造分页请求
        // Page page = newPage(request);
        // ==执行分页查询
        List<Attachment> datas = attachmentService.queryForListByEntity(page.getData(), page);
        //returnObject.setQueryBean(page.getData());
        returnObject.setPage(page);
        returnObject.setResult(datas);
        return returnObject;
    }

    /**
     * 查看的Json格式数据
     */
    @RequestMapping(value = "/look", method = RequestMethod.POST)
    public ReturnDatas<Attachment> look(String id) throws Exception {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();

        if (StringUtils.isNotBlank(id)) {
            Attachment attachment = attachmentService.findAttachmentById(id);
            returnObject.setResult(attachment);
        } else {
            returnObject.setStatus(ReturnDatas.ERROR);
        }
        return returnObject;

    }

    /**
     * 保存 操作,返回json格式数据
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ReturnDatas<Attachment> save(@RequestBody Attachment attachment) {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.SAVE_SUCCESS);
        try {

            String id = attachment.getId();
            if (StringUtils.isBlank(id)) {
                attachment.setId(null);
            }
            attachmentService.save(attachment);

            returnObject.setResult(attachment);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.SAVE_ERROR);
        }
        return returnObject;

    }


    /**
     * 修改 操作,返回json格式数据
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ReturnDatas<Attachment> update(@RequestBody Attachment attachment) {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
        try {

            String id = attachment.getId();
            if (StringUtils.isBlank(id)) {
                return ReturnDatas.getErrorReturnDatas(MessageUtils.UPDATE_NULL_ERROR);
            }
            attachmentService.update(attachment);

            returnObject.setResult(attachment);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.UPDATE_ERROR);
        }
        return returnObject;

    }


    /**
     * 删除操作
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ReturnDatas<Attachment> delete(String id) throws Exception {
        // 执行删除
        try {
            if (StringUtils.isNotBlank(id)) {
                attachmentService.deleteById(id, Attachment.class);
                return new ReturnDatas(ReturnDatas.SUCCESS, MessageUtils.DELETE_SUCCESS);
            } else {
                return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_ERROR);
    }

    /**
     * 删除多条记录
     */
    @RequestMapping(value = "/delete/more", method = RequestMethod.POST)
    public ReturnDatas deleteMore(@RequestBody String[] ids) {

        if (ids == null || ids.length < 1) {
            return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_ERROR);
        }
        try {
            List<String> listIds = Arrays.asList(ids);
            attachmentService.deleteByIds(listIds, Attachment.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_ALL_ERROR);
        }
        return new ReturnDatas(ReturnDatas.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);

    }

    /**
     * 附件上传
     * 0.其他文件,1.政策附件 2.企业认证文件 3.专家认证文件  4.企业个人认证文件,5... , 6... ~
     *
     * @param file
     * @param type
     * @return
     */
    @PostMapping("/upload")
    public ReturnDatas<Attachment> upload(@RequestParam("file") MultipartFile file, Integer type) {
        ReturnDatas<Attachment> returnDatas = ReturnDatas.getSuccessReturnDatas();
        String userId = SessionUser.getUserId();
        if (StringUtils.isEmpty(userId)) {
            logger.error("用户不存在--upload()");
            //throw new RuntimeException("用户不存在");
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
        String fileUrl = folder + LocalDateTime.now().toString().replace(":", "")+UUID.randomUUID()+suffix;//+ originalFilename;
        //上传的本地路径
        String upLoadPath = staticdir + fileUrl;
        long size = file.getSize();
        try (InputStream is = file.getInputStream();
             OutputStream os = new FileOutputStream(upLoadPath)) {
            IOUtils.copy(is, os);
            Attachment attachment = new Attachment();
            List<String> orgIdByUserId = userRoleOrgService.findOrgIdByUserId(userId, null);
            if (CollectionUtils.isNotEmpty(orgIdByUserId)) {
                String orgId = orgIdByUserId.get(0);
                attachment.setOrgId(orgId);
            } else {
                logger.error("用户部门信息异常--upload()");
                //throw new RuntimeException("用户部门信息异常!");
            }
            attachment.setCreateUser(userId);
            attachment.setCreateTime(new Date());
            attachment.setId(SecUtils.getTimeNO());
            attachment.setFileName(originalFilename);
            attachment.setFileURL(fileUrl.replace("\\", "/"));
            attachment.setFileSize(Integer.parseInt(String.valueOf(size)) / 1024);
            attachment.setAttachmentType(type == null ? 0 : type);
            attachment.setSuffix(suffix);
            attachment.setSortno(0);
            attachment.setActive(1);

            String md5Hex = DigestUtils.md5Hex(new FileInputStream(staticdir + attachment.getFileURL()));
            attachment.setMd5Code(md5Hex);
            //保存附件信息
            attachmentService.save(attachment);
            returnDatas.setResult(attachment);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage(e.getMessage());

        }
        return returnDatas;
    }

    /**
     * 表单组件上传
     * @param file
     * @param type
     * @return
     */
    @PostMapping("/formEleUpload")
    public ReturnDatas<Attachment> formEleUpload(@RequestParam("file") MultipartFile file, Integer type) {
        ReturnDatas<Attachment> returnDatas = ReturnDatas.getSuccessReturnDatas();
        String userId = SessionUser.getUserId();
        if (StringUtils.isEmpty(userId)) {
            logger.error("用户不存在--upload()");
            //throw new RuntimeException("用户不存在");
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
        String fileUrl = folder + LocalDateTime.now().toString().replace(":", "") + originalFilename;
        //上传的本地路径
        String upLoadPath = staticdir + fileUrl;
        long size = file.getSize();
        try (InputStream is = file.getInputStream();
             OutputStream os = new FileOutputStream(upLoadPath)) {
            IOUtils.copy(is, os);
            Attachment attachment = new Attachment();
            attachment.setOrgId("~");
            attachment.setCreateUser("~");
            attachment.setCreateTime(new Date());
            attachment.setId(SecUtils.getTimeNO());
            attachment.setFileName(originalFilename);
            attachment.setFileURL(fileUrl.replace("\\", "/"));
            attachment.setFileSize(Integer.parseInt(String.valueOf(size)) / 1024);
            attachment.setAttachmentType(type == null ? 0 : type);
            attachment.setSuffix(suffix);
            attachment.setSortno(0);
            attachment.setActive(1);

            String md5Hex = DigestUtils.md5Hex(new FileInputStream(staticdir + attachment.getFileURL()));
            attachment.setMd5Code(md5Hex);
            //保存附件信息
            attachmentService.save(attachment);
            returnDatas.setResult(attachment);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage(e.getMessage());

        }
        return returnDatas;
    }

}
