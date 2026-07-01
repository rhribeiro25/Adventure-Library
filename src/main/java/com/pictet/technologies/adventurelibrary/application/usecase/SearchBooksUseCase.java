package com.pictet.technologies.adventurelibrary.application.usecase;

import com.pictet.technologies.adventurelibrary.domain.port.in.SearchBooksRestPort;
import com.pictet.technologies.adventurelibrary.domain.port.out.BookPersistencePort;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request.BookSearchFilterRequest;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.BookSummaryResponse;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper.BookDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SearchBooksUseCase implements SearchBooksRestPort {

    private final BookPersistencePort bookPersistencePort;
    private final BookDtoMapper bookDtoMapper;

    public Page<BookSummaryResponse> execute(BookSearchFilterRequest filter, Pageable pageable) {

        return bookPersistencePort.searchBooks(filter, pageable).map(bookDtoMapper::toSummaryResponse);
    }
}
