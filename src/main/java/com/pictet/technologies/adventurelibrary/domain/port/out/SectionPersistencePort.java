package com.pictet.technologies.adventurelibrary.domain.port.out;

import com.pictet.technologies.adventurelibrary.domain.model.Section;

import java.util.Optional;
import java.util.Set;

public interface SectionPersistencePort {

    Optional<Section> findById(Long id);

    Set<Section> findByBookId(Long bookId);
}