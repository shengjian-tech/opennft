package net.shengjian.weixin.sdk.pay;

import net.shengjian.weixin.sdk.common.WxConsts;
import org.apache.http.client.HttpClient;

/**
 * 常量
 */
public class WXPayConstants {

    public static final String FAIL = "FAIL";

    // public static final String DOMAIN_API = "api.mch.weixin.qq.com";
    // public static final String DOMAIN_API2 = "api2.mch.weixin.qq.com";
    // public static final String DOMAIN_APIHK = "apihk.mch.weixin.qq.com";
    // public static final String DOMAIN_APIUS = "apius.mch.weixin.qq.com";
    public static final String SUCCESS = "SUCCESS";
    public static final String HMACSHA256 = "HMAC-SHA256";
    public static final String MD5 = "MD5";
    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_TYPE = "sign_type";
    public static final String WXPAYSDK_VERSION = "WXPaySDK/3.0.9";
    public static final String USER_AGENT = WXPAYSDK_VERSION +
            " (" + System.getProperty("os.arch") + " " + System.getProperty("os.name") + " " + System.getProperty("os.version") +
            ") Java/" + System.getProperty("java.version") + " HttpClient/" + HttpClient.class.getPackage().getImplementationVersion();
    public static final String MICROPAY_URL = WxConsts.mppaybaseurl + "/pay/micropay";
    public static final String UNIFIEDORDER_URL = WxConsts.mppaybaseurl + "/pay/unifiedorder";
    public static final String ORDERQUERY_URL = WxConsts.mppaybaseurl + "/pay/orderquery";
    public static final String REVERSE_URL = WxConsts.mppaybaseurl + "/secapi/pay/reverse";
    public static final String CLOSEORDER_URL = WxConsts.mppaybaseurl + "/pay/closeorder";
    public static final String REFUND_URL = WxConsts.mppaybaseurl + "/secapi/pay/refund";
    public static final String REFUNDQUERY_URL = WxConsts.mppaybaseurl + "/pay/refundquery";
    public static final String DOWNLOADBILL_URL = WxConsts.mppaybaseurl + "/pay/downloadbill";
    public static final String REPORT_URL = WxConsts.mppaybaseurl + "/payitil/report";
    public static final String SHORTURL_URL = WxConsts.mppaybaseurl + "/tools/shorturl";
    public static final String AUTHCODETOOPENID_URL = WxConsts.mppaybaseurl + "/tools/authcodetoopenid";

    public static final String FACEPAYORDER_URL = WxConsts.mppaybaseurl + "/pay/facepay";
    public static final String FACEPAYQUERY_URL = WxConsts.mppaybaseurl + "/pay/facepayquery";
    public static final String WXPAYFACE_AUTHINFO = WxConsts.payappbaseurl + "/face/get_wxpayface_authinfo";


    // sandbox
    public static final boolean useSandbox = false;
    public static final String SANDBOX_MICROPAY_URL = WxConsts.mppaybaseurl + "/sandboxnew/pay/micropay";
    public static final String SANDBOX_UNIFIEDORDER_URL = WxConsts.mppaybaseurl + "/sandboxnew/pay/unifiedorder";
    public static final String SANDBOX_ORDERQUERY_URL = WxConsts.mppaybaseurl + "/sandboxnew/pay/orderquery";
    public static final String SANDBOX_REVERSE_URL = WxConsts.mppaybaseurl + "/sandboxnew/secapi/pay/reverse";
    public static final String SANDBOX_CLOSEORDER_URL = WxConsts.mppaybaseurl + "/sandboxnew/pay/closeorder";
    public static final String SANDBOX_REFUND_URL = WxConsts.mppaybaseurl + "/sandboxnew/secapi/pay/refund";
    public static final String SANDBOX_REFUNDQUERY_URL = WxConsts.mppaybaseurl + "/sandboxnew/pay/refundquery";
    public static final String SANDBOX_DOWNLOADBILL_URL = WxConsts.mppaybaseurl + "/sandboxnew/pay/downloadbill";
    public static final String SANDBOX_REPORT_URL = WxConsts.mppaybaseurl + "/sandboxnew/payitil/report";
    public static final String SANDBOX_SHORTURL_URL = WxConsts.mppaybaseurl + "/sandboxnew/tools/shorturl";
    public static final String SANDBOX_AUTHCODETOOPENID_URL = WxConsts.mppaybaseurl + "/sandboxnew/tools/authcodetoopenid";


}

