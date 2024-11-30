package com.santiagosalvador.IOBuildersBank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santiagosalvador.IOBuildersBank.PTSantiagoApplication;
import com.santiagosalvador.models.UserCreationDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PTSantiagoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetUserById() throws Exception {
        mockMvc.perform(get("/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("santisr117"))
                .andExpect(jsonPath("$.email").value("santiagosr117@gmail.com"))
                .andExpect(jsonPath("$.wallets").isArray()) // Verifica que wallets es un array
                .andExpect(jsonPath("$.wallets[0].id").value(1))
                .andExpect(jsonPath("$.wallets[0].name").value("wallet1"))
                .andExpect(jsonPath("$.wallets[0].balance").value(100.00))
                .andExpect(jsonPath("$.wallets[0].transactions").isArray()) // Verifica que transactions es un array
                .andExpect(jsonPath("$.wallets[0].transactions").isEmpty()); // Verifica que transactions está vacío
    }

    @Test
    void testPostUserSuccess() throws Exception {
        UserCreationDTO userCreationDTO = new UserCreationDTO();
        userCreationDTO.setUsername("newUser");
        userCreationDTO.setPassword("password123");
        userCreationDTO.setEmail("newuser@gmail.com");

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreationDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void testPostUserConflict() throws Exception {
        UserCreationDTO userCreationDTO = new UserCreationDTO();
        userCreationDTO.setUsername("santisr117");
        userCreationDTO.setPassword("password123");
        userCreationDTO.setEmail("santiagosr117@gmail.com");

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreationDTO)))
                .andExpect(status().isConflict());
    }
}
