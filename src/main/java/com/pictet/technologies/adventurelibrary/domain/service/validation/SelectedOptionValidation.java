package com.pictet.technologies.adventurelibrary.domain.service.validation;

import com.pictet.technologies.adventurelibrary.domain.exception.BadRequestException;
import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.domain.model.Option;
import org.springframework.stereotype.Component;

@Component
public class SelectedOptionValidation {

    public Option validate(Game game, Long optionId) {
        return game.getCurrentSection()
                .getOptions()
                .stream()
                .filter(option -> option.getId().equals(optionId))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Invalid option for current section."));
    }
}