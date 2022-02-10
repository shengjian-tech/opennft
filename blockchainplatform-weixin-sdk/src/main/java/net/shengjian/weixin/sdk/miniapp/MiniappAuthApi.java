package net.shengjian.weixin.sdk.miniapp;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMiniappConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 小城的API接口
 * <p>
 * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
 * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/user-info/auth.getPaidUnionId.html
 */
public class MiniappAuthApi {
    private static final Logger logger = LoggerFactory.getLogger(MiniappAuthApi.class);
    private static String code2SessionUrl = WxConsts.mpapiurl + "/sns/jscode2session";
    private static String getPaidUnionIdUrl = WxConsts.mpapiurl + "/wxa/getpaidunionid?access_token=";

    private MiniappAuthApi() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    public static ApiResult code2Session(IWxMiniappConfig config, String jsCode) {
        String apiurl = code2SessionUrl + "?appid=" + config.getAppId() + "&secret=" + config.getSecret() + "&js_code=" + jsCode + "&grant_type=authorization_code";
        String jsonResult = HttpClientUtils.sendHttpGet(apiurl);
        return new ApiResult(jsonResult);
    }

    public static ApiResult getPaidUnionId(IWxMiniappConfig config, String openId) {
        String apiurl = getPaidUnionIdUrl + config.getAccessToken() + "&openid=" + openId;
        String jsonResult = HttpClientUtils.sendHttpGet(apiurl);
        return new ApiResult(jsonResult);
    }


}
