package com.skcc.sample.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(title = "ErrorDetailsType")
public class ErrorMessage {
  private String code;
  private String message;

  @Builder
  public ErrorMessage(final String errorCode, final String message) {
    this.code = errorCode;
    this.message = message;
  }
}