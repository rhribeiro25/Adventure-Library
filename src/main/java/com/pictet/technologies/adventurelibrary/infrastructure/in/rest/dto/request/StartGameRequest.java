package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StartGameRequest(
        @NotBlank String playerName,
        @NotBlank @Email String playerEmail
) {
}