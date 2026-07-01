package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pictet.technologies.adventurelibrary.domain.model.enums.DifficultyLevel;
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