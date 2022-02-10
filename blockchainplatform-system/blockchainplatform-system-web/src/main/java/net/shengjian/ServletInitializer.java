package net.shengjian;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 打成独立war包,就不能用main方法了,使用这种方式注册Application
 * jar包比较方便,不推荐这种方式
 *
 * @author caomei
 */

@Deprecated
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringrainApplication.class);
    }
}
