package com.santiagosalvador.IOBuildersBank.output.repository;

import com.santiagosalvador.IOBuildersBank.model.User;
import com.santiagosalvador.IOBuildersBank.output.entity.UserEntity;
import com.santiagosalvador.IOBuildersBank.output.mapper.UserEntityMapper;
import com.santiagosalvador.IOBuildersBank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserH2RepositoryImpl implements UserRepository {
    @Autowired
    UserJPARepository userJPARepository;

    @Autowired
    UserEntityMapper userEntityMapper;

    @Override
    public User getUserById(Long id) {
        return userEntityMapper.toDomain(userJPARepository.getById(id));
    }
}
