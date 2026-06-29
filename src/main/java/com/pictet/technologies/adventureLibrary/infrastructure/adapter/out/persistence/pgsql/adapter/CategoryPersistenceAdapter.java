package com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.adapter;

import com.pictet.technologies.adventureLibrary.domain.model.Category;
import com.pictet.technologies.adventureLibrary.domain.port.out.CategoryPersistencePort;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.mapper.CategoryEntityMapper;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryPersistencePort {

    private final CategoryJpaRepository categoryJpaRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public Optional<Category> findByNameIgnoreCase(String name) {
        return categoryJpaRepository.findByNameIgnoreCase(name)
                .map(categoryEntityMapper::toDomain);
    }

    @Override
    public Category save(Category category) {
        var entity = categoryEntityMapper.toEntity(category);
        var saved = categoryJpaRepository.save(entity);
        return categoryEntityMapper.toDomain(saved);
    }
}