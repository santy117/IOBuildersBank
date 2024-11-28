package com.santiagosalvador.IOBuildersBank.usecase;

import com.santiagosalvador.IOBuildersBank.model.User;

public interface UserService {


    User getUserById(Long id);

    User getUserByUsername(String username);

    User createUser(String username, String password, String email);
}
