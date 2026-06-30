package com.pictet.technologies.adventureLibrary.application.usecase;

import com.pictet.technologies.adventureLibrary.domain.port.in.SearchBooksRestPort;
import com.pictet.technologies.adventureLibrary.domain.port.out.BookPersistencePort;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.BookSearchFilter;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.BookSummaryResponse;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.mapper.BookDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SearchBooksUseCase implements SearchBooksRestPort {

    private final BookPersistencePort bookPersistencePort;
    private final BookDtoMapper bookDtoMapper;

    public Page<BookSummaryResponse> execute(BookSearchFilter filter, Pageable pageable) {

        return bookPersistencePort.searchBooks(filter, pageable).map(bookDtoMapper::toSummaryResponse);
    }
}
