package com.pictet.technologies.adventureLibrary.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    private Long id;
    private String email;
    private String name;
}
