package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper;

import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.BookDetailsResponse;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.BookSummaryResponse;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request.CreateBookRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookDtoMapper {

    private final CategoryDtoMapper categoryDtoMapper;
    private final SectionDtoMapper sectionDtoMapper;

    public BookSummaryResponse toSummaryResponse(Book book) {
        return BookSummaryResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .difficulty(book.getDifficultyLevel())
                .categories(categoryDtoMapper.toCategoryResponses(book.getCategories()))
                .sections(sectionDtoMapper.toSectionResponses(book.getSections()))
                .build();
    }

    public BookDetailsResponse toDetailsResponse(Book book) {
        return BookDetailsResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .difficulty(book.getDifficultyLevel())
                .categories(categoryDtoMapper.toCategoryResponses(book.getCategories()))
                .sections(sectionDtoMapper.toSectionResponses(book.getSections()))
                .build();
    }

    public Book toDomain(CreateBookRequest request) {
        return Book.builder()
                .title(request.title().trim())
                .author(request.author().trim())
                .difficultyLevel(request.difficulty())
                .sections(sectionDtoMapper.toSections(request.sections()))
                .build();
    }

}