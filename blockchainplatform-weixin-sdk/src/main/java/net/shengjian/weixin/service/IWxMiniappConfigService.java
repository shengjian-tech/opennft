package net.shengjian.weixin.service;

import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.weixin.entity.WxMiniappConfig;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMiniappConfig;

//@RpcServiceAnnotation(implpackage = "weixin.wxconfig.impl")
@RpcServiceAnnotation
public interface IWxMiniappConfigService {

    /**
     * 根据ID查找微信配置,可以进行缓存处理
     *
     * @param id
     * @return
     */
    IWxMiniappConfig findWxMiniappConfigById(String id);


    /**
     * 更新WxMiniappConfig,可以进行缓存处理
     *
     * @param wxminiappconfig
     * @return
     */
    IWxMiniappConfig updateWxMiniappConfig(WxMiniappConfig wxminiappconfig);


}
