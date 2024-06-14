package com.example.exapp.infrastructure.security;

import com.example.exapp.domain.repositories.UserRepo;
import com.example.exapp.infrastructure.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {
    private final MessageSource messageSource;
    private final UserRepo repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return repository.findByLogin(username).orElseThrow(() -> new NotFoundException(messageSource.getMessage("users.not_found", null, LocaleContextHolder.getLocale())));
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
