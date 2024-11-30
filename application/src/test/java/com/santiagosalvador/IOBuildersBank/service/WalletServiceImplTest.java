package com.santiagosalvador.IOBuildersBank.service;

import com.santiagosalvador.IOBuildersBank.exception.WalletException;
import com.santiagosalvador.IOBuildersBank.model.Transaction;
import com.santiagosalvador.IOBuildersBank.model.Wallet;
import com.santiagosalvador.IOBuildersBank.repository.TransactionRepository;
import com.santiagosalvador.IOBuildersBank.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletServiceImplTest {

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private WalletServiceImpl walletService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWalletByUserIDAndName() {
        Long userId = 1L;
        String walletName = "Main Wallet";

        Wallet mockWallet = new Wallet();
        mockWallet.setId(1L);
        mockWallet.setName(walletName);
        mockWallet.setUserId(userId);
        mockWallet.setBalance(BigDecimal.valueOf(100.0));

        when(walletRepository.findWalletByUserIdAndName(userId, walletName)).thenReturn(mockWallet);

        Wallet result = walletService.getWalletByUserIDAndName(userId, walletName);

        assertEquals(mockWallet, result);
    }

    @Test
    void testWalletDepositSuccessfully() {
        Long walletId = 1L;
        BigDecimal amount = BigDecimal.valueOf(100.00);
        String description = "Test Deposit";

        Wallet mockWallet = new Wallet();
        mockWallet.setId(walletId);
        mockWallet.setBalance(BigDecimal.valueOf(200.00));
        mockWallet.setTransactions(new ArrayList<>());

        when(walletRepository.findWalletById(walletId)).thenReturn(mockWallet);

        walletService.walletDeposit(walletId, amount, description);

        verify(walletRepository, times(1)).saveWallet(mockWallet);
        assertEquals(BigDecimal.valueOf(300.00), mockWallet.getBalance());
    }

    @Test
    void testWalletDepositWalletNotFound() {
        Long walletId = 1L;
        BigDecimal amount = BigDecimal.valueOf(100.00);
        String description = "Test Deposit";

        when(walletRepository.findWalletById(walletId)).thenReturn(null);

        assertThrows(WalletException.WalletNotFoundException.class, () ->
                walletService.walletDeposit(walletId, amount, description));

    }

    @Test
    void testGetWalletByWalletId() {
        Long walletId = 1L;

        Wallet mockWallet = new Wallet();
        mockWallet.setId(walletId);
        mockWallet.setName("Test Wallet");
        mockWallet.setBalance(BigDecimal.valueOf(150.00));

        when(walletRepository.findWalletById(walletId)).thenReturn(mockWallet);

        Wallet result = walletService.getWalletByWalletId(walletId);

        assertEquals(mockWallet, result);
    }

    @Test
    void testCreateWalletSuccessfully() {
        Long userId = 1L;
        String walletName = "New Wallet";

        when(walletRepository.findWalletByUserIdAndName(userId, walletName)).thenReturn(null);

        Wallet newWallet = new Wallet(userId, walletName);
        when(walletRepository.saveWallet(any(Wallet.class))).thenReturn(newWallet);

        Wallet result = walletService.createWallet(userId, walletName);

        assertEquals(newWallet, result);
        verify(walletRepository, times(1)).saveWallet(any(Wallet.class));
    }

    @Test
    void testCreateWalletAlreadyExists() {
        Long userId = 1L;
        String walletName = "Existing Wallet";

        Wallet existingWallet = new Wallet(userId, walletName);
        when(walletRepository.findWalletByUserIdAndName(userId, walletName)).thenReturn(existingWallet);

        assertThrows(WalletException.WalletAlreadyExistsException.class, () ->
                walletService.createWallet(userId, walletName));

    }

    @Test
    void testWalletTransferSuccessfully() {
        Long sourceWalletId = 1L;
        Long targetWalletId = 2L;
        BigDecimal amount = BigDecimal.valueOf(50.00);
        String description = "Transfer Test";

        Wallet sourceWallet = new Wallet();
        sourceWallet.setId(sourceWalletId);
        sourceWallet.setBalance(BigDecimal.valueOf(100.00));
        sourceWallet.setTransactions(new ArrayList<>());

        Wallet targetWallet = new Wallet();
        targetWallet.setId(targetWalletId);
        targetWallet.setBalance(BigDecimal.valueOf(50.00));
        targetWallet.setTransactions(new ArrayList<>());

        when(walletRepository.findWalletById(sourceWalletId)).thenReturn(sourceWallet);
        when(walletRepository.findWalletById(targetWalletId)).thenReturn(targetWallet);

        walletService.walletTransfer(sourceWalletId, targetWalletId, amount, description);

        verify(walletRepository, times(1)).saveWallet(sourceWallet);
        verify(walletRepository, times(1)).saveWallet(targetWallet);

        assertEquals(BigDecimal.valueOf(50.00), sourceWallet.getBalance());
        assertEquals(BigDecimal.valueOf(100.00), targetWallet.getBalance());
    }

    @Test
    void testWalletTransferWalletNotFound() {
        Long sourceWalletId = 1L;
        Long targetWalletId = 2L;
        BigDecimal amount = BigDecimal.valueOf(50.00);
        String description = "Transfer Test";

        when(walletRepository.findWalletById(sourceWalletId)).thenReturn(null);

        assertThrows(WalletException.WalletNotFoundException.class, () ->
                walletService.walletTransfer(sourceWalletId, targetWalletId, amount, description));
    }
}
