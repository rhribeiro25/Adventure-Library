package com.pictet.technologies.adventureLibrary.domain.service.validation;

import com.pictet.technologies.adventureLibrary.domain.model.Book;
import com.pictet.technologies.adventureLibrary.domain.model.Section;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UniqueSectionValidation implements BookValidation {

    @Override
    public void validate(Book book) {
        Set<Long> sectionNumbers = new HashSet<>();

        for (Section section : book.getSections()) {
            if (!sectionNumbers.add(section.getId())) {
                throw new IllegalArgumentException(
                        "Duplicated section number: " + section.getId()
                );
            }
        }
    }
}