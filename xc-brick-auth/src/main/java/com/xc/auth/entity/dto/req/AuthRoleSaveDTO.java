package com.xc.auth.entity.dto.req;


import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;

/**
* 角色表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@ApiModel(value = "authRole", description = "角色表")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRoleSaveDTO implements Serializable {

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
    @ApiModelProperty(value = "创建人id")
    private Long createUser;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人id")
    private Long updateUser;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
