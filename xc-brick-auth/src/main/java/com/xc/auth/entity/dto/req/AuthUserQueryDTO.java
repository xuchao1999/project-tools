package com.xc.auth.entity.dto.req;

import java.io.Serializable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.xc.core.base.BasePage;

import java.util.Date;

/**
* 用户表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@ApiModel(value = "authUser", description = "用户表")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthUserQueryDTO extends BasePage implements Serializable {

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
    @ApiModelProperty(value = "创建人id")
    private Long createUser;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人id")
    private Long updateUser;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
