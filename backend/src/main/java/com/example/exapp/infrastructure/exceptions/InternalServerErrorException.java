package com.example.exapp.infrastructure.exceptions;

public class InternalServerErrorException extends Exception {
    public InternalServerErrorException(String message){
        super(message);
    }
}
