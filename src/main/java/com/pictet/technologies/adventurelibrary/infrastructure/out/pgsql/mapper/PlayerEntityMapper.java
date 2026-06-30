package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.mapper;

import com.pictet.technologies.adventurelibrary.domain.model.Player;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.PlayerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerEntityMapper {


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