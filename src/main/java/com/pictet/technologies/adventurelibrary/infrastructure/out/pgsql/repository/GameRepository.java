package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.repository;

import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.GameEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<GameEntity, Long> {

    @EntityGraph(attributePaths = {
            "player",
            "book",
            "currentSection",
            "currentSection.options",
            "currentSection.options.consequence"
    })
    Optional<GameEntity> findById(Long id);

}