package com.pictet.technologies.adventurelibrary.application.usecase;

import com.pictet.technologies.adventurelibrary.domain.exception.NotFoundException;
import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.domain.port.in.GetBookDetailsRestPort;
import com.pictet.technologies.adventurelibrary.domain.port.out.BookPersistencePort;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.BookDetailsResponse;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper.BookDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBookDetailsUseCase implements GetBookDetailsRestPort {

    private final BookPersistencePort bookPersistencePort;
    private final BookDtoMapper bookDtoMapper;

    public BookDetailsResponse execute(Long bookId) {
        Book book = bookPersistencePort.findDetailsById(bookId);
        if (book == null) {
            throw new NotFoundException(
                    "Book with id %d not found.".formatted(bookId)
            );
        }
        return bookDtoMapper.toDetailsResponse(book);
    }
}