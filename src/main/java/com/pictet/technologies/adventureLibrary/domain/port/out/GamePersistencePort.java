package com.pictet.technologies.adventureLibrary.domain.port.out;

import com.pictet.technologies.adventureLibrary.domain.model.Game;

import java.util.Optional;

public interface GamePersistencePort {

    Optional<Game> findById(Long gameId);

    Game save(Game game);
}