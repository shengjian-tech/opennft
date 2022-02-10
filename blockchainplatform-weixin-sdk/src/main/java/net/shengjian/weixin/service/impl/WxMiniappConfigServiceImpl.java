package net.shengjian.weixin.service.impl;

import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.weixin.entity.WxMiniappConfig;
import net.shengjian.weixin.sdk.common.WxAccessToken;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMiniappConfig;
import net.shengjian.weixin.service.IWxAccessTokenService;
import net.shengjian.weixin.service.IWxMiniappConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("wxMiniappConfigService")
public class WxMiniappConfigServiceImpl extends BaseSpringrainWeiXinServiceImpl implements IWxMiniappConfigService {

    private String cacheKeyPrefix = "wxminiapp_config_";

    @Resource
    private IWxAccessTokenService wxAccessTokenService;

    @Override
    public IWxMiniappConfig findWxMiniappConfigById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        IWxMiniappConfig wxMiniappConfig = null;
        try {
            wxMiniappConfig = super.getByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + id, WxMiniappConfig.class);
            if (wxMiniappConfig == null) {
                wxMiniappConfig = super.findById(id, WxMiniappConfig.class);
                if (wxMiniappConfig == null) {
                    return null;
                }
                super.putByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + id, wxMiniappConfig);

            }

            WxAccessToken wxAccessToken = wxAccessTokenService.findWxAccessToken(wxMiniappConfig);
            wxMiniappConfig.setAccessToken(wxAccessToken.getAccessToken());


        } catch (Exception e) {
            wxMiniappConfig = null;
            logger.error(e.getMessage(), e);
        }

        return wxMiniappConfig;
    }

    /**
     * 缓存处理,可以把配置进行缓存更新 @
     */
    @Override
    public IWxMiniappConfig updateWxMiniappConfig(WxMiniappConfig wxminiappconfig) {

        String id = wxminiappconfig.getId();
        if (StringUtils.isBlank(id)) {
            return null;
        }

        try {
            super.update(wxminiappconfig);
            super.putByCache(GlobalStatic.wxConfigCacheKey, id, wxminiappconfig);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return wxminiappconfig;
    }


}
