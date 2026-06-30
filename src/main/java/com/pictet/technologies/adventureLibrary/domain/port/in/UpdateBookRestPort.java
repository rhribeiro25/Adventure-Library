package com.pictet.technologies.adventureLibrary.domain.port.in;

import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.BookDetailsResponse;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.UpdateBookRequest;

public interface UpdateBookRestPort {
    BookDetailsResponse execute(Long bookId, UpdateBookRequest request);
}
