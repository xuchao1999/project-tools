package com.xc.gateway.config;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.Set;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "authentication")
public class IgnoreTokenConfig {
    /**
     * 需要忽略的 URL 格式，不考虑请求方法
     */
    private Set<String> whitelist;

    private Set<String> defaultWhitelist = CollUtil.newHashSet(
            "/api/**",
            "/workflow/**",
//            "/merchants/**",
            //"/sales/**",
            "/internalface/**",
            //swagger相关文档
            "/doc.html/**",
            "/favicon.ico",
            "/**.js",
            "/**.css",
            "/webjars/**",
            "/swagger-resources/**",
            "/v2/api-docs/**",
            "/v3/api-docs/**"
    );

    private final PathMatcher PATH_MATCHER  = new AntPathMatcher();

    /**
     * 判断是否校验 token
     *
     * @param path
     * @return
     */
    public boolean checkIgnores(String path) {
        if (CollUtil.isNotEmpty(whitelist)) {
            // 比如/oauth/** 可以匹配/oauth/token
            for(String skipPath : whitelist) {
                if(PATH_MATCHER.match(skipPath,path)) {
                    return true;
                }
            }
        }

        if (CollUtil.isNotEmpty(defaultWhitelist)) {
            for (String skipPath : defaultWhitelist) {
                if (PATH_MATCHER.match(skipPath, path)) {
                    return true;
                }
            }
            log.error("IgnoreTokenConfig:{}, matchResult:{}", path);
            return false;
        }
        return false;
    }


}
