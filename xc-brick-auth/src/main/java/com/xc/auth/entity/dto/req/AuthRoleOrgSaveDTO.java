package com.xc.auth.entity.dto.req;


import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;

/**
* 角色组织关系表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@ApiModel(value = "authRoleOrg", description = "角色组织关系表")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRoleOrgSaveDTO implements Serializable {

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
