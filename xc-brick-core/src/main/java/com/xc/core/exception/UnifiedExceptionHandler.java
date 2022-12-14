package com.xc.core.exception;


import cn.hutool.core.util.StrUtil;
import com.xc.core.base.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Unified exception handler.
 *
 * @author xuchao
 * @date 2022 -07-19 15:25:32
 */
@Slf4j
@Component
@ControllerAdvice
public class UnifiedExceptionHandler {

    @ExceptionHandler(BusinessBaseExceptionCode.class)
    public R<String> bizException(BusinessBaseExceptionCode ex, HttpServletRequest request) {
        log.warn("BizException:", ex);
        return R.result(ex.getCode(), "", ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R httpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        log.warn("HttpMessageNotReadableException:", ex);
        String message = ex.getMessage();
        if (StrUtil.containsAny(message, "Could not read document:")) {
            String msg = String.format("?????????????????????json??????????????????%s", StrUtil.subBetween(message, "Could not read document:", " at "));
            return R.result(ExceptionCode.PARAM_EX.getCode(), "", msg);
        }
        return R.result(ExceptionCode.PARAM_EX.getCode(), "", ExceptionCode.PARAM_EX.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public R bindException(BindException ex, HttpServletRequest request) {
        log.warn("BindException:", ex);
        try {
            String msgs = ex.getBindingResult().getFieldError().getDefaultMessage();
            if (StrUtil.isNotEmpty(msgs)) {
                return R.result(ExceptionCode.PARAM_EX.getCode(), "", msgs);
            }
        } catch (Exception ee) {
        }
        StringBuilder msg = new StringBuilder();
        List<FieldError> fieldErrors = ex.getFieldErrors();
        fieldErrors.forEach((oe) ->
                msg.append("??????:[").append(oe.getObjectName())
                        .append(".").append(oe.getField())
                        .append("]????????????:[").append(oe.getRejectedValue()).append("]?????????????????????????????????.")
        );
        return R.result(ExceptionCode.PARAM_EX.getCode(), "", msg.toString());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        log.warn("MethodArgumentTypeMismatchException:", ex);
        MethodArgumentTypeMismatchException eee = (MethodArgumentTypeMismatchException) ex;
        StringBuilder msg = new StringBuilder("?????????[").append(eee.getName())
                .append("]???????????????[").append(eee.getValue())
                .append("]???????????????????????????[").append(eee.getRequiredType().getName()).append("]?????????");
        return R.result(ExceptionCode.PARAM_EX.getCode(), "", msg.toString());
    }

    @ExceptionHandler(IllegalStateException.class)
    public R illegalStateException(IllegalStateException ex, HttpServletRequest request) {
        log.warn("IllegalStateException:", ex);
        return R.result(ExceptionCode.ILLEGALA_ARGUMENT_EX.getCode(), "", ExceptionCode.ILLEGALA_ARGUMENT_EX.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R missingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletRequest request) {
        log.warn("MissingServletRequestParameterException:", ex);
        StringBuilder msg = new StringBuilder();
        msg.append("???????????????[").append(ex.getParameterType()).append("]???????????????[").append(ex.getParameterName()).append("]");
        return R.result(ExceptionCode.ILLEGALA_ARGUMENT_EX.getCode(), "", msg.toString());
    }

    @ExceptionHandler(NullPointerException.class)
    public R nullPointerException(NullPointerException ex, HttpServletRequest request) {
        log.warn("NullPointerException:", ex);
        return R.result(ExceptionCode.NULL_POINT_EX.getCode(), "", ExceptionCode.NULL_POINT_EX.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public R illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        log.warn("IllegalArgumentException:", ex);
        return R.result(ExceptionCode.ILLEGALA_ARGUMENT_EX.getCode(), "", ExceptionCode.ILLEGALA_ARGUMENT_EX.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public R httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {
        log.warn("HttpMediaTypeNotSupportedException:", ex);
        MediaType contentType = ex.getContentType();
        if (contentType != null) {
            StringBuilder msg = new StringBuilder();
            msg.append("????????????(Content-Type)[").append(contentType.toString()).append("] ???????????????????????????????????????");
            return R.result(ExceptionCode.MEDIA_TYPE_EX.getCode(), "", msg.toString());
        }
        return R.result(ExceptionCode.MEDIA_TYPE_EX.getCode(), "", "?????????Content-Type??????");
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public R missingServletRequestPartException(MissingServletRequestPartException ex, HttpServletRequest request) {
        log.warn("MissingServletRequestPartException:", ex);
        return R.result(ExceptionCode.REQUIRED_FILE_PARAM_EX.getCode(), "", ExceptionCode.REQUIRED_FILE_PARAM_EX.getMessage());
    }

    @ExceptionHandler(ServletException.class)
    public R servletException(ServletException ex, HttpServletRequest request) {
        log.warn("ServletException:", ex);
        String msg = "UT010016: Not a multi part request";
        if (msg.equalsIgnoreCase(ex.getMessage())) {
            return R.result(ExceptionCode.REQUIRED_FILE_PARAM_EX.getCode(), "", ExceptionCode.REQUIRED_FILE_PARAM_EX.getMessage());
        }
        return R.result(ExceptionCode.SYSTEM_BUSY.getCode(), "", ex.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public R multipartException(MultipartException ex, HttpServletRequest request) {
        log.warn("MultipartException:", ex);
        return R.result(ExceptionCode.REQUIRED_FILE_PARAM_EX.getCode(), "", ExceptionCode.REQUIRED_FILE_PARAM_EX.getMessage());
    }

    /**
     * spring ?????????????????????????????? ???conttoller????????????result?????????????????????
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.warn("MethodArgumentNotValidException:", ex);
        return R.result(ExceptionCode.BASE_VALID_PARAM.getCode(), "", ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * ????????????
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R<String> otherExceptionHandler(Exception ex, HttpServletRequest request) {
        log.warn("Exception:", ex);
        if (ex.getCause() instanceof BusinessBaseExceptionCode) {
            return this.bizException((BusinessBaseExceptionCode) ex.getCause(), request);
        }
        return R.result(ExceptionCode.SYSTEM_BUSY.getCode(), "", ExceptionCode.SYSTEM_BUSY.getMessage());
    }


    /**
     * ???????????????:405
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public R<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        log.warn("HttpRequestMethodNotSupportedException:", ex);
        return R.result(ExceptionCode.METHOD_NOT_ALLOWED.getCode(), "", ExceptionCode.METHOD_NOT_ALLOWED.getMessage());
    }


    @ExceptionHandler(SQLException.class)
    public R sqlException(SQLException ex, HttpServletRequest request) {
        log.warn("SQLException:", ex);
        return R.result(ExceptionCode.SQL_EX.getCode(), "", ExceptionCode.SQL_EX.getMessage());
    }

}
