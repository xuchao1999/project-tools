package com.xc.jwt.annotation;

import com.xc.jwt.client.config.AuthConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({AuthConfiguration.class})
@Documented
@Inherited
public @interface EnableAuthClient {
}
