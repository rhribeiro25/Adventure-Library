package com.pictet.technologies.adventurelibrary.domain.port.in;

import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request.UpdateBookRequest;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.BookDetailsResponse;

public interface UpdateBookRestPort {
    BookDetailsResponse execute(Long bookId, UpdateBookRequest request);
}
