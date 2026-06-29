package com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity;

import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity.enums.GameEntityStatus;
import jakarta.persistence.*;
import lombok.*;
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

    @Column(name = "player_name", nullable = false)
    private String playerName;

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

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}