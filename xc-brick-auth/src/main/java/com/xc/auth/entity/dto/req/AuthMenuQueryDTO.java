package com.xc.auth.entity.dto.req;

import java.io.Serializable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.xc.core.base.BasePage;

import java.util.Date;

/**
* 菜单表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@ApiModel(value = "authMenu", description = "菜单表")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthMenuQueryDTO extends BasePage implements Serializable {

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
    @ApiModelProperty(value = "创建人id")
    private Long createUser;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人id")
    private Long updateUser;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
