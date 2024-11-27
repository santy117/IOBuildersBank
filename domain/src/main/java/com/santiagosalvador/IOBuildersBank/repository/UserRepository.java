package com.santiagosalvador.IOBuildersBank.repository;

import com.santiagosalvador.IOBuildersBank.model.User;

public interface UserRepository {

    User getUserById(Long id);
}
