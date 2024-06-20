package com.vm.travel.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vm.travel.dto.request.RegisterDTO;
import com.vm.travel.dto.response.LoginResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Try to get my account without authentication")
    void testGetMyAccountForbidden() throws Exception {
        mockMvc.perform(get("/api/v1/accounts/me"))
                .andExpect(status().isForbidden());
    }


    @Test
    @DisplayName("Try to get my account with authentication")
    void testGetMyAccount() throws Exception {
        RegisterDTO request = new RegisterDTO(
                "melvin_logs@gmail.com",
                "Melvin Nunes",
                "1234"
        );

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                )
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        // Deserialize the response body
        LoginResponseDTO response = objectMapper.readValue(responseBody, LoginResponseDTO.class);

        assertNotNull(response.token());
    }
}