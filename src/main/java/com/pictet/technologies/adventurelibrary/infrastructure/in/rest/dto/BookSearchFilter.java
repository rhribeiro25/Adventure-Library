package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto;

import com.pictet.technologies.adventurelibrary.domain.model.enums.DifficultyLevel;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookSearchFilter {

    private String query;
    private String author;
    private DifficultyLevel difficulty;
    private String category;
}