package com.pictet.technologies.adventurelibrary.application.usecase;

import com.pictet.technologies.adventurelibrary.domain.exception.NotFoundException;
import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.domain.port.in.NavigateGameRestPort;
import com.pictet.technologies.adventurelibrary.domain.port.out.GamePersistencePort;
import com.pictet.technologies.adventurelibrary.domain.service.GameNavigationService;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.GameResponse;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper.GameDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NavigateGameRestUseCase implements NavigateGameRestPort {

    private final GamePersistencePort gamePersistencePort;
    private final GameNavigationService gameNavigationService;
    private final GameDtoMapper gameDtoMapper;

    @Transactional
    public GameResponse execute(Long gameId, Long optionId) {
        Game game = gamePersistencePort.findById(gameId);
        if (game == null) {
            throw new NotFoundException(
                    "Game with id %d not found.".formatted(gameId)
            );
        }

        gameNavigationService.navigate(game, optionId);

        Game savedGame = gamePersistencePort.update(game);

        return gameDtoMapper.toResponse(savedGame);
    }
}