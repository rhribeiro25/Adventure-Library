package com.pictet.technologies.adventureLibrary.domain.port.in;

import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.CategoryResponse;

public interface CreateCategoryPort {
    CategoryResponse execute(String name);
}
