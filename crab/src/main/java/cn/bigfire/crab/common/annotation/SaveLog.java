package cn.bigfire.crab.common.annotation;

import java.lang.annotation.*;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/11/6  9:30
 * @ Desc   : 保存系统日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SaveLog {
    String value() default "";
}
