package com.pictet.technologies.adventureLibrary.domain.service.validation;

import com.pictet.technologies.adventureLibrary.domain.model.Book;

public interface BookValidation {

    void validate(Book book);
}