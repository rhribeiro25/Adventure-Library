package com.pictet.technologies.adventurelibrary.domain.port.out;

import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.BookSearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookPersistencePort {

    Page<Book> searchBooks(BookSearchFilter filter, Pageable pageable);

    Optional<Book> findDetailsById(Long bookId);

    Book save(Book book);

}