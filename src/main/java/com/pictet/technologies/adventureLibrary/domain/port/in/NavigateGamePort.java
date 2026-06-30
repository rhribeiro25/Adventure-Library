package com.pictet.technologies.adventureLibrary.domain.port.in;

import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.GameResponse;

public interface NavigateGamePort {
    GameResponse execute(Long gameId, Long optionId);
}
