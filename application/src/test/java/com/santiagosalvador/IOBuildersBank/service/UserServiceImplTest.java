package com.santiagosalvador.IOBuildersBank.service;

import com.santiagosalvador.IOBuildersBank.exception.UserException;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

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
        mockUser.setId(userId);
        mockUser.setUsername("santisr117");
        mockUser.setEmail("santiagosr117@gmail.com");
        mockUser.setPassword("123123");
        List<Wallet> wallets = new ArrayList<>();
        Wallet mockWallet = new Wallet();
        mockWallet.setUserId(userId);
        mockWallet.setBalance(BigDecimal.valueOf(100.0));
        mockWallet.setId(1L);
        wallets.add(mockWallet);
        mockUser.setWallets(wallets);

        when(userRepository.findUserById(userId)).thenReturn(mockUser);

        User result = userService.getUserById(userId);

        assertEquals(mockUser, result);
    }

    @Test
    void testGetUserByUsername() {
        String username = "santisr117";
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername(username);
        mockUser.setEmail("santiagosr117@gmail.com");
        mockUser.setPassword("123123");

        when(userRepository.findUserByUsername(username)).thenReturn(mockUser);

        User result = userService.getUserByUsername(username);

        assertEquals(mockUser, result);
    }

    @Test
    void testCreateUserSuccessfully() {
        String username = "newUser";
        String password = "password";
        String email = "newuser@gmail.com";

        when(passwordEncoder.encode(password)).thenReturn("passwordEncoded");
        when(userRepository.findUserByUsername(username)).thenReturn(null);

        User mockUser = new User(username, "passwordEncoded", email);
        when(userRepository.saveUser(any(User.class))).thenReturn(mockUser);

        User result = userService.createUser(username, password, email);

        assertEquals(mockUser, result);
    }

    @Test
    void testCreateUserAlreadyExists() {
        String username = "existingUser";
        String password = "password";
        String email = "existinguser@gmail.com";

        User existingUser = new User(username, password, email);
        when(userRepository.findUserByUsername(username)).thenReturn(existingUser);

        assertThrows(UserException.UserAlreadyExistsException.class, () -> userService.createUser(username, password, email));
    }
}
