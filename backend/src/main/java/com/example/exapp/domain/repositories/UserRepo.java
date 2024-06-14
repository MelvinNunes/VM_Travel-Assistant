package com.example.exapp.domain.repositories;

import com.example.exapp.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {

    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);
}
