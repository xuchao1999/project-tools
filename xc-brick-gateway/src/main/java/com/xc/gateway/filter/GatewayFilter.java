package com.xc.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.xc.gateway.config.IgnoreTokenConfig;
import com.xc.jwt.client.contstants.BaseContextConstants;
import com.xc.jwt.client.entity.JwtUserInfo;
import com.xc.jwt.client.properties.AuthProperties;
import com.xc.jwt.client.service.JwtTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;


/**
 * 描述：网关过滤器
 *
 * @author xc
 * @date 20220825 16:27:55
 * @since v1.0
 */
@Component
@Slf4j
public class GatewayFilter implements GlobalFilter, Ordered {
    @Resource
    private IgnoreTokenConfig ignoreTokenConfig;
    @Resource
    private AuthProperties authClientProperties;
    @Resource
    private JwtTokenService jwtTokenClientUtils;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest.Builder mutate = request.mutate();
        String url = request.getURI().getPath();
        // 1. 跳过不需要验证的路径
        if (ignoreTokenConfig.checkIgnores(url)) {
            log.info("网关统一鉴权 - 忽略 - 直接请求后面服务");
            return chain.filter(exchange);
        }
        log.info("网关统一鉴权 - 不忽略 - 执行鉴权,url:{}",url);
        String token = request.getHeaders().getFirst(authClientProperties.getTokenInfo().getAccessToken());
        LocaleContext localeContext = exchange.getLocaleContext();
        if (StrUtil.isBlank(token)) {
            log.error("网关统一鉴权 - 未授权 ");
            return setUnauthorizedResponse(exchange, "未授权");
        }

        // 2.获取当前请求的请求方式和uri，拼接成GET/user/page这种形式，称为权限标识符
        // 3.从缓存中获取所有需要进行鉴权的资源(同样是由资源表的method字段值+url字段值拼接成)，如果没有获取到则通过Feign调用权限服务获取并放入缓存中
        // 4.判断这些资源是否包含当前请求的权限标识符，如果不包含当前请求的权限标识符，则返回未经授权错误提示
        // 5.如果包含当前的权限标识符，则从zuul header中取出用户id，根据用户id取出缓存中的用户拥有的权限，如果没有取到则通过Feign调用权限服务获取并放入缓存，判断用户拥有的权限是否包含当前请求的权限标识符
        // 6.如果用户拥有的权限包含当前请求的权限标识符则说明当前用户拥有权限，直接放行
        // 7.根据token获取用户信息，并放入header
        // 获取用户信息
        JwtUserInfo userInfo = jwtTokenClientUtils.getUserInfo(token);
        //将信息放入header
        if (userInfo != null) {
            addHeader(mutate, BaseContextConstants.JWT_KEY_ACCOUNT,
                    userInfo.getAccount());
            addHeader(mutate, BaseContextConstants.JWT_KEY_USER_ID,
                    String.valueOf(userInfo.getUserId()));
            addHeader(mutate, BaseContextConstants.JWT_KEY_NAME,
                    userInfo.getName());
            addHeader(mutate, BaseContextConstants.JWT_KEY_ORG_ID,
                    String.valueOf(userInfo.getOrgId()));
            addHeader(mutate, BaseContextConstants.JWT_KEY_STATION_ID,
                    String.valueOf(userInfo.getStationId()));
        }
        ServerHttpRequest build = mutate.build();
        return chain.filter(exchange.mutate().request(build).build());
    }

    //将指定信息放入header中
    private ServerHttpRequest.Builder addHeader(ServerHttpRequest.Builder builder, String name, String value) {
        if (StrUtil.isEmpty(value)) {
            return builder;
        }
        return builder.header(name, value);
    }

    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, String msg) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);

        log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JSON.toJSONBytes(msg));
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
