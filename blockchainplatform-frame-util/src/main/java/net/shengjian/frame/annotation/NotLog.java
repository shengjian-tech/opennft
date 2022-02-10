/**
 *
 */
package net.shengjian.frame.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 用于Entity的注解,不希望记录日志
 *
 * @copyright {@link jiagou.com}
 * @author springrain<9 iuorg @ gmail.com>
 * @version 2013-03-19 11:08:15
 * @see NotLog
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface NotLog {

}
