package com.santiagosalvador.IOBuildersBank.usecase;

import com.santiagosalvador.IOBuildersBank.model.Wallet;

import java.math.BigDecimal;

public interface WalletService {

    Wallet createWallet(Long userId, String name);
    Wallet getWalletByUserIDAndName(Long userId, String name);

    void walletDeposit(Long id, BigDecimal amount, String description);

    Wallet getWalletByWalletId(Long id);
}
