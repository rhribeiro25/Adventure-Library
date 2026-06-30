package com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.repository;

import com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.entity.SectionEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

    @Override
    @EntityGraph(attributePaths = {
            "options",
            "options.consequence"
    })
    Optional<SectionEntity> findById(Long id);
}