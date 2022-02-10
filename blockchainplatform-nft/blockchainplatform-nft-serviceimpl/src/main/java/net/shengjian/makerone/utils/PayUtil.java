package net.shengjian.makerone.utils;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.exception.HttpCodeException;
import com.wechat.pay.contrib.apache.httpclient.exception.NotFoundException;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.constant.NFTExceptionConst;
import net.shengjian.makerone.dto.PayRequestParamDTO;
import net.shengjian.makerone.exception.NFTException;
import net.shengjian.weixin.sdk.common.wxconfig.IWxPayConfig;
import net.shengjian.weixin.service.IWxPayConfigService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.xmlbeans.impl.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.*;
import java.util.Map;
import java.util.UUID;

/**
 * @descriptions: 支付工具类
 * @author: YSK
 * @date: 2022/1/11 14:39
 * @version: 1.0
 */
@Component
public class PayUtil {
    public Logger logger = LoggerFactory.getLogger(getClass());

    public static final String WX_JSAPI_URL = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";
    public static final String WX_NATIVE_URL = "https://api.mch.weixin.qq.com/v3/pay/transactions/native";
    public static final String WX_FIND_ORDER_URL = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/{out_trade_no}?mchid={mchid}";

    private static final String CHARSET = "utf-8";
    private static final String ALGORITHMS = "RSA";
    private static final String SIGNALGORITHMS = "SHA256withRSA";
    private static final String X509 = "X509";
    @Resource
    private IWxPayConfigService wxPayConfigService;

    private String appId;
    private String mchId;
    private String notifyUrl;


    private CloseableHttpClient httpClient;

