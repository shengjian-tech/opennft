package net.shengjian.rpc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用于标识RPC方法 </br>
 * timeout:超时时间,为空就是接口注解的超时时间 </br>
 * autocommit:事务自动提交,默认true,如果是false就需要等待.
 *
 * @author caomei
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RUNTIME)
public @interface RpcServiceMethodAnnotation {


    /**
     * 超时时间,超时之后,事务回滚.
     *
     * @return
     */
    int timeout();

    /**
     * 事务自动提交,默认true,如果是false就需要等待入口通知提交.
     *
     * @return
     */
    boolean autocommit();


}