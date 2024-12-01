package com.santiagosalvador.IOBuildersBank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santiagosalvador.IOBuildersBank.PTSantiagoApplication;
import com.santiagosalvador.models.AuthResponseDTO;
import com.santiagosalvador.models.WalletDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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



    private String jwtToken;

    @BeforeEach
    public void setUp() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .param("username", "santisr117")
                        .param("password", "123123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        AuthResponseDTO authResponse = objectMapper.readValue(result.getResponse().getContentAsString(), AuthResponseDTO.class);
        jwtToken = authResponse.getToken();
    }


    @Test
    void testGetWalletById() throws Exception {
        // Assuming the database contains a wallet with ID 1
        mockMvc.perform(get("/wallet/1")
                        .header("Authorization", "Bearer " + jwtToken)
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
                        .header("Authorization", "Bearer " + jwtToken)
                        .param("userId", "1")
                        .param("name", "wallet3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testPostWalletConflict() throws Exception {
        // Assuming the user already has a wallet named "wallet1"
        mockMvc.perform(post("/wallet")
                        .header("Authorization", "Bearer " + jwtToken)
                        .param("userId", "1")
                        .param("name", "wallet1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void testPostWalletDepositSuccess() throws Exception {
        mockMvc.perform(post("/wallet/1/deposit")
                        .header("Authorization", "Bearer " + jwtToken)
                        .queryParam("amount", "50.00")
                        .queryParam("description", "Deposit test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testPostWalletTransferSuccess() throws Exception {
        mockMvc.perform(post("/wallet/1/transfer")
                        .header("Authorization", "Bearer " + jwtToken)
                        .param("targetId", "2")
                        .param("amount", "25.00")
                        .param("description", "Transfer test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testPostWalletTransferInsufficientFunds() throws Exception {
        mockMvc.perform(post("/wallet/1/transfer")
                        .header("Authorization", "Bearer " + jwtToken)
                        .param("targetId", "2")
                        .param("amount", "1000.00")
                        .param("description", "Transfer test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
}
