package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto;

import com.pictet.technologies.adventurelibrary.domain.model.enums.DifficultyLevel;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UpdateBookRequest(

        @Size(max = 255)
        String title,

        @Size(max = 255)
        String author,

        DifficultyLevel difficulty,

        @Size(max = 3, message = "A book can have at most 3 categories.")
        Set<Long> categories
) {
}