package com.vm.travel.dto.filters;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CityFilters {
    @Parameter(description = "This is the query field for finding cities based on the name")
    private String name;
}
