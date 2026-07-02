package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.repository;

import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.ConsequenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsequenceRepository extends JpaRepository<ConsequenceEntity, Long> {

}