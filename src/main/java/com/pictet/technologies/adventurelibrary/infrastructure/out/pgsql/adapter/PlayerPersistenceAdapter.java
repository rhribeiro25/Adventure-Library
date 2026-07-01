package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.adapter;

import com.pictet.technologies.adventurelibrary.domain.model.Player;
import com.pictet.technologies.adventurelibrary.domain.port.out.PlayerPersistencePort;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.mapper.PlayerEntityMapper;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlayerPersistenceAdapter implements PlayerPersistencePort {

    private final PlayerRepository repository;
    private final PlayerEntityMapper mapper;

    @Override
    public Optional<Player> findByEmailIgnoreCase(String email) {
        return repository.findByEmailIgnoreCase(email)
                .map(mapper::toDomain);
    }

    @Override
    public Player save(Player player) {
        var entity = mapper.toEntity(player);
        var saved = repository.save(entity);
        return mapper.toDomain(saved);
    }
}