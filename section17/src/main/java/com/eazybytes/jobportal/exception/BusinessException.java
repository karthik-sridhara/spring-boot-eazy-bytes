package com.eazybytes.jobportal.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends  RuntimeException{

    private final HttpStatus status;
    private final String code;

    public BusinessException(String code,String message, HttpStatus  status){
        super(message);
        this.status = status;
        this.code = code;
    }
}