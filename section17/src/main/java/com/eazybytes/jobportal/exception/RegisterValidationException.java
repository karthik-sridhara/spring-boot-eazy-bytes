package com.eazybytes.jobportal.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class RegisterValidationException extends RuntimeException {
    private final Map<String,String> errors;
    public RegisterValidationException(
            String message,
            Map<String,String> errors
    ) {
        super(message);
        this.errors = errors;
    }

}
