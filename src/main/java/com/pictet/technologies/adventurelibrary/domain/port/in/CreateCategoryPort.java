package com.pictet.technologies.adventurelibrary.domain.port.in;

import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.CategoryResponse;

public interface CreateCategoryPort {
    CategoryResponse execute(String name);
}
