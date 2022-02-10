package net.shengjian.weixin.service;

import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.weixin.entity.WxMpConfig;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;

import java.util.Map;

//@RpcServiceAnnotation(implpackage = "weixin.wxconfig.impl")
@RpcServiceAnnotation
public interface IWxMpConfigService {

    /**
     * 根据ID查找微信配置,可以进行缓存处理
     *
     * @param id
     * @return
     */
    IWxMpConfig findWxMpConfigById(String id);

    /**
     * 更新WxMpConfig,可以进行缓存处理
     *
     * @param wxmpconfig
     * @return
     */
    IWxMpConfig updateWxMpConfig(WxMpConfig wxmpconfig);


    /**
     * 根据siteid和request查询jsapi配置信息
     *
     * @param wxmpconfig
     * @param url
     * @return
     * @throws Exception
     */
    Map<String, String> findMpJsApiParam(IWxMpConfig wxmpconfig, String url) throws Exception;


}
