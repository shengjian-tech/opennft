package net.shengjian.weixin.service;

import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.weixin.sdk.common.WxAccessToken;
import net.shengjian.weixin.sdk.common.WxCardTicket;
import net.shengjian.weixin.sdk.common.WxJsTicket;
import net.shengjian.weixin.sdk.common.wxconfig.IWxConfig;

@RpcServiceAnnotation
public interface IWxAccessTokenService {

    /**
     * 获取AccessToken
     *
     * @param wxConfig
     * @return
     */
    WxAccessToken findWxAccessToken(IWxConfig wxConfig) throws Exception;

    /**
     * 会员卡 ticket
     *
     * @param wxConfig
     * @return
     * @throws Exception
     */
    WxCardTicket findWxCardTicket(IWxConfig wxConfig) throws Exception;

    /**
     * js ticket
     *
     * @param wxConfig
     * @return
     * @throws Exception
     */
    WxJsTicket findWxJsTicket(IWxConfig wxConfig) throws Exception;

}
