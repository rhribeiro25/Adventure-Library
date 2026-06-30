package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.adapter;

import com.pictet.technologies.adventurelibrary.domain.model.enums.DifficultyLevel;
import com.pictet.technologies.adventurelibrary.domain.port.in.CreateBookRestPort;
import com.pictet.technologies.adventurelibrary.domain.port.in.GetBookDetailsRestPort;
import com.pictet.technologies.adventurelibrary.domain.port.in.SearchBooksRestPort;
import com.pictet.technologies.adventurelibrary.domain.port.in.UpdateBookRestPort;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.*;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.pagination.BookPageableFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@Tag(name = "Books", description = "Book catalog management APIs")
public class BookController {

    private static final int MAX_PAGE_SIZE = 100;

    private final SearchBooksRestPort searchBooksRestPort;
    private final BookPageableFactory pageableFactory;
    private final GetBookDetailsRestPort getBookDetailsRestPort;
    private final UpdateBookRestPort updateBookRestPort;
    private final CreateBookRestPort createBookRestPort;

    @GetMapping
    @Operation(
            summary = "Search books",
            description = "Lists books with optional filters by title/author query, title, author, difficulty and category."
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

            @Parameter(description = "Filter by title")
            @RequestParam(required = false) String title,

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
                .title(title)
                .author(author)
                .difficulty(difficulty)
                .category(category)
                .build();

        log.info(
                "Searching books. query={}, title={}, author={}, difficulty={}, category={}, page={}, size={}, sort={}, direction={}",
                query, author, title, difficulty, category, page, safeSize, sort, sortDirection
        );

        return searchBooksRestPort.execute(filter, pageable);
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

        return getBookDetailsRestPort.execute(bookId);
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

        return updateBookRestPort.execute(bookId, request);
    }

    @PostMapping
    @Operation(
            summary = "Create book",
            description = "Adds a new adventure book to the collection."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Created",
            content = @Content(schema = @Schema(implementation = BookDetailsResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request",
            content = @Content
    )
    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    public ResponseEntity<BookDetailsResponse> createBook(
            @Valid @RequestBody CreateBookRequest request) {

        log.info("Creating book. title={}, author={}", request.title(), request.author());

        BookDetailsResponse response = createBookRestPort.execute(request);

        return ResponseEntity
                .created(URI.create("/api/v1/books/" + response.id()))
                .body(response);
    }
}
