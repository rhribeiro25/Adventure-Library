package com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.mapper;

import com.pictet.technologies.adventureLibrary.domain.model.Option;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity.OptionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OptionEntityMapper {

    private final ConsequenceEntityMapper consequenceEntityMapper;

    public Option toDomain(OptionEntity entity) {
        if (entity == null) {
            return null;
        }

        return Option.builder()
                .description(entity.getDescription())
                .gotoId(entity.getGotoSectionNumber())
                .consequence(consequenceEntityMapper.toDomain(entity.getConsequence()))
                .build();
    }

    public OptionEntity toEntity(Option domain) {
        if (domain == null) {
            return null;
        }

        return OptionEntity.builder()
                .description(domain.getDescription())
                .gotoSectionNumber(domain.getGotoId())
                .consequence(consequenceEntityMapper.toEntity(domain.getConsequence()))
                .build();
    }

    public Set<Option> toDomain(Set<OptionEntity> entities) {
        if (entities == null) {
            return Collections.emptySet();
        }

        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toSet());
    }

    public Set<OptionEntity> toEntity(Set<Option> domains) {
        if (domains == null) {
            return Collections.emptySet();
        }

        return domains.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}