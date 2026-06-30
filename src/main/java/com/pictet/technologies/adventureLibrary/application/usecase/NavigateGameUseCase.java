package com.pictet.technologies.adventureLibrary.application.usecase;

import com.pictet.technologies.adventureLibrary.domain.exception.NotFoundException;
import com.pictet.technologies.adventureLibrary.domain.model.Game;
import com.pictet.technologies.adventureLibrary.domain.model.Option;
import com.pictet.technologies.adventureLibrary.domain.model.Section;
import com.pictet.technologies.adventureLibrary.domain.port.in.NavigateGamePort;
import com.pictet.technologies.adventureLibrary.domain.port.out.GamePersistencePort;
import com.pictet.technologies.adventureLibrary.domain.port.out.SectionPersistencePort;
import com.pictet.technologies.adventureLibrary.domain.service.SelectedOptionValidation;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.GameResponse;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.mapper.GameDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NavigateGameUseCase implements NavigateGamePort {

    private final GamePersistencePort gamePersistencePort;
    private final SectionPersistencePort sectionPersistencePort;
    private final SelectedOptionValidation optionValidation;
    private final GameDtoMapper gameDtoMapper;

    @Transactional
    public GameResponse execute(Long gameId, Long optionId) {

        Game game = gamePersistencePort.findById(gameId)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Game with id %d not found.".formatted(gameId)
                        ));

        Option selectedOption = optionValidation.validate(game, optionId);

        Section targetSection = sectionPersistencePort
                .findById(selectedOption.getNextSectionId())
                .orElseThrow(() ->
                        new NotFoundException(
                                "Target section %d not found."
                                        .formatted(selectedOption.getNextSectionId())
                        ));

        game.setCurrentSection(targetSection);

        Game savedGame = gamePersistencePort.save(game);

        return gameDtoMapper.toResponse(savedGame);
    }
}