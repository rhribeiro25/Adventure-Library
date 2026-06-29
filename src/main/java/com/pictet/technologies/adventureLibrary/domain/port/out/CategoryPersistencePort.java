package com.pictet.technologies.adventureLibrary.domain.port.out;

import com.pictet.technologies.adventureLibrary.domain.model.Category;

import java.util.Optional;

public interface CategoryPersistencePort {

    Optional<Category> findByNameIgnoreCase(String name);

    Category save(Category category);

    Optional<Category> findById(Long categoryId);
}