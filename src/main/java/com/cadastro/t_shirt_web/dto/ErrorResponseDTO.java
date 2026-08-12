package com.cadastro.t_shirt_web.dto;

import java.util.Map;

public record ErrorResponseDTO(
        int status,
        String error,
        String message,
        Map<String, String> errors

) {
}