package com.pictet.technologies.adventureLibrary.infrastructure.in.rest.mapper;

import com.pictet.technologies.adventureLibrary.domain.model.Game;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.GameResponse;
import org.springframework.stereotype.Component;

@Component
public class GameDtoMapper {

    public GameResponse toResponse(Game game) {
        return GameResponse.builder()
                .id(game.getId())
                .playerName(game.getPlayer().getName())
                .health(game.getHealth())
                .status(game.getStatus())
                .bookId(game.getBook().getId())
                .currentSectionNumber(game.getCurrentSection().getId())
                .build();
    }
}