package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.mapper;

import com.pictet.technologies.adventurelibrary.domain.model.Category;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoryEntityMapper {

    public Category toDomain(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }

        return Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public CategoryEntity toEntity(Category domain) {
        if (domain == null) {
            return null;
        }

        return CategoryEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .build();
    }

    public Set<Category> toDomain(Set<CategoryEntity> entities) {
        if (entities == null) {
            return Collections.emptySet();
        }

        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toSet());
    }

    public Set<CategoryEntity> toEntity(Set<Category> domains) {
        if (domains == null) {
            return Collections.emptySet();
        }

        return domains.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}