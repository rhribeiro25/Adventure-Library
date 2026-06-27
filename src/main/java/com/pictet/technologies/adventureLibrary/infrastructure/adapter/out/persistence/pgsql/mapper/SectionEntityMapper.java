package com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.mapper;

import com.pictet.technologies.adventureLibrary.domain.model.Section;
import com.pictet.technologies.adventureLibrary.domain.model.enums.SectionType;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity.SectionEntity;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity.enums.SectionEntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SectionEntityMapper {

    private final OptionEntityMapper optionEntityMapper;

    public Section toDomain(SectionEntity entity) {
        if (entity == null) {
            return null;
        }

        return Section.builder()
                .id(entity.getId())
                .text(entity.getText())
                .type(toDomainType(entity.getType()))
                .options(optionEntityMapper.toDomain(entity.getOptions()))
                .build();
    }

    public SectionEntity toEntity(Section domain) {
        if (domain == null) {
            return null;
        }

        return SectionEntity.builder()
                .id(domain.getId())
                .text(domain.getText())
                .type(toEntityType(domain.getType()))
                .options(optionEntityMapper.toEntity(domain.getOptions()))
                .build();
    }

    public List<Section> toDomain(List<SectionEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDomain)
                .toList();
    }

    public List<SectionEntity> toEntity(List<Section> domains) {
        if (domains == null) {
            return Collections.emptyList();
        }

        return domains.stream()
                .map(this::toEntity)
                .toList();
    }

    private SectionType toDomainType(SectionEntityType entityType) {
        if (entityType == null) {
            return null;
        }

        return SectionType.valueOf(entityType.name());
    }

    private SectionEntityType toEntityType(SectionType domainType) {
        if (domainType == null) {
            return null;
        }

        return SectionEntityType.valueOf(domainType.name());
    }
}