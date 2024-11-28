package com.santiagosalvador.IOBuildersBank.repository;

import com.santiagosalvador.IOBuildersBank.model.User;

public interface UserRepository {

    User findUserById(Long id);

    User saveUser(User user);

    User findUserByUsername(String username);
}
