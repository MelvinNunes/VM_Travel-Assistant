package com.vm.travel.infrastructure.exceptions;

public class UnprocessableEntityException extends Exception {
    public UnprocessableEntityException(String message){
        super(message);
    }
}
