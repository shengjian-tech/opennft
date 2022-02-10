package net.shengjian.weixin.sdk.mp;


import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;

/**
 * 获取自动回复规则
 * <p>
 * https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Getting_Rules_for_Auto_Replies.html
 */
public class AutoReplyInfoApi {

    private static String getCurrentAutoreplyInfoUrl = WxConsts.mpapiurl + "/cgi-bin/get_current_autoreply_info?access_token=";

    /**
     * 获取自动回复
     *
     * @param wxmpconfig
     * @return
     */
    public static ApiResult getAutoreply(IWxMpConfig wxmpconfig) {
        String jsonResult = HttpClientUtils.sendHttpGet(getCurrentAutoreplyInfoUrl + wxmpconfig.getAccessToken());
        return new ApiResult(jsonResult);
    }

}
