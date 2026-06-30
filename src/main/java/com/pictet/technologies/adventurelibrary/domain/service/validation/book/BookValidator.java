package com.pictet.technologies.adventurelibrary.domain.service.validation.book;

import com.pictet.technologies.adventurelibrary.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private final List<BookValidation> validations;

    public void validate(Book book) {
        validations.forEach(validation -> validation.validate(book));
    }
}