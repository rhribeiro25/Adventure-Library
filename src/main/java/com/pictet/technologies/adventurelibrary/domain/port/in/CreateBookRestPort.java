package com.pictet.technologies.adventurelibrary.domain.port.in;

import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.BookDetailsResponse;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.CreateBookRequest;

public interface CreateBookRestPort {

    BookDetailsResponse execute(CreateBookRequest request);
}