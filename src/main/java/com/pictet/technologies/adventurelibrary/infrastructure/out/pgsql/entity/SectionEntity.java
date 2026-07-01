package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity;

import com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity.enums.SectionEntityType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sections")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class SectionEntity {

    @Id
    @EqualsAndHashCode.Include
    protected Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    @EqualsAndHashCode.Include
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private SectionEntityType type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Set<OptionEntity> options = new HashSet<>();

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime updatedAt;
}