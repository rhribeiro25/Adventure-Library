package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto;

import com.pictet.technologies.adventureLibrary.domain.model.enums.DifficultyLevel;
import lombok.Builder;

import java.util.List;

@Builder
public record BookSummaryResponse(
        Long id,
        String title,
        String author,
        DifficultyLevel difficultyLevel,
        List<String> categories
) {}