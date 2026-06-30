package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity;

import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.enums.BookEntityDifficultyLevel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class BookEntity extends AbstractEntity {

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String title;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookEntityDifficultyLevel difficultyLevel;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> categories = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Set<SectionEntity> sections = new HashSet<>();
}