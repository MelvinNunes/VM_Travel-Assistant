package com.example.exapp.controllers;

import com.example.exapp.domain.services.internal.auth.AuthenticationService;
import com.example.exapp.dto.ResponseAPI;
import com.example.exapp.dto.request.LoginDTO;
import com.example.exapp.dto.request.RegisterDTO;
import com.example.exapp.dto.response.LoginResponseDTO;
import com.example.exapp.infrastructure.exceptions.ConflictException;
import com.example.exapp.infrastructure.exceptions.NotFoundException;
import com.example.exapp.infrastructure.exceptions.UnauthorizedException;
import com.example.exapp.domain.services.UserService;
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

@Tag(name = "Authentication", description = "This collection manages the authentication endpoints list")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final MessageSource messageSource;
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Login using username and password")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO data) throws UnauthorizedException, NotFoundException {
        String token = authenticationService.login(data);
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(
                messageSource.getMessage("auth.success", null, LocaleContextHolder.getLocale()),
                token
        ));
    }

    @Operation(summary = "Register using email and password")
    @PostMapping("/register")
    public ResponseEntity<ResponseAPI> register(@RequestBody @Valid RegisterDTO data) throws ConflictException {
        var created = userService.registerUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseAPI(
                messageSource.getMessage("register.success", null, LocaleContextHolder.getLocale()),
                created
        ));
    }
}
