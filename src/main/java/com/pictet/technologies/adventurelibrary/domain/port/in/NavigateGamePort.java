package com.pictet.technologies.adventurelibrary.domain.port.in;

import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.GameResponse;

public interface NavigateGamePort {
    GameResponse execute(Long gameId, Long optionId);
}
