package com.pictet.technologies.adventurelibrary.domain.port.in;

import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.BookDetailsResponse;

public interface GetBookDetailsRestPort {
    BookDetailsResponse execute(Long bookId);
}
