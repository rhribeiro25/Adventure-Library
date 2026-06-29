package com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity;

import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity.enums.SectionEntityType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sections", uniqueConstraints = {
        @UniqueConstraint(name = "uk_book_section_number", columnNames = {"book_id", "section_number"})
})
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SectionEntity extends AbstractEntity {

    @Column(name = "section_number", nullable = false)
    private Long sectionNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SectionEntityType type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @Builder.Default
    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    private Set<OptionEntity> options = new HashSet<>();
}