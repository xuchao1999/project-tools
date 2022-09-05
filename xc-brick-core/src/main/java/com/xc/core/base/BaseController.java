package com.xc.core.base;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xc.core.contstants.BaseContextHandler;
import com.xc.core.exception.BaseExceptionCode;
import com.xc.core.exception.BusinessBaseExceptionCode;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public abstract class BaseController {
    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;

    /**
     * 当前页
     */
    protected static final String CURRENT = "current";
    /**
     * 每页显示条数
     */
    protected static final String SIZE = "size";
    /**
     * 排序字段 ASC
     */
    protected static final String PAGE_ASCS = "ascs";
    /**
     * 排序字段 DESC
     */
    protected static final String PAGE_DESCS = "descs";

    protected static final String START_CREATE_TIME = "startCreateTime";
    protected static final String END_CREATE_TIME = "endCreateTime";
    /**
     * 默认每页条目20,最大条目数100
     */
    int DEFAULT_LIMIT = 20;
    int MAX_LIMIT = 10000;

    /**
     * 成功返回
     *
     * @param data
     * @return
     */
    public <T> R<T> success(T data) {
        return R.success(data);
    }

    public R<Boolean> success() {
        return R.success();
    }

    /**
     * 失败返回
     *
     * @param msg
     * @return
     */
    public <T> R<T> fail(String msg) {
        return R.fail(msg);
    }

    public <T> R<T> fail(String msg, Object... args) {
        return R.fail(msg, args);
    }

    /**
     * 失败返回
     *
     * @param code
     * @param msg
     * @return
     */
    public <T> R<T> fail(int code, String msg) {
        return R.fail(code, msg);
    }

    public <T> R<T> fail(BaseExceptionCode exceptionCode) {
        return R.fail(exceptionCode);
    }

    public <T> R<T> fail(BusinessBaseExceptionCode exception) {
        return R.fail(exception);
    }

    public <T> R<T> fail(Throwable throwable) {
        return R.fail(throwable);
    }

    public <T> R<T> validFail(String msg) {
        return R.validFail(msg);
    }

    public <T> R<T> validFail(String msg, Object... args) {
        return R.validFail(msg, args);
    }

    public <T> R<T> validFail(BaseExceptionCode exceptionCode) {
        return R.validFail(exceptionCode);
    }
}
