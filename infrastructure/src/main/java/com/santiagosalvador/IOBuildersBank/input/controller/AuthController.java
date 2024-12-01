package com.santiagosalvador.IOBuildersBank.input.controller;

import com.santiagosalvador.IOBuildersBank.input.utils.JwtTokenUtil;
import com.santiagosalvador.IOBuildersBank.model.User;
import com.santiagosalvador.IOBuildersBank.usecase.UserService;
import com.santiagosalvador.api.LoginApi;
import com.santiagosalvador.models.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController implements LoginApi {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public ResponseEntity<AuthResponseDTO> postLogin(String username, String password) {

        User user = userService.getUserByUsername(username);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = this.jwtTokenUtil.generateToken(user.getUsername());

        AuthResponseDTO responseDTO = new AuthResponseDTO();
        responseDTO.setToken(token);
        responseDTO.setUsername(username);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }
}
