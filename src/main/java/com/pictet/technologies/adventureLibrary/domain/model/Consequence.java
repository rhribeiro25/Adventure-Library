package com.pictet.technologies.adventureLibrary.domain.model;

import com.pictet.technologies.adventureLibrary.domain.model.enums.ConsequenceType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consequence {

    private ConsequenceType type;
    private Integer value;
    private String text;
}