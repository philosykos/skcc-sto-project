package com.skcc.sample.token.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Slf4j
public class CreateTokenDto {

    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Request {
        @Schema(description = "토큰 이름", example = "샘플 토큰")
        @NotBlank private String name;

        @Schema(description = "토큰 심볼", example = "ST1")
        @NotBlank private String symbol;

        @Schema(description = "토큰 설명", example = "테스트용 토큰")
        @NotBlank private String description;

        @Schema(description = "초기 발행할 토큰량", example = "1000")
        @NotNull private Long amount;
    }

    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Response {
        @Schema(description = "Transaction Hash", example = "Transaction Hash")
        @NotBlank private String transactionHash;
    }
}
