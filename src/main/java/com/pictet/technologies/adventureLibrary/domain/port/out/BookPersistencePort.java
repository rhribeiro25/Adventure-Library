package com.pictet.technologies.adventureLibrary.domain.port.out;

import com.pictet.technologies.adventureLibrary.domain.model.Book;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookSearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookPersistencePort {

    Page<Book> searchBooks(
            BookSearchFilter filter,
            Pageable pageable
    );

    Optional<Book> findDetailsById(Long bookId);

}