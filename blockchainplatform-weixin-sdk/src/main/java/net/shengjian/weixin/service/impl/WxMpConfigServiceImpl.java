package net.shengjian.weixin.service.impl;

import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.weixin.entity.WxMpConfig;
import net.shengjian.weixin.sdk.common.WxAccessToken;
import net.shengjian.weixin.sdk.common.WxCryptUtils;
import net.shengjian.weixin.sdk.common.WxJsTicket;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;
import net.shengjian.weixin.service.IWxAccessTokenService;
import net.shengjian.weixin.service.IWxMpConfigService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("wxMpConfigService")
public class WxMpConfigServiceImpl extends BaseSpringrainWeiXinServiceImpl implements IWxMpConfigService {

    private String cacheKeyPrefix = "wxmp_config_";

    @Resource
    private IWxAccessTokenService wxAccessTokenService;

    @Override
    public IWxMpConfig findWxMpConfigById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        IWxMpConfig wxMpConfig = null;
        try {
            wxMpConfig = super.getByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + id, WxMpConfig.class);
            if (wxMpConfig == null) {
                wxMpConfig = super.findById(id, WxMpConfig.class);
                if (wxMpConfig == null) {
                    return null;
                }
                super.putByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + id, wxMpConfig);
            }


            WxAccessToken wxAccessToken = wxAccessTokenService.findWxAccessToken(wxMpConfig);
            wxMpConfig.setAccessToken(wxAccessToken.getAccessToken());

            // WxCardTicket wxCardTicket = wxAccessTokenService.findWxCardTicket(wxMpConfig);
            // wxMpConfig.setCardApiTicket(wxCardTicket.getCardTicket());

        } catch (Exception e) {
            wxMpConfig = null;
            logger.error(e.getMessage(), e);
        }

        return wxMpConfig;
    }

    /**
     * 缓存处理,可以把配置进行缓存更新 @
     */
    @Override
    public IWxMpConfig updateWxMpConfig(WxMpConfig wxmpconfig) {

        String id = wxmpconfig.getId();
        if (StringUtils.isBlank(id)) {
            return null;
        }

        try {
            super.update(wxmpconfig);
            super.putByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + id, wxmpconfig);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return wxmpconfig;
    }

    @Override
    public Map<String, String> findMpJsApiParam(IWxMpConfig wxMpConfig, String url) throws Exception {

        if (wxMpConfig == null || StringUtils.isBlank(url)) {
            return null;
        }


        WxJsTicket wxJsTicket = wxAccessTokenService.findWxJsTicket(wxMpConfig);

        Map<String, String> params = new HashMap<>();

        String jsapiTicket = wxJsTicket.getJsTicket();
        String nonceStr = RandomStringUtils.random(32, "123456789"); // 8位随机数
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String jsApiSignature = this.getSignature(nonceStr, jsapiTicket, timestamp, url);

        params.put("timestamp", timestamp);
        params.put("nonceStr", nonceStr);
        params.put("jsApiSignature", jsApiSignature);
        params.put("appId", wxMpConfig.getAppId());
        return params;
    }

    private String getSignature(String nonceStr, String jsapiTicket, String timestamp, String url) {

        StringBuilder sb = new StringBuilder();
        sb.append("jsapi_ticket=").append(jsapiTicket).append("&noncestr=").append(nonceStr).append("&timestamp=")
                .append(timestamp).append("&url=").append(url);
        return WxCryptUtils.genSHA1(sb.toString());

    }


}
