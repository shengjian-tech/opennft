package net.shengjian.makerone.service.impl;

import com.alibaba.druid.util.StringUtils;
import net.shengjian.makerone.service.INftMailService;
import net.shengjian.makerone.vo.MailVO;
import net.shengjian.system.service.impl.BaseSpringrainServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

@Service("nftMailService")
public class NftMailServiceImpl extends BaseSpringrainServiceImpl implements INftMailService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Override
    public MailVO sendMail(MailVO mailVo) throws Exception {
        checkMail(mailVo);
        sendMimeMail(mailVo);
        return mailVo;
        }

    @Override
    public void checkMail(MailVO mailVo) throws Exception {
        if (StringUtils.isEmpty(mailVo.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    @Override
    public void sendMimeMail(MailVO mailVo) throws Exception {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true,"utf-8");
            mailVo.setFrom(getMailSendFrom());
            messageHelper.setFrom(mailVo.getFrom());
            messageHelper.setTo(mailVo.getTo());
            messageHelper.setSubject(mailVo.getSubject());
            messageHelper.setText(mailVo.getText());

            if (mailVo.getPath() != null) {
                File file = new File(mailVo.getPath());
                messageHelper.addAttachment(file.getName(),file);
            }
            if (mailVo.getSentDate() == null) {
                mailVo.setSentDate(new Date());
                messageHelper.setSentDate(mailVo.getSentDate());
            }
            mailSender.send(messageHelper.getMimeMessage());

            logger.info("发送邮件成功：{}->{}", mailVo.getFrom(), mailVo.getTo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public String getMailSendFrom() throws Exception {
        return mailSender.getJavaMailProperties().getProperty("from");

    }
}
