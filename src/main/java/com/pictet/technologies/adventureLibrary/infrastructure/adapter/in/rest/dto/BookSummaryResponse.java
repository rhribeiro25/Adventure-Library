package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto;

import com.pictet.technologies.adventureLibrary.domain.model.enums.DifficultyLevel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class BookSummaryResponse {

    Long id;
    String title;
    String author;
    DifficultyLevel difficultyLevel;
    List<String> categories;
}