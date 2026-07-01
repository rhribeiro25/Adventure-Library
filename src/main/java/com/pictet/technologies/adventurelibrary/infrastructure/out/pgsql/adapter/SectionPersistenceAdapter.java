package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.adapter;

import com.pictet.technologies.adventurelibrary.domain.model.Section;
import com.pictet.technologies.adventurelibrary.domain.port.out.SectionPersistencePort;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.mapper.SectionEntityMapper;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public Set<Section> findByBookId(Long bookId) {
        return sectionJpaRepository.findAllByBookId(bookId)
                .stream()
                .map(sectionEntityMapper::toDomain)
                .collect(Collectors.toSet());
    }
}