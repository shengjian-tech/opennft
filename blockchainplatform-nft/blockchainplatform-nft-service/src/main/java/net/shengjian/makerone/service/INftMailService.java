package net.shengjian.makerone.service;


import net.shengjian.makerone.vo.MailVO;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.service.IBaseSpringrainService;

@RpcServiceAnnotation
public interface INftMailService extends IBaseSpringrainService {
    public MailVO sendMail(MailVO mailVo) throws Exception;

    public void checkMail(MailVO mailVo) throws Exception;


    public void sendMimeMail(MailVO mailVo) throws Exception;

    public String getMailSendFrom() throws Exception;
}
