package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request;

import com.pictet.technologies.adventurelibrary.domain.model.enums.SectionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreateSectionRequest(
        @NotNull Long id,
        @NotBlank String text,
        @NotNull SectionType type,
        Set<@Valid CreateOptionRequest> options
) {
}