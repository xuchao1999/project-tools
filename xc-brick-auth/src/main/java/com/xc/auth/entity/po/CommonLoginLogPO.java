package com.xc.auth.entity.po;

import java.io.Serializable;

import lombok.*;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 字典名
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("common_login_log")
public class CommonLoginLogPO implements Serializable {

    private static final long serialVersionUID = 348836146658388892L;
    /**
     * 操作ip
     */
    private String requestIp;
    /**
     * 登录人id
     */
    private Long userId;
    /**
     * 登录人姓名
     */
    private String userName;
    /**
     * 登录人账号
     */
    private String account;
    /**
     * 登录描述
     */
    private String description;
    /**
     * 登录时间
     */
    private Date loginDate;
    /**
     * 浏览器请求头
     */
    private String ua;
    /**
     * 浏览器名称
     */
    private String browser;
    /**
     * 浏览器版本
     */
    private String browserVersion;
    /**
     * 操作系统
     */
    private String operatingSystem;
    /**
     * 登录地点
     */
    private String location;
}