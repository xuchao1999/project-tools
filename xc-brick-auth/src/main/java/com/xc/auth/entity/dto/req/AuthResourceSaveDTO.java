package com.xc.auth.entity.dto.req;


import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;

/**
* 资源表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@ApiModel(value = "authResource", description = "资源表")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResourceSaveDTO implements Serializable {

    private static final long serialVersionUID = 514468715783299613L;

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "资源编码")
    private String code;
    @ApiModelProperty(value = "接口名称")
    private String name;
    @ApiModelProperty(value = "菜单id")
    private Long menuId;
    @ApiModelProperty(value = "http请求方式")
    private String method;
    @ApiModelProperty(value = "接口请求url")
    private String url;
    @ApiModelProperty(value = "接口描述")
    private String describe;
    @ApiModelProperty(value = "创建人id")
    private Long createUser;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人id")
    private Long updateUser;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
