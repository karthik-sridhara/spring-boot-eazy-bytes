package com.eazybytes.jobportal.exception.dto;

import java.time.Instant;
import java.util.List;

public record ApiErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path,
        String traceId,
        List<ApiFieldError> violations
) {
}

