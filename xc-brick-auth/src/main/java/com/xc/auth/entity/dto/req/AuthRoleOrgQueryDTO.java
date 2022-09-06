package com.xc.auth.entity.dto.req;

import java.io.Serializable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.xc.core.base.BasePage;

import java.util.Date;

/**
* 角色组织关系表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@ApiModel(value = "authRoleOrg", description = "角色组织关系表")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRoleOrgQueryDTO extends BasePage implements Serializable {

    private static final long serialVersionUID = 779398929931333153L;
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "角色id")
    private Long roleId;
    @ApiModelProperty(value = "组织id")
    private Long orgId;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "创建人id")
    private Long createUser;
}
