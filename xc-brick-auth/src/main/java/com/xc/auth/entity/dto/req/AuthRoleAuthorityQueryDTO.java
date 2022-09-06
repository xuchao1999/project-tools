package com.xc.auth.entity.dto.req;

import java.io.Serializable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.xc.core.base.BasePage;

import java.util.Date;

/**
* 角色权限关系表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@ApiModel(value = "authRoleAuthority", description = "角色权限关系表")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRoleAuthorityQueryDTO extends BasePage implements Serializable {

    private static final long serialVersionUID = 671917756378255165L;
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "权限id")
    private Long authorityId;
    @ApiModelProperty(value = "权限类型 menu:菜单 resource:资源")
    private String authorityType;
    @ApiModelProperty(value = "角色id")
    private Long roleId;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "创建人id")
    private Long createUser;
}
