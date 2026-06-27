package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsequenceRequest {
    private String type;
    private String value;
    private String text;
}