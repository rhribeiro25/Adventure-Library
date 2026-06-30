package com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.mapper;

import com.pictet.technologies.adventureLibrary.domain.model.Game;
import com.pictet.technologies.adventureLibrary.domain.model.enums.GameStatus;
import com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.entity.GameEntity;
import com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.entity.enums.GameEntityStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameEntityMapper {

    private final BookEntityMapper bookEntityMapper;
    private final SectionEntityMapper sectionEntityMapper;
    private final PlayerEntityMapper playerEntityMapper;

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
                .id(domain.getId())
                .player(playerEntityMapper.toEntity(domain.getPlayer()))
                .health(domain.getHealth())
                .status(GameEntityStatus.valueOf(domain.getStatus().name()))
                .book(bookEntityMapper.toEntity(domain.getBook()))
                .currentSection(sectionEntityMapper.toEntity(domain.getCurrentSection()))
                .build();
    }
}