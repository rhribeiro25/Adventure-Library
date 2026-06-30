package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity;

import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.enums.ConsequenceEntityType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "consequences")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class ConsequenceEntity extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private ConsequenceEntityType type;

    @EqualsAndHashCode.Include
    private Integer value;

    @Column(columnDefinition = "TEXT")
    @EqualsAndHashCode.Include
    private String text;

    @OneToOne(optional = false)
    @JoinColumn(name = "option_id", nullable = false)
    private OptionEntity option;
}