package com.pictet.technologies.adventurelibrary.domain.port.out;

import com.pictet.technologies.adventurelibrary.domain.model.Game;

public interface GamePersistencePort {

    Game findById(Long gameId);

    Game save(Game game);

    Game update(Game game);
}