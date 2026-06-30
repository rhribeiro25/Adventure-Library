package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.adapter;

import com.pictet.technologies.adventurelibrary.domain.exception.NotFoundException;
import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.domain.port.out.GamePersistencePort;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.GameEntity;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.SectionEntity;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.mapper.GameEntityMapper;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.repository.GameRepository;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GamePersistenceAdapter implements GamePersistencePort {

    private final GameRepository gameJpaRepository;
    private final GameEntityMapper gameEntityMapper;
    private final SectionRepository sectionRepository;

    @Override
    public Optional<Game> findById(Long gameId) {
        return gameJpaRepository.findWithCurrentSectionById(gameId)
                .map(gameEntityMapper::toDomain);
    }

    @Override
    public Game save(Game game) {

        GameEntity gameEntity = gameJpaRepository.findById(game.getId())
                .orElseThrow(() -> new NotFoundException("Game not found: " + game.getId()));

        SectionEntity sectionEntity = sectionRepository.findById(game.getCurrentSection().getId())
                .orElseThrow(() -> new NotFoundException("Section not found: " + game.getCurrentSection().getId()));

        gameEntity.setCurrentSection(sectionEntity);
        gameEntityMapper.mergeToEntity(gameEntity, game);

        GameEntity saved = gameJpaRepository.save(gameEntity);
        return gameEntityMapper.toDomain(saved);
    }

}