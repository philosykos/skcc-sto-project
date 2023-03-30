package com.skcc.sample.chainz.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendTransactionDto {
    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private String serviceName;
        private String from;
        private String to;
        private String data;
        private final Boolean requiredCallback = true;

        @Builder
        public Request(String serviceName, String from, String to, String data) {
            this.serviceName = serviceName;
            this.from = from;
            this.to = to;
            this.data = data;
        }
    }
    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private String transactionHash;
    }
}
