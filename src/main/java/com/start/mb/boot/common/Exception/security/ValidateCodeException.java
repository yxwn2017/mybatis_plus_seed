package com.start.mb.boot.common.Exception.security;


import org.springframework.security.core.AuthenticationException;

/**
 * @author wll
 * @desc ValidateCodeException
 * @link
 * @date 2020/4/8 8:06 下午
 */
public class ValidateCodeException extends AuthenticationException {


    public ValidateCodeException(String msg) {
        super(msg);
    }
    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }
}
