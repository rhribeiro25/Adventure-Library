package com.pictet.technologies.adventureLibrary.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Option {

    private String description;
    private Long gotoId;
    private Consequence consequence;
}