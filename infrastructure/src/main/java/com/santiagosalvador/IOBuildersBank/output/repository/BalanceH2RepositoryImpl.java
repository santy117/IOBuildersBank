package com.santiagosalvador.IOBuildersBank.output.repository;

import com.santiagosalvador.IOBuildersBank.model.Balance;
import com.santiagosalvador.IOBuildersBank.output.mapper.BalanceEntityMapper;
import com.santiagosalvador.IOBuildersBank.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BalanceH2RepositoryImpl implements BalanceRepository {

    @Autowired
    BalanceJPARepository balanceJPARepository;

    @Autowired
    BalanceEntityMapper balanceEntityMapper;

    @Override
    public Balance getById(Integer id) {
       return balanceEntityMapper.toDomain(balanceJPARepository.getById(id));
    }
}
