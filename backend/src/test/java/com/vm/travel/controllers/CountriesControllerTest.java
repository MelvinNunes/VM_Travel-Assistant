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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CountriesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("Use country code to get country population without token")
    void getCountryPopulationDataViaCountryCodeWithoutAuthorization() throws Exception {
        mockMvc.perform(get("/api/v1/countries/mz/population"))
                .andExpect(status().isForbidden());
    }



    @Test
    @DisplayName("Try to get country details from API")
    void testGetCountryDetails() throws Exception {
        mockMvc.perform(get("/api/v1/countries/mozambique"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Try to get country details from API [invalid country]")
    void testGetCountryDetailsInvalidCountry() throws Exception {
        mockMvc.perform(get("/api/v1/countries/mozambiques"))
                .andExpect(status().isNotFound());
    }
}