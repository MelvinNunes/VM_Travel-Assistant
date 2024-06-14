package com.example.exapp.infrastructure.enums;

import lombok.Getter;

@Getter
public enum Roles {
    ADMIN("admin"),
    USER("user");

    private final String role;

    Roles(String role){
        this.role = role;
    }

}
