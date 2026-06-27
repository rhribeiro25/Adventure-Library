package com.pictet.technologies.adventureLibrary.domain.service.validation;

import com.pictet.technologies.adventureLibrary.domain.model.Book;
import com.pictet.technologies.adventureLibrary.domain.model.enums.SectionType;
import org.springframework.stereotype.Component;

@Component
public class EndingValidation implements BookValidation {

    @Override
    public void validate(Book book) {
        boolean hasEnd = book.getSections().stream()
                .anyMatch(section -> section.getType() == SectionType.END);

        if (!hasEnd) {
            throw new IllegalArgumentException("Book must have at least one ending section.");
        }
    }
}