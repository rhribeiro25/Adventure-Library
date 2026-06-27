package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest;

import com.pictet.technologies.adventureLibrary.application.usecase.SearchBooksUseCase;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookSearchFilter;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final SearchBooksUseCase searchBooksUseCase;

    @GetMapping
    public Page<BookSummaryResponse> searchBooks(BookSearchFilter filter, Pageable pageable) {

        return searchBooksUseCase.execute(filter, pageable);
    }
}