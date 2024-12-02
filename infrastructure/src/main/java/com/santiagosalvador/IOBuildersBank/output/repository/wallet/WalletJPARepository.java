package com.santiagosalvador.IOBuildersBank.output.repository.wallet;

import com.santiagosalvador.IOBuildersBank.output.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletJPARepository extends JpaRepository<WalletEntity, Long> {
    Optional<WalletEntity> findByUserIdAndName(Long userId, String name);
}
