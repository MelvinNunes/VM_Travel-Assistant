package com.vm.travel.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CitiesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Try to get all cities from api")
    void getAllCities() throws Exception {
       mockMvc.perform(get("/api/v1/cities"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Try to get current weather for a specific city from api - remember to set api key")
    void getCityCurrentWeather() throws Exception {
        mockMvc.perform(get("/api/v1/cities/maputo/weather/current"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Try to get current weather for a specific city [invalid city] from api - remember to set api key in test")
    void getCityCurrentWeatherInvalidCity() throws Exception {
        mockMvc.perform(get("/api/v1/cities/maputos/weather/current"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("Try to get weather forecast for a specific city from api - remember to set api key")
    void getCityWeatherForecast() throws Exception {
        mockMvc.perform(get("/api/v1/cities/maputo/weather/forecast"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Try to get weather forecast for a specific city [invalid city] from api - remember to set api key")
    void getCityWeatherForecastInvalidCity() throws Exception {
        mockMvc.perform(get("/api/v1/cities/maputos/weather/current"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("Try to get details of a specific city from api")
    void getCityDetails() throws Exception {
        mockMvc.perform(get("/api/v1/cities/maputo"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Try to get details of a specific city [invalid] from api")
    void getCityDetailsInvalidCity() throws Exception {
        mockMvc.perform(get("/api/v1/cities/maputos"))
                .andExpect(status().isNotFound());
    }
}