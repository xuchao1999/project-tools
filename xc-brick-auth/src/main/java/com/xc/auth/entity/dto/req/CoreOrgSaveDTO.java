package com.xc.auth.entity.dto.req;


import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;

/**
* 组织表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@ApiModel(value = "coreOrg", description = "组织表")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoreOrgSaveDTO implements Serializable {

    private static final long serialVersionUID = 737154116584374256L;

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "组织名称")
    private String name;
    @ApiModelProperty(value = "简称")
    private String abbreviation;
    @ApiModelProperty(value = "父id")
    private Long parentId;
    @ApiModelProperty(value = "树结构")
    private String treePath;
    @ApiModelProperty(value = "排序")
    private Integer sortValue;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "描述")
    private String describe;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "创建人id")
    private Long createUser;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "更新人id")
    private Long updateUser;
}
