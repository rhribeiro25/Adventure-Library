package com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity;

import com.pictet.technologies.adventureLibrary.infrastructure.adapter.out.persistence.pgsql.entity.enums.SectionEntityType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

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

    @Lob
    @Column(nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SectionEntityType type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @Builder.Default
    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OptionEntity> options = new ArrayList<>();
}