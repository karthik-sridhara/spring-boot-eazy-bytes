package com.eazybytes.jobportal.exception.dto;

public record ApiFieldError(String field, String message, Object rejectedValue) {
}

