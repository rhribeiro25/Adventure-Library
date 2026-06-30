package com.pictet.technologies.adventureLibrary.infrastructure.in.rest.mapper;

import com.pictet.technologies.adventureLibrary.domain.model.Category;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoryDtoMapper {


    public Set<CategoryResponse> toCategoryResponses(Set<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            return Collections.emptySet();
        }

        return categories.stream()
                .map(this::toCategoryResponse)
                .collect(Collectors.toSet());
    }

    public CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }
}