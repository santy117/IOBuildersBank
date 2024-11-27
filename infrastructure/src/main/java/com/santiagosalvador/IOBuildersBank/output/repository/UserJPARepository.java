package com.santiagosalvador.IOBuildersBank.output.repository;

import com.santiagosalvador.IOBuildersBank.output.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<UserEntity, Long> {
}
