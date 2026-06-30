package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.adapter;

import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.domain.port.out.BookPersistencePort;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.BookSearchFilter;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.enums.BookEntityDifficultyLevel;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.mapper.BookEntityMapper;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.mapper.DifficultyLevelEntityMapper;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.repository.BookRepository;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookPersistenceAdapter implements BookPersistencePort {

    private final BookRepository repository;
    private final BookEntityMapper bookEntityMapper;
    private final DifficultyLevelEntityMapper difficultyLevelEntityMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<Book> searchBooks(BookSearchFilter filter, Pageable pageable) {
        BookEntityDifficultyLevel entityDifficultyLevel = difficultyLevelEntityMapper.toEntity(filter.getDifficulty());
        return repository.findAll(BookSpecification.from(filter, entityDifficultyLevel), pageable).map(bookEntityMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findDetailsById(Long bookId) {
        return repository.findById(bookId)
                .map(bookEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Book save(Book book) {
        var entity = bookEntityMapper.toEntity(book);
        var saved = repository.save(entity);
        return bookEntityMapper.toDomain(saved);
    }
}