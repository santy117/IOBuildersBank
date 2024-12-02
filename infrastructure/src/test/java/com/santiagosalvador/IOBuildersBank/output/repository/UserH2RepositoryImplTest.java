package com.santiagosalvador.IOBuildersBank.output.repository;

import com.santiagosalvador.IOBuildersBank.model.User;
import com.santiagosalvador.IOBuildersBank.output.entity.UserEntity;
import com.santiagosalvador.IOBuildersBank.output.mapper.UserEntityMapper;
import com.santiagosalvador.IOBuildersBank.output.repository.user.UserH2RepositoryImpl;
import com.santiagosalvador.IOBuildersBank.output.repository.user.UserJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class UserH2RepositoryImplTest {

    @Mock
    private UserJPARepository userJPARepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UserH2RepositoryImpl userH2Repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindUserById() {
        Long userId = 1L;
        UserEntity mockUserEntity = new UserEntity();
        mockUserEntity.setId(userId);
        mockUserEntity.setUsername("santisr117");

        User mockUser = new User();
        mockUser.setId(userId);
        mockUser.setUsername("santisr117");

        when(userJPARepository.getById(userId)).thenReturn(mockUserEntity);
        when(userEntityMapper.toDomain(mockUserEntity)).thenReturn(mockUser);

        User result = userH2Repository.findUserById(userId);

        assertEquals(mockUser, result);
        verify(userJPARepository).getById(userId);
        verify(userEntityMapper).toDomain(mockUserEntity);
    }

    @Test
    void testSaveUser() {
        User mockUser = new User();
        mockUser.setUsername("santisr117");

        UserEntity mockUserEntity = new UserEntity();
        mockUserEntity.setUsername("santisr117");

        when(userEntityMapper.toEntity(mockUser)).thenReturn(mockUserEntity);
        when(userJPARepository.save(mockUserEntity)).thenReturn(mockUserEntity);
        when(userEntityMapper.toDomain(mockUserEntity)).thenReturn(mockUser);

        User result = userH2Repository.saveUser(mockUser);

        assertEquals(mockUser, result);
        verify(userEntityMapper).toEntity(mockUser);
        verify(userJPARepository).save(mockUserEntity);
        verify(userEntityMapper).toDomain(mockUserEntity);
    }

    @Test
    void testFindUserByUsername() {
        String username = "santisr117";
        UserEntity mockUserEntity = new UserEntity();
        mockUserEntity.setUsername(username);

        User mockUser = new User();
        mockUser.setUsername(username);

        when(userJPARepository.findByUsername(username)).thenReturn(Optional.of(mockUserEntity));
        when(userEntityMapper.toDomain(mockUserEntity)).thenReturn(mockUser);

        User result = userH2Repository.findUserByUsername(username);

        assertEquals(mockUser, result);
        verify(userJPARepository).findByUsername(username);
        verify(userEntityMapper).toDomain(mockUserEntity);
    }

    @Test
    void testFindUserByUsernameNotFound() {
        String username = "nonexistent";

        when(userJPARepository.findByUsername(username)).thenReturn(Optional.empty());

        User result = userH2Repository.findUserByUsername(username);

        assertNull(result);
        verify(userJPARepository).findByUsername(username);
        verifyNoInteractions(userEntityMapper);
    }
}
