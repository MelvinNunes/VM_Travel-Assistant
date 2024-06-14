package com.example.exapp.infrastructure.exceptions;

public class UnauthorizedException extends Exception {
    public UnauthorizedException(String message){
        super(message);
    }
}