    private void init() throws GeneralSecurityException, IOException, HttpCodeException, NotFoundException {
        IWxPayConfig wxPayConfig = wxPayConfigService.findWxPayConfigById(CommonConst.SITE);
        if(wxPayConfig==null){
            throw new NFTException("未找到微信支付配置! wxPayConfig is null!");
        }
        String privateKeyPath = wxPayConfig.getCertificateFile()+ File.separator+"apiclient_key.pem";
        String certPath = wxPayConfig.getCertificateFile()+ File.separator+"apiclient_cert.pem";

        //证书序列号
        X509Certificate certificate = this.getCertificate(certPath);
        String mchSerialNo = certificate.getSerialNumber().toString(16);

        String privateKey = PathUtil.readPath(privateKeyPath);
        appId = wxPayConfig.getAppId();
        mchId = wxPayConfig.getMchId();
        String apiV3Key = wxPayConfig.getApiV3Key();
        notifyUrl = wxPayConfig.getNotifyUrl();

        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(privateKey);
        /*verifier = new ScheduledUpdateCertificatesVerifier(
                new WechatPay2Credentials(mchId, new PrivateKeySigner(mchSerialNo, merchantPrivateKey)),
                apiV3Key.getBytes(StandardCharsets.UTF_8));*/
        CertificatesManager certificatesManager = CertificatesManager.getInstance();
                certificatesManager.putMerchant(mchId, new WechatPay2Credentials(mchId,
                        new PrivateKeySigner(mchSerialNo, merchantPrivateKey)), apiV3Key.getBytes(StandardCharsets.UTF_8));
        Verifier verifier = certificatesManager.getVerifier(mchId);

        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier))
                .build();
    }

    /**
     * 微信支付下单 jsapi
     * @param amountTotal 金额 (分)
     * @param description 商品描述
     * @param openid 用戶openid
     * @param orderId 订单id
     * @return 预支付id
     * @throws Exception 异常
     */
    public String placeAnOrderByJsApi(String amountTotal,String description,String openid,String orderId) throws Exception {
        init();

        //请求URL
        HttpPost httpPost = new HttpPost(WX_JSAPI_URL);
        // 请求body参数
        String reqdata = "{"
                + "\"amount\": {"
                    + "\"total\": "+amountTotal+","
                    + "\"currency\": \"CNY\""
                + "},"
                + "\"mchid\": \""+mchId+"\","
                + "\"description\": \""+description+"\","
                + "\"notify_url\": \""+notifyUrl+"\","
                + "\"payer\": {"
                    + "\"openid\": \""+openid+"\"" + "},"
                + "\"out_trade_no\": \""+orderId+"\","
                + "\"appid\": \""+appId+"\"}";
        StringEntity entity = new StringEntity(reqdata,"utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                Map map = JsonUtils.readValue(response.getEntity().getContent(), Map.class);
                return map.get("prepay_id").toString();
            } else {
                throw NFTExceptionConst.ORDER_ERR;
            }
        }finally {
            httpClient.close();
        }
    }

    /**
     * 获取微信支付二维码
     * @param productName 商品名称
     * @param sysOrderId 商户订单号
     * @param totalFee 支付金额
     * @param attach 附加数据
     * @return 二维码链接
     * @throws Exception 异常
     */
    public String getWeChatPayQRCode(String productName,String sysOrderId,String totalFee,String attach) throws Exception {
        int attachLength = attach.getBytes(StandardCharsets.UTF_8).length;
        if(attachLength>128){
            logger.error("附加数据大小超过128个字节！");
            throw NFTExceptionConst.OPERATION_FAIL;
        }
        init();
        //生成微信支付订单
        //请求URL
        HttpPost httpPost = new HttpPost(WX_NATIVE_URL);
        // 请求body参数
        String reqdata = "{"
                + "\"amount\": {"
                + "\"total\": "+totalFee+","
                + "\"currency\": \"CNY\""
                + "},"
                + "\"mchid\": \""+mchId+"\","
                + "\"description\": \""+productName+"\","
                + "\"notify_url\": \""+notifyUrl+"\","
                + "\"attach\": \""+attach+"\","
                + "\"out_trade_no\": \""+sysOrderId+"\","
                + "\"appid\": \""+appId+"\"}";
        StringEntity entity = new StringEntity(reqdata,"utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");

        CloseableHttpResponse response = httpClient.execute(httpPost);
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                Map map = JsonUtils.readValue(response.getEntity().getContent(), Map.class);
                return map.get("code_url").toString();
            } else {
                throw NFTExceptionConst.ORDER_ERR;
            }
        } finally {
            response.close();
            httpClient.close();
        }
    }
    /**
     * 调起微信收银台的js请求参数
     * @param prepay_id 预支付id
     * @return 源数据和签名后的数据
     * @throws Exception 异常
     */
    public PayRequestParamDTO getPayRequestParam(String prepay_id) throws Exception {
        IWxPayConfig wxPayConfig = wxPayConfigService.findWxPayConfigById(CommonConst.SITE);
        if(wxPayConfig==null){
            logger.error("未找到微信支付配置! wxPayConfig is null!");
            return null;
        }
        PayRequestParamDTO dto = new PayRequestParamDTO();
        dto.setAppId(wxPayConfig.getAppId());
        dto.setTimeStamp(System.currentTimeMillis()+"");
        dto.setNonceStr(UUID.randomUUID().toString().replace("-",""));
        dto.setSignType(ALGORITHMS);
        dto.setPackageStr("prepay_id="+prepay_id);

        String builder = dto.getAppId() + "\n" +
                dto.getTimeStamp() + "\n" +
                dto.getNonceStr() + "\n" +
                dto.getPackageStr() + "\n";

        String signStr = this.sign(builder);
        dto.setPaySign(signStr);
        return dto;
    }

    /**
     * 查询订单
     * @param orderId 订单id
     * @return
     */
    public Map<String,String> finOrder(String orderId) throws Exception {
        init();

        String url = WX_FIND_ORDER_URL
                .replace("{out_trade_no}",orderId)
                .replace("{mchid}",mchId);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "application/json");
        try (CloseableHttpResponse response = httpClient.execute(httpGet)){
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return JsonUtils.readValue(response.getEntity().getContent(), Map.class);
            } else {
                throw NFTExceptionConst.ORDER_ERR;
            }
        } finally {
            httpClient.close();
        }
    }

    /**
     * 签名
     * 发送方私钥签名
     */
    private String sign(String encryptText) throws Exception {
        IWxPayConfig wxPayConfig = wxPayConfigService.findWxPayConfigById(CommonConst.SITE);
        if(wxPayConfig==null){
            throw new NFTException("未找到微信支付配置! wxPayConfig is null!");
        }
        Signature signature = Signature.getInstance(SIGNALGORITHMS);
        String privateKeyPath = wxPayConfig.getCertificateFile() + File.separator + "apiclient_key.pem";
        String privateKeyStr = PathUtil.readPath(privateKeyPath);
        PrivateKey privateKey = PemUtil.loadPrivateKey(privateKeyStr);

        if (privateKey != null) {
            signature.initSign(privateKey);
            signature.update(encryptText.getBytes(CHARSET));
            byte[] bytes = signature.sign();
            return new String(Base64.encode(bytes));
        }
        return null;
    }

    private X509Certificate getCertificate(String certPath){
        try {
            InputStream is = new FileInputStream(certPath);
            CertificateFactory cf = CertificateFactory.getInstance(X509);
            X509Certificate cert = (X509Certificate) cf.generateCertificate(is);
            cert.checkValidity();
            return cert;
        } catch (CertificateNotYetValidException e) {
            throw new RuntimeException("证书尚未生效!");
        } catch (CertificateExpiredException e) {
            throw new RuntimeException("证书已过期!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件不存在!");
        } catch (CertificateException e) {
            throw new RuntimeException("无效的证书!");
        }
    }
}
