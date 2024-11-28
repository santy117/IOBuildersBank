package com.santiagosalvador.IOBuildersBank.output.repository.user;

import com.santiagosalvador.IOBuildersBank.model.User;
import com.santiagosalvador.IOBuildersBank.output.entity.UserEntity;
import com.santiagosalvador.IOBuildersBank.output.mapper.UserEntityMapper;
import com.santiagosalvador.IOBuildersBank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserH2RepositoryImpl implements UserRepository {
    @Autowired
    UserJPARepository userJPARepository;

    @Autowired
    UserEntityMapper userEntityMapper;

    @Override
    public User findUserById(Long id) {
        return userEntityMapper.toDomain(userJPARepository.getById(id));
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        return userEntityMapper.toDomain(userJPARepository.save(userEntity));
    }

    @Override
    public User findUserByUsername(String username) {
        Optional<UserEntity> userEntityOpt = userJPARepository.findByUsername(username);
        return userEntityOpt.map(userEntity -> userEntityMapper.toDomain(userEntity)).orElse(null);

    }
}
