package com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.adapter;

import com.pictet.technologies.adventureLibrary.domain.model.Book;
import com.pictet.technologies.adventureLibrary.domain.port.out.BookPersistencePort;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookSearchFilter;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity.enums.BookEntityDifficultyLevel;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.mapper.BookEntityMapper;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.mapper.DifficultyLevelEntityMapper;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.repository.BookJpaRepository;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookPersistenceAdapter implements BookPersistencePort {

    private final BookJpaRepository repository;
    private final BookEntityMapper bookEntityMapper;
    private final DifficultyLevelEntityMapper difficultyLevelEntityMapper;

    @Override
    public Page<Book> searchBooks(BookSearchFilter filter, Pageable pageable) {
        BookEntityDifficultyLevel entityDifficultyLevel = difficultyLevelEntityMapper.toEntity(filter.getDifficulty());
        return repository.findAll(BookSpecification.from(filter, entityDifficultyLevel), pageable).map(bookEntityMapper::toDomain);
    }

    @Override
    public boolean existsByTitleAndAuthor(String title, String author) {
        return repository.existsByTitleIgnoreCaseAndAuthorIgnoreCase(title, author);
    }

    @Override
    public Book save(Book book) {
        var entity = bookEntityMapper.toEntity(book);
        var saved = repository.save(entity);
        return bookEntityMapper.toDomain(saved);
    }
}