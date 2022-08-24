package com.xc.core.exception;

import java.text.MessageFormat;

/**
 * 业务异常接口.
 *
 * @author xuchao
 * @date 2022 -07-19 14:03:59
 */
public interface BusinessExceptionAssert extends IAssert, ExceptionEnumInterface {
    @Override
    default BaseExceptionCode newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessBaseExceptionCode(this, args, msg);
    }

    @Override
    default BaseExceptionCode newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new BusinessBaseExceptionCode(this, args, msg, t);
    }
}
