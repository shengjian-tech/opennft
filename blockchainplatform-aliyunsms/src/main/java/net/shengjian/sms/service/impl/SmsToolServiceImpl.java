package net.shengjian.sms.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.sms.service.ISmsToolService;
import net.shengjian.sms.util.BaseAliyunTools;
import net.shengjian.sms.util.SmsTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @descriptions:
 * @author: YSK
 * @date: 2021/7/5 16:01
 * @version: 1.0
 */
@Service("smsToolService")
public class SmsToolServiceImpl extends BaseAliyunTools implements ISmsToolService {
    public Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void send(String phoneNumbers, SmsTypeEnum typeEnum, Map templateParam) throws ClientException, InterruptedException {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        if(SmsTypeEnum.资产预警.getCode().equals(typeEnum.getCode())){
            request.putQueryParameter("SignName", aliyunProperties.getSmsWarning().getSignName());
            request.putQueryParameter("TemplateCode", aliyunProperties.getSmsWarning().getTemplateCode());
        }else if(SmsTypeEnum.通知.getCode().equals(typeEnum.getCode())) {
            request.putQueryParameter("SignName", aliyunProperties.getSmsNotice().getSignName());
            request.putQueryParameter("TemplateCode", aliyunProperties.getSmsNotice().getTemplateCode());
        } else if(SmsTypeEnum.验证码.getCode().equals(typeEnum.getCode())) {
            request.putQueryParameter("SignName", aliyunProperties.getSmsVerify().getSignName());
            request.putQueryParameter("TemplateCode", aliyunProperties.getSmsVerify().getTemplateCode());
        } else if(SmsTypeEnum.补充条件.getCode().equals(typeEnum.getCode())) {
            request.putQueryParameter("SignName", aliyunProperties.getSmsSupplementary().getSignName());
            request.putQueryParameter("TemplateCode", aliyunProperties.getSmsSupplementary().getTemplateCode());
        } else {
            request.putQueryParameter("SignName", aliyunProperties.getSmsEnough().getSignName());
            request.putQueryParameter("TemplateCode", aliyunProperties.getSmsEnough().getTemplateCode());
        }
        if (templateParam != null){
            Thread.sleep(1000);
            request.putQueryParameter("TemplateParam", JsonUtils.writeValueAsString(templateParam));
            try {
                CommonResponse response = client.getCommonResponse(request);
                String data = response.getData();
                logger.error("发送阿里云短信 - {}", data);
                Map<String,String> map = JsonUtils.readValue(data, Map.class);
                System.out.println(data);
                String message = map.get("Message");
                if(!"OK".equals(message)){
                    logger.error("短信业务繁忙!请稍后重试!--send()");
                    throw new RuntimeException("短信业务繁忙!请稍后重试!");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
