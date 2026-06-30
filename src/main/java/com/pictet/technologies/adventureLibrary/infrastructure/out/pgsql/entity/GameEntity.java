package com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.entity;

import com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.entity.enums.GameEntityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "games")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GameEntity extends AbstractEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerEntity player;

    @Column(nullable = false)
    private Integer health;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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