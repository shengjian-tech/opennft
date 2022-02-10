package net.shengjian.weixin.service;

import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.weixin.entity.WxCpConfig;
import net.shengjian.weixin.sdk.common.wxconfig.IWxCpConfig;

//@RpcServiceAnnotation(implpackage = "weixin.wxconfig.impl")
@RpcServiceAnnotation
public interface IWxCpConfigService {

    /**
     * 根据ID查找微信配置,可以进行缓存处理
     *
     * @param id
     * @return
     */
    IWxCpConfig findWxCpConfigById(String id) throws Exception;

    /**
     * 更新WxCpConfig,可以进行缓存处理
     *
     * @param wxcpconfig
     * @return
     */
    IWxCpConfig updateWxCpConfig(WxCpConfig wxcpconfig);


}
