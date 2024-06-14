package com.example.exapp.domain.services;

import com.example.exapp.domain.entities.User;
import com.example.exapp.domain.repositories.UserRepo;
import com.example.exapp.dto.request.RegisterDTO;
import com.example.exapp.infrastructure.enums.Roles;
import com.example.exapp.infrastructure.exceptions.ConflictException;
import com.example.exapp.infrastructure.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public User registerUser(RegisterDTO data) throws ConflictException {
        if (this.existsByUsername(data.username())) {
            throw new ConflictException(messageSource.getMessage("users.exists", null, LocaleContextHolder.getLocale()));
        }
        User user = new User(data.username(), data.password());
        user.setRole(Roles.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User findById(String id) throws NotFoundException {
        return userRepo.findById(id).orElseThrow(() -> new NotFoundException(messageSource.getMessage("users.not_found", null, LocaleContextHolder.getLocale())));
    }

    public User findByLogin(String login) throws NotFoundException {
        return userRepo.findByLogin(login).orElseThrow(() -> new NotFoundException(messageSource.getMessage("users.not_found", null, LocaleContextHolder.getLocale())));
    }

    public boolean existsByUsername(String login){
        return userRepo.existsByLogin(login);
    }


}
