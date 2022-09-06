package com.xc.auth.entity.dto.req;

import java.io.Serializable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.xc.core.base.BasePage;

import java.util.Date;

/**
* 用户角色关系
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@ApiModel(value = "authUserRole", description = "用户角色关系")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthUserRoleQueryDTO extends BasePage implements Serializable {

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
