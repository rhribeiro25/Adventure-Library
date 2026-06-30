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
    private String title;
    private String author;
    private DifficultyLevel difficulty;
    private String category;

    public String cacheKey() {
        return "%s:%s:%s:%s:%s".formatted(
                query,
                title,
                author,
                difficulty,
                category
        );
    }
}