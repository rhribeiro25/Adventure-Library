package com.pictet.technologies.adventurelibrary.domain.port.in;

import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.BookSearchFilter;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.BookSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SearchBooksRestPort {
    Page<BookSummaryResponse> execute(BookSearchFilter filter, Pageable pageable);
}
