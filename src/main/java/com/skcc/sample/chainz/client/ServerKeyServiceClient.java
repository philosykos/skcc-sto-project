package com.skcc.sample.chainz.client;

import com.skcc.sample.chainz.dto.CreateAccountDto;
import com.skcc.sample.chainz.dto.CreateAccountDto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="sksClient", url = "${chainz.server_key_service.url}")
public interface ServerKeyServiceClient {
    @PostMapping(value="/accounts")
    Response createAccount(@RequestHeader("x-service-provider") String serviceProvider, @RequestBody CreateAccountDto.Request request);
}
