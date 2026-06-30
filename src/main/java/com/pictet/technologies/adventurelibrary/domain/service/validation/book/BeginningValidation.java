package com.pictet.technologies.adventurelibrary.domain.service.validation.book;

import com.pictet.technologies.adventurelibrary.domain.exception.BadRequestException;
import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.domain.model.enums.SectionType;
import org.springframework.stereotype.Component;

@Component
public class BeginningValidation implements BookValidation {

    @Override
    public void validate(Book book) {

        if (book == null || book.getSections() == null || book.getSections().isEmpty()) {
            throw new BadRequestException("Book must have exactly one beginning section.");
        }

        long begins = book.getSections().stream()
                .filter(section -> section.getType() == SectionType.BEGIN)
                .count();

        if (begins != 1) {
            throw new BadRequestException("Book must have exactly one beginning section.");
        }
    }
}