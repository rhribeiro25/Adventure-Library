package com.pictet.technologies.adventurelibrary.domain.service;

import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.domain.model.Section;
import com.pictet.technologies.adventurelibrary.domain.model.enums.GameStatus;
import com.pictet.technologies.adventurelibrary.domain.model.enums.SectionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameResultService {


    public void verify(Game game, Section currentSection) {

        if (game.getHealth() <= 0) {
            game.setStatus(GameStatus.LOST);
            return;
        }

        if (currentSection.getType() == SectionType.END) {
            game.setStatus(GameStatus.WON);
            return;
        }

        game.setStatus(GameStatus.PLAYING);
    }
}