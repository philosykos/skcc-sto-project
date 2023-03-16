package com.skcc.sto.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "Default Response")
public final class ResponseTemplate<T> {

  @Schema(description = "Whether it is success or not.", example = "true")
  private final boolean success;
  private final T result;
  @Schema(description = "This is Error Reason.")
  private final ErrorDetails errorResponse;


  @Builder
  public ResponseTemplate(boolean success, T result, ErrorDetails errorResponse) {
    this.success = success;
    this.result = result;
    this.errorResponse = errorResponse;
  }
}