package com.pictet.technologies.adventurelibrary.application.usecase;

import com.pictet.technologies.adventurelibrary.domain.exception.NotFoundException;
import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.domain.model.enums.GameStatus;
import com.pictet.technologies.adventurelibrary.domain.port.in.UpdateGameRestPort;
import com.pictet.technologies.adventurelibrary.domain.port.out.GamePersistencePort;
import com.pictet.technologies.adventurelibrary.domain.service.GameUpdateService;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.GameResponse;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper.GameDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateGameUseCase implements UpdateGameRestPort {

    private final GamePersistencePort gamePersistencePort;
    private final GameUpdateService gameUpdateService;
    private final GameDtoMapper gameDtoMapper;

    @Override
    @Transactional
    public GameResponse execute(Long gameId, GameStatus status) {
        Game game = gamePersistencePort.findById(gameId);
        if (game == null) {
            throw new NotFoundException(
                    "Game with id %d not found.".formatted(gameId)
            );
        }

        gameUpdateService.updateStatus(game, status);

        Game savedGame = gamePersistencePort.update(game);

        return gameDtoMapper.toResponse(savedGame);
    }
}