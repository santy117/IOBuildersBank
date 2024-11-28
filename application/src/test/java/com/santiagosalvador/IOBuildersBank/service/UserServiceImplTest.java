package com.santiagosalvador.IOBuildersBank.service;

import com.santiagosalvador.IOBuildersBank.model.User;
import com.santiagosalvador.IOBuildersBank.model.Wallet;
import com.santiagosalvador.IOBuildersBank.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("santisr117");
        mockUser.setEmail("santiagosr117@gmail.com");
        mockUser.setPassword("123123");
        List<Wallet> wallets = new ArrayList<>();
        Wallet mockWallet = new Wallet();
        mockWallet.setUserId(1L);
        mockWallet.setBalance(BigDecimal.valueOf(100.0));
        mockWallet.setId(1L);
        wallets.add(mockWallet);
        mockUser.setWallets(wallets);

        when(userRepository.findUserById(userId)).thenReturn(mockUser);

        User result = userService.getUserById(userId);

        assertEquals(mockUser, result);
    }
}
