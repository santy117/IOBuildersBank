package com.santiagosalvador.IOBuildersBank.service;

import com.santiagosalvador.IOBuildersBank.model.User;
import com.santiagosalvador.IOBuildersBank.repository.UserRepository;
import com.santiagosalvador.IOBuildersBank.usecase.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return this.userRepository.getUserById(id);
    }

    @Override
    public void createUser(String username, String password, String email) {

    }
}
