package com.vm.travel.infrastructure.exceptions;

public class InternalServerErrorException extends Exception {
    public InternalServerErrorException(String message){
        super(message);
    }
}
