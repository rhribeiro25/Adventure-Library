package com.pictet.technologies.adventurelibrary.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Player {

    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Include
    private String email;
    @EqualsAndHashCode.Include
    private String name;
}
