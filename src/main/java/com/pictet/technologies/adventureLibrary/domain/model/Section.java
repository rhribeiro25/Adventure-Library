package com.pictet.technologies.adventureLibrary.domain.model;

import com.pictet.technologies.adventureLibrary.domain.model.enums.SectionType;
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
public class Section {

    private Long id;
    private String text;
    private SectionType type;

    @Builder.Default
    private Set<Option> options = new HashSet<>();
}