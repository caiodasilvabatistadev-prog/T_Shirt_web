package com.cadastro.t_shirt_web.dto;
import java.math.BigDecimal;
public record ProductResponseDTO
        (Long id,
         String name,
         String description,
         BigDecimal price,
         Integer stock)
{

}
