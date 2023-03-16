package com.skcc.sto.chainz.dto;

import lombok.*;

public class CreateErc20TokenDto {
    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private String admin;
        private String recipient;
        private String name;
        private String symbol;
        private String description;
        private Long amount;
        private String serviceName;

        @Builder
        public Request(String admin, String recipient, String name, String symbol, String description, Long amount, String serviceName) {
            this.admin = admin;
            this.recipient = recipient;
            this.name = name;
            this.symbol = symbol;
            this.description = description;
            this.amount = amount;
            this.serviceName = serviceName;
        }
    }
    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private String txHash;
    }
}
