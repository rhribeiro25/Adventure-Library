package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pictet.technologies.adventureLibrary.domain.model.Section;
import com.pictet.technologies.adventureLibrary.domain.model.enums.DifficultyLevel;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record BookSummaryResponse(

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long id,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String title,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String author,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        DifficultyLevel difficultyLevel,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        Set<CategoryResponse> categories,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        Set<SectionResponse> sections

) {}