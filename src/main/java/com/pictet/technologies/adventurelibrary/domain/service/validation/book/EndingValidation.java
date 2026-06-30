package com.pictet.technologies.adventurelibrary.domain.service.validation.book;

import com.pictet.technologies.adventurelibrary.domain.exception.BadRequestException;
import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.domain.model.enums.SectionType;
import org.springframework.stereotype.Component;

@Component
public class EndingValidation implements BookValidation {

    @Override
    public void validate(Book book) {
        boolean hasEnd = book.getSections().stream()
                .anyMatch(section -> section.getType() == SectionType.END);

        if (!hasEnd) {
            throw new BadRequestException("Book must have at least one ending section.");
        }
    }
}