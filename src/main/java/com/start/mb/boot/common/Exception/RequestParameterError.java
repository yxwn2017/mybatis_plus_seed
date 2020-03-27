package com.start.mb.boot.common.Exception;

/**
 * @author wll
 * @version version
 * @title RequestParameterError
 * @desc
 * @date 2020/3/27
 */
public class RequestParameterError extends BaseException {

    public RequestParameterError(String message) {
        super(message, 1000001);
    }
}
