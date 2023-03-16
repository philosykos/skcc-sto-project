package com.skcc.sto.token.controller;

import com.skcc.sto.common.dto.ResponseTemplate;
import com.skcc.sto.token.dto.CreateTokenDto;
import com.skcc.sto.token.dto.CreateTokenDto.Response;
import com.skcc.sto.token.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@AllArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @Operation(summary = "ERC20 토큰 배포", description = "ERC20 토큰 배포")
    @PostMapping(value = "/create")
    public ResponseTemplate<CreateTokenDto.Response> createErc20Token(@RequestBody CreateTokenDto.Request request) {
        CreateTokenDto.Response response = new Response(tokenService.createErc20Token(request));
        return ResponseTemplate.<CreateTokenDto.Response>builder()
            .success(true)
            .result(response)
            .build();
    }
}
