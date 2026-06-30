package com.pictet.technologies.adventurelibrary.application.usecase;

import com.pictet.technologies.adventurelibrary.domain.model.Category;
import com.pictet.technologies.adventurelibrary.domain.port.in.CreateCategoryPort;
import com.pictet.technologies.adventurelibrary.domain.port.out.CategoryPersistencePort;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.CategoryResponse;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper.CategoryDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCategoryUseCase implements CreateCategoryPort {

    private final CategoryPersistencePort categoryPersistencePort;
    private final CategoryDtoMapper categoryDtoMapper;

    @Transactional
    public CategoryResponse execute(String name) {
        String normalizedName = name.trim();

        categoryPersistencePort.findByNameIgnoreCase(normalizedName)
                .ifPresent(category -> {
                    throw new IllegalArgumentException("Category already exists: " + normalizedName);
                });

        Category category = Category.builder()
                .name(normalizedName)
                .build();

        Category savedCategory = categoryPersistencePort.save(category);

        return categoryDtoMapper.toCategoryResponse(savedCategory);
    }
}