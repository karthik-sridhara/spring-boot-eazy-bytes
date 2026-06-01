package com.eazybyte.jobportal.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CompanyDTO(
        Long id,
        String name,
        String logo,
        String industry,
        String size,
        BigDecimal rating,
        String locations,
        Integer founded,
        String description,
        Integer employees,
        String website,
        LocalDateTime createdAt
) {}
