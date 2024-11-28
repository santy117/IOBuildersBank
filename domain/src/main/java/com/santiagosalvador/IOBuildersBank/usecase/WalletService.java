package com.santiagosalvador.IOBuildersBank.usecase;

import com.santiagosalvador.IOBuildersBank.model.Wallet;

public interface WalletService {

    Wallet createWallet(Long userId, String name);
    Wallet getWalletByUserIDAndName(Long userId, String name);
}
