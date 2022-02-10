package net.shengjian.system.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;

/**
 * 错误页面设置,等同 api.xml的<error-page>
 *
 * @author caomei
 */
//@Configuration("configuration-ErrorPageConfig")
@Deprecated
public class ErrorPageConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        registry.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/errorpage/400"));
        registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/errorpage/404"));
        registry.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errorpage/500"));
        registry.addErrorPages(new ErrorPage(HttpStatus.URI_TOO_LONG, "/errorpage/414"));
        registry.addErrorPages(new ErrorPage(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, "/errorpage/505"));
        registry.addErrorPages(new ErrorPage(Exception.class, "/errorpage/error"));
    }

}
