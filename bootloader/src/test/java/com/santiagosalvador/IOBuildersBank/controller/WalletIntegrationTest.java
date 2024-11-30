package com.santiagosalvador.IOBuildersBank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santiagosalvador.IOBuildersBank.PTSantiagoApplication;
import com.santiagosalvador.models.WalletDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PTSantiagoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class WalletIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetWalletById() throws Exception {
        // Assuming the database contains a wallet with ID 1
        mockMvc.perform(get("/wallet/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("wallet1"))
                .andExpect(jsonPath("$.transactions").isArray());
    }

    @Test
    void testPostWalletSuccess() throws Exception {
        mockMvc.perform(post("/wallet")
                        .param("userId", "1")
                        .param("name", "wallet3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testPostWalletConflict() throws Exception {
        // Assuming the user already has a wallet named "wallet1"
        mockMvc.perform(post("/wallet")
                        .param("userId", "1")
                        .param("name", "wallet1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void testPostWalletDepositSuccess() throws Exception {
        mockMvc.perform(post("/wallet/1/deposit") // El ID de la wallet está en el path
                        .queryParam("amount", "50.00") // El resto son query parameters
                        .queryParam("description", "Deposit test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testPostWalletTransferSuccess() throws Exception {
        mockMvc.perform(post("/wallet/1/transfer")
                        .param("targetId", "2")
                        .param("amount", "25.00")
                        .param("description", "Transfer test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testPostWalletTransferInsufficientFunds() throws Exception {
        // Assuming the source wallet has insufficient funds
        mockMvc.perform(post("/wallet/1/transfer")
                        .param("targetId", "2")
                        .param("amount", "1000.00")
                        .param("description", "Transfer test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
}
