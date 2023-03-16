package com.skcc.sto.common.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skcc.sto.common.dto.ErrorDetails;
import com.skcc.sto.common.exception.BizException;
import com.skcc.sto.common.exception.ErrorCode;
import feign.FeignException;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Global exception handlers.
 */
@Slf4j
@AllArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandlers {

  private final ObjectMapper objectMapper;

  @ExceptionHandler(BizException.class)
  public ResponseEntity<ErrorDetails> handleBizException(BizException e) {
    String message = null;
    if (e.getHttpStatus() != HttpStatus.INTERNAL_SERVER_ERROR) {
      message = e.getMessage();
    }

    return new ResponseEntity<>(ErrorDetails.builder().errorCode(e.getCode()).message(message)
        .statusCode(e.getHttpStatus().value()).build(), e.getHttpStatus());
  }


  /**
   * Handle unauthorized error error details.
   *
   * @param e the e
   * @return the error details
   */
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(value = {AuthenticationException.class, AccessDeniedException.class})
  @ResponseBody
  protected ErrorDetails handleUnauthorizedError(final RuntimeException e) {
    return ErrorDetails.builder().errorCode(ErrorCode.AUTHORIZATION_ERROR.getCode())
        .message(e.getMessage()).statusCode(HttpStatus.UNAUTHORIZED.value()).build();
  }

  /**
   * Handle exception error details.
   *
   * @param e the e
   * @return the error details
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  protected ErrorDetails handleException(Exception e) {
    return ErrorDetails.builder().errorCode(ErrorCode.INTERNAL_SERVER_ERROR.getCode())
        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
  }

  /**
   * Handle db constraint exception error details.
   *
   * @param e the e
   * @return the error details
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = DataAccessException.class)
  @ResponseBody
  protected ErrorDetails handleDBConstraintException(DataAccessException e) {
    return ErrorDetails.builder().errorCode(ErrorCode.DATABASE_ERROR.getCode())
        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = {IllegalArgumentException.class, HttpMessageNotReadableException.class,
      NoSuchElementException.class})
  @ResponseBody
  protected ErrorDetails handleIllegalArgumentException(final Exception e) {
    return ErrorDetails.builder().errorCode(ErrorCode.INVALID_REQUEST.getCode())
        .message("입력값이 잘못되었습니다.").statusCode(HttpStatus.BAD_REQUEST.value()).build();
  }


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  @ResponseBody
  protected ErrorDetails handleMethodArgumentNotValidException(
      final MethodArgumentNotValidException e) {
    return ErrorDetails.builder().errorCode(ErrorCode.INVALID_REQUEST.getCode())
        .message(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())
        .statusCode(HttpStatus.BAD_REQUEST.value()).build();
  }

  /**
   * Handle constraint violation exception error details.
   *
   * @param e the e
   * @return the error details
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = {ConstraintViolationException.class})
  @ResponseBody
  protected ErrorDetails handleConstraintViolationException(final ConstraintViolationException e) {
    return ErrorDetails.builder().errorCode(ErrorCode.INVALID_REQUEST.getCode())
        .message(e.getMessage()).statusCode(HttpStatus.BAD_REQUEST.value()).build();
  }

  /**
   * Handle unsupported media type exception error details.
   *
   * @param e the e
   * @return the error details
   */
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
  @ResponseBody
  protected ErrorDetails handleUnsupportedMediaTypeException(
      final HttpMediaTypeNotSupportedException e) {
    return ErrorDetails.builder().errorCode(ErrorCode.INVALID_REQUEST.getCode())
        .message(e.getMessage()).statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()).build();
  }

  @ExceptionHandler(value = {FeignException.class})
  @ResponseBody
  protected ErrorDetails handleFeignException(FeignException e, HttpServletResponse response) throws JsonProcessingException {
    String responseJson = e.contentUTF8();
    Map<String, String> responseMap = objectMapper.readValue(responseJson, Map.class);
    log.info(responseMap.toString());
    return ErrorDetails.builder().errorCode(404)
        .message(responseMap.get("message")).statusCode(e.status()).build();
  }
}
