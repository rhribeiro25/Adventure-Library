package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.mapper;

import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.domain.model.enums.DifficultyLevel;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.BookEntity;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.GameEntity;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.enums.BookEntityDifficultyLevel;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.enums.GameEntityStatus;
import com.pictet.technologies.adventurelibrary.infrastructure.shared.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookEntityMapper {

    private final DifficultyLevelEntityMapper difficultyLevelEntityMapper;
    private final CategoryEntityMapper categoryEntityMapper;
    private final SectionEntityMapper sectionEntityMapper;
    private final ObjectUtils objectUtils;

    public Book toDomain(BookEntity entity) {
        if (entity == null) {
            return null;
        }

        return Book.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .difficultyLevel(difficultyLevelEntityMapper.toDomain(entity.getDifficultyLevel()))
                .categories(categoryEntityMapper.toDomain(entity.getCategories()))
                .sections(sectionEntityMapper.toDomain(entity.getSections()))
                .build();
    }

    public BookEntity toEntity(Book domain) {
        if (domain == null) {
            return null;
        }

        return BookEntity.builder()
                .id(domain.getId())
                .title(domain.getTitle())
                .author(domain.getAuthor())
                .difficultyLevel(difficultyLevelEntityMapper.toEntity(domain.getDifficultyLevel()))
                .categories(categoryEntityMapper.toEntity(domain.getCategories()))
                .sections(sectionEntityMapper.toEntity(domain.getSections()))
                .build();
    }

    public void mergeToEntity(BookEntity entity, Book domain) {
        if (entity == null || domain == null) {
            return;
        }
        objectUtils.updateIfChanged(entity::getAuthor, entity::setAuthor, domain.getAuthor());
        objectUtils.updateIfChanged(entity::getTitle, entity::setTitle, domain.getTitle());
        objectUtils.updateIfChanged(entity::getDifficultyLevel, entity::setDifficultyLevel, BookEntityDifficultyLevel.valueOf(domain.getDifficultyLevel().name()));
        objectUtils.updateIfChanged(entity::getCategories, entity::setCategories, categoryEntityMapper.toEntity(domain.getCategories()));
    }

}