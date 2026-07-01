package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper;

import com.pictet.technologies.adventurelibrary.domain.model.Category;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.CategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
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

    public Set<Category> toCategories(Set<String> categories) {
        if (categories == null) {
            return Collections.emptySet();
        }

        return categories.stream()
                .filter(name -> name != null && !name.isBlank())
                .map(name -> Category.builder().name(name.trim()).build())
                .collect(Collectors.toSet());
    }
}