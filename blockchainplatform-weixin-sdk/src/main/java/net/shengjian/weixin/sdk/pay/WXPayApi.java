package net.shengjian.weixin.sdk.pay;


import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.weixin.sdk.common.wxconfig.IWxPayConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付的API,基于微信官方SDK改造
 * 方法增加 IWxPayConfig config 参数,使用frame的 HttpClientUtils
 * <p>
 * https://pay.weixin.qq.com/wiki/doc/api/index.html
 */
public class WXPayApi {
    private static Logger logger = LoggerFactory.getLogger(WXPayApi.class);

    private WXPayApi() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    /**
     * 判断支付结果通知中的sign是否有效
     *
     * @param reqData 向wxpay post的请求数据
     * @return 签名是否有效
     * @throws Exception
     */
    public static boolean isPayResultNotifySignatureValid(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        String signTypeInData = reqData.get(WXPayConstants.FIELD_SIGN_TYPE);
        String signType;
        if (signTypeInData == null) {
            signType = WXPayConstants.MD5;
        } else {
            signTypeInData = signTypeInData.trim();
            if (signTypeInData.length() == 0) {
                signType = WXPayConstants.MD5;
            } else if (WXPayConstants.MD5.equals(signTypeInData)) {
                signType = WXPayConstants.MD5;
            } else if (WXPayConstants.HMACSHA256.equals(signTypeInData)) {
                signType = WXPayConstants.HMACSHA256;
            } else {
                throw new Exception(String.format("Unsupported sign_type: %s", signTypeInData));
            }
        }
        return WXPayUtil.isSignatureValid(reqData, config.getKey(), signType);
    }


    /**
     * 处理 HTTPS API返回数据，转换成Map对象。return_code为SUCCESS时，验证签名。
     *
     * @param xmlStr API返回的XML格式数据
     * @return Map类型数据
     * @throws Exception
     */
    public static Map<String, String> processResponseXml(IWxPayConfig config, String xmlStr) throws Exception {
        String RETURN_CODE = "return_code";
        String return_code;
        Map<String, String> respData = WXPayUtil.xmlToMap(xmlStr);
        if (respData.containsKey(RETURN_CODE)) {
            return_code = respData.get(RETURN_CODE);
        } else {
            throw new Exception(String.format("No `return_code` in XML: %s", xmlStr));
        }

        if (return_code.equals(WXPayConstants.FAIL)) {
            return respData;
        } else if (return_code.equals(WXPayConstants.SUCCESS)) {
            if (isResponseSignatureValid(config, respData)) {
                return respData;
            } else {
                throw new Exception(String.format("Invalid sign value in XML: %s", xmlStr));
            }
        } else {
            throw new Exception(String.format("return_code value %s is invalid in XML: %s", return_code, xmlStr));
        }
    }


