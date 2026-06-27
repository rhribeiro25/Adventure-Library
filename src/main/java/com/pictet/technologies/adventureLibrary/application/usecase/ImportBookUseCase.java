package com.pictet.technologies.adventureLibrary.application.usecase;

import com.pictet.technologies.adventureLibrary.domain.model.Book;
import com.pictet.technologies.adventureLibrary.domain.port.out.BookPersistencePort;
import com.pictet.technologies.adventureLibrary.domain.service.validation.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImportBookUseCase {

    private final BookPersistencePort bookPersistencePort;
    private final BookValidator bookValidator;

    public void execute(Book book) {
        if (bookPersistencePort.existsByTitleAndAuthor(book.getTitle(), book.getAuthor())) {
            return;
        }

        bookValidator.validate(book);

        bookPersistencePort.save(book);
    }
}