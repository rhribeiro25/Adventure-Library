package com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity;

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

    @Column(name = "goto_section_number", nullable = false)
    private Long gotoSectionNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    private SectionEntity section;

    @OneToOne(mappedBy = "option")
    private ConsequenceEntity consequence;
}