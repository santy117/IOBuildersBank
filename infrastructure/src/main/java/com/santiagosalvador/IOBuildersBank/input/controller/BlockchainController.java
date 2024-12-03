package com.santiagosalvador.IOBuildersBank.input.controller;

import com.santiagosalvador.api.BlockchainApi;
import com.santiagosalvador.models.BalanceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.web3j.crypto.Credentials;
import org.web3j.model.BalanceContract;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigDecimal;
import java.math.BigInteger;

@Controller
public class BlockchainController implements BlockchainApi {

    @Autowired
    private Web3j web3j;

    @Value("${blockchain.contract.address}")
    private String contractAddress;

    @Value("${blockchain.credentials.privateKey}")
    private String privateKey;

    @Override
    public ResponseEntity<BalanceResponseDTO> getBlockchainBalance(String walletAddress) {
        BalanceResponseDTO responseDTO = new BalanceResponseDTO();
        try {
            BalanceContract balanceContract = BalanceContract.load(
                    contractAddress,
                    web3j,
                    Credentials.create(privateKey),
                    new DefaultGasProvider()
            );

            BigInteger balance = balanceContract.checkEther(walletAddress).send();
            responseDTO.setBalance(BigDecimal.valueOf(balance.longValue()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(responseDTO);
    }
}
