package com.santiagosalvador.IOBuildersBank.repository;

import com.santiagosalvador.IOBuildersBank.model.Wallet;

public interface WalletRepository {

    Wallet findWalletByUserIdAndName(Long userId, String name);

    Wallet saveWallet(Wallet wallet);
}
