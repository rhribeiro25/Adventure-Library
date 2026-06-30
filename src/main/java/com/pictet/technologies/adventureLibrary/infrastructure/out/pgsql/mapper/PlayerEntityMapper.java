package com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.mapper;

import com.pictet.technologies.adventureLibrary.domain.model.Player;
import com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.entity.PlayerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerEntityMapper {

    private final OptionEntityMapper optionEntityMapper;

    public Player toDomain(PlayerEntity entity) {
        if (entity == null) {
            return null;
        }

        return Player.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .build();
    }

    public PlayerEntity toEntity(Player domain) {
        if (domain == null) {
            return null;
        }

        return PlayerEntity.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .name(domain.getName())
                .build();
    }

}