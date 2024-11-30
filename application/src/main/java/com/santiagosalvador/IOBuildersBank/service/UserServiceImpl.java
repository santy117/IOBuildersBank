package com.santiagosalvador.IOBuildersBank.service;

import com.santiagosalvador.IOBuildersBank.exception.UserAlreadyExistsException;
import com.santiagosalvador.IOBuildersBank.model.User;
import com.santiagosalvador.IOBuildersBank.repository.UserRepository;
import com.santiagosalvador.IOBuildersBank.usecase.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Long id) {
        return this.userRepository.findUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    @Override
    public User createUser(String username, String password, String email) {
        User exists = this.getUserByUsername(username);
        if(exists != null){
            throw new UserAlreadyExistsException("User with username " + username + " already exists");
        }
        String encodedPassword = passwordEncoder.encode(password);

        return this.userRepository.saveUser(new User(username, encodedPassword, email));

    }
}
