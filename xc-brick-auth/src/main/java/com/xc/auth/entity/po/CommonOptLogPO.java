package com.xc.auth.entity.po;

import java.io.Serializable;

import lombok.*;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 操作日志表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("common_opt_log")
public class CommonOptLogPO implements Serializable {

    private static final long serialVersionUID = 837131719644928918L;
    /**
     * 操作ip
     */
    private String requestIp;
    /**
     * 日志类型 opt:操作类型 ex:异常类型
     */
    private String type;
    /**
     * 操作人
     */
    private String userName;
    /**
     * 操作描述
     */
    private String description;
    /**
     * 类路径
     */
    private String classPath;
    /**
     * 请求方法
     */
    private String actionMethod;
    /**
     * 请求地址
     */
    private String requestUri;
    /**
     * 请求类型 get:get请求;post:post请求;put:put请求;delete:delete请求;patch:patch请求;trace:trace请求;head:head请求;options:options请求
     */
    private String httpMethod;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 返回值
     */
    private String result;
    /**
     * 异常详情信息
     */
    private String exDesc;
    /**
     * 异常描述
     */
    private String exDetail;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 完成时间
     */
    private Date finishTime;
    /**
     * 消耗时间
     */
    private Long consumingTime;
    /**
     * 浏览器请求头
     */
    private String ua;
}