package com.pictet.technologies.adventurelibrary.domain.service.validation.book;

import com.pictet.technologies.adventurelibrary.domain.exception.BadRequestException;
import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.domain.model.enums.SectionType;
import org.springframework.stereotype.Component;

@Component
public class OptionsValidation implements BookValidation {

    @Override
    public void validate(Book book) {
        book.getSections().stream()
                .filter(section -> section.getType() != SectionType.END)
                .filter(section -> section.getOptions() == null || section.getOptions().isEmpty())
                .findFirst()
                .ifPresent(section -> {
                    throw new BadRequestException(
                            "Non-ending section must have options. Section: " + section.getId()
                    );
                });
    }
}