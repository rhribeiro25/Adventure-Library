package com.pictet.technologies.adventureLibrary.domain.service.validation;

import com.pictet.technologies.adventureLibrary.domain.model.Book;
import com.pictet.technologies.adventureLibrary.domain.model.enums.SectionType;
import org.springframework.stereotype.Component;

@Component
public class BeginningValidation implements BookValidation {

    @Override
    public void validate(Book book) {
        long begins = book.getSections().stream()
                .filter(section -> section.getType() == SectionType.BEGIN)
                .count();

        if (begins != 1) {
            throw new IllegalArgumentException("Book must have exactly one beginning section.");
        }
    }
}