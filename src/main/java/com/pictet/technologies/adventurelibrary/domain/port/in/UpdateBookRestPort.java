package com.pictet.technologies.adventurelibrary.domain.port.in;

import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.BookDetailsResponse;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.UpdateBookRequest;

public interface UpdateBookRestPort {
    BookDetailsResponse execute(Long bookId, UpdateBookRequest request);
}
