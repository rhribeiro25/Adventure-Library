package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request;

import com.pictet.technologies.adventurelibrary.domain.model.enums.ConsequenceType;
import jakarta.validation.constraints.NotNull;

public record CreateConsequenceRequest(
        @NotNull ConsequenceType type,
        Integer value,
        String text
) {
}