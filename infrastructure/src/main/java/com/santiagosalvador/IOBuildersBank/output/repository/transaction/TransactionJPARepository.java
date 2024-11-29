package com.santiagosalvador.IOBuildersBank.output.repository.transaction;

import com.santiagosalvador.IOBuildersBank.output.entity.TransactionEntity;
import com.santiagosalvador.IOBuildersBank.output.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionJPARepository extends JpaRepository<TransactionEntity, Long> {
    //List<TransactionEntity> findByWalletId(Long id);
}
