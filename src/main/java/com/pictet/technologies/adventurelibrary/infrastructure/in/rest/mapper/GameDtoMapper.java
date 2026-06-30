package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper;

import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.GameResponse;
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