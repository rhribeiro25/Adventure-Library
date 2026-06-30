package com.pictet.technologies.adventurelibrary.domain.model;

import com.pictet.technologies.adventurelibrary.domain.model.enums.ConsequenceType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Consequence {

    @EqualsAndHashCode.Include
    private ConsequenceType type;
    @EqualsAndHashCode.Include
    private Integer value;
    @EqualsAndHashCode.Include
    private String text;
}