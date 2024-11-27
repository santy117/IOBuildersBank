package com.santiagosalvador.IOBuildersBank.repository;

import com.santiagosalvador.IOBuildersBank.model.Balance;


public interface BalanceRepository {

    Balance getById(Integer id);
}
