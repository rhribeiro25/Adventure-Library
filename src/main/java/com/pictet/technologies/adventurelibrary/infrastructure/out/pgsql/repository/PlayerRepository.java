package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.repository;

import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    Optional<PlayerEntity> findByEmailIgnoreCase(String email);
}