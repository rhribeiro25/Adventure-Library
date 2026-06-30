package com.pictet.technologies.adventurelibrary.domain.port.out;

import com.pictet.technologies.adventurelibrary.domain.model.Category;

import java.util.Optional;

public interface CategoryPersistencePort {

    Optional<Category> findByNameIgnoreCase(String name);

    Category save(Category category);

    Optional<Category> findById(Long categoryId);
}