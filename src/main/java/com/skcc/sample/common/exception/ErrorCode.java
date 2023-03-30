package com.skcc.sample.common.exception;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import org.springframework.http.HttpStatus;

public enum ErrorCode {

  INVALID_REQUEST("INVALID_REQUEST", "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
  UNAUTHORIZED("UNAUTHORIZED", "인증 오류입니다.", HttpStatus.UNAUTHORIZED),

  FORBIDDEN("FORBIDDEN", "권한 오류입니다.", HttpStatus.FORBIDDEN),

  NOT_FOUND("NOT_FOUND", "요청하신 정보가 존재하지 않습니다.", HttpStatus.NOT_FOUND),

  METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "대상 리소스가 요청하신 메소드를 지원하지 않습니다.", HttpStatus.METHOD_NOT_ALLOWED),

  ALREADY_EXISTS("ALREADY_EXISTS", "요청하신 정보가 이미 존재합니다.", HttpStatus.CONFLICT),
  INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "서버 오류 입니다. 잠시 후 다시 시도해주세요.", HttpStatus.INTERNAL_SERVER_ERROR),
  DATABASE_ERROR("DATABASE_ERROR", "데이터베이스 처리 오류가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
  private String code;
  private String message;
  private HttpStatus httpStatus;

  ErrorCode(String code, String message, HttpStatus httpStatus) {
    this.code = code;
    this.message = message;
    this.httpStatus = httpStatus;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
