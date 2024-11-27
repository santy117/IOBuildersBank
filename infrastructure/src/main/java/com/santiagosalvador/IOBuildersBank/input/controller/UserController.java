package com.santiagosalvador.IOBuildersBank.input.controller;

import com.santiagosalvador.IOBuildersBank.input.mapper.UserDtoMapper;
import com.santiagosalvador.IOBuildersBank.usecase.UserService;
import com.santiagosalvador.api.UserApi;
import com.santiagosalvador.models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class UserController implements UserApi {

    @Autowired
    UserService userService;

    @Autowired
    UserDtoMapper userDtoMapper;

    @Override
    public ResponseEntity<UserDTO> getUserById(Long id) {
        UserDTO response = this.userDtoMapper.toDto(this.userService.getUserById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
