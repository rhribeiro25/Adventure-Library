package com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity;

import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity.enums.ConsequenceEntityType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "consequences")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsequenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConsequenceEntityType type;

    private Integer value;

    @Column(length = 1000)
    private String text;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "option_id", nullable = false)
    private OptionEntity option;
}