package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOptionRequest(
        @NotBlank String description,
        @NotNull Long gotoId,
        @Valid CreateConsequenceRequest consequence
) {
}