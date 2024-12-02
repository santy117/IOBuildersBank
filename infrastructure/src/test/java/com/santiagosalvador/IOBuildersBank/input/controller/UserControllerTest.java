package com.santiagosalvador.IOBuildersBank.input.controller;

import com.santiagosalvador.IOBuildersBank.exception.UserException;
import com.santiagosalvador.IOBuildersBank.input.mapper.UserDtoMapper;
import com.santiagosalvador.IOBuildersBank.model.User;
import com.santiagosalvador.IOBuildersBank.usecase.UserService;
import com.santiagosalvador.models.UserCreationDTO;
import com.santiagosalvador.models.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserDtoMapper balanceDtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        Long id = 1L;
        UserDTO userDTO = new UserDTO();
        User serviceResponse = new User();

        when(userService.getUserById(id)).thenReturn(serviceResponse);
        when(balanceDtoMapper.toDto(serviceResponse)).thenReturn(userDTO);

        ResponseEntity<UserDTO> responseEntity = userController.getUserById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDTO, responseEntity.getBody());

    }

    @Test
    void testPostUserOK(){
        UserCreationDTO userCreationDTO = new UserCreationDTO();
        userCreationDTO.setUsername("username");
        userCreationDTO.setPassword("password");
        userCreationDTO.setEmail("mail");

        when(userService.createUser(userCreationDTO.getUsername(),userCreationDTO.getPassword(),userCreationDTO.getEmail())).thenReturn(null);
        ResponseEntity<Void> responseEntity = userController.postUser(userCreationDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testPostUserConflict(){
        UserCreationDTO userCreationDTO = new UserCreationDTO();
        userCreationDTO.setUsername("username");
        userCreationDTO.setPassword("password");
        userCreationDTO.setEmail("mail");

        when(userService.createUser(userCreationDTO.getUsername(),userCreationDTO.getPassword(),userCreationDTO.getEmail())).thenThrow(UserException.UserAlreadyExistsException.class);
        ResponseEntity<Void> responseEntity = userController.postUser(userCreationDTO);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());


    }
}
