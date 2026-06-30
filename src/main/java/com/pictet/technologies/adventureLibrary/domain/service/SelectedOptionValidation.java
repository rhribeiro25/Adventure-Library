package com.pictet.technologies.adventureLibrary.domain.service;

import com.pictet.technologies.adventureLibrary.domain.exception.BadRequestException;
import com.pictet.technologies.adventureLibrary.domain.exception.BusinessException;
import com.pictet.technologies.adventureLibrary.domain.model.Game;
import com.pictet.technologies.adventureLibrary.domain.model.Option;
import com.pictet.technologies.adventureLibrary.domain.model.enums.GameStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SelectedOptionValidation {

    public Option validate(Game game, Long optionId) {
        if (game.getStatus() != GameStatus.PLAYING) {
            throw new BusinessException("Game is not playing.", HttpStatus.BAD_REQUEST);
        }

        return game.getCurrentSection()
                .getOptions()
                .stream()
                .filter(option -> option.getId().equals(optionId))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Invalid option for current section."));
    }
}