package com.vm.travel.dto.filters;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CountryFilters {
    @Parameter(description = "This is the query field for finding countries based on region")
    private String region;
}
