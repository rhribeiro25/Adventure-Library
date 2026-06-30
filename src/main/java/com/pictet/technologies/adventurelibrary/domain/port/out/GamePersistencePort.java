package com.pictet.technologies.adventurelibrary.domain.port.out;

import com.pictet.technologies.adventurelibrary.domain.model.Game;

import java.util.Optional;

public interface GamePersistencePort {

    Optional<Game> findById(Long gameId);

    Game save(Game game);

    Game update(Game game);
}