package com.skcc.sample.token.service;

import com.skcc.sample.token.dto.CreateTokenDto;

public interface TokenService {

    String createErc20Token(CreateTokenDto.Request createTokenRequest);
}
