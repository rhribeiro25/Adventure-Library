package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pictet.technologies.adventureLibrary.domain.model.enums.DifficultyLevel;
import lombok.Builder;

import java.util.Set;

@Builder
public record BookDetailsResponse(

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long id,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String title,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String author,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        DifficultyLevel difficulty,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        Set<CategoryResponse> categories,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        Set<SectionResponse> sections
) {
}