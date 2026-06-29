package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.controller;

import com.pictet.technologies.adventureLibrary.application.usecase.GetBookDetailsUseCase;
import com.pictet.technologies.adventureLibrary.application.usecase.SearchBooksUseCase;
import com.pictet.technologies.adventureLibrary.application.usecase.UpdateBookUseCase;
import com.pictet.technologies.adventureLibrary.domain.model.enums.DifficultyLevel;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.*;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.pagination.BookPageableFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@Tag(name = "Books", description = "Book catalog management APIs")
public class BookController {

    private static final int MAX_PAGE_SIZE = 100;

    private final SearchBooksUseCase searchBooksUseCase;
    private final BookPageableFactory pageableFactory;
    private final GetBookDetailsUseCase getBookDetailsUseCase;
    private final UpdateBookUseCase updateBookUseCase;

    @GetMapping
    @Operation(
            summary = "Search books",
            description = "Lists books with optional filters by title/author query, author, difficulty and category."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = BookDetailsResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request",
            content = @Content
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

            @Parameter(description = "Sort field. Allowed: title, author, difficulty")
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

    @GetMapping("/{bookId}")
    @Operation(
            summary = "Get book details",
            description = "Returns book details by id, including title, author, difficulty and categories."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = BookDetailsResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content
    )
    public BookDetailsResponse getBookDetails(
            @Parameter(description = "Book id", required = true)
            @PathVariable Long bookId) {

        log.info("Getting book details. bookId={}", bookId);

        return getBookDetailsUseCase.execute(bookId);
    }

    @PatchMapping("/{bookId}")
    @Operation(
            summary = "Update book",
            description = "Partially updates book title, author, difficulty and category."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = BookDetailsResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content
    )
    public BookDetailsResponse updateBook(
            @Parameter(description = "Book id", required = true)
            @PathVariable Long bookId,

            @Valid @RequestBody UpdateBookRequest request) {

        log.info("Updating book. bookId={}", bookId);

        return updateBookUseCase.execute(bookId, request);
    }
}