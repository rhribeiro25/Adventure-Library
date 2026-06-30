package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.mapper;

import com.pictet.technologies.adventurelibrary.domain.model.Consequence;
import com.pictet.technologies.adventurelibrary.domain.model.enums.ConsequenceType;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.ConsequenceEntity;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.enums.ConsequenceEntityType;
import org.springframework.stereotype.Component;

@Component
public class ConsequenceEntityMapper {

    public Consequence toDomain(ConsequenceEntity entity) {
        if (entity == null) {
            return null;
        }

        return Consequence.builder()
                .type(toDomainType(entity.getType()))
                .value(entity.getValue())
                .text(entity.getText())
                .build();
    }

    public ConsequenceEntity toEntity(Consequence domain) {
        if (domain == null) {
            return null;
        }

        return ConsequenceEntity.builder()
                .type(toEntityType(domain.getType()))
                .value(domain.getValue())
                .text(domain.getText())
                .build();
    }

    private ConsequenceType toDomainType(ConsequenceEntityType entityType) {
        if (entityType == null) {
            return null;
        }

        return ConsequenceType.valueOf(entityType.name());
    }

    private ConsequenceEntityType toEntityType(ConsequenceType domainType) {
        if (domainType == null) {
            return null;
        }

        return ConsequenceEntityType.valueOf(domainType.name());
    }
}