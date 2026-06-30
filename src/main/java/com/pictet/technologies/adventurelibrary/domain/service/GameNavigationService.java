package com.pictet.technologies.adventurelibrary.domain.service;

import com.pictet.technologies.adventurelibrary.domain.exception.NotFoundException;
import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.domain.model.Option;
import com.pictet.technologies.adventurelibrary.domain.model.Section;
import com.pictet.technologies.adventurelibrary.domain.port.out.SectionPersistencePort;
import com.pictet.technologies.adventurelibrary.domain.service.validation.game.GameValidation;
import com.pictet.technologies.adventurelibrary.domain.service.validation.option.SelectedOptionValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameNavigationService {

    private final GameValidation gameValidation;
    private final SelectedOptionValidation selectedOptionValidation;
    private final GameConsequenceService gameConsequenceService;
    private final SectionPersistencePort sectionPersistencePort;
    private final GameResultService gameResultService;

    public void navigate(Game game, Long optionId) {
        gameValidation.validate(game);

        Option selectedOption = selectedOptionValidation.validate(game, optionId);

        gameConsequenceService.apply(game, selectedOption.getConsequence());

        Section nextSection = findNextSection(selectedOption.getNextSectionId());

        moveToNextSection(game, nextSection);

        gameResultService.verify(game, nextSection);
    }

    private Section findNextSection(Long nextSectionId) {
        return sectionPersistencePort.findById(nextSectionId)
                .orElseThrow(() -> new NotFoundException(
                        "Section with id %d not found.".formatted(nextSectionId)
                ));
    }

    private void moveToNextSection(Game game, Section nextSection) {
        game.setCurrentSection(nextSection);
    }
}