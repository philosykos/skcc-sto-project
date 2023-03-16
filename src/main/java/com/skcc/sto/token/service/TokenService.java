package com.skcc.sto.token.service;

import com.skcc.sto.token.dto.CreateTokenDto;

public interface TokenService {

    String createErc20Token(CreateTokenDto.Request createTokenRequest);
}
