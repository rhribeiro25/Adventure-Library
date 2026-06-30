package com.pictet.technologies.adventurelibrary.domain.service.validation.book;

import com.pictet.technologies.adventurelibrary.domain.exception.BadRequestException;
import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.domain.model.Section;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NextSectionValidation implements BookValidation {

    @Override
    public void validate(Book book) {
        Set<Long> sectionIds = book.getSections().stream()
                .map(Section::getId)
                .collect(Collectors.toSet());

        book.getSections().stream()
                .filter(section -> section.getOptions() != null)
                .flatMap(section -> section.getOptions().stream())
                .filter(option -> option.getNextSectionId() == null
                        || !sectionIds.contains(option.getNextSectionId()))
                .findFirst()
                .ifPresent(option -> {
                    throw new BadRequestException(
                            "Invalid next section id: " + option.getNextSectionId()
                    );
                });
    }
}