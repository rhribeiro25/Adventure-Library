package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.repository;

import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.SectionEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

    @EntityGraph(attributePaths = {
            "options",
            "options.consequence"
    })
    Optional<SectionEntity> findById(Long id);

    @EntityGraph(attributePaths = {
            "options",
            "options.consequence"
    })
    @Query("""
                select s
                from SectionEntity s
                where s.book.id = :bookId
            """)
    Set<SectionEntity> findAllByBookId(@Param("bookId") Long bookId);
}