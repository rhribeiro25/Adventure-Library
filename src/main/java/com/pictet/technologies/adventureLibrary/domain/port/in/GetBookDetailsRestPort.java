package com.pictet.technologies.adventureLibrary.domain.port.in;

import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.BookDetailsResponse;

public interface GetBookDetailsRestPort {
    BookDetailsResponse execute(Long bookId);
}
