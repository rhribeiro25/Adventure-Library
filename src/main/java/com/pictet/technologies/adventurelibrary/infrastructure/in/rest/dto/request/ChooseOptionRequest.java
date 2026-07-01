package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request;

import jakarta.validation.constraints.NotNull;

public record ChooseOptionRequest(
        @NotNull Long optionId
) {
}