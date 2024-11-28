package com.santiagosalvador.IOBuildersBank.input.controller;

import com.santiagosalvador.IOBuildersBank.exception.UserAlreadyExistsException;
import com.santiagosalvador.IOBuildersBank.exception.WalletAlreadyExistsException;
import com.santiagosalvador.IOBuildersBank.usecase.WalletService;
import com.santiagosalvador.api.WalletApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class WalletController implements WalletApi {

    @Autowired
    WalletService walletService;


    @Override
    public ResponseEntity<Void> postWallet(Long userId, String name) {
        try {
            this.walletService.createWallet(userId, name);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (WalletAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
