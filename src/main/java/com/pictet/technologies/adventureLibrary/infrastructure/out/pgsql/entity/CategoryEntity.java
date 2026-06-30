package com.pictet.technologies.adventureLibrary.infrastructure.out.pgsql.entity;

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
public class CategoryEntity extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String name;
}