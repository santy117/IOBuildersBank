package com.santiagosalvador.IOBuildersBank.output.repository;

import com.santiagosalvador.IOBuildersBank.model.Wallet;
import com.santiagosalvador.IOBuildersBank.output.entity.UserEntity;
import com.santiagosalvador.IOBuildersBank.output.entity.WalletEntity;
import com.santiagosalvador.IOBuildersBank.output.mapper.WalletEntityMapper;
import com.santiagosalvador.IOBuildersBank.output.repository.wallet.WalletH2RepositoryImpl;
import com.santiagosalvador.IOBuildersBank.output.repository.wallet.WalletJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class WalletH2RepositoryImplTest {

    @Mock
    private WalletJPARepository walletJPARepository;

    @Mock
    private WalletEntityMapper walletEntityMapper;

    @InjectMocks
    private WalletH2RepositoryImpl walletH2Repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindWalletByUserIdAndName() {
        Long userId = 1L;
        String walletName = "MyWallet";

        WalletEntity mockWalletEntity = new WalletEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        mockWalletEntity.setId(1L);
        mockWalletEntity.setUser(userEntity);
        mockWalletEntity.setName(walletName);

        Wallet mockWallet = new Wallet();
        mockWallet.setId(1L);
        mockWallet.setUserId(userId);
        mockWallet.setName(walletName);

        when(walletJPARepository.findByUserIdAndName(userId, walletName)).thenReturn(Optional.of(mockWalletEntity));
        when(walletEntityMapper.toDomain(mockWalletEntity)).thenReturn(mockWallet);

        Wallet result = walletH2Repository.findWalletByUserIdAndName(userId, walletName);

        assertEquals(mockWallet, result);
        verify(walletJPARepository).findByUserIdAndName(userId, walletName);
        verify(walletEntityMapper).toDomain(mockWalletEntity);
    }

    @Test
    void testFindWalletByUserIdAndNameNotFound() {
        Long userId = 1L;
        String walletName = "NonexistentWallet";

        when(walletJPARepository.findByUserIdAndName(userId, walletName)).thenReturn(Optional.empty());

        Wallet result = walletH2Repository.findWalletByUserIdAndName(userId, walletName);

        assertNull(result);
        verify(walletJPARepository).findByUserIdAndName(userId, walletName);
        verifyNoInteractions(walletEntityMapper);
    }

    @Test
    void testSaveWallet() {
        Wallet mockWallet = new Wallet();
        mockWallet.setId(1L);
        mockWallet.setUserId(1L);
        mockWallet.setName("MyWallet");

        WalletEntity mockWalletEntity = new WalletEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        mockWalletEntity.setId(1L);
        mockWalletEntity.setUser(userEntity);
        mockWalletEntity.setName("MyWallet");

        when(walletEntityMapper.toEntity(mockWallet)).thenReturn(mockWalletEntity);
        when(walletJPARepository.save(mockWalletEntity)).thenReturn(mockWalletEntity);
        when(walletEntityMapper.toDomain(mockWalletEntity)).thenReturn(mockWallet);

        Wallet result = walletH2Repository.saveWallet(mockWallet);

        assertEquals(mockWallet, result);
        verify(walletEntityMapper).toEntity(mockWallet);
        verify(walletJPARepository).save(mockWalletEntity);
        verify(walletEntityMapper).toDomain(mockWalletEntity);
    }

    @Test
    void testFindWalletById() {
        Long walletId = 1L;

        WalletEntity mockWalletEntity = new WalletEntity();
        mockWalletEntity.setId(walletId);

        Wallet mockWallet = new Wallet();
        mockWallet.setId(walletId);

        when(walletJPARepository.findById(walletId)).thenReturn(Optional.of(mockWalletEntity));
        when(walletEntityMapper.toDomain(mockWalletEntity)).thenReturn(mockWallet);

        Wallet result = walletH2Repository.findWalletById(walletId);

        assertEquals(mockWallet, result);
        verify(walletJPARepository).findById(walletId);
        verify(walletEntityMapper).toDomain(mockWalletEntity);
    }

    @Test
    void testFindWalletByIdNotFound() {
        Long walletId = 999L;

        when(walletJPARepository.findById(walletId)).thenReturn(Optional.empty());

        Wallet result = walletH2Repository.findWalletById(walletId);

        assertNull(result);
        verify(walletJPARepository).findById(walletId);
        verifyNoInteractions(walletEntityMapper);
    }
}
