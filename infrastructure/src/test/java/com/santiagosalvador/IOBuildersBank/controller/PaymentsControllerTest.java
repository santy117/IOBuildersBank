package com.santiagosalvador.IOBuildersBank.controller;

import com.santiagosalvador.IOBuildersBank.input.controller.PaymentsController;
import com.santiagosalvador.IOBuildersBank.input.mapper.BalanceDtoMapper;
import com.santiagosalvador.IOBuildersBank.model.Balance;
import com.santiagosalvador.IOBuildersBank.service.PaymentsService;
import com.santiagosalvador.models.BalanceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentsControllerTest {

    @InjectMocks
    private PaymentsController paymentsController;

    @Mock
    private PaymentsService paymentsService;

    @Mock
    private BalanceDtoMapper balanceDtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPaymentsBalanceId() {
        Integer id = 1;
        BalanceDTO balanceDTO = new BalanceDTO(); // Simulated DTO object
        Balance serviceResponse = new Balance();   // Simulated service response object

        when(paymentsService.getPaymentsBalanceId(id)).thenReturn(serviceResponse);
        when(balanceDtoMapper.toDto(serviceResponse)).thenReturn(balanceDTO);

        ResponseEntity<BalanceDTO> responseEntity = paymentsController.getPaymentsBalanceId(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(balanceDTO, responseEntity.getBody());

    }
}
