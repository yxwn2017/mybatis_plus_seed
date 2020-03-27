package com.start.mb.boot.common.Exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author wll
 * @version version
 * @title BaseException
 * @desc
 * @date 2020/3/27
 */
@NoArgsConstructor
public class BaseException extends RuntimeException {

    /**
     * 错误码
     */
    private int errorCode;

    public BaseException(int errorCode) {
        this.errorCode = errorCode;
    }

    public BaseException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BaseException(Throwable cause, int errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}

