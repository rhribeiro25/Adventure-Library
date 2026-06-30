package com.pictet.technologies.adventureLibrary.application.usecase;

import com.pictet.technologies.adventureLibrary.domain.exception.NotFoundException;
import com.pictet.technologies.adventureLibrary.domain.port.out.BookPersistencePort;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.BookDetailsResponse;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.mapper.BookDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBookDetailsUseCase {

    private final BookPersistencePort bookPersistencePort;
    private final BookDtoMapper bookDtoMapper;

    public BookDetailsResponse execute(Long bookId) {
        return bookPersistencePort.findDetailsById(bookId)
                .map(bookDtoMapper::toDetailsResponse)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %d not found.", bookId)));
    }
}