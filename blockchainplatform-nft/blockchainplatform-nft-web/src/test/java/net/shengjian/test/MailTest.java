package net.shengjian.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
    JavaMailSender mailSender;


    @Test
    public void mail(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("CESHI YOUJIAN");
        mailMessage.setText("12345600");
        mailMessage.setTo("416676416@qq.com");
        mailMessage.setFrom("416676416@qq.com");

        mailSender.send(mailMessage);
    }
}
