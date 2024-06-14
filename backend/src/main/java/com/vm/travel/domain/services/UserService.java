package com.vm.travel.domain.services;

import com.vm.travel.domain.entities.User;
import com.vm.travel.domain.repositories.UserRepo;
import com.vm.travel.dto.request.RegisterDTO;
import com.vm.travel.infrastructure.enums.Roles;
import com.vm.travel.infrastructure.exceptions.ConflictException;
import com.vm.travel.infrastructure.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
