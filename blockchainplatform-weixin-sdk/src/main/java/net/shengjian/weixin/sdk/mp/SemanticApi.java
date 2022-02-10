package net.shengjian.weixin.sdk.mp;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;

/**
 * 语义理解接口
 * <p>
 * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Intelligent_Interface/Natural_Language_Processing.html
 */
public class SemanticApi {

    private static String semanticUrl = WxConsts.mpapiurl + "/semantic/semproxy/search?access_token=";

    /**
     * 发送语义理解请求
     *
     * @param jsonStr POST数据格式：JSON
     * @return ApiResult
     */
    public static ApiResult search(IWxMpConfig wxmpconfig, String jsonStr) {
        String url = semanticUrl + wxmpconfig.getAccessToken();
        String jsonResult = HttpClientUtils.sendHttpPost(url, jsonStr);
        return new ApiResult(jsonResult);
    }

}
