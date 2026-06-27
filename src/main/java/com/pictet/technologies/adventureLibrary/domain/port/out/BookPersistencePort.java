package com.pictet.technologies.adventureLibrary.domain.port.out;

import com.pictet.technologies.adventureLibrary.domain.model.Book;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookSearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookPersistencePort {

    Page<Book> searchBooks(
            BookSearchFilter filter,
            Pageable pageable
    );

    Book save(Book book);

    boolean existsByTitleAndAuthor(String title, String author);

}