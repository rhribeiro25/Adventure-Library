package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.controller;

import com.pictet.technologies.adventureLibrary.application.usecase.SearchBooksUseCase;
import com.pictet.technologies.adventureLibrary.domain.model.enums.DifficultyLevel;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookSearchFilter;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookSummaryResponse;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.pagination.BookPageableFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@Tag(name = "Books", description = "Book catalog and search APIs")
public class BookController {

    private static final int MAX_PAGE_SIZE = 100;

    private final SearchBooksUseCase searchBooksUseCase;
    private final BookPageableFactory pageableFactory;

    @GetMapping
    @Operation(
            summary = "Search books",
            description = "Lists books with optional filters by title/author query, author, difficulty and category."
    )
    public Page<BookSummaryResponse> searchBooks(
            @Parameter(description = "Search by title or author")
            @RequestParam(required = false) String query,

            @Parameter(description = "Filter by author")
            @RequestParam(required = false) String author,

            @Parameter(description = "Filter by difficulty")
            @RequestParam(required = false) DifficultyLevel difficulty,

            @Parameter(description = "Filter by category")
            @RequestParam(required = false) String category,

            @Parameter(description = "Page number, starting from 0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Page size")
            @RequestParam(defaultValue = "20") int size,

            @Parameter(description = "Sort field. Allowed: title, author, difficultyLevel")
            @RequestParam(defaultValue = "title") String sort,

            @Parameter(description = "Sort direction: asc or desc")
            @RequestParam(defaultValue = "asc") String direction) {

        int safeSize = Math.min(size, MAX_PAGE_SIZE);

        Sort.Direction sortDirection = Sort.Direction.fromOptionalString(direction)
                .orElse(Sort.Direction.ASC);

        Pageable pageable = pageableFactory.create(page, size, sort, direction);

        BookSearchFilter filter = BookSearchFilter.builder()
                .query(query)
                .author(author)
                .difficulty(difficulty)
                .category(category)
                .build();

        log.info(
                "Searching books. query={}, author={}, difficulty={}, category={}, page={}, size={}, sort={}, direction={}",
                query, author, difficulty, category, page, safeSize, sort, sortDirection
        );

        return searchBooksUseCase.execute(filter, pageable);
    }
}