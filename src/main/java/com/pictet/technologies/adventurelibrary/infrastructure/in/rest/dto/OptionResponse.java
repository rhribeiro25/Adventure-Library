package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pictet.technologies.adventurelibrary.domain.model.Consequence;
import lombok.Builder;

@Builder
public record OptionResponse(

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long id,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String description,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long gotoId,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Consequence consequence
) {
}