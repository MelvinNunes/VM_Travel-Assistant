package com.vm.travel.infrastructure.exceptions;

public class RateLimitException extends RuntimeException {
    public RateLimitException(final String message) {
        super(message);
    }
}
