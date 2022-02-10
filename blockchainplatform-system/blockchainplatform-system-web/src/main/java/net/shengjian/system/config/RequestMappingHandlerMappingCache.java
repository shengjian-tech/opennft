package net.shengjian.system.config;

import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class RequestMappingHandlerMappingCache extends RequestMappingHandlerMapping {

    // 绑定HandlerMethod 和 RequestMappingInfo的关系
    private static Map<HandlerMethod, RequestMappingInfo> registerHandlerMethodMap = new HashMap<HandlerMethod, RequestMappingInfo>();

    // 绑定lookupPath 和 HandlerMethod的关系
    private static Map<String, HandlerMethod> lookupHandlerMethodMap = new HashMap<String, HandlerMethod>();

    /**
     * 注册时绑定 HandlerMethod 和 RequestMappingInfo的关系
     */
    @Override
    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
        HandlerMethod handlerMethod = createHandlerMethod(handler, method);
        // RequestMapping rMapping = AnnotationUtils.getAnnotation(method,
        // RequestMapping.class);
        registerHandlerMethodMap.put(handlerMethod, mapping);
        super.registerHandlerMethod(handler, method, mapping);
    }


    /**
     * 根据路由直接返回HandlerMethod,不再查找.
     */
    @Override
    @Nullable
    protected HandlerMethod lookupHandlerMethod(String lookupPath, HttpServletRequest request) throws Exception {

        // 查找的key,默认使用请求方法+uri实现,如果id在uri里,需要前端请求时放入服务名,通过服务名做key,不然uri会非常多,缓存会比较吃力.
        String mapKey = request.getMethod() + "_" + lookupPath;

        HandlerMethod handlerMethod = lookupHandlerMethodMap.get(mapKey);

        if (handlerMethod != null) {
            // 处理PathVariable的参数,registerHandlerMethodMap就是为了这个功能记录了HandlerMethod 和
            // RequestMappingInfo的关系
            handleMatch(registerHandlerMethodMap.get(handlerMethod), lookupPath, request);
            return handlerMethod;
        } else {
            handlerMethod = super.lookupHandlerMethod(lookupPath, request);
            lookupHandlerMethodMap.put(mapKey, handlerMethod);
        }

        return handlerMethod;

    }


}
