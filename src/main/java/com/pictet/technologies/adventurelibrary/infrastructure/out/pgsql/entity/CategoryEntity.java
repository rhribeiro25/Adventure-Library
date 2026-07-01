package com.pictet.technologies.adventurelibrary.infrastructure.out.pgsql.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "categories")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class CategoryEntity extends AbstractEntity {

    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String name;

    @Version
    private Long version;
}