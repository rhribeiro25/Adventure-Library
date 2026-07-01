package com.pictet.technologies.adventurelibrary.domain.port.out;

import com.pictet.technologies.adventurelibrary.domain.model.Player;

import java.util.Optional;

public interface PlayerPersistencePort {

    Optional<Player> findByEmailIgnoreCase(String email);

    Player save(Player player);
}