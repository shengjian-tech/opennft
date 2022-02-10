package net.shengjian.weixin.service;

import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.weixin.entity.WxPayConfig;
import net.shengjian.weixin.sdk.common.wxconfig.IWxPayConfig;

//@RpcServiceAnnotation(implpackage = "weixin.wxconfig.impl")
@RpcServiceAnnotation
public interface IWxPayConfigService {

    /**
     * 根据ID查找微信配置,可以进行缓存处理
     *
     * @param id
     * @return
     */
    IWxPayConfig findWxPayConfigById(String id);

    /**
     * 更新WxPayConfig,可以进行缓存处理
     *
     * @param wxpayconfig
     * @return
     */
    IWxPayConfig updateWxPayConfig(WxPayConfig wxpayconfig);


}
