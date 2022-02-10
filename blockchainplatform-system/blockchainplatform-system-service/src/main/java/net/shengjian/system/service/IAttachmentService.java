package net.shengjian.system.service;

import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.entity.Attachment;

import java.util.List;

/**
 * 附件service
 *
 * @author springrain<Auto generate>
 * @version 2021-06-09 11:55:02
 */
@RpcServiceAnnotation
public interface IAttachmentService extends IBaseSpringrainService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    Attachment findAttachmentById(String id) throws Exception;


    /**
     * 删除政策相关的附件
     *
     * @param policyId
     */
    void deleteByPolicyId(String policyId) throws Exception;

    /**
     * 删除不存在的附件Id
     *
     * @param policyId
     * @param attachmentIdList
     * @throws Exception
     */
    void deleteByListId(String policyId, List<String> attachmentIdList) throws Exception;

    /**
     * 根据业务ID,和业务类型 查询相关的附件
     *
     * @param businessId
     * @param businessType 附件类型,1.政策附件.2.企业认证文件3.专家认证文件.4.企业个人认证文件.0.其他文件
     * @return
     */
    List<Attachment> findAttachmentByBusinessId(String businessId, Integer businessType) throws Exception;
}
