package com.xc.jwt.client.service;

import com.xc.core.exception.BusinessBaseExceptionCode;
import com.xc.jwt.annotation.EnableAuthClient;
import com.xc.jwt.client.entity.*;
import com.xc.jwt.client.properties.*;
import com.xc.jwt.utils.JwtHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: JwtTokenClientUtils
 * @description: todo
 * @Author: Chao Xu
 * @Date: 2022/8/21 17:40
 **/
@Data
@AllArgsConstructor
@Service
public class JwtTokenService {

    /**
     * 用于 认证服务的 客户端使用（如 网关） ， 在网关获取到token后，
     * 调用此工具类进行token 解析。
     * 客户端一般只需要解析token 即可
     */
    private AuthProperties authProperties;


    /**
     * 生成token
     * @param jwtInfo
     * @param expire
     * @return
     * @throws BusinessBaseExceptionCode
     */
    public Token generateUserToken(JwtUserInfo jwtInfo, Integer expire)  throws BusinessBaseExceptionCode {
        TokenInfo userTokenInfo = authProperties.getUser();
        if (expire == null || expire <= 0) {
            expire = userTokenInfo.getExpire();
        }
        return JwtHelper.generateUserToken(jwtInfo, "pri.key", expire);
    }

    /**
     * 解析token
     */
    public JwtUserInfo getUserInfo(String token)  throws BusinessBaseExceptionCode {
        TokenInfo userTokenInfo = authProperties.getUser();
        return JwtHelper.getJwtFromToken(token, userTokenInfo.getPubKey());
    }
}
