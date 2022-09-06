package com.xc.auth.entity.vo;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Date;

/**
 * 操作日志表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@ApiModel(description = "操作日志表")
@Data
public class CommonOptLogVO implements Serializable {

    private static final long serialVersionUID = 837131719644928918L;
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "操作ip")
    private String requestIp;
    @ApiModelProperty(value = "日志类型 opt:操作类型 ex:异常类型")
    private String type;
    @ApiModelProperty(value = "操作人")
    private String userName;
    @ApiModelProperty(value = "操作描述")
    private String description;
    @ApiModelProperty(value = "类路径")
    private String classPath;
    @ApiModelProperty(value = "请求方法")
    private String actionMethod;
    @ApiModelProperty(value = "请求地址")
    private String requestUri;
    @ApiModelProperty(value = "请求类型 get:get请求;post:post请求;put:put请求;delete:delete请求;patch:patch请求;trace:trace请求;head:head请求;options:options请求")
    private String httpMethod;
    @ApiModelProperty(value = "请求参数")
    private String params;
    @ApiModelProperty(value = "返回值")
    private String result;
    @ApiModelProperty(value = "异常详情信息")
    private String exDesc;
    @ApiModelProperty(value = "异常描述")
    private String exDetail;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "完成时间")
    private Date finishTime;
    @ApiModelProperty(value = "消耗时间")
    private Long consumingTime;
    @ApiModelProperty(value = "浏览器请求头")
    private String ua;
}