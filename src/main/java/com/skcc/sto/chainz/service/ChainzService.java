package com.skcc.sto.chainz.service;

import com.skcc.sto.chainz.dto.CreateAccountDto;
import com.skcc.sto.chainz.dto.TransactionResultDto;

public interface ChainzService {

    String deployContract(String from);

    CreateAccountDto.Response createAccount(String userId);

    TransactionResultDto.Message getTransactionResult(String userId);

    String sendTransaction(String from, String to, String data);

    String callFunction(String from, String to, String data);

    String createErc20Token(String name, String symbol, String description, long amount);
}
