package com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto;

import com.pictet.technologies.adventureLibrary.domain.model.enums.DifficultyLevel;
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