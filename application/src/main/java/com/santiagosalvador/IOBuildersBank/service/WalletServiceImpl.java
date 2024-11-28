package com.santiagosalvador.IOBuildersBank.service;

import com.santiagosalvador.IOBuildersBank.exception.UserAlreadyExistsException;
import com.santiagosalvador.IOBuildersBank.exception.WalletAlreadyExistsException;
import com.santiagosalvador.IOBuildersBank.model.User;
import com.santiagosalvador.IOBuildersBank.model.Wallet;
import com.santiagosalvador.IOBuildersBank.repository.WalletRepository;
import com.santiagosalvador.IOBuildersBank.usecase.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    WalletRepository walletRepository;
    @Override
    public Wallet getWalletByUserIDAndName(Long userId, String name) {
        return this.walletRepository.findWalletByUserIdAndName(userId, name);
    }

    @Override
    public Wallet createWallet(Long userId, String name) {
        Wallet exists = this.getWalletByUserIDAndName(userId, name);
        if(exists != null){
            throw new WalletAlreadyExistsException("Wallet with name " + name + " already exists for the user id "+ userId);
        }
        return this.walletRepository.saveWallet(new Wallet(userId, name));

    }

}
