package com.santiagosalvador.IOBuildersBank.input.controller;

import com.example.api.PaymentsApi;
import com.santiagosalvador.IOBuildersBank.input.mapper.BalanceDtoMapper;

import com.santiagosalvador.IOBuildersBank.service.PaymentsService;
import com.santiagosalvador.models.BalanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


@Controller
public class PaymentsController implements PaymentsApi {

    @Autowired
    PaymentsService paymentsService;

    @Autowired
    BalanceDtoMapper balanceDtoMapper;
    @Override
    public ResponseEntity<BalanceDTO> getPaymentsBalanceId(Integer id) {
        BalanceDTO response = this.balanceDtoMapper.toDto(this.paymentsService.getPaymentsBalanceId(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
