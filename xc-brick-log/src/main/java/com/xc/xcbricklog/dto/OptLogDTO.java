package com.xc.xcbricklog.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName: OptLogDTO
 * @description: 操作日志类
 * @Author: Chao Xu
 * @Date: 2022/8/20 20:41
 **/
@Data
@EqualsAndHashCode
@ToString
public class OptLogDTO {
    // 操作IP
    private String requestIp;
    //日志类型 LogType{OPT:操作类型;EX:异常类型}
    private String type;
    // 操作人编号
    private String userCode;
    //操作人
    private String userName;
    //操作描述
    private String description;
    // 类路径
    private String classPath;
    // 请求方法名
    private String actionMethod;
    // 请求地址
    private String requestUrl;
    // 请求参数
    private String params;
    // 返回结果
    private Object result;
    // 请求开始时间
    private Date startTime;
    // 请求结束时间
    private Date finishTime;
    // 请求花费时间
    private Long consumingTime;

}
