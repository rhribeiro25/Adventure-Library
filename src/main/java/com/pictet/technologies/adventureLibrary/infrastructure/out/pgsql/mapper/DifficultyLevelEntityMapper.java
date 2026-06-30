package com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.mapper;

import com.pictet.technologies.adventureLibrary.domain.model.enums.DifficultyLevel;
import com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.entity.enums.BookEntityDifficultyLevel;
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