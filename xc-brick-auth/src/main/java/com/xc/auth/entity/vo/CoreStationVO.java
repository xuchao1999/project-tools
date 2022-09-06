package com.xc.auth.entity.vo;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Date;

/**
 * 岗位表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@ApiModel(description = "岗位表")
@Data
public class CoreStationVO implements Serializable {

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
}