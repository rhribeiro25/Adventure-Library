package com.pictet.technologies.adventurelibrary.application.usecase;

import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.domain.model.Category;
import com.pictet.technologies.adventurelibrary.domain.port.in.UpdateBookRestPort;
import com.pictet.technologies.adventurelibrary.domain.port.out.BookPersistencePort;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request.UpdateBookRequest;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.BookDetailsResponse;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper.BookDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateBookUseCase implements UpdateBookRestPort {

    private final BookPersistencePort bookPersistencePort;
    private final BookDtoMapper bookDtoMapper;

    @Transactional
    public BookDetailsResponse execute(Long bookId, UpdateBookRequest request) {
        Book book = Book.builder()
                .id(bookId)
                .author(request.author())
                .title(request.title())
                .difficultyLevel(request.difficulty())
                .build();
        for (Long id : request.categories()) {
            book.getCategories().add(Category.builder().id(id).build());
        }

        Book savedBook = bookPersistencePort.update(book);

        return bookDtoMapper.toDetailsResponse(savedBook);
    }

}