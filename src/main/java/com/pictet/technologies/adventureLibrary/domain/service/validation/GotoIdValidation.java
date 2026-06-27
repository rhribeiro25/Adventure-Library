package com.pictet.technologies.adventureLibrary.domain.service.validation;

import com.pictet.technologies.adventureLibrary.domain.model.Book;
import com.pictet.technologies.adventureLibrary.domain.model.Section;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GotoIdValidation implements BookValidation {

    @Override
    public void validate(Book book) {
        Set<Long> gotoId = book.getSections().stream()
                .map(Section::getId)
                .collect(Collectors.toSet());

        book.getSections().stream()
                .filter(section -> section.getOptions() != null)
                .flatMap(section -> section.getOptions().stream())
                .filter(option -> !gotoId.contains(option.getGotoId()))
                .findFirst()
                .ifPresent(option -> {
                    throw new IllegalArgumentException(
                            "Invalid goto section number: " + option.getGotoId()
                    );
                });
    }
}