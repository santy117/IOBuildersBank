package com.santiagosalvador.IOBuildersBank.controller;

import com.santiagosalvador.IOBuildersBank.exception.WalletException;
import com.santiagosalvador.IOBuildersBank.input.controller.WalletController;
import com.santiagosalvador.IOBuildersBank.input.mapper.WalletDtoMapper;
import com.santiagosalvador.IOBuildersBank.model.Wallet;
import com.santiagosalvador.IOBuildersBank.usecase.WalletService;
import com.santiagosalvador.models.WalletDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletControllerTest {

    @InjectMocks
    private WalletController walletController;

    @Mock
    private WalletService walletService;

    @Mock
    private WalletDtoMapper walletDtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWalletId() {
        Long walletId = 1L;
        Wallet wallet = new Wallet();
        WalletDTO walletDTO = new WalletDTO();

        when(walletService.getWalletByWalletId(walletId)).thenReturn(wallet);
        when(walletDtoMapper.toDto(wallet)).thenReturn(walletDTO);

        ResponseEntity<WalletDTO> responseEntity = walletController.getWalletId(walletId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(walletDTO, responseEntity.getBody());
    }

    @Test
    void testPostWalletCreated() {
        Long userId = 1L;
        String name = "Main Wallet";

        when(walletService.createWallet(userId, name)).thenReturn(null);

        ResponseEntity<Void> responseEntity = walletController.postWallet(userId, name);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testPostWalletConflict() {
        Long userId = 1L;
        String name = "Main Wallet";

        when(walletService.createWallet(userId, name)).thenThrow(WalletException.WalletAlreadyExistsException.class);

        ResponseEntity<Void> responseEntity = walletController.postWallet(userId, name);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @Test
    void testPostWalletDepositOK() {
        Long walletId = 1L;
        BigDecimal amount = BigDecimal.valueOf(100.00);
        String description = "Deposit";

        doNothing().when(walletService).walletDeposit(walletId, amount, description);

        ResponseEntity<Void> responseEntity = walletController.postWalletDeposit(walletId, amount, description);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testPostWalletDepositConflict() {
        Long walletId = 1L;
        BigDecimal amount = BigDecimal.valueOf(100.00);
        String description = "Deposit";

        doThrow(WalletException.WalletUpdateException.class)
                .when(walletService).walletDeposit(walletId, amount, description);

        ResponseEntity<Void> responseEntity = walletController.postWalletDeposit(walletId, amount, description);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @Test
    void testPostWalletTransferOK() {
        Long sourceWalletId = 1L;
        Long targetWalletId = 2L;
        BigDecimal amount = BigDecimal.valueOf(50.00);
        String description = "Transfer";

        doNothing().when(walletService).walletTransfer(sourceWalletId, targetWalletId, amount, description);

        ResponseEntity<Void> responseEntity = walletController.postWalletTransfer(sourceWalletId, targetWalletId, amount, description);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testPostWalletTransferConflict() {
        Long sourceWalletId = 1L;
        Long targetWalletId = 2L;
        BigDecimal amount = BigDecimal.valueOf(50.00);
        String description = "Transfer";

        doThrow(WalletException.WalletUpdateException.class)
                .when(walletService).walletTransfer(sourceWalletId, targetWalletId, amount, description);

        ResponseEntity<Void> responseEntity = walletController.postWalletTransfer(sourceWalletId, targetWalletId, amount, description);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }
}
