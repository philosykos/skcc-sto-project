package com.skcc.sample.chainz.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TransactionResultDto {
    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Message {
        String serviceProvider;
        String transactionHash;
        String serviceName;
        TransactionStatus transactionStatus;
        TransactionReceipt transactionReceipt;
    }
    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TransactionReceipt {
        String status;
        String transactionHash;
        String transactionIndex;
        String blockHash;
        String blockNumber;
        String from;
        String to;
        String contractAddress;
        String cumulativeGasUsed;
        String gasUsed;
        String effectiveGasPrice;
        List<Log> logs;
        String logsBloom;
        Object events;
    }
    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Log {
        String address;
        String data;
        List<String> topics;
        String logIndex;
        String transactionIndex;
        String transactionHash;
        String blockHash;
        String blockNumber;
    }

    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ErrorMessage {
        Integer code;
        String message;

        @Builder
        public ErrorMessage(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
