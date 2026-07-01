package com.pictet.technologies.adventurelibrary.domain.port.in;

import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.CategoryResponse;

public interface CreateCategoryRestPort {
    CategoryResponse execute(String name);
}
