package com.skcc.sample.common.exception;

import org.springframework.http.HttpStatus;

public class BizException extends RuntimeException {

  private final String code;
  private final HttpStatus httpStatus;

  public BizException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.code = errorCode.getCode();
    this.httpStatus = errorCode.getHttpStatus();
  }

  public BizException(ErrorCode errorCode, String str) {
    super(errorCode.getMessage());
    this.code = errorCode.getCode();
    this.httpStatus = errorCode.getHttpStatus();
  }

  public String getCode() {
    return code;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
