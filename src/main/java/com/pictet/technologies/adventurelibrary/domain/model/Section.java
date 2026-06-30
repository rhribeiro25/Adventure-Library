package com.pictet.technologies.adventurelibrary.domain.model;

import com.pictet.technologies.adventurelibrary.domain.model.enums.SectionType;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Section {

    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Include
    private String text;

    private SectionType type;

    @Builder.Default
    private Set<Option> options = new HashSet<>();
}