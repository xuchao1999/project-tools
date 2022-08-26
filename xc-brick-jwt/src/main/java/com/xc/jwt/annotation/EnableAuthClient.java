package com.xc.jwt.annotation;

import com.xc.jwt.client.config.AuthConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * 描述: 开启jwt认证
 * @author xc
 * @date 20220826 14:37:19
 * @since v1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({AuthConfiguration.class})
@Documented
@Inherited
public @interface EnableAuthClient {
}
