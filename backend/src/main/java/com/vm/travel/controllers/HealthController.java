package com.vm.travel.controllers;

import com.vm.travel.dto.ResponseAPI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;


@Tag(name = "Health", description = "This collection manages the API health collection")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/health")
public class HealthController {
    private final MessageSource messageSource;

    @Operation(summary = "Check API health")
    @GetMapping
    public ResponseEntity<ResponseAPI> health() {
        String healthMessage = messageSource.getMessage("health", null, LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(
                healthMessage,
                null
        ));
    }
}
