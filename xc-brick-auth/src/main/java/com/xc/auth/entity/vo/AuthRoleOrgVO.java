package com.xc.auth.entity.vo;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Date;

/**
 * 角色组织关系表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@ApiModel(description = "角色组织关系表")
@Data
public class AuthRoleOrgVO implements Serializable {

    private static final long serialVersionUID = 779398929931333153L;
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "角色id")
    private Long roleId;
    @ApiModelProperty(value = "组织id")
    private Long orgId;
}