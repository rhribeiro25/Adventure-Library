package com.pictet.technologies.adventurelibrary.domain.model;

import com.pictet.technologies.adventurelibrary.domain.model.enums.DifficultyLevel;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {

    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Include
    private String title;
    @EqualsAndHashCode.Include
    private String author;

    private DifficultyLevel difficultyLevel;

    @Builder.Default
    private Set<Category> categories = new HashSet<>();

    @Builder.Default
    private Set<Section> sections = new HashSet<>();
}