package com.santiagosalvador.IOBuildersBank.input.controller;

import com.santiagosalvador.IOBuildersBank.exception.WalletException;
import com.santiagosalvador.IOBuildersBank.input.mapper.WalletDtoMapper;
import com.santiagosalvador.IOBuildersBank.model.Wallet;
import com.santiagosalvador.IOBuildersBank.usecase.WalletService;
import com.santiagosalvador.api.WalletApi;
import com.santiagosalvador.models.WalletDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
public class WalletController implements WalletApi {

    @Autowired
    WalletService walletService;

    @Autowired
    WalletDtoMapper walletDtoMapper;

    @Override
    public ResponseEntity<WalletDTO> getWalletId(Long id) {
        WalletDTO walletDTO = this.walletDtoMapper.toDto(this.walletService.getWalletByWalletId(id));
        return new ResponseEntity<>(walletDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> postWallet(Long userId, String name) {
        try {
            this.walletService.createWallet(userId, name);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (WalletException.WalletAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<Void> postWalletDeposit(Long id, BigDecimal amount, String description) {
        try {
            this.walletService.walletDeposit(id, amount, description);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (WalletException.WalletUpdateException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