    /**
     * 作用：提交刷卡支付<br>
     * 场景：刷卡支付
     *
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public static Map<String, String> microPay(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        String url;
        if (WXPayConstants.useSandbox) {
            url = WXPayConstants.SANDBOX_MICROPAY_URL;
        } else {
            url = WXPayConstants.MICROPAY_URL;
        }
        String respXml = payRequest(config, url, fillRequestData(config, reqData), false);
        return processResponseXml(config, respXml);
    }


    /**
     * 提交刷卡支付，针对软POS，尽可能做成功
     * 内置重试机制，最多60s
     *
     * @param reqData
     * @return
     * @throws Exception
     */
    public static Map<String, String> microPayWithPos(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        int remainingTimeMs = 60 * 1000;
        long startTimestampMs = 0;
        int httpConnectTimeoutMs = 6 * 1000;
        Map<String, String> lastResult = null;
        Exception lastException = null;

        while (true) {
            startTimestampMs = WXPayUtil.getCurrentTimestampMs();
            int readTimeoutMs = remainingTimeMs - httpConnectTimeoutMs;
            if (readTimeoutMs > 1000) {
                try {
                    lastResult = microPay(config, reqData);
                    String returnCode = lastResult.get("return_code");
                    if (returnCode.equals("SUCCESS")) {
                        String resultCode = lastResult.get("result_code");
                        String errCode = lastResult.get("err_code");
                        if (resultCode.equals("SUCCESS")) {
                            break;
                        } else {
                            // 看错误码，若支付结果未知，则重试提交刷卡支付
                            if (errCode.equals("SYSTEMERROR") || errCode.equals("BANKERROR") || errCode.equals("USERPAYING")) {
                                remainingTimeMs = remainingTimeMs - (int) (WXPayUtil.getCurrentTimestampMs() - startTimestampMs);
                                if (remainingTimeMs <= 100) {
                                    break;
                                } else {
                                    logger.info("microPayWithPos: try micropay again");
                                    if (remainingTimeMs > 5 * 1000) {
                                        Thread.sleep(5 * 1000);
                                    } else {
                                        Thread.sleep(1 * 1000);
                                    }
                                    continue;
                                }
                            } else {
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                } catch (Exception ex) {
                    lastResult = null;
                    lastException = ex;
                }
            } else {
                break;
            }
        }

        if (lastResult == null) {
            throw lastException;
        } else {
            return lastResult;
        }
    }


    /**
     * 作用：统一下单<br>
     * 场景：公共号支付、扫码支付、APP支付
     *
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public static Map<String, String> unifiedOrder(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        String url;
        if (WXPayConstants.useSandbox) {
            url = WXPayConstants.SANDBOX_UNIFIEDORDER_URL;
        } else {
            url = WXPayConstants.UNIFIEDORDER_URL;
        }
        if (config.getNotifyUrl() != null) {
            reqData.put("notify_url", config.getNotifyUrl());
        }
        String respXml = payRequest(config, url, fillRequestData(config, reqData), false);
        return processResponseXml(config, respXml);
    }


    /**
     * 作用：查询订单<br>
     * 场景：刷卡支付、公共号支付、扫码支付、APP支付
     *
     * @param reqData 向wxpay post的请求数据 int
     * @return API返回数据
     * @throws Exception
     */
    public static Map<String, String> orderQuery(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        String url;
        if (WXPayConstants.useSandbox) {
            url = WXPayConstants.SANDBOX_ORDERQUERY_URL;
        } else {
            url = WXPayConstants.ORDERQUERY_URL;
        }
        String respXml = payRequest(config, url, fillRequestData(config, reqData), false);
        return processResponseXml(config, respXml);
    }


    /**
     * 作用：撤销订单<br>
     * 场景：刷卡支付<br>
     * 其他：需要证书
     *
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public static Map<String, String> reverse(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        String url;
        if (WXPayConstants.useSandbox) {
            url = WXPayConstants.SANDBOX_REVERSE_URL;
        } else {
            url = WXPayConstants.REVERSE_URL;
        }
        String respXml = payRequest(config, url, fillRequestData(config, reqData), true);
        return processResponseXml(config, respXml);
    }


    /**
     * 作用：关闭订单<br>
     * 场景：公共号支付、扫码支付、APP支付
     *
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public static Map<String, String> closeOrder(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        String url;
        if (WXPayConstants.useSandbox) {
            url = WXPayConstants.SANDBOX_CLOSEORDER_URL;
        } else {
            url = WXPayConstants.CLOSEORDER_URL;
        }
        String respXml = payRequest(config, url, fillRequestData(config, reqData), false);
        return processResponseXml(config, respXml);
    }


    /**
     * 作用：申请退款<br>
     * 场景：刷卡支付、公共号支付、扫码支付、APP支付<br>
     * 其他：需要证书
     *
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public static Map<String, String> refund(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        String url;
        if (WXPayConstants.useSandbox) {
            url = WXPayConstants.SANDBOX_REFUND_URL;
        } else {
            url = WXPayConstants.REFUND_URL;
        }
        String respXml = payRequest(config, url, fillRequestData(config, reqData), false);
        return processResponseXml(config, respXml);
    }


    /**
     * 作用：退款查询<br>
     * 场景：刷卡支付、公共号支付、扫码支付、APP支付
     *
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public static Map<String, String> refundQuery(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        String url;
        if (WXPayConstants.useSandbox) {
            url = WXPayConstants.SANDBOX_REFUNDQUERY_URL;
        } else {
            url = WXPayConstants.REFUNDQUERY_URL;
        }
        String respXml = payRequest(config, url, fillRequestData(config, reqData), false);
        return processResponseXml(config, respXml);
    }


    /**
     * 作用：对账单下载<br>
     * 场景：刷卡支付、公共号支付、扫码支付、APP支付<br>
     * 其他：无论是否成功都返回Map。若成功，返回的Map中含有return_code、return_msg、data，
     * 其中return_code为`SUCCESS`，data为对账单数据。
     *
     * @param reqData 向wxpay post的请求数据
     * @return 经过封装的API返回数据
     * @throws Exception
     */
    public static Map<String, String> downloadBill(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        String url;
        if (WXPayConstants.useSandbox) {
            url = WXPayConstants.SANDBOX_DOWNLOADBILL_URL;
        } else {
            url = WXPayConstants.DOWNLOADBILL_URL;
        }
        String respStr = payRequest(config, url, fillRequestData(config, reqData), false).trim();
        Map<String, String> ret;
        // 出现错误，返回XML数据
        if (respStr.indexOf("<") == 0) {
            ret = WXPayUtil.xmlToMap(respStr);
        } else {
            // 正常返回csv数据
            ret = new HashMap<>();
            ret.put("return_code", WXPayConstants.SUCCESS);
            ret.put("return_msg", "ok");
            ret.put("data", respStr);
        }
        return ret;
    }


    /**
     * 作用：交易保障<br>
     * 场景：刷卡支付、公共号支付、扫码支付、APP支付
     *
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public static Map<String, String> report(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        String url;
        if (WXPayConstants.useSandbox) {
            url = WXPayConstants.SANDBOX_REPORT_URL;
        } else {
            url = WXPayConstants.REPORT_URL;
        }
        String respXml = payRequest(config, url, fillRequestData(config, reqData), false);
        return WXPayUtil.xmlToMap(respXml);
    }


    /**
     * 作用：转换短链接<br>
     * 场景：刷卡支付、扫码支付
     *
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public static Map<String, String> shortUrl(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        String url;
        if (WXPayConstants.useSandbox) {
            url = WXPayConstants.SANDBOX_SHORTURL_URL;
        } else {
            url = WXPayConstants.SHORTURL_URL;
        }
        String respXml = payRequest(config, url, fillRequestData(config, reqData), false);
        return processResponseXml(config, respXml);
    }


    /**
     * 作用：授权码查询OPENID接口<br>
     * 场景：刷卡支付
     *
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public static Map<String, String> authCodeToOpenid(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        String url;
        if (WXPayConstants.useSandbox) {
            url = WXPayConstants.SANDBOX_AUTHCODETOOPENID_URL;
        } else {
            url = WXPayConstants.AUTHCODETOOPENID_URL;
        }
        String respXml = payRequest(config, url, fillRequestData(config, reqData), false);
        return processResponseXml(config, respXml);
    }


    /**
     * @param config
     * @param reqData
     * @return
     * @throws Exception
     */
    public static Map<String, String> facePayOrder(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        String respXml = payRequest(config, WXPayConstants.FACEPAYORDER_URL, fillRequestData(config, reqData), false);
        return processResponseXml(config, respXml);
    }


    /**
     * 获取微信人脸识别信息
     *
     * @param config
     * @param reqData
     * @return
     * @throws Exception
     */
    public static Map<String, String> getWxpayfaceAuthinfo(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        String respXml = payRequest(config, WXPayConstants.WXPAYFACE_AUTHINFO, fillRequestData(config, reqData), false);
        return processResponseXml(config, respXml);
    }

    /**
     * 人脸识别订单查询
     *
     * @param config
     * @param reqData
     * @return
     * @throws Exception
     */
    public static Map<String, String> facePayOrderQuery(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        String respXml = payRequest(config, WXPayConstants.FACEPAYQUERY_URL, fillRequestData(config, reqData), true);
        return processResponseXml(config, respXml);
    }


    /**
     * 请求，只请求一次，不做重试
     *
     * @param apiUrl
     * @param reqData
     * @param useCert 是否使用证书，针对退款、撤销等操作
     * @return
     * @throws Exception
     */
    public static String payRequest(IWxPayConfig config, String apiUrl, Map<String, String> reqData, boolean useCert) {

        BasicHttpClientConnectionManager connManager;

        long elapsedTimeMillis = 0;
        long startTimestampMs = WXPayUtil.getCurrentTimestampMs();
        Exception exception = null;
        String msgUUID = reqData.get("nonce_str");
        try {
            String data = WXPayUtil.mapToXml(reqData);
            SSLContext sslContext = null;

            if (useCert) {

                String certificateFile = config.getCertificateFile();
                if (StringUtils.isBlank(certificateFile)) {
                    return null;
                }

                String certFilePath = certificateFile;
                if (!(certificateFile.startsWith("file:/") || certificateFile.startsWith("/"))) {
                    certFilePath = GlobalStatic.rootDir + "/" + certificateFile;
                }

                try (FileInputStream inputStream = new FileInputStream(certFilePath)) {
                    // 证书
                    char[] password = config.getMchId().toCharArray();
                    KeyStore ks = KeyStore.getInstance("PKCS12");
                    ks.load(inputStream, password);
                    // 实例化密钥库 & 初始化密钥工厂
                    KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                    kmf.init(ks, password);
                    // 创建 SSLContext
                    sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

                    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                            sslContext,
                            new String[]{"TLSv1"},
                            null,
                            new DefaultHostnameVerifier());

                    connManager = new BasicHttpClientConnectionManager(
                            RegistryBuilder.<ConnectionSocketFactory>create()
                                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                                    .register("https", sslConnectionSocketFactory)
                                    .build(),
                            null,
                            null,
                            null
                    );


                }

            } else {
                connManager = new BasicHttpClientConnectionManager(
                        RegistryBuilder.<ConnectionSocketFactory>create()
                                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                                .build(),
                        null,
                        null,
                        null
                );
            }
            HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connManager).build();
            HttpPost httpPost = new HttpPost(apiUrl);

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
            httpPost.setConfig(requestConfig);

            StringEntity postEntity = new StringEntity(data, "UTF-8");
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.addHeader("User-Agent", WXPayConstants.USER_AGENT + " " + config.getMchId());
            httpPost.setEntity(postEntity);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();

            String httpHeaderPost = EntityUtils.toString(httpEntity, "UTF-8");
            elapsedTimeMillis = WXPayUtil.getCurrentTimestampMs() - startTimestampMs;
            WXPayReport reportInfo = new WXPayReport();
            reportInfo.setUuid(msgUUID);
            reportInfo.setElapsedTimeMillis(elapsedTimeMillis);
            WXPayReportApi.report(config, reportInfo);
            return httpHeaderPost;
        } catch (UnknownHostException ex) {
            exception = ex;

            elapsedTimeMillis = WXPayUtil.getCurrentTimestampMs() - startTimestampMs;
            WXPayReport reportInfo = new WXPayReport();
            reportInfo.setUuid(msgUUID);
            reportInfo.setElapsedTimeMillis(elapsedTimeMillis);
            reportInfo.setFirstHasDnsError(true);

            logger.error("UnknownHostException for reportInfo {}", reportInfo);

            WXPayReportApi.report(config, reportInfo);

        } catch (ConnectTimeoutException ex) {
            exception = ex;
            elapsedTimeMillis = WXPayUtil.getCurrentTimestampMs() - startTimestampMs;
            WXPayReport reportInfo = new WXPayReport();
            reportInfo.setUuid(msgUUID);
            reportInfo.setElapsedTimeMillis(elapsedTimeMillis);
            reportInfo.setFirstHasConnectTimeout(true);

            logger.error("ConnectTimeoutException for reportInfo {}", reportInfo);

            WXPayReportApi.report(config, reportInfo);

        } catch (SocketTimeoutException ex) {
            exception = ex;
            elapsedTimeMillis = WXPayUtil.getCurrentTimestampMs() - startTimestampMs;
            WXPayReport reportInfo = new WXPayReport();
            reportInfo.setUuid(msgUUID);
            reportInfo.setElapsedTimeMillis(elapsedTimeMillis);
            reportInfo.setFirstHasReadTimeout(true);
            logger.error("SocketTimeoutException for reportInfo {}", reportInfo);

            WXPayReportApi.report(config, reportInfo);

        } catch (Exception ex) {
            exception = ex;
            elapsedTimeMillis = WXPayUtil.getCurrentTimestampMs() - startTimestampMs;
            WXPayReport reportInfo = new WXPayReport();
            reportInfo.setUuid(msgUUID);
            reportInfo.setElapsedTimeMillis(elapsedTimeMillis);
            logger.error("Exception for reportInfo {}", reportInfo);
            WXPayReportApi.report(config, reportInfo);
        }


        // WXPayReportApi.getWXPayDomain().report(domainInfo.domain, elapsedTimeMillis, exception);
        return null;

    }


    /**
     * 向 Map 中添加 appid、mch_id、nonce_str、sign_type、sign <br>
     * 该函数适用于商户适用于统一下单等接口，不适用于红包、代金券接口
     *
     * @param reqData
     * @return
     * @throws Exception
     */
    private static Map<String, String> fillRequestData(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        reqData.put("appid", config.getAppId());
        reqData.put("mch_id", config.getMchId());
        reqData.put("nonce_str", WXPayUtil.generateNonceStr());
        if (WXPayConstants.MD5.equals(config.getSignType())) {
            reqData.put("sign_type", WXPayConstants.MD5);
        } else if (WXPayConstants.HMACSHA256.equals(config.getSignType())) {
            reqData.put("sign_type", WXPayConstants.HMACSHA256);
        }
        reqData.put("sign", WXPayUtil.generateSignature(reqData, config.getKey(), config.getSignType()));
        return reqData;
    }

    /**
     * 判断xml数据的sign是否有效，必须包含sign字段，否则返回false。
     *
     * @param reqData 向wxpay post的请求数据
     * @return 签名是否有效
     * @throws Exception
     */
    private static boolean isResponseSignatureValid(IWxPayConfig config, Map<String, String> reqData) throws Exception {
        // 返回数据的签名方式和请求中给定的签名方式是一致的
        return WXPayUtil.isSignatureValid(reqData, config.getKey(), config.getSignType());
    }


} // end class
