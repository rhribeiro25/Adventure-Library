package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto;

import com.pictet.technologies.adventurelibrary.domain.model.enums.DifficultyLevel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreateBookRequest(
        @NotBlank String title,
        @NotBlank String author,
        @NotNull DifficultyLevel difficulty,
        @NotEmpty Set<@Valid CreateSectionRequest> sections
) {
}