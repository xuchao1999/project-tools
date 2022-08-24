package com.xc.jwt.utils;



import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import cn.hutool.core.bean.BeanUtil;
import com.xc.core.exception.BusinessBaseExceptionCode;
import com.xc.core.exception.ExceptionCode;
import com.xc.core.utils.DateUtils;
import com.xc.jwt.client.entity.Token;

import com.xc.jwt.client.entity.JwtUserInfo;
import com.xc.jwt.client.contstants.BaseContextConstants;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
/**
 * @ClassName: JwtHelper
 * @description: todo
 * @Author: Chao Xu
 * @Date: 2022/8/21 17:59
 **/
@Slf4j
public class JwtHelper {

    private static final JwtKeyHelper RSA_KEY_HELPER = new JwtKeyHelper();
    /**
     * 生成用户token
     * @param jwtInfo
     * @param priKeyPath
     * @param expire
     * @return
     * @throws BusinessBaseExceptionCode
     */
    public static Token generateUserToken(JwtUserInfo jwtInfo, String priKeyPath, int expire) throws BusinessBaseExceptionCode {
        JwtBuilder jwtBuilder = Jwts.builder()
                //设置主题
                .setSubject(String.valueOf(jwtInfo.getUserId()))
                .claim(BaseContextConstants.JWT_KEY_USER_ID, jwtInfo.getUserId())
                .claim(BaseContextConstants.JWT_KEY_ACCOUNT, jwtInfo.getAccount())
                .claim(BaseContextConstants.JWT_KEY_NAME, jwtInfo.getName())
                .claim(BaseContextConstants.JWT_KEY_ORG_ID, jwtInfo.getOrgId())
                .claim(BaseContextConstants.JWT_KEY_STATION_ID, jwtInfo.getStationId());
        return generateToken(jwtBuilder, priKeyPath, expire);
    }

    /**
     * 获取token中的用户信息
     * @param token      token
     * @param pubKeyPath 公钥路径
     * @return
     * @throws Exception
     */
    public static JwtUserInfo getJwtFromToken(String token, String pubKeyPath) throws BusinessBaseExceptionCode {
        Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
        Object body = claimsJws.getBody();

        JwtUserInfo jwtUserInfo = new JwtUserInfo();
        BeanUtil.copyProperties(body, jwtUserInfo);

        return jwtUserInfo;
    }

    /**
     * 生成token
     * @param builder
     * @param priKeyPath
     * @param expire
     * @return
     * @throws BusinessBaseExceptionCode
     */
    protected static Token generateToken(JwtBuilder builder, String priKeyPath, int expire) throws BusinessBaseExceptionCode {
        try {
            //返回的字符串便是我们的jwt串了
            String compactJws = builder.setExpiration(DateUtils.localDateTime2Date(LocalDateTime.now().plusSeconds(expire)))
                    //设置算法（必须）
                    .signWith(SignatureAlgorithm.RS256, RSA_KEY_HELPER.getPrivateKey(priKeyPath))
                    //这个是全部设置完成后拼成jwt串的方法
                    .compact();
            return new Token(compactJws, expire);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("errcode:{}, message:{}", ExceptionCode.JWT_GEN_TOKEN_FAIL.getCode(), e.getMessage());
            ExceptionCode.JWT_GEN_TOKEN_FAIL.assertIsFalse(false);
            return null;
        }
    }

    /**
     * 公钥解析token
     * @param token
     * @param pubKeyPath 公钥路径
     * @return
     * @throws Exception
     */
    private static Jws<Claims> parserToken(String token, String pubKeyPath){
        try {
            return Jwts.parser().setSigningKey(RSA_KEY_HELPER.getPublicKey(pubKeyPath)).parseClaimsJws(token);
        } catch (ExpiredJwtException ex) {
            //过期
            ExceptionCode.JWT_TOKEN_EXPIRED.assertIsFalse(false);
        } catch (SignatureException ex) {
            //签名错误
            ExceptionCode.JWT_SIGNATURE.assertIsFalse(false);
        } catch (IllegalArgumentException ex) {
            //token 为空
            ExceptionCode.JWT_ILLEGAL_ARGUMENT.assertIsFalse(false);
        } catch (Exception e) {
            log.error("errcode:{}, message:{}", ExceptionCode.JWT_PARSER_TOKEN_FAIL.getCode(), e.getMessage());
            ExceptionCode.JWT_PARSER_TOKEN_FAIL.assertIsFalse(false);
        }
        return null;
    }

}
