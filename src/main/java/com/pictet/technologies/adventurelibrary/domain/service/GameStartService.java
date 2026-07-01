package com.pictet.technologies.adventurelibrary.domain.service;

import com.pictet.technologies.adventurelibrary.domain.exception.BadRequestException;
import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.domain.model.Player;
import com.pictet.technologies.adventurelibrary.domain.model.Section;
import com.pictet.technologies.adventurelibrary.domain.model.enums.GameStatus;
import com.pictet.technologies.adventurelibrary.domain.model.enums.SectionType;
import org.springframework.stereotype.Service;

@Service
public class GameStartService {

    private static final int INITIAL_HEALTH = 10;

    public Game start(Book book, Player player) {
        Section beginSection = book.getSections()
                .stream()
                .filter(section -> section.getType() == SectionType.BEGIN)
                .findFirst()
                .orElseThrow(() -> new BadRequestException(
                        "Book must have exactly one beginning section."
                ));

        return Game.builder()
                .player(player)
                .book(book)
                .currentSection(beginSection)
                .health(INITIAL_HEALTH)
                .status(GameStatus.PLAYING)
                .build();
    }
}