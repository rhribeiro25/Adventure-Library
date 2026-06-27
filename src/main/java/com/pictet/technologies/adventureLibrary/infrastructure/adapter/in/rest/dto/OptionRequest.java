package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionRequest {
    private String description;
    private Long gotoId;
    private ConsequenceRequest consequence;
}