package com.pictet.technologies.adventureLibrary.domain.port.out;

import com.pictet.technologies.adventureLibrary.domain.model.Section;

import java.util.Optional;

public interface SectionPersistencePort {

    Optional<Section> findById(Long id);
}