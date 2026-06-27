package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SectionRequest {
    private Long id;
    private String text;
    private String type;
    private List<OptionRequest> options;
}