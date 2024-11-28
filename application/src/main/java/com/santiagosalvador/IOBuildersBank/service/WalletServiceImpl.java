package com.santiagosalvador.IOBuildersBank.service;

import com.santiagosalvador.IOBuildersBank.exception.WalletException;
import com.santiagosalvador.IOBuildersBank.model.Wallet;
import com.santiagosalvador.IOBuildersBank.repository.WalletRepository;
import com.santiagosalvador.IOBuildersBank.usecase.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    WalletRepository walletRepository;
    @Override
    public Wallet getWalletByUserIDAndName(Long userId, String name) {
        return this.walletRepository.findWalletByUserIdAndName(userId, name);
    }

    @Override
    @Transactional
    public void walletDeposit(Long id, BigDecimal amount) {
        Wallet wallet = this.walletRepository.findWalletById(id);
        if (wallet == null) {
            throw new WalletException.WalletNotFoundException("Wallet with ID " + id + " not found.");
        }

        wallet.deposit(amount);

        //TODO: CREAR TRANSACCION
        try {
            this.walletRepository.saveWallet(wallet);
        } catch (Exception e) {
            throw new WalletException.WalletUpdateException("Failed to update wallet with ID " + id, e);
        }
    }

    @Override
    public Wallet createWallet(Long userId, String name) {
        Wallet exists = this.getWalletByUserIDAndName(userId, name);
        if(exists != null){
            throw new WalletException.WalletAlreadyExistsException("Wallet with name " + name + " already exists for the user id "+ userId);
        }
        return this.walletRepository.saveWallet(new Wallet(userId, name));

    }

}
