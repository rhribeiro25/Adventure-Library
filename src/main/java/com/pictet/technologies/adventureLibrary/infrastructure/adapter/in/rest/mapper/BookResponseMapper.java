package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.mapper;

import com.pictet.technologies.adventureLibrary.domain.model.Book;
import com.pictet.technologies.adventureLibrary.domain.model.Category;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookSummaryResponse;
import org.springframework.stereotype.Component;

@Component
public class BookResponseMapper {

    public BookSummaryResponse toSummaryResponse(Book book) {

        return BookSummaryResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .difficultyLevel(book.getDifficultyLevel())
                .categories(
                        book.getCategories()
                                .stream()
                                .map(Category::getName)
                                .toList()
                )
                .build();
    }
}