package com.xc.xcbricklog.aspect;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.xc.xcbricklog.annotation.RecordOptLog;
import com.xc.xcbricklog.dto.OptLogDTO;
import com.xc.xcbricklog.event.SysLogEvent;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @ClassName: ControllerLogAspect
 * @description: 统一系统日志打印
 * @Author: Chao Xu
 * @Date: 2022/8/20 21:56
 **/
@Aspect
@Component
@Slf4j
public class SysLogAspect {

    // spring event 需要用到该接口去发布事件
    @Resource
    private ApplicationContext applicationContext;

    // 记录操作日志信息
    private static final ThreadLocal<OptLogDTO> THREAD_LOCAL = new ThreadLocal<>();


    // 切点：controller目录下所有方法
    @Pointcut("execution (* *..*.*Controller.*(..))")
    public void recordLog(){}

    /**
     * @Author Chao Xu
     * @Description 环绕通知打印日志，以及发布事件
     * @Date 12:22 2022/8/21
     * @Param [joinPoint]
     * @return java.lang.Object
     **/
    @Around(value = "recordLog()")
    public Object sysLog(ProceedingJoinPoint joinPoint) throws Throwable{
        Object proceed = null;
        try {
            handleBefore(joinPoint);
            proceed = joinPoint.proceed();
            handleAfter(proceed);
        } finally {
            //System.lineSeparator():系统换行符

        }
        return proceed;
    }

    /**
     * @Author Chao Xu
     * @Description 前置方法：
     * @Date 12:22 2022/8/21
     * @Param [joinPoint]
     * @return void
     **/
    private void handleBefore(ProceedingJoinPoint joinPoint) {
        // 打印请求信息
        printRequestInfo(joinPoint);

        // 如果controller方法上有@RecordOptLog注解，则构建操作日志类
        if(null != ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(RecordOptLog.class)){
            setOptLog(joinPoint);
        }
    }


    private void handleAfter(Object proceed) {
        // 打印返回信息
        log.info("Response           ：{}", JSON.toJSONString(proceed));
        log.info("=========END=========" + System.lineSeparator());

        // 如果optLogDTO不为空，则发布事件
        OptLogDTO optLogDTO = THREAD_LOCAL.get();
        if(null != optLogDTO){
            optLogDTO.setResult(proceed);
            // 发布事件
            pulishEvent(optLogDTO);
        }
    }

    /**
     * @Author Chao Xu
     * @Description 打印请求信息
     * @Date 12:29 2022/8/21
     * @Param [joinPoint]
     * @return void
     **/
    private void printRequestInfo(ProceedingJoinPoint joinPoint){
        // 获取swagger注解controller描述信息
        String controllerDes = "";
        Api api = joinPoint.getTarget().getClass().getAnnotation(Api.class);
        if(null != api && null !=api.tags() && api.tags().length > 0){
            controllerDes = api.tags()[0];
        }
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("=========Start=========");
        log.info("URL                ：{}", request.getRequestURL());
        log.info("BusinessName       ：{}", controllerDes);
        log.info("HTTP Method        ：{}", request.getMethod());
        log.info("Class Method       ：{}.{}", joinPoint.getSignature().getDeclaringTypeName(), ((MethodSignature) joinPoint.getSignature()).getName());
        log.info("IP                 ：{}", request.getRemoteHost());
        log.info("Request Args       ：{}",  JSON.toJSONString(joinPoint.getArgs()));
    }

    /**
     * @Author Chao Xu
     * @Description 发布记录操作日志事件
     * @Date 10:28 2022/8/21
     * @Param [optLogDTO]
     * @return void
     **/
    private void pulishEvent(OptLogDTO optLogDTO){
        optLogDTO.setFinishTime(DateTime.now());
        optLogDTO.setConsumingTime(optLogDTO.getFinishTime().getTime() - optLogDTO.getStartTime().getTime());
        applicationContext.publishEvent(new SysLogEvent(optLogDTO));
        THREAD_LOCAL.remove();
    }

    /**
     * @Author Chao Xu
     * @Description 构造OptLog
     * @Date 12:25 2022/8/21
     * @Param [joinPoint]
     * @return void
     **/
    public void setOptLog(ProceedingJoinPoint joinPoint){

        OptLogDTO optLogDTO = new OptLogDTO();
        optLogDTO.setStartTime(DateTime.now());
        optLogDTO.setUserCode("1110");
        optLogDTO.setUserName("张三");

        // 获取swagger注解controller描述信息
        String controllerDes = "";
        Api api = joinPoint.getTarget().getClass().getAnnotation(Api.class);
        if(null != api && null !=api.tags() && api.tags().length > 0){
            controllerDes = api.tags()[0];
        }
        optLogDTO.setDescription(controllerDes);

        // 获取方法描述信息
        String controllerMethodDes = "";

        // 类名
        optLogDTO.setClassPath(joinPoint.getTarget().getClass().getName());
        // 方法名
        optLogDTO.setActionMethod(joinPoint.getSignature().getName());

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // ip
        optLogDTO.setRequestIp(request.getRemoteHost());
        // 入参
        optLogDTO.setParams(JSON.toJSONString(joinPoint.getArgs()));
        // 请求路径
        optLogDTO.setRequestUrl(request.getRequestURL().toString());

        THREAD_LOCAL.set(optLogDTO);
    }
}
