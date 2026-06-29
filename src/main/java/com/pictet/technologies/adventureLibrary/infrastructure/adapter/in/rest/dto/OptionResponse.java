package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pictet.technologies.adventureLibrary.domain.model.Consequence;
import lombok.Builder;

@Builder
public record OptionResponse(

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String description,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long gotoId,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Consequence consequence
) {
}