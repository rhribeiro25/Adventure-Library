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
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.pictet.technologies.adventurelibrary.infrastructure.shared.constants.RedisConstants.GAMES_CACHE;

@Component
@RequiredArgsConstructor
public class GamePersistenceAdapter implements GamePersistencePort {

    private final GameRepository gameJpaRepository;
    private final GameEntityMapper gameEntityMapper;
    private final SectionRepository sectionRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = GAMES_CACHE,
            key = "#gameId",
            unless = "#result == null"
    )
    public Game findById(Long gameId) {
        return gameJpaRepository.findById(gameId)
                .map(gameEntityMapper::toDomain)
                .orElse(null);
    }

    @Override
    @Transactional
    @Retryable(
            retryFor = OptimisticLockingFailureException.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 30, multiplier = 2)
    )
    @Caching(
            put = @CachePut(value = GAMES_CACHE, key = "#result.id")
    )
    public Game save(Game game) {
        var entity = gameEntityMapper.toEntity(game);
        var saved = gameJpaRepository.save(entity);
        return gameEntityMapper.toDomain(saved);
    }

    @Override
    @Transactional
    @Retryable(
            retryFor = OptimisticLockingFailureException.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 30, multiplier = 2)
    )
    @Caching(
            put = @CachePut(value = GAMES_CACHE, key = "#result.id")
    )
    public Game update(Game game) {
        GameEntity entity = gameJpaRepository.findById(game.getId())
                .orElseThrow(() -> new NotFoundException(
                        "Game with id %d not found.".formatted(game.getId())
                ));
        if (game.getCurrentSection().getId() != null &&
                !Objects.equals(game.getCurrentSection().getId(), entity.getCurrentSection().getId())) {

            SectionEntity sectionEntity = sectionRepository.findById(game.getCurrentSection().getId())
                    .orElseThrow(() -> new NotFoundException(
                            "Game with id %d not found.".formatted(game.getCurrentSection().getId())
                    ));

            entity.setCurrentSection(sectionEntity);
        }
        gameEntityMapper.mergeToEntity(entity, game);
        var saved = gameJpaRepository.save(entity);
        return gameEntityMapper.toDomain(saved);
    }

}