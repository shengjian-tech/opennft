package net.shengjian.system.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现一个URI映射多个业务实现,通过@Order注解标识优先级,目前强制一个URI只能有一个@Order起效.
 * 风险:复写请求A和未复写的B存在逻辑关联关系,如果产品更新逻辑,复写的A和产品的B会存在逻辑兼容风险.需要规范的版本依赖管理体系(例如1.0.0和1.0.9保持逻辑兼容,只是bug修复),补丁包里的请求需要回归测试......
 * 风险:复写请求A如果是从产品复制的代码,修改其中几行,如果产品A的代码有了不兼容性修改,需要根据情况合并或者修改复写A的代码
 * 注意要保留普通mapping的重复报错检查
 * 前台VUE实现多模块,可以单独运行,也可以集成,实现路由页面拦截替换,最好是组件或页面元素级别的拦截替换.
 */
public class RequestMappingHandlerMappingOrder extends RequestMappingHandlerMapping {


    // post uri 作为key,对应构造的RequestMappingInfo,用于删除和@Order冲突的RequestMapping
    private static Map<String, RequestMappingInfo> requestMappingInfoMap = new HashMap<>();

    /**
     * 注册时绑定 HandlerMethod 和 RequestMappingInfo的关系
     */
    @Override
    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {

        //普通mapping key
        //String key = mapping.getMethodsCondition().toString() + " " + mapping.getPatternsCondition();
        // springboot 2.6.0 默认策略已从 AntPathMatcher 更改为 PathPatternParser
        String key = mapping.getMethodsCondition().toString() + " " + mapping.getPathPatternsCondition();
        // order mapping key
        String orderKey = key + " order";
        if (method.getDeclaringClass().isAnnotationPresent(Order.class) || method.isAnnotationPresent(Order.class)) {//如果存在order,就删除掉已经注册的 普通mapping
            RequestMappingInfo oldMapping = requestMappingInfoMap.get(key);
            if (oldMapping != null && requestMappingInfoMap.get(orderKey) == null) {//如果没有order mapping映射,就删除这个普通映射
                //删除掉映射
                super.unregisterMapping(oldMapping);
            }
            //放入 order
            requestMappingInfoMap.put(orderKey, mapping);
        } else if (requestMappingInfoMap.get(orderKey) != null) {//已经存在 order mapping 了,就不再处理 普通mapping了
            return;
        } else {//其他情况 作为 普通mapping
            requestMappingInfoMap.put(key, mapping);
        }
        super.registerHandlerMethod(handler, method, mapping);
    }


}
