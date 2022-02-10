package net.shengjian.rpc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 标识接口可能会被rpc代理,所有的接口都加上就可以了,根据路径规则找默认的实现文件,
 * 如果找到就初始化本地,如果找不到就调用远程RPC,目前只支持一个bean,按照类型装配
 *
 * @author caomei
 */

@Documented
@Inherited
@Retention(RUNTIME)
public @interface RpcServiceAnnotation {

    /**
     * service实现的路径,用于设置特殊情况,basepath+implpackage+serviceimpl的名称
     *
     * @return
     */
    String implpackage() default "";

    /**
     * rpc的主机地址
     *
     * @return
     */
    String rpcHost() default "";

    /**
     * rpc的端口
     *
     * @return
     */
    int rpcPort() default -1;

    /**
     * springBean 的beanName,目前只支持一个bean,暂时无用
     *
     * @return
     */
    String beanName() default "";

    /**
     * 版本的编号,用于处理不同的版本
     *
     * @return
     */
    int versionCode() default 100;

    /**
     * 超时时间,超时之后,事务回滚.
     *
     * @return
     */
    int timeout() default 10000;

    /**
     * 事务自动提交,默认true,如果是false就需要等待入口通知提交.
     *
     * @return
     */
    boolean autocommit() default true;


}