package com.pictet.technologies.adventurelibrary.domain.service.validation.game;

import com.pictet.technologies.adventurelibrary.domain.exception.BusinessException;
import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.domain.model.enums.GameStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class GameValidation {

    public void validate(Game game) {

        if (game.getStatus() != GameStatus.PLAYING) {
            throw new BusinessException("Game is not playing.", HttpStatus.BAD_REQUEST);
        }
    }
}