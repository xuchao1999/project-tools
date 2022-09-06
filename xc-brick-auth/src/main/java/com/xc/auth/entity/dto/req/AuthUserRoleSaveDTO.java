package com.xc.auth.entity.dto.req;


import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;

/**
* 用户角色关系
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@ApiModel(value = "authUserRole", description = "用户角色关系")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthUserRoleSaveDTO implements Serializable {

    private static final long serialVersionUID = 958368645113273954L;

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "角色id")
    private Long roleId;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "创建人id")
    private Long createUser;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
