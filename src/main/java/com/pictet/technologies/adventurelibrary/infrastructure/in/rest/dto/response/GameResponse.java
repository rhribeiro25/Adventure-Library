package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response;

import com.pictet.technologies.adventurelibrary.domain.model.enums.GameStatus;
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