package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.mapper;

import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.domain.model.enums.GameStatus;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.GameEntity;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.enums.GameEntityStatus;
import com.pictet.technologies.adventurelibrary.infrastructure.shared.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GameEntityMapper {

    private final BookEntityMapper bookEntityMapper;
    private final SectionEntityMapper sectionEntityMapper;
    private final PlayerEntityMapper playerEntityMapper;
    private final ObjectUtils objectUtils;

    public Game toDomain(GameEntity entity) {

        if (entity == null) {
            return null;
        }

        return Game.builder()
                .id(entity.getId())
                .player(playerEntityMapper.toDomain(entity.getPlayer()))
                .health(entity.getHealth())
                .status(GameStatus.valueOf(entity.getStatus().name()))
                .book(bookEntityMapper.toDomain(entity.getBook()))
                .currentSection(sectionEntityMapper.toDomain(entity.getCurrentSection()))
                .build();
    }

    public GameEntity toEntity(Game domain) {

        if (domain == null) {
            return null;
        }

        return GameEntity.builder()
                .id(Optional.of(domain.getId()).orElse(null))
                .player(playerEntityMapper.toEntity(domain.getPlayer()))
                .health(domain.getHealth())
                .status(GameEntityStatus.valueOf(domain.getStatus().name()))
                .book(bookEntityMapper.toEntity(domain.getBook()))
                .currentSection(sectionEntityMapper.toEntity(domain.getCurrentSection()))
                .build();
    }

    public void mergeToEntity(GameEntity entity, Game domain) {
        if (entity == null || domain == null) {
            return;
        }
        objectUtils.updateIfChanged(entity::getHealth, entity::setHealth, domain.getHealth());
        objectUtils.updateIfChanged(entity::getStatus, entity::setStatus, GameEntityStatus.valueOf(domain.getStatus().name()));
    }
}