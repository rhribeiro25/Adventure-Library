package com.pictet.technologies.adventurelibrary.application.usecase;

import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.domain.port.in.CreateBookRestPort;
import com.pictet.technologies.adventurelibrary.domain.port.out.BookPersistencePort;
import com.pictet.technologies.adventurelibrary.domain.service.validation.book.BookValidator;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request.CreateBookRequest;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.BookDetailsResponse;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper.BookDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateBookUseCase implements CreateBookRestPort {

    private final BookValidator bookValidator;
    private final BookPersistencePort bookPersistencePort;
    private final BookDtoMapper bookDtoMapper;

    @Override
    @Transactional
    public BookDetailsResponse execute(CreateBookRequest request) {
        Book book = bookDtoMapper.toDomain(request);

        bookValidator.validate(book);

        Book savedBook = bookPersistencePort.save(book);

        return bookDtoMapper.toDetailsResponse(savedBook);
    }
}