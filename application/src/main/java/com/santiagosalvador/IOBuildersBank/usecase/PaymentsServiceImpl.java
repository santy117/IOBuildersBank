package com.santiagosalvador.IOBuildersBank.usecase;

import com.santiagosalvador.IOBuildersBank.repository.BalanceRepository;
import com.santiagosalvador.IOBuildersBank.service.PaymentsService;
import com.santiagosalvador.IOBuildersBank.model.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    BalanceRepository balanceRepository;


    @Override
    public Balance getPaymentsBalanceId(Integer id) {

        return this.balanceRepository.getById(id);
    }
}
