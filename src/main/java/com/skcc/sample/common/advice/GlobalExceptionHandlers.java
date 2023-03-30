package com.skcc.sample.common.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skcc.sample.common.dto.ErrorMessage;
import com.skcc.sample.common.exception.BizException;
import com.skcc.sample.common.exception.ErrorCode;
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
  public ResponseEntity<ErrorMessage> handleBizException(BizException e) {
    String message = null;
    if (e.getHttpStatus() != HttpStatus.INTERNAL_SERVER_ERROR) {
      message = e.getMessage();
    }

    return new ResponseEntity<>(ErrorMessage.builder().errorCode(e.getCode()).message(message).build(), e.getHttpStatus());
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
  protected ErrorMessage handleUnauthorizedError(final RuntimeException e) {
    return ErrorMessage.builder().errorCode(ErrorCode.UNAUTHORIZED.getCode())
        .message(e.getMessage()).build();
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
  protected ErrorMessage handleException(Exception e) {
    return ErrorMessage.builder().errorCode(ErrorCode.INTERNAL_SERVER_ERROR.getCode()).build();
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
  protected ErrorMessage handleDBConstraintException(DataAccessException e) {
    return ErrorMessage.builder().errorCode(ErrorCode.DATABASE_ERROR.getCode()).build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = {IllegalArgumentException.class, HttpMessageNotReadableException.class,
      NoSuchElementException.class})
  @ResponseBody
  protected ErrorMessage handleIllegalArgumentException(final Exception e) {
    return ErrorMessage.builder().errorCode(ErrorCode.INVALID_REQUEST.getCode())
        .message("입력값이 잘못되었습니다.").build();
  }


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  @ResponseBody
  protected ErrorMessage handleMethodArgumentNotValidException(
      final MethodArgumentNotValidException e) {
    return ErrorMessage.builder().errorCode(ErrorCode.INVALID_REQUEST.getCode())
        .message(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()).build();
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
  protected ErrorMessage handleConstraintViolationException(final ConstraintViolationException e) {
    return ErrorMessage.builder().errorCode(ErrorCode.INVALID_REQUEST.getCode())
        .message(e.getMessage()).build();
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
  protected ErrorMessage handleUnsupportedMediaTypeException(
      final HttpMediaTypeNotSupportedException e) {
    return ErrorMessage.builder().errorCode(ErrorCode.INVALID_REQUEST.getCode())
        .message(e.getMessage()).build();
  }

  @ExceptionHandler(value = {FeignException.class})
  @ResponseBody
  protected ErrorMessage handleFeignException(FeignException e, HttpServletResponse response) throws JsonProcessingException {
    String responseJson = e.contentUTF8();
    Map<String, Object> responseMap = objectMapper.readValue(responseJson, Map.class);
    String code = String.valueOf(responseMap.get("code"));
    String message = String.valueOf(responseMap.get("message"));
    response.setStatus(e.status());
    return ErrorMessage.builder().errorCode(code)
        .message(message).build();
  }
}
