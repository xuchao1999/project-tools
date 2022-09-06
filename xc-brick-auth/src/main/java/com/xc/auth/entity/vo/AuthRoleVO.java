package com.xc.auth.entity.vo;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Date;

/**
 * 角色表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@ApiModel(description = "角色表")
@Data
public class AuthRoleVO implements Serializable {

    private static final long serialVersionUID = 591466457423218893L;
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "角色名称")
    private String name;
    @ApiModelProperty(value = "角色编码")
    private String code;
    @ApiModelProperty(value = "角色描述")
    private String describe;
    @ApiModelProperty(value = "是否启用状态")
    private String status;
    @ApiModelProperty(value = "是否内置角色")
    private String readonly;
}