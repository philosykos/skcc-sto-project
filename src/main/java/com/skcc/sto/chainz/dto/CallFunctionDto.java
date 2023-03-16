package com.skcc.sto.chainz.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CallFunctionDto {
    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private CallObject callObject;

        @Builder
        public Request(CallObject callObject) {
            this.callObject = callObject;
        }
    }

    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class CallObject {
        private String from;
        private String to;
        private String data;
        private final String gas = "0x4000000";
    }

    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private String response;
    }
}
