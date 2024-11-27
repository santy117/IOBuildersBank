package com.santiagosalvador.IOBuildersBank.controller;

import com.santiagosalvador.IOBuildersBank.input.controller.UserController;
import com.santiagosalvador.IOBuildersBank.input.mapper.UserDtoMapper;
import com.santiagosalvador.IOBuildersBank.model.User;
import com.santiagosalvador.IOBuildersBank.usecase.UserService;
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
        UserDTO userDTO = new UserDTO(); // Simulated DTO object
        User serviceResponse = new User();   // Simulated service response object

        when(userService.getUserById(id)).thenReturn(serviceResponse);
        when(balanceDtoMapper.toDto(serviceResponse)).thenReturn(userDTO);

        ResponseEntity<UserDTO> responseEntity = userController.getUserById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDTO, responseEntity.getBody());

    }
}
