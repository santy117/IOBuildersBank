package com.santiagosalvador.IOBuildersBank.output.repository;

import com.santiagosalvador.IOBuildersBank.output.entity.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BalanceJPARepository extends JpaRepository<BalanceEntity, Integer> {
}
