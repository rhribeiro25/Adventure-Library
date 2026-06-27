package com.pictet.technologies.adventureLibrary.application.usecase;

import com.pictet.technologies.adventureLibrary.domain.port.out.BookPersistencePort;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookSearchFilter;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookSummaryResponse;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.mapper.BookResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SearchBooksUseCase {

    private final BookPersistencePort bookPersistencePort;
    private final BookResponseMapper bookDtoMapper;

    public Page<BookSummaryResponse> execute(BookSearchFilter filter, Pageable pageable) {

        return bookPersistencePort.searchBooks(filter, pageable).map(bookDtoMapper::toSummaryResponse);
    }
}
