package com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto;

import com.pictet.technologies.adventureLibrary.domain.model.enums.GameStatus;
import lombok.Builder;

@Builder
public record GameResponse(
        Long id,
        String playerName,
        Integer health,
        GameStatus status,
        Long bookId,
        Long currentSectionNumber
) {
}