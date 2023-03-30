package com.skcc.sample.chainz.service.impl;

import com.skcc.sample.chainz.client.Erc20Client;
import com.skcc.sample.chainz.client.ServerKeyServiceClient;
import com.skcc.sample.chainz.client.TransactionManagerClient;
import com.skcc.sample.chainz.dto.*;
import com.skcc.sample.chainz.service.ChainzService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChainzServiceImpl implements ChainzService {
    @Value("${chainz.service.name}")
    private String serviceName;
    @Value("${chainz.service.provider}")
    private String serviceProvider;
    @Value("${chainz.erc20.admin}")
    private String tokenAdmin;
    @Value("${chainz.erc20.recipient}")
    private String tokenRecipient;

    // Remix IDE의 Store Contract의 Bytecode
    static String bytecode = "0x608060405234801561001057600080fd5b50610150806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c80632e64cec11461003b5780636057361d14610059575b600080fd5b610043610075565b60405161005091906100a1565b60405180910390f35b610073600480360381019061006e91906100ed565b61007e565b005b60008054905090565b8060008190555050565b6000819050919050565b61009b81610088565b82525050565b60006020820190506100b66000830184610092565b92915050565b600080fd5b6100ca81610088565b81146100d557600080fd5b50565b6000813590506100e7816100c1565b92915050565b600060208284031215610103576101026100bc565b5b6000610111848285016100d8565b9150509291505056fea2646970667358221220322c78243e61b783558509c9cc22cb8493dde6925aa5e89a08cdf6e22f279ef164736f6c63430008120033";

    private final ServerKeyServiceClient serverKeyServiceClient;
    private final TransactionManagerClient transactionManagerClient;
    private final Erc20Client erc20Client;

    @Override
    public String deployContract(String from) {
        return transactionManagerClient.sendTransaction(serviceProvider,
                SendTransactionDto.Request.builder()
                .serviceName(serviceName)
                .from(from)
                .to(null)
                .data(bytecode)
                .build()).getTransactionHash();
    }

    @Override
    public CreateAccountDto.Response createAccount(String userId) {
        return serverKeyServiceClient.createAccount(serviceProvider,
                CreateAccountDto.Request.builder().userId(userId).build());
    }

    @Override
    public TransactionResultDto.Message getTransactionResult(String transactionHash) {
        return transactionManagerClient.getTransactionResult(serviceProvider, transactionHash);
    }

    @Override
    public String sendTransaction(String from, String to, String data) {
        return transactionManagerClient.sendTransaction(serviceProvider,
            SendTransactionDto.Request.builder()
                .serviceName(serviceName)
                .from(from)
                .to(to)
                .data(data)
                .build()).getTransactionHash();
    }

    @Override
    public String callFunction(String from, String to, String data) {
        return transactionManagerClient.callFunction(serviceProvider,
            CallFunctionDto.Request.builder()
                .callObject( CallFunctionDto.CallObject.builder()
                    .from(from)
                    .to(to)
                    .data(data)
                    .build())
                .build())
            .getResponse();
    }

    @Override
    public String createErc20Token(String name, String symbol, String description, long amount) {
        return erc20Client.createErc20Token(serviceProvider,
            CreateErc20TokenDto.Request.builder()
                .admin(tokenAdmin)
                .recipient(tokenRecipient)
                .name(name)
                .symbol(symbol)
                .description(description)
                .amount(amount)
                .build()).getTxHash();
    }
}
