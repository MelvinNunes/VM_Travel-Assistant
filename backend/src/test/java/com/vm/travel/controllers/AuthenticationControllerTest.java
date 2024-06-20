package com.vm.travel.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vm.travel.dto.request.LoginDTO;
import com.vm.travel.dto.request.RegisterDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test account creation with valid values")
    void testAccountCreation() throws Exception {
        RegisterDTO request = new RegisterDTO(
                "melvin@gmail.com",
                "Melvin Nunes",
                "1234"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Test account creation with invalid values")
    void testAccountCreationInvalid() throws Exception {
        RegisterDTO request = new RegisterDTO(
                "melvingmailcom",
                "Melvin Nunes",
                "123"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test account creation with existing values")
    void testCreateDuplicateAccount() throws Exception {
        RegisterDTO registerData = new RegisterDTO(
                "melvin@gmail.com",
                "Melvin Nunes",
                "1234"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerData))
                )
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("Test account login with valid values")
    void testAccountLogin() throws Exception {
        RegisterDTO registerData = new RegisterDTO(
                "melvin1234@gmail.com",
                "Melvin Nunes",
                "1234"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerData))
                )
                .andExpect(status().isCreated());

        LoginDTO loginData = new LoginDTO(
                registerData.email(),
                registerData.password()
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginData))
        ).andExpect(status().isOk());
    }


}