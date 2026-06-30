package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public record CategoryResponse(

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long id,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String name
) {
}