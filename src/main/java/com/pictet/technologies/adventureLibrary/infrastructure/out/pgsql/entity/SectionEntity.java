package com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.entity;

import com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.entity.enums.SectionEntityType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sections")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SectionEntity extends AbstractEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SectionEntityType type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Set<OptionEntity> options = new HashSet<>();
}