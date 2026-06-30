package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class OptionEntity extends AbstractEntity {

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String description;

    @Column(name = "next_section_id", nullable = false)
    @EqualsAndHashCode.Include
    private Long nextSectionId;

    @OneToOne(mappedBy = "option")
    private ConsequenceEntity consequence;
}