package com.pictet.technologies.adventurelibrary.domain.port.in;

import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request.StartGameRequest;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.GameResponse;

public interface StartGameRestPort {

    GameResponse execute(Long bookId, StartGameRequest request);
}