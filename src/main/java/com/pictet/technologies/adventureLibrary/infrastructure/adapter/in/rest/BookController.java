package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest;

import com.pictet.technologies.adventureLibrary.application.usecase.SearchBooksUseCase;
import com.pictet.technologies.adventureLibrary.domain.model.enums.DifficultyLevel;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookSearchFilter;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final SearchBooksUseCase searchBooksUseCase;

    @GetMapping
    public Page<BookSummaryResponse> searchBooks(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) DifficultyLevel difficulty,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "title") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        BookSearchFilter filter = BookSearchFilter.builder()
                .query(query)
                .author(author)
                .difficulty(difficulty)
                .category(category)
                .build();

        return searchBooksUseCase.execute(filter, pageable);
    }
}