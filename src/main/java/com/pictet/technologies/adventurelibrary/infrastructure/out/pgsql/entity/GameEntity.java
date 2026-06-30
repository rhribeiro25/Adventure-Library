package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity;

import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.enums.GameEntityStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "games")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class GameEntity extends AbstractEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerEntity player;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private Integer health;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private GameEntityStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @ManyToOne(optional = false)
    @JoinColumn(name = "current_section_id", nullable = false)
    private SectionEntity currentSection;

    @Version
    private Long version;
}