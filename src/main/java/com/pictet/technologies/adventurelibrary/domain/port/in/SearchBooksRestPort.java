package com.pictet.technologies.adventurelibrary.domain.port.in;

import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request.BookSearchFilterRequest;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.BookSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SearchBooksRestPort {
    Page<BookSummaryResponse> execute(BookSearchFilterRequest filter, Pageable pageable);
}
