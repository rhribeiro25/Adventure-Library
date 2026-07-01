package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.adapter;

import com.pictet.technologies.adventurelibrary.domain.exception.NotFoundException;
import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.domain.model.Category;
import com.pictet.technologies.adventurelibrary.domain.model.Option;
import com.pictet.technologies.adventurelibrary.domain.model.Section;
import com.pictet.technologies.adventurelibrary.domain.port.out.BookPersistencePort;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request.BookSearchFilterRequest;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.*;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.enums.BookEntityDifficultyLevel;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.mapper.*;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.repository.*;
import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pictet.technologies.adventurelibrary.infrastructure.shared.constants.RedisConstants.BOOKS_CACHE;
import static com.pictet.technologies.adventurelibrary.infrastructure.shared.constants.RedisConstants.BOOKS_SEARCH_CACHE;

@Component
@RequiredArgsConstructor
public class BookPersistenceAdapter implements BookPersistencePort {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final SectionRepository sectionRepository;
    private final OptionRepository optionRepository;
    private final ConsequenceRepository consequenceRepository;

    private final BookEntityMapper bookEntityMapper;
    private final ConsequenceEntityMapper consequenceEntityMapper;
    private final OptionEntityMapper optionEntityMapper;
    private final SectionEntityMapper sectionEntityMapper;
    private final DifficultyLevelEntityMapper difficultyLevelEntityMapper;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = BOOKS_SEARCH_CACHE,
            key = "#filter.cacheKey() + ':' + #pageable.pageNumber + ':' + #pageable.pageSize + ':' + #pageable.sort.toString()"
    )
    public Page<Book> searchBooks(BookSearchFilterRequest filter, Pageable pageable) {
        BookEntityDifficultyLevel entityDifficultyLevel = difficultyLevelEntityMapper.toEntity(filter.getDifficulty());
        return bookRepository.findAll(BookSpecification.from(filter, entityDifficultyLevel), pageable).map(bookEntityMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = BOOKS_CACHE,
            key = "#bookId",
            unless = "#result == null"
    )
    public Book findDetailsById(Long bookId) {
        return bookRepository.findById(bookId)
                .map(bookEntityMapper::toDomain)
                .orElse(null);
    }

    @Override
    @Transactional
    @Retryable(
            retryFor = OptimisticLockingFailureException.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 30, multiplier = 2)
    )
    @Caching(
            put = @CachePut(value = BOOKS_CACHE, key = "#result.id"),
            evict = @CacheEvict(value = BOOKS_SEARCH_CACHE, allEntries = true)
    )
    public Book save(Book book) {
        BookEntity entity = bookEntityMapper.toEntity(book);

        entity.setId(null);
        entity.setCategories(new HashSet<>());
        entity.setSections(new HashSet<>());

        BookEntity savedBook = bookRepository.saveAndFlush(entity);

        saveSections(book, savedBook);

        BookEntity reloadedBook = bookRepository.findById(savedBook.getId())
                .orElseThrow(() -> new NotFoundException(
                        "Book with id %d not found.".formatted(savedBook.getId())
                ));

        return bookEntityMapper.toDomain(reloadedBook);
    }

    @Override
    @Transactional
    @Retryable(
            retryFor = OptimisticLockingFailureException.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 30, multiplier = 2)
    )
    @Caching(
            put = @CachePut(value = BOOKS_CACHE, key = "#result.id"),
            evict = @CacheEvict(value = BOOKS_SEARCH_CACHE, allEntries = true)
    )
    public Book update(Book book) {

        BookEntity entity = bookRepository.findById(book.getId()).orElse(null);
        if (entity == null) {
            throw new NotFoundException(
                    "Book with id %d not found.".formatted(book.getId())
            );
        }

        entity.setCategories(new HashSet<>());

        for (Long categoryId : book.getCategories().stream().map(Category::getId).collect(Collectors.toSet())) {
            if (categoryId != null) {
                CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new NotFoundException(
                                "Game with id %d not found.".formatted(categoryId)
                        ));
                entity.getCategories().add(categoryEntity);
            }
        }
        bookEntityMapper.mergeToEntity(entity, book);
        var saved = bookRepository.save(entity);
        return bookEntityMapper.toDomain(saved);
    }

    private void saveSections(Book book, BookEntity savedBook) {
        if (book.getSections() == null || book.getSections().isEmpty()) {
            return;
        }

        book.getSections().stream()
                .map(section -> saveSection(section, savedBook))
                .collect(Collectors.toSet());
    }

    private SectionEntity saveSection(Section section, BookEntity savedBook) {
        SectionEntity sectionEntity = sectionEntityMapper.toEntity(section);

        sectionEntity.setBook(savedBook);
        sectionEntity.setOptions(new HashSet<>());

        SectionEntity savedSection = sectionRepository.saveAndFlush(sectionEntity);

        Set<OptionEntity> savedOptions = saveOptions(section);
        savedSection.setOptions(savedOptions);

        return sectionRepository.save(savedSection);
    }

    private Set<OptionEntity> saveOptions(Section section) {
        if (section.getOptions() == null || section.getOptions().isEmpty()) {
            return new HashSet<>();
        }

        return section.getOptions().stream()
                .map(this::saveOption)
                .collect(Collectors.toSet());
    }

    private OptionEntity saveOption(Option option) {
        OptionEntity optionEntity = optionEntityMapper.toEntity(option);
        optionEntity.setConsequence(null);

        OptionEntity savedOption = optionRepository.save(optionEntity);

        if (option.getConsequence() != null) {
            ConsequenceEntity consequenceEntity =
                    consequenceEntityMapper.toEntity(option.getConsequence());

            consequenceEntity.setOption(savedOption);
            savedOption.setConsequence(consequenceRepository.save(consequenceEntity));
        }

        return optionRepository.save(savedOption);
    }
}