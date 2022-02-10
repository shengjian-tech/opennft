package net.shengjian.system.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 配置 RequestMappingHandlerMapping,有产品线以外的 项目分支进行集成,复写产品线的RequestMapping
 * 产品线里不应该存在@Order注解的mapping
 */
@Configuration("configuration-RequestMappingHandlerMappingConfig")
public class RequestMappingHandlerMappingConfig implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMappingOrder();
    }
}
