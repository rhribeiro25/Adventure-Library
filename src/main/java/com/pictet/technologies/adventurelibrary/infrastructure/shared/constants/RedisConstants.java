package com.pictet.technologies.adventurelibrary.infrastructure.shared.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisConstants {

    public static final String BOOKS_CACHE = "books-cache";
    public static final String BOOKS_SEARCH_CACHE = "books-search-cache";


}
