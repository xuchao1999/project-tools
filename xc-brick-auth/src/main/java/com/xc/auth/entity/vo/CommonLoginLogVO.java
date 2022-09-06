package com.xc.auth.entity.vo;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Date;

/**
 * 字典名
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@ApiModel(description = "字典名")
@Data
public class CommonLoginLogVO implements Serializable {

    private static final long serialVersionUID = 348836146658388892L;
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "操作ip")
    private String requestIp;
    @ApiModelProperty(value = "登录人id")
    private Long userId;
    @ApiModelProperty(value = "登录人姓名")
    private String userName;
    @ApiModelProperty(value = "登录人账号")
    private String account;
    @ApiModelProperty(value = "登录描述")
    private String description;
    @ApiModelProperty(value = "登录时间")
    private Date loginDate;
    @ApiModelProperty(value = "浏览器请求头")
    private String ua;
    @ApiModelProperty(value = "浏览器名称")
    private String browser;
    @ApiModelProperty(value = "浏览器版本")
    private String browserVersion;
    @ApiModelProperty(value = "操作系统")
    private String operatingSystem;
    @ApiModelProperty(value = "登录地点")
    private String location;
}