package com.xc.jwt.client.service;

import com.xc.jwt.annotation.EnableAuthClient;
import com.xc.jwt.client.entity.JwtUserInfo;
import com.xc.jwt.client.entity.Token;
import com.xc.jwt.client.properties.AuthProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class JwtTokenServiceTest {

    @Resource
    private AuthProperties authProperties;
    @Test
    void generateUserToken() {
        JwtUserInfo jwtUserInfo = new JwtUserInfo(1L, "ddd", "dddd", "dddd", 2L, 3L);
        JwtTokenService jwtTokenService = new JwtTokenService(authProperties);
        Token token = jwtTokenService.generateUserToken(jwtUserInfo, 100);

        log.info("token: {}", token);
    }

    @Test
    void getUserInfo() {

        JwtUserInfo jwtUserInfo = new JwtUserInfo(1L, "ddd", "dddd", "dddd", 2L, 3L);
        JwtTokenService jwtTokenService = new JwtTokenService(authProperties);
        Token token = jwtTokenService.generateUserToken(jwtUserInfo, 100);

        JwtUserInfo userInfo = jwtTokenService.getUserInfo(token.getToken());
        log.info("userInfo: {}", userInfo);
    }
}