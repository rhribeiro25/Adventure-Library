package com.pictet.technologies.adventurelibrary.domain.port.out;

import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request.BookSearchFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookPersistencePort {

    Page<Book> searchBooks(BookSearchFilterRequest filter, Pageable pageable);

    Book findDetailsById(Long bookId);

    Book save(Book book);

    Book update(Book book);

}