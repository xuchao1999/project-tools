package com.xc.jwt.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: TokenInfo
 * @description: todo
 * @Author: Chao Xu
 * @Date: 2022/8/21 17:51
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {
    /**
     * @Author Chao Xu
     * @Description accessToken
     * @Date 17:38 2022/8/21
     **/
    private String accessToken;
    /**
     * 过期时间
     */
    private int expire;
    /**
     * @Author Chao Xu
     * @Description 公钥
     * @Date 17:38 2022/8/21
     **/
    private String pubKey;

    /**
     * 私钥
     */
    private String priKey;
}
