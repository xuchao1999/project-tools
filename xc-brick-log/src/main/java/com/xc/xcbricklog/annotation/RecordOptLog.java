package com.xc.xcbricklog.annotation;

import java.lang.annotation.*;

/**
 * @Author Chao Xu
 * @Description 系统日志注解
 * @Date 21:01 2022/8/20
 * @Param
 * @return
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecordOptLog {
    // 描述
    String value();
}
