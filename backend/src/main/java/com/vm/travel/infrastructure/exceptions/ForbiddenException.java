package com.vm.travel.infrastructure.exceptions;

public class ForbiddenException extends Exception {
    public ForbiddenException(String message){
        super(message);
    }
}
