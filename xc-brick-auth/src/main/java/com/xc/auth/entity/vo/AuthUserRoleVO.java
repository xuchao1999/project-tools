package com.xc.auth.entity.vo;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Date;

/**
 * 用户角色关系
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@ApiModel(description = "用户角色关系")
@Data
public class AuthUserRoleVO implements Serializable {

    private static final long serialVersionUID = 958368645113273954L;
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "角色id")
    private Long roleId;
    @ApiModelProperty(value = "用户id")
    private Long userId;
}