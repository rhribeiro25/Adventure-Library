package com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "options")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OptionEntity extends AbstractEntity {

    @Column(nullable = false)
    private String description;

    @Column(name = "next_section_id", nullable = false)
    private Long nextSectionId;

    @OneToOne(mappedBy = "option")
    private ConsequenceEntity consequence;
}