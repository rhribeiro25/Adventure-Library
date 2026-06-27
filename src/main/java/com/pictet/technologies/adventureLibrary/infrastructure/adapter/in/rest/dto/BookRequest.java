package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookRequest {
    private String title;
    private String author;
    private String difficulty;
    private List<SectionRequest> sections;
}