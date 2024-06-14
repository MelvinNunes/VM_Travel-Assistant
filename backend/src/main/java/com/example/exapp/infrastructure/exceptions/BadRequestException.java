package com.example.exapp.infrastructure.exceptions;

public class BadRequestException extends Exception {
    public BadRequestException(String message){
        super(message);
    }
}
