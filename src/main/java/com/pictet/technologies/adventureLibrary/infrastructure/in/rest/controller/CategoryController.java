package com.pictet.technologies.adventureLibrary.infrastructure.in.rest.controller;

import com.pictet.technologies.adventureLibrary.application.usecase.CreateCategoryUseCase;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.SaveCategoryRequest;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@Tag(name = "Categories", description = "Category management APIs")
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;

    @PostMapping
    @Operation(
            summary = "Create category",
            description = "Creates a new category if it does not already exist."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Category successfully created",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request",
            content = @Content
    )
    @ApiResponse(
            responseCode = "409",
            description = "Category already exists",
            content = @Content
    )
    public ResponseEntity<CategoryResponse> createCategory(
            @Valid @RequestBody SaveCategoryRequest request) {

        log.info("Creating category '{}'", request.name());

        CategoryResponse response = createCategoryUseCase.execute(request.name());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);
    }
}