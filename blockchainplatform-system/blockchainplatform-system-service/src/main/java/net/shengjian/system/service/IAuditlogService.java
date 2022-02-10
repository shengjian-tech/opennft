package net.shengjian.system.service;

import net.shengjian.frame.entity.AuditLog;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2013-04-02 10:17:31
 * @see IAuditlogService
 */
@RpcServiceAnnotation
public interface IAuditlogService extends IBaseSpringrainService {
    String saveAuditlog(AuditLog entity) throws Exception;


    Integer updateAuditlog(AuditLog entity) throws Exception;

    AuditLog findAuditlogById(Object id) throws Exception;
}
