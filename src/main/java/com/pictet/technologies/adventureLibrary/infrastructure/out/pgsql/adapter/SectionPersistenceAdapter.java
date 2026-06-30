package com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.adapter;

import com.pictet.technologies.adventureLibrary.domain.model.Section;
import com.pictet.technologies.adventureLibrary.domain.port.out.SectionPersistencePort;
import com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.mapper.SectionEntityMapper;
import com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SectionPersistenceAdapter implements SectionPersistencePort {

    private final SectionRepository sectionJpaRepository;
    private final SectionEntityMapper sectionEntityMapper;

    @Override
    public Optional<Section> findById(Long sectionId) {
        return sectionJpaRepository.findById(sectionId)
                .map(sectionEntityMapper::toDomain);
    }
}