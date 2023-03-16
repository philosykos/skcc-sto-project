package com.skcc.sto.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(title = "ErrorDetailsType")
public class ErrorDetails {
  private Integer code;
  private String message;
  private Integer statusCode;

  @Builder
  public ErrorDetails(final Integer errorCode, final String message, final Integer statusCode) {
    this.code = errorCode;
    this.message = message;
    this.statusCode = statusCode;
  }
}