package com.pictet.technologies.adventurelibrary.domain.model;

import com.pictet.technologies.adventurelibrary.domain.model.enums.GameStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Game {

    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Include
    private Player player;
    @EqualsAndHashCode.Include
    private Integer health;
    @EqualsAndHashCode.Include

    private GameStatus status;
    private Book book;
    private Section currentSection;
}