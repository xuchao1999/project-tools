package com.xc.auth.infrastract.common.enums;


import com.xc.core.exception.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 参数异常枚举.
 * @author xc
 */
@Getter
@AllArgsConstructor
public enum ParamExceptionEnum implements BusinessExceptionAssert {


    ;
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
}
