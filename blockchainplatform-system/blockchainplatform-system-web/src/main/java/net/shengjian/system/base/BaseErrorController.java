package net.shengjian.system.base;

import net.shengjian.frame.util.ReturnDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * 覆盖springboot默认的BasicErrorController,使用自定义的BaseErrorController拦截404等异常信息,返回JSON格式的数据.
 * 需要和GlobalExceptionHandler配合,才能拦截所有的异常
 *
 * @see BaseErrorController
 */
@RestController
public class BaseErrorController implements ErrorController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final ErrorAttributes errorAttributes;

    public BaseErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }


    /**
     * 拦截异常,返回固定格式消息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/error")
    public ReturnDatas<?> error(HttpServletRequest request) throws Exception {
        //返回结果
        ReturnDatas<?> returnDatas = ReturnDatas.getErrorReturnDatas();

        //获取HTTP 状态码
        HttpStatus httpStatus = getStatus(request);
        returnDatas.setMessage(httpStatus.value() + "");
        //获取异常信息
        WebRequest webRequest = new ServletWebRequest(request);
        Throwable throwable = errorAttributes.getError(webRequest);
        if (throwable != null) {
            logger.error(throwable.getMessage(), throwable);
            // returnDatas.setMessage(throwable.getMessage());
        }
        return returnDatas;
    }


    /**
     * 获取异常的状态码
     *
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


}