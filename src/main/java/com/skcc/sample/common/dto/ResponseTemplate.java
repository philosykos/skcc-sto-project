package com.skcc.sample.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "Default Response")
public final class ResponseTemplate<T> {

  @Schema(description = "Whether it is success or not.", example = "true")
  private final boolean success;
  private final T result;


  @Builder
  public ResponseTemplate(boolean success, T result) {
    this.success = success;
    this.result = result;
  }
}