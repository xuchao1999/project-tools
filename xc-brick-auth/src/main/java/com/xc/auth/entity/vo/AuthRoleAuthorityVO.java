package com.xc.auth.entity.vo;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Date;

/**
 * 角色权限关系表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@ApiModel(description = "角色权限关系表")
@Data
public class AuthRoleAuthorityVO implements Serializable {

    private static final long serialVersionUID = 671917756378255165L;
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "权限id")
    private Long authorityId;
    @ApiModelProperty(value = "权限类型 menu:菜单 resource:资源")
    private String authorityType;
    @ApiModelProperty(value = "角色id")
    private Long roleId;
}