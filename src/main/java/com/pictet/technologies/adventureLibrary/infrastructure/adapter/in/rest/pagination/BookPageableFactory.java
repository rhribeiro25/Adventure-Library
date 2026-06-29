package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class BookPageableFactory {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 20;
    private static final int MAX_SIZE = 100;

    public Pageable create(
            Integer page,
            Integer size,
            String sort,
            String direction) {

        int safePage = page == null || page < 0
                ? DEFAULT_PAGE
                : page;

        int safeSize = size == null || size <= 0
                ? DEFAULT_SIZE
                : Math.min(size, MAX_SIZE);

        String safeSort = normalizeSort(sort);

        Sort.Direction sortDirection =
                Sort.Direction.fromOptionalString(direction)
                        .orElse(Sort.Direction.ASC);

        return PageRequest.of(
                safePage,
                safeSize,
                Sort.by(sortDirection, safeSort)
        );
    }

    private static final Map<String, String> SORT_FIELDS = Map.of(
            "title", "title",
            "author", "author",
            "difficulty", "difficultyLevel",
            "createdAt", "createdAt"
    );

    private String normalizeSort(String sort) {
        if (sort == null || sort.isBlank()) {
            return "title";
        }

        return SORT_FIELDS.getOrDefault(sort, "title");
    }
}