package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pictet.technologies.adventurelibrary.domain.model.enums.SectionType;
import lombok.Builder;

import java.util.Set;

@Builder
public record SectionResponse(

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long id,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String text,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        SectionType type,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        Set<OptionResponse> options
) {
}