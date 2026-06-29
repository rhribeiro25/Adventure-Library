package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.mapper;

import com.pictet.technologies.adventureLibrary.domain.model.Book;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookDetailsResponse;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookSummaryResponse;
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

}