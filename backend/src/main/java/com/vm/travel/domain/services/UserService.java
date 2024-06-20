package com.vm.travel.domain.services;

import com.vm.travel.domain.entities.User;
import com.vm.travel.domain.repositories.UserRepo;
import com.vm.travel.dto.request.RegisterDTO;
import com.vm.travel.infrastructure.exceptions.ConflictException;
import com.vm.travel.infrastructure.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    @Autowired
    public UserService(MessageSource messageSource, PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.messageSource = messageSource;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }


    /**
     * Registers a new user with the provided registration data.
     *
     * @param data the {@link RegisterDTO} object containing the user's registration details.
     * @return the registered {@link User} object.
     * @throws ConflictException if a user with the provided email already exists.
     */
    public User registerUser(RegisterDTO data) throws ConflictException {
        if (this.existsByUsername(data.email())) {
            throw new ConflictException(messageSource.getMessage("users.exists", null, LocaleContextHolder.getLocale()));
        }
        User user = new User(data.email(), data.password(), data.name());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    /**
     * Retrieves the currently authenticated user.
     *
     * @return the {@link User} object representing the currently authenticated user.
     * @throws NotFoundException if no authenticated user is found.
     */
    public User onlineUser() throws NotFoundException {
        return findByLogin(getAuthenticatedUserId());
    }

    private String getAuthenticatedUserId(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User findByLogin(String login) throws NotFoundException {
        return userRepo.findByLogin(login).orElseThrow(() -> new NotFoundException(messageSource.getMessage("users.not_found", null, LocaleContextHolder.getLocale())));
    }


    public boolean existsByUsername(String login){
        return userRepo.existsByLogin(login);
    }


}
