package com.pictet.technologies.adventurelibrary.domain.service.validation.book;

import com.pictet.technologies.adventurelibrary.domain.exception.BadRequestException;
import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.domain.model.Section;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UniqueSectionValidation implements BookValidation {

    @Override
    public void validate(Book book) {
        Set<Long> sectionIds = new HashSet<>();

        for (Section section : book.getSections()) {
            if (section.getId() == null) {
                throw new BadRequestException("Section id is required.");
            }

            if (!sectionIds.add(section.getId())) {
                throw new BadRequestException("Duplicated section id: " + section.getId());
            }
        }
    }
}