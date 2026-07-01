package com.pictet.technologies.adventurelibrary.application.usecase;

import com.pictet.technologies.adventurelibrary.domain.exception.NotFoundException;
import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.domain.port.in.GetGameDetailsRestPort;
import com.pictet.technologies.adventurelibrary.domain.port.out.GamePersistencePort;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.GameResponse;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper.GameDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetGameDetailsUseCase implements GetGameDetailsRestPort {

    private final GamePersistencePort gamePersistencePort;
    private final GameDtoMapper gameDtoMapper;

    @Override
    @Transactional(readOnly = true)
    public GameResponse execute(Long gameId) {
        Game game = gamePersistencePort.findById(gameId);
        if (game == null) {
            throw new NotFoundException(
                    "Game with id %d not found.".formatted(gameId)
            );
        }
        return gameDtoMapper.toResponse(game);
    }
}