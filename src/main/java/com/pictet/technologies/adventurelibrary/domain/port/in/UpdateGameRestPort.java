package com.pictet.technologies.adventurelibrary.domain.port.in;

import com.pictet.technologies.adventurelibrary.domain.model.enums.GameStatus;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.GameResponse;

public interface UpdateGameRestPort {
    GameResponse execute(Long id, GameStatus status);
}
