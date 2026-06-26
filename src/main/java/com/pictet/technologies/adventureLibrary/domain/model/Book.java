package com.pictet.technologies.adventureLibrary.domain.model;

import com.pictet.technologies.adventureLibrary.domain.model.enums.DifficultyLevel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private List<Category> categories = new ArrayList<>();

    @Builder.Default
    private List<Section> sections = new ArrayList<>();
}