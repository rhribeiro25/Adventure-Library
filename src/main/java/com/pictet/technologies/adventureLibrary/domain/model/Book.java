package com.pictet.technologies.adventureLibrary.domain.model;

import com.pictet.technologies.adventureLibrary.domain.model.enums.DifficultyLevel;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Long id;
    private String title;
    private String author;
    private DifficultyLevel difficultyLevel;

    @Builder.Default
    private Set<Category> categories = new HashSet<>();

    @Builder.Default
    private Set<Section> sections = new HashSet<>();
}