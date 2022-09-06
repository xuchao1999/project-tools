package com.xc.auth.entity.dto.req;


import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;

/**
* 岗位表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@ApiModel(value = "coreStation", description = "岗位表")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoreStationSaveDTO implements Serializable {

    private static final long serialVersionUID = 427463831626356521L;

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "岗位名称")
    private String name;
    @ApiModelProperty(value = "组织id")
    private Long orgId;
    @ApiModelProperty(value = "是否启用状态")
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
