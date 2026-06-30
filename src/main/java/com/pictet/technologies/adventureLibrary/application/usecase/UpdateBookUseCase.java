package com.pictet.technologies.adventureLibrary.application.usecase;

import com.pictet.technologies.adventureLibrary.domain.exception.NotFoundException;
import com.pictet.technologies.adventureLibrary.domain.model.Book;
import com.pictet.technologies.adventureLibrary.domain.model.Category;
import com.pictet.technologies.adventureLibrary.domain.port.in.UpdateBookRestPort;
import com.pictet.technologies.adventureLibrary.domain.port.out.BookPersistencePort;
import com.pictet.technologies.adventureLibrary.domain.port.out.CategoryPersistencePort;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.BookDetailsResponse;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.UpdateBookRequest;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.mapper.BookDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateBookUseCase implements UpdateBookRestPort {

    private final BookPersistencePort bookPersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;
    private final BookDtoMapper bookDtoMapper;

    @Transactional
    public BookDetailsResponse execute(Long bookId, UpdateBookRequest request) {
        Book book = bookPersistencePort.findDetailsById(bookId)
                .orElseThrow(() -> new NotFoundException(
                        "Book with id %d not found.".formatted(bookId)
                ));

        applyPartialUpdates(book, request);

        Book savedBook = bookPersistencePort.save(book);

        return bookDtoMapper.toDetailsResponse(savedBook);
    }

    private void applyPartialUpdates(Book book, UpdateBookRequest request) {
        updateTitle(book, request);
        updateAuthor(book, request);
        updateDifficulty(book, request);
        updateCategories(book, request);
    }

    private void updateTitle(Book book, UpdateBookRequest request) {
        if (hasText(request.title())) {
            book.setTitle(request.title().trim());
        }
    }

    private void updateAuthor(Book book, UpdateBookRequest request) {
        if (hasText(request.author())) {
            book.setAuthor(request.author().trim());
        }
    }

    private void updateDifficulty(Book book, UpdateBookRequest request) {
        if (request.difficulty() != null) {
            book.setDifficultyLevel(request.difficulty());
        }
    }

    private void updateCategories(Book book, UpdateBookRequest request) {
        if (request.categories() == null) {
            return;
        }

        if (request.categories().isEmpty()) {
            book.getCategories().clear();
            return;
        }

        Set<Category> categories = request.categories()
                .stream()
                .map(this::findCategoryById)
                .collect(Collectors.toSet());

        book.setCategories(categories);
    }

    private Category findCategoryById(Long categoryId) {
        return categoryPersistencePort.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(
                        "Category with id %d not found.".formatted(categoryId)
                ));
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}