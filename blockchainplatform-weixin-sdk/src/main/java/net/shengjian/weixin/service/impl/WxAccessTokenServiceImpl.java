package net.shengjian.weixin.service.impl;

import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.weixin.sdk.common.WxAccessToken;
import net.shengjian.weixin.sdk.common.WxCardTicket;
import net.shengjian.weixin.sdk.common.WxJsTicket;
import net.shengjian.weixin.sdk.common.wxconfig.IWxConfig;
import net.shengjian.weixin.sdk.mp.AccessTokenApi;
import net.shengjian.weixin.service.IWxAccessTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service("wxAccessTokenService")
public class WxAccessTokenServiceImpl extends BaseSpringrainWeiXinServiceImpl implements IWxAccessTokenService {

    private String cacheKeyPrefix = "wx_accesstoken_";

    @Override
    public WxAccessToken findWxAccessToken(IWxConfig wxConfig) throws Exception {
        if (wxConfig == null || StringUtils.isBlank(wxConfig.getAppId())) {
            return null;
        }
        WxAccessToken wxAccessToken = super.getByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + wxConfig.getAppId(), WxAccessToken.class);
        if (wxAccessToken == null || wxAccessToken.isAccessTokenExpired()) {
            wxAccessToken = AccessTokenApi.getAccessToken(wxConfig);
            if (wxAccessToken == null) {
                return null;
            }
            super.putByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + wxConfig.getAppId(), wxAccessToken);
        }

        return wxAccessToken;
    }

    @Override
    public WxCardTicket findWxCardTicket(IWxConfig wxConfig) throws Exception {
        if (wxConfig == null || StringUtils.isBlank(wxConfig.getAppId()) || StringUtils.isBlank(wxConfig.getAccessToken())) {
            return null;
        }

        WxCardTicket wxCardTicket = super.getByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + "wxcardticket_" + wxConfig.getAppId(), WxCardTicket.class);
        if (wxCardTicket == null || wxCardTicket.isCardTicketExpired()) {
            wxCardTicket = AccessTokenApi.getCardTicket(wxConfig);
            if (wxCardTicket == null) {
                return null;
            }
            super.putByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + "wxcardticket_" + wxConfig.getAppId(), wxCardTicket);
        }

        return wxCardTicket;
    }

    @Override
    public WxJsTicket findWxJsTicket(IWxConfig wxConfig) throws Exception {
        if (wxConfig == null || StringUtils.isBlank(wxConfig.getAppId()) || StringUtils.isBlank(wxConfig.getAccessToken())) {
            return null;
        }

        WxJsTicket wxJsTicket = super.getByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + "wxjsticket_" + wxConfig.getAppId(), WxJsTicket.class);
        if (wxJsTicket == null || wxJsTicket.isJsTicketExpired()) {
            wxJsTicket = AccessTokenApi.getJsTicket(wxConfig);
            if (wxJsTicket == null) {
                return null;
            }
            super.putByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + "wxjsticket_" + wxConfig.getAppId(), wxJsTicket);
        }

        return wxJsTicket;
    }
}
