package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.mapper;

import com.pictet.technologies.adventurelibrary.domain.model.enums.DifficultyLevel;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.enums.BookEntityDifficultyLevel;
import org.springframework.stereotype.Component;

@Component
public class DifficultyLevelEntityMapper {

    public DifficultyLevel toDomain(BookEntityDifficultyLevel entityDifficultyLevel) {
        if (entityDifficultyLevel == null) {
            return null;
        }

        return DifficultyLevel.valueOf(entityDifficultyLevel.name());
    }

    public BookEntityDifficultyLevel toEntity(DifficultyLevel difficultyLevel) {
        if (difficultyLevel == null) {
            return null;
        }

        return BookEntityDifficultyLevel.valueOf(difficultyLevel.name());
    }
}