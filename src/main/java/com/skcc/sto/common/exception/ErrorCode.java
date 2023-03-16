package com.skcc.sto.common.exception;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import org.springframework.http.HttpStatus;

public enum ErrorCode {

    INVALID_REQUEST("INVALID_PARAMETER", 40000, str -> "잘못된 요청입니다." + str, HttpStatus.BAD_REQUEST),
    AUTHORIZATION_ERROR("AUTHORIZATION_ERROR", 40100, str -> "인증 오류입니다." + str, HttpStatus.UNAUTHORIZED),

    FORBIDDEN("FORBIDDEN", 40300, str -> str + "권한 오류입니다.", HttpStatus.FORBIDDEN),

    NOT_FOUND("NOT_FOUND", 40400, str -> str + "이(가) 존재하지 않습니다.", HttpStatus.NOT_FOUND),

    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", 40500, str -> str + " 조건이 맞지 않습니다.", HttpStatus.METHOD_NOT_ALLOWED),

    ALREADY_EXISTS("ALREADY_EXISTS", 40900, str -> str + "이(가) 이미 존재합니다.", HttpStatus.CONFLICT),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", 50000, str -> "서버 오류 입니다." + str, HttpStatus.INTERNAL_SERVER_ERROR),
    DATABASE_ERROR("DB_ERROR", 50000, str -> "데이터베이스 처리 오류가 발생하였습니다." + str, HttpStatus.INTERNAL_SERVER_ERROR);

    private String codeStr;
    private int code;
    private Function<String, String> message;
    private HttpStatus httpStatus;

    ErrorCode(String codeStr, int code, UnaryOperator<String> message, HttpStatus httpStatus) {
        this.codeStr = codeStr;
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public String getMessage(String str) {
        if (str == null) str = "";
        return message.apply(str);
    }

    public String getMessage() {
        return message.apply("");
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
