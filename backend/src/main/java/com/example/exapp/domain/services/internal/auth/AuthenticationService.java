package com.example.exapp.domain.services.internal.auth;

import com.example.exapp.domain.entities.User;
import com.example.exapp.domain.services.UserService;
import com.example.exapp.dto.request.LoginDTO;
import com.example.exapp.infrastructure.exceptions.NotFoundException;
import com.example.exapp.infrastructure.exceptions.UnauthorizedException;
import com.example.exapp.infrastructure.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final MessageSource messageSource;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;

    public String login(LoginDTO data) throws UnauthorizedException, NotFoundException {
        if (!userService.existsByUsername(data.username())) {
            throw new NotFoundException(messageSource.getMessage("users.not_found", null, LocaleContextHolder.getLocale()));
        }
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            return tokenService.generateToken((User) auth.getPrincipal());
        } catch (Exception e) {
            throw new UnauthorizedException(messageSource.getMessage("auth.password.error", null, LocaleContextHolder.getLocale()));
        }
    }
}
