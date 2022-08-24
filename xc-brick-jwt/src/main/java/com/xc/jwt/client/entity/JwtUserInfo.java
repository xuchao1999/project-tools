package com.xc.jwt.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: JwtUserInfo
 * @description: todo
 * @Author: Chao Xu
 * @Date: 2022/8/21 17:41
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserInfo {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 账户
     */
    private String account;
    /**
     * 姓名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 部门id
     */
    private Long orgId;
    /**
     * 组织id
     */
    private Long stationId;

}
