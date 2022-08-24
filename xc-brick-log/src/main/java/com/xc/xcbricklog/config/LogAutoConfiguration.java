package com.xc.xcbricklog.config;

import com.xc.xcbricklog.aspect.SysLogAspect;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @ClassName: LogAutoConfiguration
 * @description: todo
 * @Author: Chao Xu
 * @Date: 2022/8/21 10:48
 **/
@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "sys.log.enabled", havingValue = "true", matchIfMissing = true)
public class LogAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public SysLogAspect controllerLogAspect(){
        return new SysLogAspect();
    }
}
