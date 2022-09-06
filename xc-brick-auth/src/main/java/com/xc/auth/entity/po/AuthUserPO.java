package com.xc.auth.entity.po;

import java.io.Serializable;

import com.xc.core.base.BasePO;
import lombok.*;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 用户表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("auth_user")
public class AuthUserPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 319369493233389455L;
    /**
     * 账号
     */
    private String account;
    /**
     * 姓名
     */
    private String name;
    /**
     * 组织id
     */
    private Long orgId;
    /**
     * 岗位id
     */
    private Long stationId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 性别
     */
    private String sex;
    /**
     * 启用状态
     */
    private String status;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 工作描述
     */
    private String workDescribe;
    /**
     * 最后一次输错密码时间
     */
    private Date passwordErrorLastTime;
    /**
     * 密码错误次数
     */
    private Integer passwordErrorNum;
    /**
     * 密码过期时间
     */
    private Date passwordExpireTime;
    /**
     * 密码
     */
    private String password;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
}