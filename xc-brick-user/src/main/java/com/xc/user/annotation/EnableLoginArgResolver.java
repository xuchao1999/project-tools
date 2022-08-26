package com.xc.user.annotation;


import com.xc.user.config.LoginArgResolverConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({LoginArgResolverConfig.class})
public @interface EnableLoginArgResolver {
}
