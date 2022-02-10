package net.shengjian.sms.service;

import com.aliyuncs.exceptions.ClientException;
import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.sms.util.SmsTypeEnum;

import java.util.Map;

/**
 * @descriptions:
 * @author: YSK
 * @date: 2021/7/5 15:59
 * @version: 1.0
 */
@RpcServiceAnnotation
public interface ISmsToolService {
    /**
     * 短信发送
     * @param phoneNumbers
     * @param typeEnum
     * @param templateParam
     */
    void send(String phoneNumbers, SmsTypeEnum typeEnum, Map templateParam) throws ClientException, InterruptedException;
}
