package com.xc.swagger2;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({SwaggerAutoConfiguration.class})
@Documented
public @interface EnableBrickSwagger {
}
