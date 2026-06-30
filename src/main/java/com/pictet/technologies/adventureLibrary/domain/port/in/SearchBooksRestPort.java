package com.pictet.technologies.adventureLibrary.domain.port.in;

import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.BookSearchFilter;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.BookSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SearchBooksRestPort {
    Page<BookSummaryResponse> execute(BookSearchFilter filter, Pageable pageable);
}
