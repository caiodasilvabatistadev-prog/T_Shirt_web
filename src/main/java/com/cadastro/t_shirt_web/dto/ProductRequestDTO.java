package com.cadastro.t_shirt_web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequestDTO(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @Positive(message = "Price must be greater than zero")
        BigDecimal price,

        @PositiveOrZero(message = "Stock cannot be negative")
        Integer stock

) {}