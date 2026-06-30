package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SaveCategoryRequest(
        @NotBlank(message = "Category name is required.")
        @Size(max = 255)
        String name
) {
}