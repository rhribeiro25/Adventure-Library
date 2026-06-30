package com.pictet.technologies.adventurelibrary.domain.port.out;

import com.pictet.technologies.adventurelibrary.domain.model.Section;

import java.util.Optional;

public interface SectionPersistencePort {

    Optional<Section> findById(Long id);
}