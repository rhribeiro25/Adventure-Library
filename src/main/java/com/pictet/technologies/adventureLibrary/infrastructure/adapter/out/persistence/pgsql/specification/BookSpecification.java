package com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.specification;

import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookSearchFilter;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity.BookEntity;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity.CategoryEntity;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity.enums.BookEntityDifficultyLevel;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public final class BookSpecification {

    private BookSpecification() {
    }

    public static Specification<BookEntity> from(
            BookSearchFilter filter,
            BookEntityDifficultyLevel difficultyLevel
    ) {
        if (filter == null) {
            return Specification.where((Specification<BookEntity>) null);
        }

        return Specification
                .where(queryContains(filter.getQuery()))
                .and(authorContains(filter.getAuthor()))
                .and(difficultyEquals(difficultyLevel))
                .and(categoryEquals(filter.getCategory()));
    }

    private static Specification<BookEntity> queryContains(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (query == null || query.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            String value = "%" + query.trim().toLowerCase() + "%";

            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), value),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), value)
            );
        };
    }

    private static Specification<BookEntity> authorContains(String author) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (author == null || author.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("author")),
                    "%" + author.trim().toLowerCase() + "%"
            );
        };
    }

    private static Specification<BookEntity> difficultyEquals(
            BookEntityDifficultyLevel difficultyLevel
    ) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (difficultyLevel == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("difficultyLevel"), difficultyLevel);
        };
    }

    private static Specification<BookEntity> categoryEquals(String category) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (category == null || category.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            Join<BookEntity, CategoryEntity> categories =
                    root.join("categories", JoinType.INNER);

            criteriaQuery.distinct(true);

            return criteriaBuilder.equal(
                    criteriaBuilder.lower(categories.get("name")),
                    category.trim().toLowerCase()
            );
        };
    }
}