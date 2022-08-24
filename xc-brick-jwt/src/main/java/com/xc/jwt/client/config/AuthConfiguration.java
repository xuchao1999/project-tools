package com.xc.jwt.client.config;


import com.xc.jwt.client.properties.AuthProperties;
import com.xc.jwt.client.service.JwtTokenService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName: AuthClientConfiguration
 * @description: todo
 * @Author: Chao Xu
 * @Date: 2022/8/21 17:47
 **/
@EnableConfigurationProperties(value = AuthProperties.class)
public class AuthConfiguration {
    @Bean
    public JwtTokenService getJwtTokenClientUtils(AuthProperties authProperties){
        return new JwtTokenService(authProperties);
    }
}
