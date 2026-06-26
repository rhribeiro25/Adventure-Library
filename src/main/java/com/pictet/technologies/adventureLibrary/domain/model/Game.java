package com.pictet.technologies.adventureLibrary.domain.model;

import com.pictet.technologies.adventureLibrary.domain.model.enums.GameStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    private Long id;
    private String player;
    private Book book;
    private Section currentSection;
    private Integer health;
    private GameStatus status;
}