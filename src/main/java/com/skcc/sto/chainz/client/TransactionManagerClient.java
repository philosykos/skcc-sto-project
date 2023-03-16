package com.skcc.sto.chainz.client;

import com.skcc.sto.chainz.dto.CallFunctionDto;
import com.skcc.sto.chainz.dto.SendTransactionDto;
import com.skcc.sto.chainz.dto.TransactionResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="transactionManagerClient", url="${chainz.transaction_manager.url}")
public interface TransactionManagerClient {

    @PostMapping(value="/transactions")
    SendTransactionDto.Response sendTransaction(@RequestHeader("x-service-provider") String serviceProvider, @RequestBody SendTransactionDto.Request request);

    @GetMapping(value="/transactions/{id}")
    TransactionResultDto.Message getTransactionResult(@RequestHeader("x-service-provider") String serviceProvider, @PathVariable String id);

    @PostMapping(value="/extension/web3-api/call")
    CallFunctionDto.Response callFunction(@RequestHeader("x-service-provider") String serviceProvider, @RequestBody CallFunctionDto.Request request);
}
