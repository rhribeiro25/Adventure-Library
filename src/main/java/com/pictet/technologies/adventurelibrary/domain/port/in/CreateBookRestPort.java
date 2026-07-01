package com.pictet.technologies.adventurelibrary.domain.port.in;


import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request.CreateBookRequest;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.BookDetailsResponse;

public interface CreateBookRestPort {

    BookDetailsResponse execute(CreateBookRequest request);
}