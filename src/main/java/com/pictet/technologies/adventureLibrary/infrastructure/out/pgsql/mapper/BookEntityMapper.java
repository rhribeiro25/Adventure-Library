package com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.mapper;

import com.pictet.technologies.adventureLibrary.domain.model.Book;
import com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.entity.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookEntityMapper {

    private final DifficultyLevelEntityMapper difficultyLevelEntityMapper;
    private final CategoryEntityMapper categoryEntityMapper;
    private final SectionEntityMapper sectionEntityMapper;

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

}