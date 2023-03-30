package com.skcc.sample.chainz.client;

import com.skcc.sample.chainz.dto.CreateErc20TokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="erc20Client", url = "${chainz.erc20.url}")
public interface Erc20Client {

    @PutMapping(value="/admin/create")
    CreateErc20TokenDto.Response createErc20Token(@RequestHeader("x-service-provider") String serviceProvider, @RequestBody CreateErc20TokenDto.Request request);
}