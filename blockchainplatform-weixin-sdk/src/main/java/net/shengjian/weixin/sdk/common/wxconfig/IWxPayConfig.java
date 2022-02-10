package net.shengjian.weixin.sdk.common.wxconfig;


public interface IWxPayConfig extends IWxConfig {


    /**
     * 获取商户证路径
     *
     * @return 商户证书路径
     */
    String getCertificateFile();

    void setCertificateFile(String certificateFile);

    /**
     * 获取 Mch ID
     *
     * @return Mch ID
     */
    String getMchId();


    /**
     * 获取 API 密钥
     *
     * @return API密钥
     */
    String getKey();

    String getNotifyUrl();

    String getSignType();

    String getApiV3Key();


}
