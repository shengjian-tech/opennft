/**
 *
 */
package net.shengjian.frame.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 用于Entity的注解,主要是用于映射表名和设置扩展后缀
 *
 * @copyright {@link jiagou.com}
 * @author springrain<9 iuorg @ gmail.com>
 * @version 2013-03-19 11:08:15
 * @see TableSuffix
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TableSuffix {
    /**
     * 扩展后缀,默认为空,可以指定字段 例如 suffix 的getSuffix() 返回值是 _history_2013
     *
     * @return
     */
    String name() default "";

}
