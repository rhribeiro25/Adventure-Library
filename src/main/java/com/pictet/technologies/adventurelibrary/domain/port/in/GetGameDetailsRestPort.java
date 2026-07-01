package com.pictet.technologies.adventurelibrary.domain.port.in;

import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.GameResponse;

public interface GetGameDetailsRestPort {

    GameResponse execute(Long gameId);
}