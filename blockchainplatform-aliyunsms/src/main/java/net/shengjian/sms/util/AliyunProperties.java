package net.shengjian.sms.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aliyun")
public class AliyunProperties {

    private String accessKeySecret;
    private String accessKeyId;
    private String regionId = "cn-shanghai";
    private SmsProperties smsNotice;
    private SmsProperties smsVerify;
    private SmsProperties smsSupplementary;
    private SmsProperties smsEnough;
    private OssProperties oss;
    private SmsProperties smsWarning;

    public static class SmsProperties {
        private String signName;
        private String templateCode;

        public String getSignName() {
            return signName;
        }

        public void setSignName(String signName) {
            this.signName = signName;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public void setTemplateCode(String templateCode) {
            this.templateCode = templateCode;
        }
    }

    public static class OssProperties {
        private String folderName;
        private String bucketName;
        private String endpoint;
        private String cdnDomain;

        public String getFolderName() {
            return folderName;
        }

        public void setFolderName(String folderName) {
            this.folderName = folderName;
        }

        public String getBucketName() {
            return bucketName;
        }

        public void setBucketName(String bucketName) {
            this.bucketName = bucketName;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getCdnDomain() {
            return cdnDomain;
        }

        public void setCdnDomain(String cdnDomain) {
            this.cdnDomain = cdnDomain;
        }
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public SmsProperties getSmsNotice() {
        return smsNotice;
    }

    public void setSmsNotice(SmsProperties smsNotice) {
        this.smsNotice = smsNotice;
    }

    public SmsProperties getSmsVerify() {
        return smsVerify;
    }

    public void setSmsVerify(SmsProperties smsVerify) {
        this.smsVerify = smsVerify;
    }

    public SmsProperties getSmsSupplementary() {
        return smsSupplementary;
    }

    public void setSmsSupplementary(SmsProperties smsSupplementary) {
        this.smsSupplementary = smsSupplementary;
    }

    public SmsProperties getSmsEnough() {
        return smsEnough;
    }

    public void setSmsEnough(SmsProperties smsEnough) {
        this.smsEnough = smsEnough;
    }

    public OssProperties getOss() {
        return oss;
    }

    public void setOss(OssProperties oss) {
        this.oss = oss;
    }

    public SmsProperties getSmsWarning() {
        return smsWarning;
    }

    public void setSmsWarning(SmsProperties smsWarning) {
        this.smsWarning = smsWarning;
    }
}

