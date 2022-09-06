package com.xc.auth.entity.vo;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Date;

/**
 * 菜单表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@ApiModel(description = "菜单表")
@Data
public class AuthMenuVO implements Serializable {

    private static final long serialVersionUID = 376274846534896664L;
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "菜单名称")
    private String name;
    @ApiModelProperty(value = "功能描述")
    private String describe;
    @ApiModelProperty(value = "是否是公开菜单")
    private String isPublic;
    @ApiModelProperty(value = "对应路由path")
    private String path;
    @ApiModelProperty(value = "对应路由组件component")
    private String component;
    @ApiModelProperty(value = "是否启用")
    private String isEnable;
    @ApiModelProperty(value = "排序")
    private Integer sortValue;
    @ApiModelProperty(value = "菜单图标")
    private String icon;
    @ApiModelProperty(value = "菜单分组")
    private String group;
    @ApiModelProperty(value = "父级菜单id")
    private Long parentId;
}