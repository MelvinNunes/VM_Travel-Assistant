package com.vm.travel.controllers;

import com.vm.travel.domain.services.UserService;
import com.vm.travel.dto.ResponseAPI;
import com.vm.travel.dto.response.MyUserResDTO;
import com.vm.travel.infrastructure.config.ratelimit.RateLimitProtection;
import com.vm.travel.infrastructure.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Accounts", description = "This collection manages accounts endpoints list")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountsController {
    private final UserService userService;
    private final MessageSource messageSource;

    @Operation(summary = "Get my account - online user account")
    @GetMapping("/me")
    @RateLimitProtection
    public ResponseEntity<MyUserResDTO> geMyProfile() throws NotFoundException {
        var user = userService.onlineUser();
        return ResponseEntity.status(HttpStatus.OK).body(new MyUserResDTO(
                messageSource.getMessage("users.me", null, LocaleContextHolder.getLocale()),
                user
        ));
    }
}
