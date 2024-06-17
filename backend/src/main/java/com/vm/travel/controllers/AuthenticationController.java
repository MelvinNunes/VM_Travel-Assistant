package com.vm.travel.controllers;

import com.vm.travel.domain.services.AuthenticationService;
import com.vm.travel.dto.ResponseAPI;
import com.vm.travel.dto.request.LoginDTO;
import com.vm.travel.dto.request.RegisterDTO;
import com.vm.travel.dto.response.LoginResponseDTO;
import com.vm.travel.infrastructure.config.ratelimit.RateLimitProtection;
import com.vm.travel.infrastructure.exceptions.ConflictException;
import com.vm.travel.infrastructure.exceptions.NotFoundException;
import com.vm.travel.infrastructure.exceptions.UnauthorizedException;
import com.vm.travel.domain.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "This collection manages authentication endpoints list")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final MessageSource messageSource;
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Login using username and password")
    @PostMapping("/login")
    @RateLimitProtection
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO data) throws UnauthorizedException, NotFoundException {
        String token = authenticationService.login(data);
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(
                messageSource.getMessage("auth.success", null, LocaleContextHolder.getLocale()),
                token
        ));
    }

    @Operation(summary = "Register using email and password")
    @PostMapping("/register")
    @RateLimitProtection
    public ResponseEntity<ResponseAPI> register(@RequestBody @Valid RegisterDTO data) throws ConflictException {
        var created = userService.registerUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseAPI(
                messageSource.getMessage("register.success", null, LocaleContextHolder.getLocale()),
                created
        ));
    }
}
