package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper;

import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.GameResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GameDtoMapper {

    public GameResponse toResponse(Game game) {
        return GameResponse.builder()
                .id(game.getId())
                .playerName(game.getPlayer().getName())
                .health(game.getHealth())
                .status(game.getStatus())
                .bookId(game.getBook().getId())
                .bookTitle(game.getBook().getTitle())
                .currentSectionNumber(game.getCurrentSection().getId())
                .build();
    }

}