package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.repository;

import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.BookEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {

    @EntityGraph(attributePaths = {
            "categories",
            "sections",
            "sections.options",
            "sections.options.consequence"
    })
    Page<BookEntity> findAll(@NonNull Specification<BookEntity> specification, @NonNull Pageable pageable);


    @EntityGraph(attributePaths = {
            "categories",
            "sections",
            "sections.options",
            "sections.options.consequence"
    })
    Optional<BookEntity> findById(Long id);

}