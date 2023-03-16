package com.skcc.sto.common.exception;

import org.springframework.http.HttpStatus;

public class BizException extends RuntimeException {
    private final int code;
    private final HttpStatus httpStatus;

    public BizException(ErrorCode errorCode){
        super(errorCode.getMessage(null));
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }

    public BizException(ErrorCode errorCode, String str){
        super(errorCode.getMessage(str));
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
