package com.xc.jwt.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Token
 * @description: todo
 * @Author: Chao Xu
 * @Date: 2022/8/21 18:10
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String token;
    private int expire;
}
