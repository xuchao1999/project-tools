package com.xc.auth.entity.vo;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Date;

/**
 * 用户表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@ApiModel(description = "用户表")
@Data
public class AuthUserVO implements Serializable {

    private static final long serialVersionUID = 319369493233389455L;
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "账号")
    private String account;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "组织id")
    private Long orgId;
    @ApiModelProperty(value = "岗位id")
    private Long stationId;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "启用状态")
    private String status;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "工作描述")
    private String workDescribe;
    @ApiModelProperty(value = "最后一次输错密码时间")
    private Date passwordErrorLastTime;
    @ApiModelProperty(value = "密码错误次数")
    private Integer passwordErrorNum;
    @ApiModelProperty(value = "密码过期时间")
    private Date passwordExpireTime;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "最后登录时间")
    private Date lastLoginTime;
}