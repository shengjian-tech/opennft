package net.shengjian.sms.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@EnableConfigurationProperties(AliyunProperties.class)
public class BaseAliyunTools {

    @Resource
    protected AliyunProperties aliyunProperties;
    protected static DefaultAcsClient client;

    @PostConstruct
    public void init() {
        DefaultProfile profile = DefaultProfile.getProfile(aliyunProperties.getRegionId(), aliyunProperties.getAccessKeyId(), aliyunProperties.getAccessKeySecret());
        client = new DefaultAcsClient(profile);
    }

}
