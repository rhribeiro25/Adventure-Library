package com.pictet.technologies.adventurelibrary.domain.service;

import com.pictet.technologies.adventurelibrary.domain.exception.BusinessException;
import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.domain.model.enums.GameStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GameUpdateService {

    public void updateStatus(Game game, GameStatus newStatus) {
        if (newStatus == null) {
            throw new BusinessException("Game status is required.", HttpStatus.BAD_REQUEST);
        }

        switch (newStatus) {
            case PAUSED -> pause(game);
            case STOPPED -> stop(game);
            case PLAYING -> resume(game);
            default -> throw new BusinessException(
                    "Status cannot be changed manually to " + newStatus,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private void pause(Game game) {
        if (game.getStatus() != GameStatus.PLAYING) {
            throw new BusinessException("Only a playing game can be paused.", HttpStatus.BAD_REQUEST);
        }

        game.setStatus(GameStatus.PAUSED);
    }

    private void stop(Game game) {
        if (game.getStatus() != GameStatus.PLAYING && game.getStatus() != GameStatus.PAUSED) {
            throw new BusinessException("Only a playing or paused game can be stopped.", HttpStatus.BAD_REQUEST);
        }

        game.setStatus(GameStatus.STOPPED);
    }

    private void resume(Game game) {
        if (game.getStatus() != GameStatus.PAUSED && game.getStatus() != GameStatus.STOPPED) {
            throw new BusinessException("Only a paused or stopped game can be resumed.", HttpStatus.BAD_REQUEST);
        }

        game.setStatus(GameStatus.PLAYING);
    }
}