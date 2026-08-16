package com.cadastro.t_shirt_web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record ProductRequestDTO(


        @Schema(
                description = "Product name",
                example = "Camiseta Oversized"
        )
        @NotBlank(message = "Name is required")
        String name,

        @Schema(
                description = "Detailed description of the product",
                example = "Camiseta oversized 100% algodão"
        )
        @NotBlank(message = "Description is required")
        String description,

        @Schema(
                description = "Product price",
                example = "89.90",
                minimum = "0.01"
        )
        @Positive(message = "Price must be greater than zero")
        BigDecimal price,

        @Schema(
                description = "Available product stock",
                example = "15",
                minimum = "0"
        )
        @PositiveOrZero(message = "Stock cannot be negative")
        Integer stock


) {
}